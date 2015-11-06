package com.mitosis.aviate.webservice.admin;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.codec.binary.Base64;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mitosis.aviate.dao.BinProductDAO;
import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.ProductDao;
import com.mitosis.aviate.dao.ProductUpdateDao;
import com.mitosis.aviate.dao.daoimpl.BinProductDAOImpl;
import com.mitosis.aviate.dao.daoimpl.CommonDaoImpl;
import com.mitosis.aviate.dao.daoimpl.ProductDaoImpl;
import com.mitosis.aviate.dao.daoimpl.ProductUpdateDaoImpl;
import com.mitosis.aviate.model.BinProductModel;
import com.mitosis.aviate.model.ProductCategory;
import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.ProductImages;
import com.mitosis.aviate.model.ProductOffer;
import com.mitosis.aviate.model.ProductPriceDetails;
import com.mitosis.aviate.model.ProductType;
import com.mitosis.aviate.model.ProductUOM;
import com.mitosis.aviate.model.Units;
import com.mitosis.aviate.model.service.CategoryResponse;
import com.mitosis.aviate.model.service.ProductCategoryResponse;
import com.mitosis.aviate.model.service.ResponseModel;
import com.mitosis.aviate.util.AVErrorMessage;
import com.mitosis.aviate.util.AVMessageStatus;
import com.mitosis.aviate.util.CommonUtil;

@Path("update")
public class ProductUpdateService {

	final static Logger log = Logger.getLogger(ProductUpdateService.class);
	CommonDao commonDao = new CommonDaoImpl();
	ProductUpdateDao productUpdateDao = new ProductUpdateDaoImpl();
	ResponseModel response = new ResponseModel();
	Properties properties = new Properties();

