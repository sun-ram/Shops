package com.mitosis.shopsbacker.webservice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.inventory.service.ProductCategoryService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.inventory.service.ProductTypeService;
import com.mitosis.shopsbacker.inventory.service.UomService;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Uom;
import com.mitosis.shopsbacker.responsevo.ProductResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.inventory.ProductUploadVO;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;

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
	public ResponseModel addProduct(ProductVo productVo) {
		
		try {
			Merchant merchant = merchantService.getMerchantById(productVo.getMerchant().getMerchantId());
			
			boolean isUpdateProcess = productVo.getProductId()!=null?true:false;
			
			ProductCategory productCategory = productCategoryService.getCategoryById(productVo.getProductCategory().getProductCategoryId());
			
			ProductType productType = ProductTypeService.getProductTypeById(productVo.getProductType().getProductTypeId());
			
			Uom uom = uomService.getUOMById(productVo.getUom().getUomId());
			if(productVo.getImage().getImage() != null){
			productService.productImageUpload(productVo,merchant);
		    }
			Image img = null;
			Product product = productService.setProduct(productVo,img);
			
			product.setMerchant(merchant);
			product.setProductCategory(productCategory);
			product.setProductType(productType);
			product.setUom(uom);
			if(!isUpdateProcess){
			productService.addProduct(product);
			}else{
				productService.updateProduct(product);	
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
	
	@Path("/updateproduct")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
	public ResponseModel ProductById(ProductVo productVo) {
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
		
		return response;

		}
	
	@Path("/productbytype")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
			
			productResponse.setProduct(productVoList);
			productResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			productResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			productResponse.setErrorString(e.getMessage());
		}
		
		return response;

		}
	
	@Path("/productbycategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
			
			productResponse.setProduct(productVoList);
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
			
			productResponse.setProduct(productVoList);
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
	public ProductResponseVo GetProduct(ProductVo productVo) {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			Merchant merchant = merchantService.getMerchantById(productVo.getMerchant().getMerchantId());
			List<Product> productList = getProductService().getProductByMerchant(merchant);
			List<ProductVo> productVoList=new ArrayList<ProductVo>();
			for (Product product : productList) {
				ProductVo productVos = productService.setProductVo(product);
				productVoList.add(productVos);
			}
			
			productResponse.setProduct(productVoList);
			productResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			productResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			productResponse.setErrorString(e.getMessage());
		} 
		
		return productResponse;

		}
	
	@Path("/product/exportExcelFile")
	 @GET
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("application/vnd.ms-excel")
	 public   Response exportExcelFile(String merchantId){
		Response productResponse = null;
		
		try {
			Merchant merchant = merchantService.getMerchantById(merchantId);
			List<Product> productList = getProductService().getProductByMerchant(merchant);
			
			   //Blank workbook
			   XSSFWorkbook workbook = new XSSFWorkbook(); 
			   
			   //Create a blank sheet
			   XSSFSheet sheet = workbook.createSheet("Product Data");
			   
			   //This data needs to be written (Object[])
			   Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
			   data.put(1, new Object[] {"NAME", "PRICE","UNIT","MEASUREMENT","BRAND"});
			   
			   for(int i=0;i<productList.size();i++){
				   
				   data.put(i+2, new Object[] {productList.get(i).getName(), 
						      productList.get(i).getPrice(),productList.get(i).getUnit(),productList.get(i).getUom().getName(),
						      productList.get(i).getBrand()} );
				   Set<Integer> keyset = data.keySet();
				   
				   int rownum = 0;
				   for (Integer key : keyset)
				   {
				       Row row = sheet.createRow(rownum++);
				       Object [] objArr = data.get(key);
				       int cellnum = 0;
				       for (Object obj : objArr)
				       {
				          Cell cell = row.createCell(cellnum++);
				          if(obj instanceof String)
				               cell.setCellValue((String)obj);
				           else if(obj instanceof Integer)
				               cell.setCellValue((Integer)obj);
				           else if(obj instanceof Long)
				               cell.setCellValue((Long)obj);
				           else if(obj instanceof Double)
				               cell.setCellValue((Double)obj);          
				       }
				   }
			   
			   }
			   Properties properties = new Properties();
				properties.load(getClass().getResourceAsStream(
						"/properties/serverurl.properties"));
				String excelPath = properties.getProperty("excelPath");
			   File file  = new File(excelPath+"storeProductList.xlsx");
			   if(!file.exists()){
				   file.getParentFile().mkdir();
				   file.createNewFile();
			   }
			   FileOutputStream out = new FileOutputStream(file);
			      workbook.write(out);
			      out.close();
			   ResponseBuilder response = Response.ok((Object) file);
			   response.header("Content-Disposition",
						"attachment; filename=storeProductList.xlsx");
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
	public ProductUploadVO excelUpload() {
		ProductUploadVO response = new ProductUploadVO();
		String excelPath = "/home/ramya/Documents/BSEE_Documents/Products.xls";
		try {
			/*Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
			defaultImagePath = properties.getProperty("imagePath");*/
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
								if (labels.get(cellPosition).equalsIgnoreCase("Price")) {
									if(cell.getNumericCellValue()==0){
										response.setErrorString(SBErrorMessage.INVALID_PRODUCT_PRICE
												.getCode());
										response.setRowNo(cellPosition);
										return response;
									}else{
										product.setPrice(BigDecimal.valueOf(cell.getNumericCellValue()));
									}
								}else if (labels.get(cellPosition).equalsIgnoreCase("Unit")) {
									if(cell.getNumericCellValue()==0){
										response.setErrorString(SBErrorMessage.INVALID_PRODUCT_UNIT.getCode());
										response.setRowNo(cellPosition);
										response.setStatus(SBMessageStatus.FAILURE.getValue());
										return response;
									}else{
										product.setUnit(BigDecimal.valueOf(cell.getNumericCellValue()));
									}
								}
								break; 

							case Cell.CELL_TYPE_STRING:
								if(labels.get(cellPosition).equalsIgnoreCase("ProductName")){
									if(cell.getStringCellValue().trim()==null){
										response.setRowNo(cellPosition);
										response.setErrorString(SBMessageStatus.FAILURE.getValue());
										return response;
									}else{
										if(product.getProductId()==null){
											product = (Product) CommonUtil.setAuditColumnInfo(Product.class.getName());
											product.setIsactive('Y');
										}else{
											product = productService.getProductByName(cell.getStringCellValue().trim());
										}
										product.setName(cell.getStringCellValue().trim());
									}
								}else if (labels.get(cellPosition).equalsIgnoreCase("Description")) {
									product.setDescription(cell.getStringCellValue().trim());
								}else if (labels.get(cellPosition).equalsIgnoreCase("Brand")) {
									product.setBrand(cell.getStringCellValue().trim());
								}else if (labels.get(cellPosition).equalsIgnoreCase("Edible_Type")) {
									product.setEdibleType(cell.getStringCellValue().trim());
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
					if(product.getProductId()==null){
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


}
