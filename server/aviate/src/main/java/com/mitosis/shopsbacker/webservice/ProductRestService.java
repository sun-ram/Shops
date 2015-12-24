package com.mitosis.shopsbacker.webservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

import org.apache.axis2.databinding.types.soapencoding.Decimal;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.inventory.service.DiscountService;
import com.mitosis.shopsbacker.inventory.service.ProductCategoryService;
import com.mitosis.shopsbacker.inventory.service.ProductImageService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.inventory.service.ProductTypeService;
import com.mitosis.shopsbacker.inventory.service.UomService;
import com.mitosis.shopsbacker.model.Discount;
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
import com.mitosis.shopsbacker.vo.inventory.ProductUploadDataVo;
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

	final static Logger log = Logger
			.getLogger(StoreRestService.class.getName());

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

	@Autowired
	DiscountService<T> discountService;

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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel addProduct(ProductVo productVo) {

		try {
			Merchant merchant = merchantService.getMerchantById(productVo
					.getMerchant().getMerchantId());
			if (productVo.getProductId() == null) {
				List<Product> checkUniqueProducts = getProductService()
						.getProductListByName(productVo.getName(), merchant);
				if (!checkUniqueProducts.isEmpty()) {
					response.setErrorCode(SBErrorMessage.PRODUCT_NAME_ALREADY_EXIST
							.getCode());
					response.setErrorString(SBErrorMessage.PRODUCT_NAME_ALREADY_EXIST
							.getMessage());
					response.setStatus(SBMessageStatus.FAILURE.getValue());
					return response;
				}

			}
			if (productVo.getProductId() != null) {

				List<Product> productsList = getProductService()
						.getProductName(productVo.getProductId(),
								productVo.getName(), merchant);
				if (productsList.size() > 0) {
					response.setErrorCode(SBErrorMessage.PRODUCT_NAME_ALREADY_EXIST
							.getCode());
					response.setErrorString(SBErrorMessage.PRODUCT_NAME_ALREADY_EXIST
							.getMessage());
					response.setStatus(SBMessageStatus.FAILURE.getValue());
					return response;
				}
			}
			boolean isUpdateProcess = productVo.getProductId() != null ? true
					: false;

			ProductCategory productCategory = productCategoryService
					.getCategoryById(productVo.getProductCategory()
							.getProductCategoryId());

			ProductType productType = ProductTypeService
					.getProductTypeById(productVo.getProductType()
							.getProductTypeId());

			// Discount discount =
			// discountService.getDiscountById(productVo.getDiscount().getDiscountId());

			Uom uom = uomService.getUOMById(productVo.getUom().getUomId());
			if (productVo.getImage().getImage() != null) {
				productService.productImageUpload(productVo.getImage(),
						merchant);
			}
			Image img = null;
			Product product = productService.setProduct(productVo, img);
			List<String> imageIds = new ArrayList<String>();

			setProductImages(productVo, merchant, product, imageIds);
			product.setMerchant(merchant);
			product.setProductCategory(productCategory);
			product.setProductType(productType);
			// product.setDiscount(discount);
			product.setUom(uom);
			ProductImage productImage = new ProductImage();

			if (!isUpdateProcess) {
				productService.addProduct(product);
			} else {
				productService.updateProduct(product);
			}
			if (productVo.getProductId() != null && img != null) {
				imageService.deleteImage(img);
			}
			for (String imageId : imageIds) {
				if (imageId == null) {
					continue;
				}
				Image image = imageService.getImageById(imageId);
				List<ProductImage> productImages = image.getProductImages();
				for (ProductImage productImg : productImages) {
					productImageService.deleteProductImage(productImg);
				}
				imageService.deleteImage(image);
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
		List<ProductImage> productImages = new ArrayList<ProductImage>();
		List<ImageVo> imageVos = productVo.getImages();

		for (ImageVo imageVo : imageVos) {
			if (imageVo.getImage() == null) {
				continue;
			}
			String imageId = imageVo.getImageId();
			productImageIds.add(imageId);
			productService.productImageUpload(imageVo, merchant);
			Image image = imageService.setImage(imageVo);
			imageService.addImage(image);
			ProductImage productimage = (ProductImage) CommonUtil
					.setAuditColumnInfo(ProductImage.class.getName());
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel updateProduct(ProductVo productVo) {

		try {
			Merchant merchant = merchantService.getMerchantById(productVo
					.getMerchant().getMerchantId());

			ProductCategory productCategory = productCategoryService
					.getCategoryById(productVo.getProductCategory()
							.getProductCategoryId());

			ProductType productType = ProductTypeService
					.getProductTypeById(productVo.getProductType()
							.getProductTypeId());
			Image img = null;
			Product product = productService.setProduct(productVo, img);

			product.setMerchant(merchant);
			product.setProductCategory(productCategory);
			product.setProductType(productType);

			productService.updateProduct(product);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
			if (productVo.getProductId() != null && img != null) {

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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteProduct(ProductVo productVo) {
		try {
			Product product = getProductService().getProduct(
					productVo.getProductId());
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductResponseVo ProductById(ProductVo productVo) {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			Product product = getProductService().getProduct(
					productVo.getProductId());
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String ProductByType(ProductVo productVo) throws Exception {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			ProductType productType = ProductTypeService
					.getProductTypeById(productVo.getProductType()
							.getProductTypeId());
			List<Product> productList = getProductService()
					.getProductListByType(productType);
			List<ProductVo> productVoList = new ArrayList<ProductVo>();
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

		return CommonUtil.getObjectMapper(productResponse);

	}

	@Path("/productbycategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String ProductByCategory(ProductVo productVo) throws Exception {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			ProductCategory productCategory = productCategoryService
					.getCategoryById(productVo.getProductCategory()
							.getProductCategoryId());
			List<Product> productList = productCategory.getProducts();
			if (productList.isEmpty()) {
				List<ProductCategory> childCategories = productCategory
						.getProductCategories();
				productList = getProductsFromCatogories(productList,
						childCategories);
			}
			List<ProductVo> productVoList = new ArrayList<ProductVo>();
			for (Product product : productList) {
				ProductVo productVos = productService.setProductVo(product);
				productVoList.add(productVos);
			}
			productResponse.setProducts(productVoList);
			productResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
			productResponse.setProducts(productVoList);
			productResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			productResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			productResponse.setErrorString(e.getMessage());
		}
		return CommonUtil.getObjectMapper(productResponse);

	}

	/**
	 * @author fayaz
	 * @param productList
	 * @param childCategories
	 * @return List<Product>
	 */
	private List<Product> getProductsFromCatogories(List<Product> productList,
			List<ProductCategory> childCategories) {
		for (ProductCategory childCategory : childCategories) {
			List<Product> listOfproducts = childCategory.getProducts();
			if (productList.isEmpty() && !listOfproducts.isEmpty()) {
				productList = listOfproducts;
			} else if (!listOfproducts.isEmpty()) {
				productList.addAll(listOfproducts);
			}
			List<ProductCategory> listOfChildCategories = childCategory
					.getProductCategories();
			if (!listOfChildCategories.isEmpty()) {
				productList = getProductsFromCatogories(productList,
						listOfChildCategories);
			}
		}
		return productList;
	}

	public ProductCategory checkHierarchyProductCategory(
			ProductCategory productCategory) {
		ProductCategory productCategoryHierarchy = productCategoryService
				.getCategoryById(productCategory.getProductCategoryId());
		List<ProductCategory> productCategoryList1 = productCategoryService
				.getParentCategory(productCategoryHierarchy);
		if (productCategoryList1.size() == 0) {

		} else {
			for (ProductCategory pr : productCategoryList1) {
				checkHierarchyProductCategory(pr);

			}
		}

		return null;
	}

	@Path("/gettopproduct")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel GetTopProduct(ProductVo productVo) {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			Merchant merchant = merchantService.getMerchantById(productVo
					.getMerchant().getMerchantId());
			List<Product> productList = getProductService().getTopProduct(
					merchant);
			List<ProductVo> productVoList = new ArrayList<ProductVo>();
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

	@Path("/deleteProductImage")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteProductImage(ImageVo imageVo) {
		response = new ResponseModel();
		try {
			Image images = imageService.getImageById(imageVo.getImageId());

			if (images.getProductImages() != null
					&& images.getProductImages().size() > 0) {
				productImageService.deleteProductImage(images
						.getProductImages().get(0));
				imageService.deleteImage(images);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		return response;

	}

	@Path("/getproductlist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductResponseVo GetProduct(ProductVo productVo) {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			Merchant merchant = merchantService.getMerchantById(productVo
					.getMerchant().getMerchantId());
			List<Product> productList = getProductService()
					.getProductByMerchant(merchant);
			;
			List<ProductVo> productVoList = new ArrayList<ProductVo>();
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Response exportExcelFile(@QueryParam("merchantId") String merchantId) {
		Response productResponse = null;

		try {
			Merchant merchant = merchantService.getMerchantById(merchantId);
			List<Product> productList = getProductService()
					.getProductByMerchant(merchant);

			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("FirstSheet");

			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell(0).setCellValue("Name");
			rowhead.createCell(1).setCellValue("Product Category");
			rowhead.createCell(2).setCellValue("Product Type");
			rowhead.createCell(3).setCellValue("Product Measurement");
			rowhead.createCell(4).setCellValue("Product Unit");
			rowhead.createCell(5).setCellValue("Edible Type");
			rowhead.createCell(6).setCellValue("Was Price");
			rowhead.createCell(7).setCellValue("Selling Price");
			rowhead.createCell(8).setCellValue("Brand");

			for (int i = 0; i < productList.size(); i++) {
				HSSFRow row = sheet.createRow((short) i + 1);
				row.createCell(0).setCellValue(productList.get(i).getName());
				row.createCell(1).setCellValue(
						productList.get(i).getProductCategory().getName());
				row.createCell(2).setCellValue(
						productList.get(i).getProductType().getName());
				row.createCell(3).setCellValue(
						productList.get(i).getUom().getName());
				row.createCell(4).setCellValue(
						productList.get(i).getUnit().toString());
				row.createCell(5).setCellValue(
						productList.get(i).getEdibleType());
				row.createCell(6).setCellValue(
						productList.get(i).getWasPrice().toString());
				row.createCell(7).setCellValue(
						productList.get(i).getPrice().toString());
				row.createCell(8).setCellValue(productList.get(i).getBrand());

			}
			Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
			String excelPath = properties.getProperty("excelPaths");
			File file = new File(excelPath + "storeProductList.xls");

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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductUploadVO excelUpload(ProductVo file) {
		ProductUploadVO response = new ProductUploadVO();
		String excelPath = null;
		Merchant merchant = new Merchant();
		try {
			Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
			excelPath = properties.getProperty("excelPath");
			byte[] data = Base64.decode(file.getImage().getImage());
			try (OutputStream stream = new FileOutputStream(excelPath)) {
				stream.write(data);
			}
			merchant = merchantService.getMerchantById(file.getMerchant()
					.getMerchantId());
			List<ProductType> productType = ProductTypeService
					.getAllProductTypeByMerchant(merchant);
			List<Product> productList = new ArrayList<Product>();
			response = convertXlsToModel(excelPath, 0, merchant, productType);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}

		return response;

	}

	@Path("/addFilesData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductUploadVO addFilesData(ProductUploadVO productUploadVO)
			throws Exception {
		ProductUploadVO response = new ProductUploadVO();
		List<ProductUploadDataVo> newData = productUploadVO.getNewData();
		Merchant merchant = merchantService.getMerchantById(productUploadVO
				.getMerchant().getMerchantId());
		List<ProductType> productTypeList = ProductTypeService
				.getAllProductTypeByMerchant(merchant);
		for (ProductUploadDataVo productUploadData : newData) {
			Product product = new Product();
			ProductCategory productCategoryAddObject = new ProductCategory();
			ProductType productTypeAddObject = new ProductType();
			List<Product> checkUniqueProducts = getProductService()
					.getProductListByName(productUploadData.getName(), merchant);
			if (checkUniqueProducts.isEmpty()) {
				for (ProductType productType : productTypeList) {
					if (productType.getName().equalsIgnoreCase(
							productUploadData.getProductType())) {
						ProductCategory productCategoryObject = productType
								.getProductCategory();
						if (productCategoryObject.getName().equalsIgnoreCase(
								productUploadData.getProductCategory())) {
							productTypeAddObject = productType;
							productCategoryAddObject = productCategoryObject;
						}

					}
				}
				if (productCategoryAddObject.getProductCategoryId() != null
						&& productTypeAddObject.getProductTypeId() != null) {
					product = (Product) CommonUtil
							.setAuditColumnInfo(Product.class.getName());
					product.setName(productUploadData.getName());
					product.setProductCategory(productCategoryAddObject);
					product.setProductType(productTypeAddObject);
					product.setMerchant(merchant);
					product.setEdibleType(productUploadData.getEdibleType());
					product.setBrand(productUploadData.getBrand());
					product.setPrice(productUploadData.getSellingPrice());
					product.setUnit(productUploadData.getProductUnit());
					product.setIsYourHot('N');
					product.setIsactive('Y');
					product.setWasPrice(productUploadData.getWasPrice());
					product.setIsBundle('N');
					product.setIsKit('N');
					product.setIsChild('N');
					Uom uom = uomService.getUomByName(productUploadData
							.getProductMeasurement().trim());
					if (uom != null) {
						product.setUom(uom);
					} else {
						uom = new Uom();
						uom = (Uom) CommonUtil.setAuditColumnInfo(Uom.class
								.getName());
						uom.setName(productUploadData.getProductMeasurement()
								.trim());
						uom.setDescription(productUploadData
								.getProductMeasurement().trim());
						uomService.addUOM(uom);
						product.setUom(uom);
					}
				}
				productService.addProduct(product);
				
			}
		}
		updateFilesData(productUploadVO, merchant, productTypeList);
		response.setStatus(SBMessageStatus.SUCCESS.getValue());
		return response;
	}

	public void updateFilesData(ProductUploadVO productUploadVO,
			Merchant merchant, List<ProductType> productTypeList) throws Exception {
		List<ProductUploadDataVo> existingData = productUploadVO
				.getExistingData();
		for (ProductUploadDataVo productUploadData : existingData) {
			Product product = productService.getProductByName(
					productUploadData.getName(), merchant);
			ProductCategory productCategoryAddObject = new ProductCategory();
			ProductType productTypeAddObject = new ProductType();
			if (product != null) {
				for (ProductType productType : productTypeList) {
					if (productType.getName().equalsIgnoreCase(
							productUploadData.getProductType())) {
						ProductCategory productCategoryObject = productType
								.getProductCategory();
						if (productCategoryObject.getName().equalsIgnoreCase(
								productUploadData.getProductCategory())) {
							productTypeAddObject = productType;
							productCategoryAddObject = productCategoryObject;
						}

					}
				}
				if(productCategoryAddObject.getProductCategoryId() != null
						&& productTypeAddObject.getProductTypeId() != null){
					product.setUpdated(new Date());
					product.setProductCategory(productCategoryAddObject);
					product.setProductType(productTypeAddObject);
					product.setEdibleType(productUploadData.getEdibleType());
					product.setBrand(productUploadData.getBrand());
					product.setPrice(productUploadData.getSellingPrice());
					product.setUnit(productUploadData.getProductUnit());
					product.setWasPrice(productUploadData.getWasPrice());
					Uom uom = uomService.getUomByName(productUploadData
							.getProductMeasurement().trim());
					if (uom != null) {
						product.setUom(uom);
					} else {
						uom = new Uom();
						uom = (Uom) CommonUtil.setAuditColumnInfo(Uom.class
								.getName());
						uom.setName(productUploadData.getProductMeasurement()
								.trim());
						uom.setDescription(productUploadData
								.getProductMeasurement().trim());
						uomService.addUOM(uom);
						product.setUom(uom);
					}
				}
				productService.updateProduct(product);
			}
		
		}

	}

	public ProductUploadVO convertXlsToModel(String path, int sheetNo,
			Merchant merchant, List<ProductType> productType) {
		ProductUploadVO response = new ProductUploadVO();
		/*
		 * Product product = new Product(); Uom uom = null; boolean isNew =
		 * false;
		 */
		try {
			FileInputStream file = new FileInputStream(new File(path));
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory
					.create(file);
			org.apache.poi.ss.usermodel.Sheet sheet = workbook
					.getSheetAt(sheetNo);
			List<String> labels = new ArrayList<String>();
			int count = 0;
			Iterator<Row> rowIterator = sheet.iterator();
			List<ProductUploadDataVo> newData1 = new ArrayList<ProductUploadDataVo>();
			List<ProductUploadDataVo> existingData1 = new ArrayList<ProductUploadDataVo>();
			List<ProductUploadDataVo> rejectedData1 = new ArrayList<ProductUploadDataVo>();
			while (rowIterator.hasNext()) {
				boolean newDataFlag = false;
				boolean existingDataFlag = false;
				boolean rejectedDataFlag = false;
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				if (count == 0) {
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
				} else {
					int cellPosition = 0;
					BigDecimal compareWasPrice = new BigDecimal("0");
					ProductUploadDataVo productUploadDataVoSet = new ProductUploadDataVo();
					while (cellIterator.hasNext()
							&& cellPosition < row.getLastCellNum()) {
					
						Cell cell = row.getCell(cellPosition,
								Row.RETURN_BLANK_AS_NULL);
						if (labels.get(cellPosition).equalsIgnoreCase("name")) {
							if (cell != null) {
								List<Product> productName = productService
										.getProductListByName(cell
												.getStringCellValue().trim(),
												merchant);
								productUploadDataVoSet.setName(cell
										.getStringCellValue().trim());
								if (productName.size() == 0) {
									newDataFlag = true;
								} else {
									newDataFlag = false;
								}
							} else {
								rejectedDataFlag = true;
								productUploadDataVoSet.setReason(labels
										.get(cellPosition) + " is Empty");
							}
						} else if (labels.get(cellPosition).equalsIgnoreCase(
								"product category")) {
							if (cell != null && !rejectedDataFlag) {
								for (ProductType productTypeValue : productType) {
									ProductCategory productCategoryValue = productTypeValue
											.getProductCategory();
									if (productCategoryValue.getName()
											.equalsIgnoreCase(
													cell.getStringCellValue()
															.trim())) {
										rejectedDataFlag = false;
										break;
									} else {
										rejectedDataFlag = true;
										newDataFlag = false;
										productUploadDataVoSet.setReason(labels
												.get(cellPosition)
												+ " name is not available");
									}
								}
								productUploadDataVoSet.setProductCategory(cell
										.getStringCellValue().trim());
							} else {
								rejectedDataFlag = true;
								if (cell == null)
									productUploadDataVoSet.setReason(labels
											.get(cellPosition) + " is Empty");
							}
						} else if (labels.get(cellPosition).equalsIgnoreCase(
								"product type")) {
							if (cell != null && !rejectedDataFlag) {
								for (ProductType productTypeValue : productType) {
									if (productTypeValue.getName()
											.equalsIgnoreCase(
													cell.getStringCellValue()
															.trim())) {
										rejectedDataFlag = false;
										break;
									} else {
										rejectedDataFlag = true;
										newDataFlag = false;
										productUploadDataVoSet.setReason(labels
												.get(cellPosition)
												+ " name is not available");
									}
								}
								productUploadDataVoSet.setProductType(cell
										.getStringCellValue().trim());
							} else {
								rejectedDataFlag = true;
								if (cell == null)
									productUploadDataVoSet.setReason(labels
											.get(cellPosition) + " is Empty");
							}
						} else if (labels.get(cellPosition).equalsIgnoreCase(
								"product measurement")) {
							if (cell != null) {
								productUploadDataVoSet
										.setProductMeasurement(cell
												.getStringCellValue().trim());
							} else {
								rejectedDataFlag = true;
								newDataFlag = false;
								productUploadDataVoSet.setReason(labels
										.get(cellPosition) + " is Empty");
							}
						} else if (labels.get(cellPosition).equalsIgnoreCase(
								"product Unit")) {
							if (cell != null) {
								BigDecimal productUnit = new BigDecimal(
										cell.toString());
								productUploadDataVoSet
										.setProductUnit(productUnit);
							} else {
								rejectedDataFlag = true;
								newDataFlag = false;
								productUploadDataVoSet.setReason(labels
										.get(cellPosition) + " is Empty");
							}
						} else if (labels.get(cellPosition).equalsIgnoreCase(
								"edible type")) {
							if (cell != null) {
								productUploadDataVoSet.setEdibleType(cell
										.getStringCellValue().trim());
							} else {
								rejectedDataFlag = true;
								newDataFlag = false;
								productUploadDataVoSet.setReason(labels
										.get(cellPosition) + " is Empty");
							}
						} else if (labels.get(cellPosition).equalsIgnoreCase(
								"was price")) {
							if (cell != null) {
								BigDecimal productWasPrice = new BigDecimal(
										cell.toString());
								compareWasPrice=productWasPrice;
								productUploadDataVoSet
										.setWasPrice(productWasPrice);
							} else {
								rejectedDataFlag = true;
								newDataFlag = false;
								productUploadDataVoSet.setReason(labels
										.get(cellPosition) + " is Empty");
							}
						} else if (labels.get(cellPosition).equalsIgnoreCase(
								"selling price")) {
							if (cell != null) {
								BigDecimal productSellingPrice = new BigDecimal(
										cell.toString());
								int res=compareWasPrice.compareTo(productSellingPrice);
								if(res==0 || res==1){
								productUploadDataVoSet
										.setSellingPrice(productSellingPrice);
								}
								else{
									productUploadDataVoSet
									.setSellingPrice(productSellingPrice);
									rejectedDataFlag = true;
									newDataFlag = false;
									productUploadDataVoSet.setReason("Was price should be greather then the selling price");
								}
							} else {
								rejectedDataFlag = true;
								newDataFlag = false;
								productUploadDataVoSet.setReason(labels
										.get(cellPosition) + " is Empty");
							}
						} else if (labels.get(cellPosition).equalsIgnoreCase(
								"brand")) {
							if (cell != null) {
								productUploadDataVoSet.setBrand(cell
										.getStringCellValue().trim());
							} else {
								rejectedDataFlag = true;
								newDataFlag = false;
								productUploadDataVoSet.setReason(labels
										.get(cellPosition) + " is Empty");
							}
						}
						cellPosition++;
					}
					if (newDataFlag) {
						newData1.add(productUploadDataVoSet);
					} else if (rejectedDataFlag) {
						rejectedData1.add(productUploadDataVoSet);
					} else {
						existingData1.add(productUploadDataVoSet);
					}
				}
			}
			response.setNewData(newData1);
			response.setExistingData(existingData1);
			response.setRejectedData(rejectedData1);
			file.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return response;
	}

	public ProductUploadVO addProduct(Product product) {
		ProductUploadVO productVo = new ProductUploadVO();
		productVo.setErrorString(SBErrorMessage.PRODUCT_NOT_AVAILABLE
				.getMessage());
		productVo.setStatus(SBMessageStatus.FAILURE.getValue());
		return productVo;

	}

	@Path("/mobileimageupload")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String imageUpload(ProductVo productVo) {
		String responseStr = null;
		List<String> imageIds = new ArrayList<String>();
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			Product product = productService.getProduct(productVo
					.getProductId());

			if (productVo.getImage().getImage() != null) {
				productService.productImageUpload(productVo.getImage(),
						product.getMerchant());
				if (productVo.getImage().getImageId() != null) {
					imageIds.add(productVo.getImage().getImageId());
				}
				Image image = imageService.setImage(productVo.getImage());
				product.setImage(image);
			}

			List<ProductImage> productImages = new ArrayList<ProductImage>();
			List<ImageVo> imageVos = productVo.getImages();

			for (ImageVo imageVo : imageVos) {

				productService.productImageUpload(imageVo,
						product.getMerchant());
				if (imageVo.getImage() != null) {
					if (imageVo.getImageId() != null) {
						imageIds.add(imageVo.getImageId());
					}
					Image image = imageService.setImage(imageVo);
					imageService.addImage(image);
					ProductImage productimage = (ProductImage) CommonUtil
							.setAuditColumnInfo(ProductImage.class.getName());
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

			if (!imageIds.isEmpty()) {
				List<Image> images = imageService.getImages(imageIds);

				// delete images and product images
				for (Image image : images) {
					if (image.getProductImages() != null
							&& image.getProductImages().size() > 0) {
						productImageService.deleteProductImage(image
								.getProductImages().get(0));
					}
					imageService.deleteImage(image);
				}
			}
			productResponse.setProduct(productvo);
			productResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			productResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			productResponse.setErrorString(CommonUtil.getErrorMessage(e));
		}

		try {
			responseStr = CommonUtil.getObjectMapper(productResponse);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return responseStr;

	}

	private void setProductVoForMobile(ProductVo productVo, Product product)
			throws IOException {
		productVo.setName(product.getName());
		productVo.setProductId(product.getProductId());
		if (product.getImage() != null) {
			ImageVo image = imageService.setImageVo(product.getImage());
			productVo.setImage(image);
		}

		List<ProductImage> productImageList = product.getProductImages();
		List<ImageVo> imageVoList = new ArrayList<ImageVo>();
		for (ProductImage productImage : productImageList) {
			ImageVo image = imageService.setImageVo(productImage.getImage());
			imageVoList.add(image);
		}
		productVo.setImages(imageVoList);
	}

	@Path("/getproducts")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getProdutsFromMobile(ProductVo productVo) {
		ProductResponseVo productResponse = new ProductResponseVo();
		try {
			String productTypeId = null;
			if (productVo.getProductType() != null
					&& productVo.getProductType().getProductTypeId() != null) {
				productTypeId = productVo.getProductType().getProductTypeId();
			} else if (productVo.getProductTypeId() != null) {
				productTypeId = productVo.getProductTypeId();
			}
			ProductType productType = ProductTypeService
					.getProductTypeById(productTypeId);

			List<Product> productList = productType.getProducts();
			List<ProductVo> productVoList = new ArrayList<ProductVo>();
			for (Product product : productList) {
				ProductVo productvo = new ProductVo();
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
		String responseString = null;
		try {
			responseString = CommonUtil.getObjectMapper(productResponse);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return responseString;
	}

}