	@Path("/product/addparentcategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addParentCategory(JSONObject requestObj){
		ProductCategory productCategory = new ProductCategory();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the update product type service");
			productCategory.setMerchantId(Long.parseLong(requestObj.getString("merchantId")));
			if(commonDao.isValidProperty(requestObj, "storeId")){
				productCategory.setStoreId(Long.parseLong(requestObj.getString("storeId")));
			}
			productCategory.setCategoryName(requestObj.getString("parentCategoryName"));
			boolean res=productUpdateDao.addCategory(productCategory);
			if(!res){

				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorCode(AVErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS.getCode());
				response.setErrorString(AVErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS.getMessage());
				return response;
			}
			if(commonDao.isValidProperty(requestObj, "categoryId")){
				ProductCategory selectCategory = new ProductCategory();
				selectCategory.setCategoryId(Long.parseLong(requestObj.getString("categoryId")));
				selectCategory = productUpdateDao.selectCategoryById(selectCategory);
				selectCategory.setParentCategoryId(productCategory.getCategoryId());
				productUpdateDao.updateCategory(selectCategory);
			}
			response.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			response=CommonUtil.addStatusMessage(e,response);
			//			response.setStatus(AVMessageStatus.FAILURE.getValue());
			//			response.setErrorCode(com.mitosis.aviate.util.Constants.E0013);
			//			response.setErrorString(com.mitosis.aviate.util.Constants.E0013Error);
		}
		log.info("\n******************************************\n"
				+ "Response of addParentCategory");
		return response;
	}

	@Path("/product/addcategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addCategory(JSONObject requestObj){
		ProductCategory productCategory = new ProductCategory();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the update product type service");
			productCategory.setMerchantId(Long.parseLong(requestObj.getString("merchantId")));
			if(commonDao.isValidProperty(requestObj, "storeId")){
				productCategory.setStoreId(Long.parseLong(requestObj.getString("storeId")));
			}
			productCategory.setCategoryName(requestObj.getString("categoryName"));
			productCategory.setParentCategoryId(Long.parseLong(requestObj.getString("parentCategoryId")));
			List<Long> categoryIds = new ArrayList<Long>();
			categoryIds.add(productCategory.getParentCategoryId());
			List<ProductType> productTypeList =	productUpdateDao.getProductType(categoryIds);
			if(!productTypeList.isEmpty()){
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorCode(AVErrorMessage.CATEGORY_ALREADY_HAS_PRODUCT_TYPE.getCode());
				response.setErrorString(AVErrorMessage.CATEGORY_ALREADY_HAS_PRODUCT_TYPE.getMessage());
				return response;
			}
			List<ProductCategory> pcl = productUpdateDao.getCategoryByParentId(productCategory.getParentCategoryId());
			for(ProductCategory pc : pcl){
				if(pc.getCategoryName().equalsIgnoreCase(requestObj.getString("categoryName"))){
					response.setStatus(AVMessageStatus.FAILURE.getValue());
					response.setErrorCode(AVErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS.getCode());
					response.setErrorString(AVErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS.getMessage());
					return response;
				}
			}

			productUpdateDao.addCategory(productCategory);
			if(productCategory.getCategoryId() == null){
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorCode(AVErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS.getCode());
				response.setErrorString(AVErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS.getMessage());
			}
			else{
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
			}
		}catch(Exception e){
			log.error(e.getMessage());
			response=CommonUtil.addStatusMessage(e, response);
		}
		log.info("\n******************************************\n"
				+ "Response of addParentCategory");
		return response;
	}

	@Path("/product/updatecategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel updateCategory(JSONObject requestObj){
		ProductCategory productCategory = new ProductCategory();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the update category type service");
			productCategory.setCategoryId(requestObj.getLong("categoryId"));
			productCategory = productUpdateDao.selectCategoryById(productCategory);
			productCategory.setCategoryName(requestObj.getString("categoryName"));

			if(commonDao.isValidProperty(requestObj, "merchantId"))
				productCategory.setMerchantId(Long.parseLong(requestObj.getString("merchantId")));

			if(commonDao.isValidProperty(requestObj, "storeId"))
				productCategory.setStoreId(Long.parseLong(requestObj.getString("storeId")));

			if(commonDao.isValidProperty(requestObj, "parentCategoryId"))
				productCategory.setParentCategoryId(Long.parseLong(requestObj.getString("parentCategoryId")));
			productUpdateDao.updateCategory(productCategory);
			response.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			response=CommonUtil.addStatusMessage(e, response);
		}
		log.info("\n******************************************\n"
				+ "Response of updateCategory");
		return response;
	}

	@Path("/product/removecategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel removeCategory(JSONObject requestObj){
		ProductCategory productCategory = new ProductCategory();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the remove product category service");
			productCategory.setCategoryId(requestObj.getLong("categoryId"));
			//	productCategory = productUpdateDao.selectCategoryById(productCategory);
			productUpdateDao.removeCategory(productCategory);
			response.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			response=CommonUtil.addStatusMessage(e, response);
		}
		log.info("\n******************************************\n"
				+ "Response of remove product category");
		return response;
	}

	@Path("/product/getLeafcategories")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getLeafcategories(JSONObject requestObj){
		CategoryResponse categoryResponse = new CategoryResponse();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the getleafcategories service");
			ProductDao productDao = new ProductDaoImpl();
			List<ProductCategory> productCategoryList = productDao.getCategoryList(Long.parseLong(requestObj.getString("merchantId")));
			List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
			productDao.getLeafCategories(productCategoryList, productCategories);
			categoryResponse.setCategory(productCategories);
			categoryResponse.setStatus(AVMessageStatus.SUCCESS.getValue());
			categoryResponse.setErrorCode("");
			categoryResponse.setErrorString("");
		}catch(Exception e){
			categoryResponse.setStatus(AVMessageStatus.FAILURE.getValue());
			categoryResponse.setErrorCode(AVErrorMessage.NO_PRODUCT_CATEGORY.getCode());
			categoryResponse.setErrorString(AVErrorMessage.NO_PRODUCT_CATEGORY.getMessage());
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String res = null;
		try {
			res = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(categoryResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.info(res);
		log.info("\n******************************************\n"
				+ "Response of getleafcategories");
		return res;
	}

	@Path("/product/addproducttypes")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductType addProductType(ProductType productType){
		try{
			log.info("\n******************************************\n"
					+ "Initializing the add product type service");
			productUpdateDao.addProductType(productType);
			productType.setStatus(AVMessageStatus.SUCCESS.getValue());
			productType.setErrorString("");
			productType.setErrorCode("");

		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			productType.setStatus(AVMessageStatus.FAILURE.getValue());
			productType.setErrorString(e.getMessage());
			productType.setErrorCode("");
		}
		log.info("\n******************************************\n"
				+ "Response of the update product type service");
		return productType;
	}

	@Path("/product/updateproducttypes")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductType updateProductType(ProductType productType){
		try{
			log.info("\n******************************************\n"
					+ "Initializing the update product type service");
			ProductType resultProducttype = productUpdateDao.getSingleProductType(productType.getProductTypeId());
			if(resultProducttype != null){
				if(productType.getCategoryId()!=null){
					resultProducttype.setCategoryId(productType.getCategoryId());
				}
				resultProducttype.setProductTypeName(productType.getProductTypeName());
				if(productUpdateDao.updateProductType(resultProducttype)){
					productType.setStatus(AVMessageStatus.SUCCESS.getValue());
					productType.setErrorString("");
					productType.setErrorCode("");
				}else{
					productType.setStatus(AVMessageStatus.FAILURE.getValue());
					productType.setErrorString(com.mitosis.aviate.util.Constants.E0011Error);
					productType.setErrorCode(com.mitosis.aviate.util.Constants.E0011);
				}
			}else{
				productType.setStatus(AVMessageStatus.FAILURE.getValue());
				productType.setErrorString(com.mitosis.aviate.util.Constants.E0001Error);
				productType.setErrorCode(com.mitosis.aviate.util.Constants.E0001);
			}
		}catch(Exception e){
			e.printStackTrace();
			productType.setStatus(AVMessageStatus.FAILURE.getValue());
			productType.setErrorString(com.mitosis.aviate.util.Constants.E0012Error);
			productType.setErrorCode(com.mitosis.aviate.util.Constants.E0012);
		}
		log.info("\n******************************************\n"
				+ "Response of the update product type service");
		return productType;
	}

	@Path("/product/removeproducttype")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel removeStore(JSONObject requestObj){
		try{
			log.info("\n******************************************\n"
					+ "Initializing the removce productType service");
			boolean flag = productUpdateDao.removeSingleProductType(Long.parseLong(requestObj.getString("productTypeId")));
			if(flag){
				response.setStatus("SUCCESS");
			}else{
				response.setStatus("FAILURE");
				response.setErrorString("");
				response.setErrorCode("");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************************\n"
				+ "Response of the remove productType service");
		return response;
	}

	@Path("/product")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addProduct(JSONObject productImageJson){


		ProductDetails productDetails = new ProductDetails();

		ResponseModel response = addProductDetailsInfo(productImageJson,
				productDetails);
		log.info("\n******************************************\n"
				+ "Response of the update product type service");
		return response;
	}

	private ResponseModel addProductDetailsInfo(JSONObject productImageJson,
			ProductDetails productDetails) {
		ResponseModel response = new ResponseModel();
		ProductPriceDetails productPrice = new ProductPriceDetails();
		List<ProductImages> productImageList = new ArrayList<ProductImages>();
		ProductOffer productOffer = new ProductOffer();
		ProductUOM productUnitOfMeasure = new ProductUOM();
		ProductImages productImage;
		String imageName, imageString, imageUrl;
		byte[] byeImage;
		try{
			log.info("\n******************************************\n"
					+ "Initializing the update product type service");
			properties.load(getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
			String imageLocation = properties.getProperty("imageUrl");
			//String imageLocation = "http://182.74.202.178:8181/aviate/ImageServlet?imageName=";
			if(commonDao.isValidProperty(productImageJson, "storeId"))			
				productDetails.setStoreId(productImageJson.getInt("storeId"));
			if(commonDao.isValidProperty(productImageJson, "merchantId"))	
				productDetails.setMerchantId(productImageJson.getInt("merchantId"));
			productDetails.setProductTypeId(Long.parseLong(productImageJson.getString("productTypeId")));
			productDetails.setProductName(productImageJson.getString("productName"));
			productDetails.setMeasurement(productImageJson.getString("measurement"));
			productDetails.setType(productImageJson.getString("type"));

			//			productDetails.setProductDescription(productImageJson.getString("productDescription"));
			productUnitOfMeasure.setAbbreviation(productImageJson.getString("abbreviation"));
			productUnitOfMeasure.setDescription(productImageJson.getString("description"));
			if(commonDao.isValidProperty(productImageJson, "productId")){

				productDetails.setProductId(productImageJson.getLong("productId"));
			}
			if(commonDao.isValidProperty(productImageJson, "offereName")){
				productOffer.setOfferName(productImageJson.getString("offereName"));
				productOffer.setStoreId(Long.parseLong(productImageJson.getString("storeId")));
				productUpdateDao.updateProductOffer(productOffer);
				if(commonDao.isValidProperty(productImageJson, "groupCount")){
					productDetails.setGroupCount(productImageJson.getString("groupCount"));
				}
			}
			if(commonDao.isValidProperty(productImageJson, "smallFrontImage")){
				productImage = new ProductImages();
				imageName = UUID.randomUUID().toString().replace("-", "");
				imageString = productImageJson.getString("smallFrontImage");
				byeImage = Base64.decodeBase64(imageString);			
				productImage.setImage(byeImage);
				productImage.setImagePosition("SMALLFRONT");
				productImage.setImageType(productImageJson.getString("smallFrontImageType"));
				productImage.setImageName(imageName);
				imageUrl = imageLocation + imageName;
				productImage.setImageUrl(imageUrl);
				productImageList.add(productImage);
			}
			if(commonDao.isValidProperty(productImageJson, "smallBackImage")){
				productImage = new ProductImages();
				imageName = UUID.randomUUID().toString().replace("-", "");
				imageString = productImageJson.getString("smallBackImage");
				byeImage = Base64.decodeBase64(imageString);			
				productImage.setImage(byeImage);
				productImage.setImagePosition("SMALLBACK");
				productImage.setImageType(productImageJson.getString("smallBackImageType"));
				productImage.setImageName(imageName);
				imageUrl = imageLocation + imageName;
				productImage.setImageUrl(imageUrl);
				productImageList.add(productImage);
			}
			if(commonDao.isValidProperty(productImageJson, "originalFrontImage")){
				productImage = new ProductImages();
				imageName = UUID.randomUUID().toString().replace("-", "");
				imageString = productImageJson.getString("originalFrontImage");
				byeImage = Base64.decodeBase64(imageString);			
				productImage.setImage(byeImage);
				productImage.setImagePosition("ORIGINALFRONT");
				productImage.setImageType(productImageJson.getString("originalFrontImageType"));
				productImage.setImageName(imageName);
				imageUrl = imageLocation + imageName;
				productImage.setImageUrl(imageUrl);
				productImageList.add(productImage);
			}
			if(commonDao.isValidProperty(productImageJson, "originalBackImage")){
				productImage = new ProductImages();
				imageName = UUID.randomUUID().toString().replace("-", "");
				imageString = productImageJson.getString("originalBackImage");
				byeImage = Base64.decodeBase64(imageString);			
				productImage.setImage(byeImage);
				productImage.setImagePosition("ORIGINALBACK");
				productImage.setImageType(productImageJson.getString("originalBackImageType"));
				productImage.setImageName(imageName);
				imageUrl = imageLocation + imageName;
				productImage.setImageUrl(imageUrl);
				productImageList.add(productImage);
			}
			//productUpdateDao.updateProductImage(productImageList);
			//	productDetails.setProductUnitOfMeasure(productUnitOfMeasure);
			productDetails.setProductOfferId(productOffer.getProductOfferId());
			productDetails.setProductImages(productImageList);

			/* Update Product Details
			if(commonDao.isValidProperty(productImageJson, "productId")){

				productDetails.setProductId(productImageJson.getLong("productId"));
			}
			/* End Update Product Details*/

			productUpdateDao.updateProduct(productDetails);
			if(productDetails.getStatus().equals("SUCCESS")){
				productPrice.setPrice(Double.parseDouble(productImageJson.getString("price")));
				productPrice.setProductId(productDetails.getProductId());
				if(productImageJson.has("priceId")){


					productPrice.setPriceId(Long.valueOf(productImageJson.getString("priceId")));

				}
				productUpdateDao.updateProductPrice(productPrice);
				productUnitOfMeasure.setProductId(productDetails.getProductId());
				if(productImageJson.has("measureId")){


					productUnitOfMeasure.setUnitOfMeasureId(Long.valueOf(productImageJson.getString("measureId")));

				}

				productUpdateDao.updateUOM(productUnitOfMeasure);
				response.setStatus("SUCCESS");
				response.setErrorString("");
				response.setErrorCode("");
				System.out.println(productUnitOfMeasure);
				return response;
			}
		}catch(Exception e){
			e.printStackTrace();
			response.setStatus("FAILURE");
			response.setErrorString("");
			response.setErrorCode("");
		}
		return response;
	}


	@Path("/product/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel updateProduct(JSONObject productImageJson){
		ProductDetails productDetails = new ProductDetails();
		try{
			if(commonDao.isValidProperty(productImageJson, "productId")){
				productDetails.setProductId(productImageJson.getLong("productId"));
				ProductDao productDao = new ProductDaoImpl();
				productDetails = productDao.getProductDetails(productImageJson.getLong("productId"));
				ResponseModel response = new ResponseModel();
				ProductPriceDetails productPrice = new ProductPriceDetails();
				List<ProductImages> productImageList = new ArrayList<ProductImages>();
				ProductOffer productOffer = new ProductOffer();
				ProductUOM productUnitOfMeasure = new ProductUOM();
				ProductImages productImage;
				String imageName, imageString, imageUrl;
				byte[] byeImage;
				try{
					log.info("\n******************************************\n"
							+ "Initializing the update product type service");
					properties.load(getClass().getResourceAsStream(
							"/properties/serverurl.properties"));
					String imageLocation = properties.getProperty("imageUrl");
					//String imageLocation = "http://182.74.202.178:8181/aviate/ImageServlet?imageName=";
					if(commonDao.isValidProperty(productImageJson, "storeId"))			
						productDetails.setStoreId(productImageJson.getInt("storeId"));
					if(commonDao.isValidProperty(productImageJson, "merchantId"))	
						productDetails.setMerchantId(productImageJson.getInt("merchantId"));
					productDetails.setProductTypeId(Long.parseLong(productImageJson.getString("productTypeId")));
					productDetails.setProductName(productImageJson.getString("productName"));
					productDetails.setMeasurement(productImageJson.getString("measurement"));
					productDetails.setType(productImageJson.getString("type"));

					//			productDetails.setProductDescription(productImageJson.getString("productDescription"));
					productUnitOfMeasure.setAbbreviation(productImageJson.getString("abbreviation"));
					productUnitOfMeasure.setDescription(productImageJson.getString("description"));
					if(commonDao.isValidProperty(productImageJson, "productId")){

						productDetails.setProductId(productImageJson.getLong("productId"));
					}
					if(commonDao.isValidProperty(productImageJson, "offereName")){
						productOffer.setOfferName(productImageJson.getString("offereName"));
						productOffer.setStoreId(Long.parseLong(productImageJson.getString("storeId")));
						productUpdateDao.updateProductOffer(productOffer);
						if(commonDao.isValidProperty(productImageJson, "groupCount")){
							productDetails.setGroupCount(productImageJson.getString("groupCount"));
						}
					}
					if(commonDao.isValidProperty(productImageJson, "smallFrontImage")){
						for(ProductImages temp : productDetails.getProductImages()){
							if(temp.getImagePosition().equalsIgnoreCase("SMALLFRONT")){
								imageName = UUID.randomUUID().toString().replace("-", "");
								imageString = productImageJson.getString("smallFrontImage");
								byeImage = Base64.decodeBase64(imageString);			
								temp.setImage(byeImage);
								temp.setImagePosition("SMALLFRONT");
								temp.setImageType(productImageJson.getString("smallFrontImageType"));
								temp.setImageName(imageName);
								imageUrl = imageLocation + imageName;
								temp.setImageUrl(imageUrl);
							}
						}

					}
					if(commonDao.isValidProperty(productImageJson, "smallBackImage")){
						for(ProductImages temp : productDetails.getProductImages()){
							if(temp.getImagePosition().equalsIgnoreCase("SMALLBACK")){
								imageName = UUID.randomUUID().toString().replace("-", "");
								imageString = productImageJson.getString("smallBackImage");
								byeImage = Base64.decodeBase64(imageString);			
								temp.setImage(byeImage);
								temp.setImagePosition("SMALLBACK");
								temp.setImageType(productImageJson.getString("smallBackImageType"));
								temp.setImageName(imageName);
								imageUrl = imageLocation + imageName;
								temp.setImageUrl(imageUrl);
							}}}

					if(commonDao.isValidProperty(productImageJson, "originalFrontImage")){
						for(ProductImages temp : productDetails.getProductImages()){
							if(temp.getImagePosition().equalsIgnoreCase("ORIGINALFRONT")){
								imageName = UUID.randomUUID().toString().replace("-", "");
								imageString = productImageJson.getString("originalFrontImage");
								byeImage = Base64.decodeBase64(imageString);			
								temp.setImage(byeImage);
								temp.setImagePosition("ORIGINALFRONT");
								temp.setImageType(productImageJson.getString("originalFrontImageType"));
								temp.setImageName(imageName);
								imageUrl = imageLocation + imageName;
								temp.setImageUrl(imageUrl);
							}}}

					if(commonDao.isValidProperty(productImageJson, "originalBackImage")){
						for(ProductImages temp : productDetails.getProductImages()){
							if(temp.getImagePosition().equalsIgnoreCase("ORIGINALBACK")){
								imageName = UUID.randomUUID().toString().replace("-", "");
								imageString = productImageJson.getString("originalBackImage");
								byeImage = Base64.decodeBase64(imageString);			
								temp.setImage(byeImage);
								temp.setImagePosition("ORIGINALBACK");
								temp.setImageType(productImageJson.getString("originalBackImageType"));
								temp.setImageName(imageName);
								imageUrl = imageLocation + imageName;
								temp.setImageUrl(imageUrl);
							}}}
					productUpdateDao.updateProduct(productDetails);
					if(productDetails.getStatus().equals("SUCCESS")){
						productPrice.setPrice(Double.parseDouble(productImageJson.getString("price")));
						productPrice.setProductId(productDetails.getProductId());
						if(productImageJson.has("priceId")){


							productPrice.setPriceId(Long.valueOf(productImageJson.getString("priceId")));

						}
						productUpdateDao.updateProductPrice(productPrice);
						productUnitOfMeasure.setProductId(productDetails.getProductId());
						if(productImageJson.has("measureId")){


							productUnitOfMeasure.setUnitOfMeasureId(Long.valueOf(productImageJson.getString("measureId")));

						}

						productUpdateDao.updateUOM(productUnitOfMeasure);
						response.setStatus("SUCCESS");
						response.setErrorString("");
						response.setErrorCode("");
						System.out.println(productUnitOfMeasure);
						return response;
					}
					
				}catch(Exception e){
					e.printStackTrace();
					response.setStatus("FAILURE");
					response.setErrorString("");
					response.setErrorCode("");
				}
				return response;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}


	@Path("/product/getproducttypes")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getProductType(JSONObject categoryId) throws JSONException{
		JSONObject listObj= new JSONObject();
		List<ProductType> productTypeList = new ArrayList<ProductType>();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get product type service");
			List<Long> categoryIds = new ArrayList<Long>();
			categoryIds.add(Long.parseLong(categoryId.getString("categoryId")));
			productTypeList =	productUpdateDao.getProductType(categoryIds);
			JSONArray arrayList = new JSONArray();
			for(ProductType iterate : productTypeList){
				JSONObject iteObj= new JSONObject();
				iteObj.put("productTypeId", iterate.getProductTypeId());
				iteObj.put("productTypeName", iterate.getProductTypeName());
				arrayList.put(iteObj);
			}
			listObj.put("productType", arrayList);
			listObj.put("status", "SUCCESS");
			listObj.put("errorCode", "");
			listObj.put("errorString", "");
		}catch(Exception e){
			e.printStackTrace();
			listObj.put("status", "FAILURE");
			listObj.put("errorCode", "");
			listObj.put("errorString", "");
		}
		log.info("\n******************************************\n"
				+ "Response of the get product type service");
		return listObj;
	}

	@Path("/product/getproducttypesbystore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryResponse getProductTypeByStore(JSONObject requestObj) throws JSONException{
		ProductCategoryResponse productTypeResponse = new ProductCategoryResponse();
		List<ProductType> productTypes = new ArrayList<ProductType>();  
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get product types by store service");
			ProductDao productDao = new ProductDaoImpl();
			List<ProductCategory> productCategoryList = productDao.getCategoryList(Long.parseLong(requestObj.getString("merchantId")));

			List<Long> ids=new ArrayList<Long>();
			productDao.getCategoryIds(productCategoryList, ids);
			productTypes = productUpdateDao.getProductType(ids);

			productTypeResponse.setProductTypes(productTypes);
			productTypeResponse.setStatus(AVMessageStatus.SUCCESS.getValue());
			productTypeResponse.setErrorString("");
			productTypeResponse.setErrorCode("");
		}catch(Exception e){
			e.printStackTrace();
			productTypeResponse.setStatus(AVMessageStatus.FAILURE.getValue());
			productTypeResponse.setErrorString("");
			productTypeResponse.setErrorCode("");
		}
		log.info("\n******************************************\n"
				+ "Response of the get product types by store service");
		return productTypeResponse;
	}

	@Path("/product/getallcategorylist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryResponse getAllParentCategories(JSONObject requestObj) throws JSONException{
		ProductCategoryResponse productCategoryResponse = new ProductCategoryResponse();
		List<ProductCategory> categories = new ArrayList<ProductCategory>();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get product types by store service");

			ProductDao productDao = new ProductDaoImpl();
			categories = productDao.getCategoryList(Long.parseLong(requestObj.getString("merchantId")));
			List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
			productDao.getParentCategories(categories, productCategories);		
			productCategoryResponse.setCategories(productCategories);
			productCategoryResponse.setStatus(AVMessageStatus.SUCCESS.getValue());

		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************************\n"
				+ "Response of the get product types by store service");
		return productCategoryResponse;
	}

	// units services

	@Path("/product/addunits")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addUnits(Units units) {
		try{
			log.info("\n******************************************\n"
					+ "Initializing the add or update units service");
			boolean flag = productUpdateDao.updateUnits(units);
			if(flag){
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
			}else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorString("This abbreviation already exists.");
				response.setErrorCode("");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************************\n"
				+ "Response of the add or update units service");
		return response;
	}

	@Path("/product/getunits")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getUnits() {
		JSONObject unitsList = new JSONObject();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get units service");
			List<Units> unitList = productUpdateDao.getUnits();
			if(!unitList.isEmpty()){
				JSONArray units  = new JSONArray();
				for(Units ul : unitList){
					JSONObject listObj = new JSONObject();
					listObj.put("unitsId", ul.getUnitsId());
					listObj.put("abbreviation", ul.getAbbreviation());
					listObj.put("description", ul.getDescription());
					units.put(listObj);
				}
				unitsList.put("units",units);
				unitsList.put("status", AVMessageStatus.SUCCESS.getValue());
			}else{
				unitsList.put("Status", AVMessageStatus.FAILURE.getValue());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************************\n"
				+ "Response of the get units service");
		return unitsList;
	}

	@Path("/product/merchant/getunits")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getUnitsByMerchant(JSONObject request) {
		JSONObject unitsList = new JSONObject();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get units service");
			List<Units> unitList = productUpdateDao.getUnitsListByMerchant(request);
			if(!unitList.isEmpty()){
				JSONArray units  = new JSONArray();
				for(Units ul : unitList){
					JSONObject listObj = new JSONObject();
					listObj.put("unitsId", ul.getUnitsId());
					listObj.put("abbreviation", ul.getAbbreviation());
					listObj.put("description", ul.getDescription());
					listObj.put("merchantId", ul.getMerchantId());
					units.put(listObj);
				}
				unitsList.put("units",units);
				unitsList.put("status", AVMessageStatus.SUCCESS.getValue());
			}else{
				unitsList.put("Status", AVMessageStatus.FAILURE.getValue());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************************\n"
				+ "Response of the get units service");
		return unitsList;
	}

	@Path("/product/removeunits")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel removeUnits(JSONObject requestObj) {

		try{
			log.info("\n******************************************\n"
					+ "Initializing the remove units service");
			boolean flag = productUpdateDao.deleteUnit(Long.parseLong(requestObj.getString("unitsId")));
			if(flag){
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
			}else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************************\n"
				+ "Response of the remove units service");
		return response;
	}

	@Path("/deleteproduct")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel deleteProduct(JSONObject requestObj){
		try{
			log.info("\n******************************************\n"
					+ "Initializing the removce product service");
			boolean flag = new ProductDaoImpl().deleteProductById(Long.parseLong(requestObj.getString("productId")));
			if(flag){
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
			}else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorString(AVErrorMessage.PRODUCT_DELETE_ERROR.getMessage());
				response.setErrorCode(AVErrorMessage.PRODUCT_DELETE_ERROR.getCode());
			}
		}catch(Exception e){
			response.setStatus(AVMessageStatus.FAILURE.getValue());
			response.setErrorString(AVErrorMessage.PRODUCT_DELETE_ERROR.getMessage());
			response.setErrorCode(AVErrorMessage.PRODUCT_DELETE_ERROR.getCode());
			e.printStackTrace();
		}
		log.info("\n******************************************\n"
				+ "Response of the remove store service");
		return response;
	}

	@Path("/product/exportExcelFile")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public   ResponseModel exportExcelFile(JSONObject requestObj){
		try{
			log.info("\n******************************************\n"
					+ "Initializing the export   product service");
			BinProductDAO binProductDao = new BinProductDAOImpl();
			List<BinProductModel> binProductList = binProductDao.getBinProducts(Long.parseLong(requestObj.getString("storeId")));
			List<ProductDetails> productLists = new ArrayList<ProductDetails>();
			
			for(int i=0;i<binProductList.size();i++){
				ProductDetails product = binProductList.get(i).getProduct();
				productLists.add(product);
			}

			//Blank workbook
			XSSFWorkbook workbook = new XSSFWorkbook(); 
			
			//Create a blank sheet
			XSSFSheet sheet = workbook.createSheet("Product Data");
			 
			//This data needs to be written (Object[])
			Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
			data.put(1, new Object[] {"PRODUCT ID", "NAME", "PRICE","UNIT","MEASUREMENT","BRAND","AVAILABLE QUANTITY"});
			for(int i=0;i<productLists.size();i++){
				data.put(i+2, new Object[] {productLists.get(i).getProductId(), productLists.get(i).getProductName(), 
						productLists.get(i).getProductPrice().getPrice(),productLists.get(i).getMeasurement(),productLists.get(i).getProductUnitOfMeasure().getAbbreviation(),
						productLists.get(i).getBrand(),productLists.get(i).getAvilability()} );
			}
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
			
		    File file  = new File(System.getProperty("java.io.tmpdir")+File.separator+"storeProductList.xlsx");
		    System.out.println(file.getAbsolutePath());
			FileOutputStream out = new FileOutputStream(file);
		    workbook.write(out);
		    out.close();
		    response.setFileInByteArrayString(file.getAbsolutePath());
		    /*FileInputStream fis = new FileInputStream(file);
		    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        byte[] buf = new byte[1024];
	        for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
            }
	        byte[] bytes = bos.toByteArray();
	        ResponseBuilder response = Response.ok((Object) file);
			response.header("Content-Disposition",
				"attachment; filename=new-excel-file.xls");
			return response.build();*/
		    return response;
			
		}catch(Exception e){

			e.printStackTrace();
			return response;
		}
		
	}


}
