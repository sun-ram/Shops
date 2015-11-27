package com.mitosis.shopsbacker.webservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.inventory.service.ProductCategoryService;
import com.mitosis.shopsbacker.inventory.service.ProductImageService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.inventory.service.ProductTypeService;
import com.mitosis.shopsbacker.inventory.service.UomService;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductImage;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Uom;
import com.mitosis.shopsbacker.responsevo.ProductResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.common.ImageVo;
import com.mitosis.shopsbacker.vo.inventory.ProductImageVo;
import com.mitosis.shopsbacker.vo.inventory.ProductUploadVO;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;
import com.sun.jersey.core.util.Base64;

/**
 * @author RiyazKhan
 *
 * @param <T>
 */

@Path("product")
@Controller("productRestService")
public class ProductRestService {
	
	final static Logger log = Logger.getLogger(StoreRestService.class
			.getName());
	
	@Autowired
	MerchantService<T> merchantService;
	
	@Autowired
	ProductCategoryService<T> productCategoryService;
	
	@Autowired
	ProductTypeService<T> ProductTypeService;
	
	@Autowired
	ProductService<T> productService;
	
	@Autowired
	UomService<T> uomService;
	
	@Autowired
	ImageService<T> imageService;
	
	@Autowired
	ProductImageService<T> productImageService;
	
	public ProductService<T> getProductService() {
		return productService;
	}

	public void setProductService(ProductService<T> productService) {
		this.productService = productService;
	}

	ResponseModel response = new ResponseModel();
	
	
	@Path("/addproduct")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseModel addProduct(ProductVo productVo) {
		
		try {
			Merchant merchant = merchantService.getMerchantById(productVo.getMerchant().getMerchantId());
			
			boolean isUpdateProcess = productVo.getProductId()!=null?true:false;
			
			ProductCategory productCategory = productCategoryService.getCategoryById(productVo.getProductCategory().getProductCategoryId());
			
			ProductType productType = ProductTypeService.getProductTypeById(productVo.getProductType().getProductTypeId());
			
			Uom uom = uomService.getUOMById(productVo.getUom().getUomId());
			if(productVo.getImage().getImage() != null){
			productService.productImageUpload(productVo.getImage(),merchant);
		    }
			Image img = null;
			Product product = productService.setProduct(productVo,img);
			List<String> productImageIds = new ArrayList<String>();
			
			setProductImages(productVo, merchant, product, productImageIds);
			product.setMerchant(merchant);
			product.setProductCategory(productCategory);
			product.setProductType(productType);
			product.setUom(uom);
			ProductImage productImage = new ProductImage();
			
			if(!isUpdateProcess){
			productService.addProduct(product);
			}else{
				productService.updateProduct(product);	
			}
			if(productVo.getProductId() != null && img != null){
				imageService.deleteImage(img);
			}
			for(String productImageId:productImageIds){
				
				ProductImage productImages = productImageService.getProductImage(productImageId);
				imageService.deleteImage(productImages.getImage());
				productImageService.deleteProductImage(productImages);
				
				
			}
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}

		return response;
	}

	private void setProductImages(ProductVo productVo, Merchant merchant,
			Product product, List<String> productImageIds) throws Exception {
		List <ProductImage> productImages = new ArrayList<ProductImage>();
		List<ProductImageVo> productImageVos = productVo.getProductImages();
		
			for(ProductImageVo productImageVo:productImageVos){
				if(productImageVo.getImage().getImage()==null){
					productImageIds.add(productImageVo.getProductImageId());
					continue;
				}
					productService.productImageUpload(productImageVo.getImage(),merchant);
					Image image = imageService.setImage(productImageVo.getImage());
					imageService.addImage(image);
					ProductImage productimage = (ProductImage) CommonUtil.setAuditColumnInfo(ProductImage.class.getName());
					productimage.setIsactive('Y');
					productimage.setImage(image);
					productimage.setProduct(product);
					productImages.add(productimage);
		}
		product.setProductImages(productImages);
	}
	
	@Path("/updateproduct")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseModel updateProduct(ProductVo productVo) {
		
		try {
			Merchant merchant = merchantService.getMerchantById(productVo.getMerchant().getMerchantId());
			
			ProductCategory productCategory = productCategoryService.getCategoryById(productVo.getProductCategory().getProductCategoryId());
			
			ProductType productType = ProductTypeService.getProductTypeById(productVo.getProductType().getProductTypeId());
			Image img =null;
			Product product = productService.setProduct(productVo,img);
			
			product.setMerchant(merchant);
			product.setProductCategory(productCategory);
			product.setProductType(productType);
			
			productService.updateProduct(product);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
			if(productVo.getProductId() != null && img != null){
				
				imageService.deleteImage(img);
				 
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}

		return response;
		
		
	}
	
	@Path("/deleteproduct")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseModel deleteProduct(ProductVo productVo) {
		try {
			Product product = getProductService().getProduct(productVo.getProductId());
			getProductService().deleteProduct(product);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		return response;
	}
	
	@Path("/productbyid")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public ProductResponseVo ProductById(ProductVo productVo) {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			Product product = getProductService().getProduct(productVo.getProductId());
			ProductVo productVos = productService.setProductVo(product);
			productResponse.setProduct(productVos);
			productResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			productResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			productResponse.setErrorString(e.getMessage());
		}
		
		return productResponse;

		}
	
	@Path("/productbytype")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseModel ProductByType(ProductVo productVo) {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			ProductType productType = ProductTypeService.getProductTypeById(productVo.getProductType().getProductTypeId());
			List<Product> productList = getProductService().getProductListByType(productType);
			List<ProductVo> productVoList=new ArrayList<ProductVo>();
			for (Product product : productList) {
				ProductVo productVos = productService.setProductVo(product);
				productVoList.add(productVos);
			}
			
			productResponse.setProducts(productVoList);
			productResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			productResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			productResponse.setErrorString(e.getMessage());
		}
		
		return productResponse;

		}
	
	@Path("/productbycategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public ProductResponseVo ProductByCategory(ProductVo productVo) {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			ProductCategory productCategory = productCategoryService.getCategoryById(productVo.getProductCategory().getProductCategoryId());
			List<Product> productList = getProductService().getProductListByCategoty(productCategory);
			List<ProductVo> productVoList=new ArrayList<ProductVo>();
			for (Product product : productList) {
				ProductVo productVos = productService.setProductVo(product);
				productVoList.add(productVos);
			}
			
			productResponse.setProducts(productVoList);
			productResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			productResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			productResponse.setErrorString(e.getMessage());
		}
		
		return productResponse;

		}
	
	@Path("/gettopproduct")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseModel GetTopProduct(ProductVo productVo) {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			Merchant merchant = merchantService.getMerchantById(productVo.getMerchant().getMerchantId());
			List<Product> productList = getProductService().getTopProduct(merchant);
			List<ProductVo> productVoList=new ArrayList<ProductVo>();
			for (Product product : productList) {
				ProductVo productVos = productService.setProductVo(product);
				productVoList.add(productVos);
			}
			
			productResponse.setProducts(productVoList);
			productResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			productResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			productResponse.setErrorString(e.getMessage());
		} 
		
		return productResponse;

		}
	
	@Path("/getproductlist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public ProductResponseVo GetProduct(ProductVo productVo) {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			Merchant merchant = merchantService.getMerchantById(productVo.getMerchant().getMerchantId());
			List<Product> productList = merchant.getProducts();
			List<ProductVo> productVoList=new ArrayList<ProductVo>();
			for (Product product : productList) {
				ProductVo productVos = productService.setProductVo(product);
				productVoList.add(productVos);
			}
			
			productResponse.setProducts(productVoList);
			productResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			productResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			productResponse.setErrorString(e.getMessage());
		} 
		
		return productResponse;

		}
	
	@Path("/exportExcelFile")
	 @GET
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("application/vnd.ms-excel")
	@Transactional(propagation=Propagation.REQUIRED)
	 public   Response exportExcelFile(@QueryParam("merchantId") String merchantId){
		Response productResponse = null;
		
		try {
			Merchant merchant = merchantService.getMerchantById(merchantId);
			List<Product> productList = getProductService().getProductByMerchant(merchant);
			
			HSSFWorkbook workbook = new HSSFWorkbook();
           HSSFSheet sheet = workbook.createSheet("FirstSheet");  

           HSSFRow rowhead = sheet.createRow((short)0);
           rowhead.createCell(0).setCellValue("Name");
           rowhead.createCell(1).setCellValue("Price");
           rowhead.createCell(2).setCellValue("Unit");
           rowhead.createCell(3).setCellValue("Measure");
           rowhead.createCell(4).setCellValue("Brand");

           for(int i=0;i<productList.size();i++){
           HSSFRow row = sheet.createRow((short)i+1);
           row.createCell(0).setCellValue(productList.get(i).getName());
           row.createCell(1).setCellValue(productList.get(i).getPrice().toString());
           row.createCell(2).setCellValue(productList.get(i).getUnit().toString());
           row.createCell(3).setCellValue(productList.get(i).getUom().getName());
           row.createCell(4).setCellValue(productList.get(i).getBrand());
           }
           Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
			String excelPath = properties.getProperty("excelPaths");
		   File file  = new File(excelPath+"storeProductList.xls");
		   
           FileOutputStream fileOut = new FileOutputStream(file);
           workbook.write(fileOut);
           fileOut.close();
           System.out.println("Your excel file has been generated!");

			   ResponseBuilder response = Response.ok((Object) file);
			   response.header("Content-Disposition",
						"attachment; filename=storeProductList.xls");
					return response.build();
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productResponse;
	  }

	
	@Path("/excelupload")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public ProductUploadVO excelUpload(ProductVo file) {
		ProductUploadVO response = new ProductUploadVO();
		String excelPath = null;
		try {
			Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
			excelPath = properties.getProperty("excelPath");
			byte[] data = Base64.decode(file.getImage().getImage());
			try (OutputStream stream = new FileOutputStream(excelPath)) {
			    stream.write(data);
			}
			List<Product> productList = new ArrayList<Product>();
			response =convertXlsToModel(excelPath, 0);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}

		return response;

	}

	public ProductUploadVO convertXlsToModel(String path, int sheetNo){
		ProductUploadVO response = new ProductUploadVO();
		Product product = new Product();
		boolean isNew = false;
		try {
			FileInputStream file = new FileInputStream(new File(path));
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(sheetNo);
			List<String> labels = new ArrayList<String>();
			int count = 0;
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				if(count == 0){
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							break;
						case Cell.CELL_TYPE_STRING:
							labels.add(cell.getStringCellValue().trim());
							break;
						}
					}
					count++;
				}else{
					int cellPosition = 0;
					while (cellIterator.hasNext() && cellPosition<row.getLastCellNum()) {
						Cell cell = row.getCell(cellPosition,Row.RETURN_BLANK_AS_NULL);

						if(cell==null){
							response.setErrorString(labels.get(cellPosition)+" "+"is Empty");
							response.setRowNo(cellPosition);
							response.setStatus(SBMessageStatus.FAILURE.getValue());
							return response;
						}else{

							switch (cell.getCellType()) {

							case Cell.CELL_TYPE_NUMERIC:
								if (labels.get(cellPosition).equalsIgnoreCase("PRICE")) {
									if(cell.getNumericCellValue()==0){
										response.setErrorString(SBErrorMessage.INVALID_PRODUCT_PRICE
												.getCode());
										response.setRowNo(cellPosition);
										return response;
									}else{
										product.setPrice(BigDecimal.valueOf(cell.getNumericCellValue()));
									}
								}else if (labels.get(cellPosition).equalsIgnoreCase("UNIT")) {
									if(cell.getNumericCellValue()==0){
										response.setErrorString(SBErrorMessage.INVALID_PRODUCT_UNIT.getCode());
										response.setRowNo(cellPosition);
										response.setStatus(SBMessageStatus.FAILURE.getValue());
										return response;
									}else{
										product.setUnit(BigDecimal.valueOf(cell.getNumericCellValue()));
									}
								}else if (labels.get(cellPosition).equalsIgnoreCase("MEASUREMENT")) {
									if(cell.getNumericCellValue()==0){
										response.setErrorString(SBErrorMessage.INVALID_PRODUCT_UNIT.getCode());
										response.setRowNo(cellPosition);
										response.setStatus(SBMessageStatus.FAILURE.getValue());
										return response;
									}else{
										//TODO MEASUREMENT
									}
								}
								break; 

							case Cell.CELL_TYPE_STRING:
								if(labels.get(cellPosition).equalsIgnoreCase("NAME")){
									if(cell.getStringCellValue().trim()==null){
										response.setRowNo(cellPosition);
										response.setErrorString(SBMessageStatus.FAILURE.getValue());
										return response;
									}else{
										if(isNew){
											product = (Product) CommonUtil.setAuditColumnInfo(Product.class.getName());
											product.setIsactive('Y');
										}else{
											product = productService.getProductByName(cell.getStringCellValue().trim());
											if(product==null){
												response.setStatus(SBMessageStatus.FAILURE.getValue());
												response.setErrorString(SBErrorMessage.PRODUCT_NOT_AVAILABLE.getMessage());
												response.setRowNo(cellPosition);
												return response;
											}
										}
										product.setName(cell.getStringCellValue().trim());
									}
								}else if (labels.get(cellPosition).equalsIgnoreCase("BRAND")) {
									product.setBrand(cell.getStringCellValue().trim());
								}
								break;

							case Cell.CELL_TYPE_BLANK:
								response.setErrorString(labels.get(cellPosition)+""+"is Empty");
								response.setRowNo(cellPosition);
								response.setStatus(SBMessageStatus.FAILURE.getValue());
								break;
							}
						}
						cellPosition++;
					}
					if(isNew){
						productService.addProduct(product);
					}else{
						productService.updateProduct(product);
					}
				}
			}
			file.close();
		}
		catch (Exception e) {
			e.getMessage();
		}
		return response;
	}

	public ProductUploadVO addProduct(Product product){
		ProductUploadVO productVo =  new ProductUploadVO();
		productVo.setErrorString(SBErrorMessage.PRODUCT_NOT_AVAILABLE.getMessage());
		productVo.setStatus(SBMessageStatus.FAILURE.getValue());
		return productVo;

	}
	
	@Path("/mobileimageupload")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public String imageUpload(ProductVo productVo) throws Exception {
	
		ObjectMapper mapper = CommonUtil.getObjectMapper();
		List<String> imageIds = new ArrayList<String>();
		ProductResponseVo productResponse = new ProductResponseVo();
		try{
		Product product = getProductService().getProduct(productVo.getProductId());
		
		if(productVo.getImage().getImage() != null){
		productService.productImageUpload(productVo.getImage(),product.getMerchant());
	    }
		Image img = null;
		if (productVo.getImage().getImage() != null) {
			if(productVo.getImage().getImageId()!=null){
				imageIds.add(productVo.getImage().getImageId());
			}
			Image image = imageService.setImage(productVo.getImage());
			product.setImage(image);
		}
		
		List <ProductImage> productImages = new ArrayList<ProductImage>();
		List<ImageVo> imageVos = productVo.getImages();
		
			for(ImageVo imageVo:imageVos){
					productService.productImageUpload(imageVo,product.getMerchant());
					if(imageVo.getImage()!=null){
						if(imageVo.getImageId()!=null){
							imageIds.add(imageVo.getImageId());
						}
						Image image = imageService.setImage(imageVo);
						imageService.addImage(image);
						ProductImage productimage = (ProductImage) CommonUtil.setAuditColumnInfo(ProductImage.class.getName());
						productimage.setIsactive('Y');
						productimage.setImage(image);
						productimage.setProduct(product);
						productImages.add(productimage);
					}
		}
		product.setProductImages(productImages);
		productService.updateProduct(product);
		
		ProductVo productvo = new ProductVo();
		setProductVoForMobile(productvo, product);
		
		List<Image> images=imageService.getImages(imageIds);
		
		//delete images and product images 
		for(Image image:images){
			if(image.getProductImages()!=null && image.getProductImages().size()>0){
				productImageService.deleteProductImage(image.getProductImages().get(0));
			}
			imageService.deleteImage(image);
		}
		productResponse.setProduct(productvo);
		productResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			productResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			productResponse.setErrorString(CommonUtil.getErrorMessage(e));
		}
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productResponse);
		
	}

	private void setProductVoForMobile(ProductVo productVo, Product product)
			throws IOException {
		productVo.setName(product.getName());
		productVo.setProductId(product.getProductId());
		if(product.getImage() != null){
		ImageVo image = imageService.setImageVo(product.getImage());
		productVo.setImage(image);
		}


		List<ProductImage> productImageList = product.getProductImages();
		List<ImageVo> imageVoList  =new  ArrayList<ImageVo>();
		for(ProductImage productImage:productImageList){
			ImageVo image = imageService.setImageVo(productImage.getImage());
			imageVoList.add(image);
		}
		productVo.setImages(imageVoList);
	}
	
	@Path("/getproducts")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public String getProdutsFromMobile(ProductVo productVo) throws Exception {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			String productTypeId =null;
			if(productVo.getProductType()!=null && productVo.getProductType().getProductTypeId()!=null){
				productTypeId=productVo.getProductType().getProductTypeId();
			}else if(productVo.getProductTypeId()!=null){
				productTypeId=productVo.getProductTypeId();
			}
				ProductType productType = ProductTypeService.getProductTypeById(productTypeId);
				
				List<Product> productList = productType.getProducts();
				List<ProductVo> productVoList = new ArrayList<ProductVo>();
				for(Product product:productList){
					ProductVo productvo= new  ProductVo();
					setProductVoForMobile(productvo, product);
					productVoList.add(productvo);
				}
				productResponse.setProducts(productVoList);
				productResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
			
		} catch (Exception e) {
			e.printStackTrace();
			productResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			productResponse.setErrorString(e.getMessage());
		}
		ObjectMapper mapper = CommonUtil.getObjectMapper();
		String responseString = null;
		try {
			responseString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productResponse);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			 throw e;
			
		}
		return responseString;
	}
	
}
