package com.mitosis.aviate.webservice.ecommerce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.MerchantDAO;
import com.mitosis.aviate.dao.ProductDao;
import com.mitosis.aviate.dao.ProductUpdateDao;
import com.mitosis.aviate.dao.StoreDao;
import com.mitosis.aviate.dao.daoimpl.CommonDaoImpl;
import com.mitosis.aviate.dao.daoimpl.MerchantDAOImpl;
import com.mitosis.aviate.dao.daoimpl.ProductDaoImpl;
import com.mitosis.aviate.dao.daoimpl.ProductUpdateDaoImpl;
import com.mitosis.aviate.dao.daoimpl.StoreDaoImpl;
import com.mitosis.aviate.model.CustomerDetailsModel;
import com.mitosis.aviate.model.MerchantDetails;
import com.mitosis.aviate.model.MyCartModel;
import com.mitosis.aviate.model.ProductCategory;
import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.ProductType;
import com.mitosis.aviate.model.ShippingChargeModel;
import com.mitosis.aviate.model.StoreModel;
import com.mitosis.aviate.model.TaxModel;
import com.mitosis.aviate.model.service.CategoryResponse;
import com.mitosis.aviate.model.service.MyCartRequestModel;
import com.mitosis.aviate.model.service.MyCartResponseModel;
import com.mitosis.aviate.model.service.ProductCategoryResponse;
import com.mitosis.aviate.model.service.ProductResponse;
import com.mitosis.aviate.model.service.ResponseModel;
import com.mitosis.aviate.model.service.SearchResponse;
import com.mitosis.aviate.util.AVErrorMessage;
import com.mitosis.aviate.util.AVMessageProperties;
import com.mitosis.aviate.util.AVMessageStatus;
import com.mitosis.aviate.util.CommonUtil;
import com.mitosis.aviate.util.Constants;

@Path("product")
public class ProductServices  {
	final static Logger log = Logger
			.getLogger(ProductServices.class);
	CommonDao commonDao = new CommonDaoImpl();
	ProductDao productDao = new ProductDaoImpl();
	MerchantDAO merchantDao=new MerchantDAOImpl();
	StoreDao storeDao = new StoreDaoImpl();
	ResponseModel response = new ResponseModel();

	@Path("/getshoplist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getShopList(JSONObject filterDetails) {
		JSONObject response = new JSONObject();
		List<StoreModel> shopList= null;
		JSONArray jsonArray = new JSONArray();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get shop list process");
			shopList = productDao.getShopList(filterDetails);
			if(commonDao.isValidProperty(filterDetails, "latitude") 
					&& commonDao.isValidProperty(filterDetails, "longitude")){
				for(StoreModel list : shopList){
					JSONObject list1 = new JSONObject();
					double dist=CommonUtil.distance(Double.parseDouble(filterDetails.getString("latitude")),Double.parseDouble(filterDetails.getString("longitude")),Double.parseDouble(list.getLatitude()),Double.parseDouble(list.getLongitude()));
					log.info("Near by store list"+ list.getStoreName()+" is "+ dist);
					if(dist<=40)
					{
						list1.put("storeId", list.getStoreId());
						list1.put("storeName",list.getStoreName());
						list1.put("storeOrder",list.getStoreOrder());
						list1.put("address",list.getAddress());
						list1.put("city",list.getCity());
						list1.put("state",list.getState());
						list1.put("postalCode",list.getPostalCode());
						list1.put("country",list.getCountry());
						list1.put("phoneNo",list.getPhoneNo());
						list1.put("merchantId", list.getMerchantId());
						if(list.getMerchantId()!=null){
							MerchantDetails merchantDetails=new MerchantDetails();
							merchantDetails=merchantDao.getMerchantById(list.getMerchantId());
							if(merchantDetails!=null){
								if(merchantDetails.getProductImages()!=null){
									System.out.println(merchantDetails.getProductImages().getImageUrl());
									list1.put("merchantLogo", merchantDetails.getProductImages().getImageUrl());
									list1.put("merchantName",merchantDetails.getMerchantName());
								}
							}
						}
						jsonArray.put(list1);
					}
				}
			}else{
				for(StoreModel list : shopList){
					JSONObject list1 = new JSONObject();
					list1.put("storeId", list.getStoreId());
					list1.put("storeName",list.getStoreName());
					list1.put("storeOrder",list.getStoreOrder());
					list1.put("address",list.getAddress());
					list1.put("city",list.getCity());
					list1.put("state",list.getState());
					list1.put("postalCode",list.getPostalCode());
					list1.put("country",list.getCountry());
					list1.put("phoneNo",list.getPhoneNo());
					list1.put("merchantId", list.getMerchantId());
					JSONArray arr = new JSONArray();
					for (CustomerDetailsModel cd : list.getCustomerDetails()) {
						JSONObject obj = new JSONObject();
						obj.put("emailId", cd.getEmailId());
						arr.put(obj);
					}	
					if(list.getMerchantId()!=null){
						MerchantDetails merchantDetails=new MerchantDetails();
						merchantDetails=merchantDao.getMerchantById(list.getMerchantId());
						if(merchantDetails!=null){
							if(merchantDetails.getProductImages()!=null){
								System.out.println(merchantDetails.getProductImages().getImageUrl());
								list1.put("merchantLogo", merchantDetails.getProductImages().getImageUrl());
								list1.put("merchantName",merchantDetails.getMerchantName());
							}
						}
					}
					list1.put("customerDetails", arr);
					jsonArray.put(list1);
				}
			}			
			response.put("shoplist",jsonArray);
			response.put(AVMessageProperties.STATUS.getValue(),AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			response=CommonUtil.addStatusMessage(e);
		}
		log.info("\n******************************************\n"
				+ "Response of the get shop list process");
		return response;
	}

	@Path("/getcategories")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getCategoryList(JSONObject storeObj) throws Exception {
		CategoryResponse response = new CategoryResponse();
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		try
		{
			log.info("\n******************************************\n"
					+ "Initializing the get product category list in store using store Id "+storeObj.getString("merchantId"));
				productCategoryList = productDao.getCategoryList(Long.parseLong(storeObj.getString("merchantId")));
				response.setCategory(productCategoryList);
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			response.setStatus(AVMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String responseString = null;
		try {
			responseString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			 throw e;
			
		}
		log.info("\n******************************************\n"
				+ "Response of the product category list "+ responseString );
		return responseString;
	}

	@Path("/getcity")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Set<String> getStoreByMerchant() throws JSONException{
		JSONObject response = new JSONObject();
		Set cityList = null;
		try{
			cityList=new HashSet(productDao.getShopCityList());
			response.put("status",cityList);
			response.put("status","SUCCESS");
		}catch(Exception e){
			log.error(e.getMessage());
			response.put("status",AVMessageStatus.SUCCESS.getValue());
			response.put("errorCode",e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the get merchant store service");
		return cityList;
	}
	
	@Path("merchant/getcategories")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getCategoryListByMerchant(JSONObject storeObj) throws Exception {
		CategoryResponse response = new CategoryResponse();
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		try
		{
			StoreModel merchant = storeDao.getStoreDetailsById(Long.parseLong(storeObj.getString("storeId")));
			log.info("\n******************************************\n"
					+ "Initializing the get product category list in store using store Id "+merchant.getMerchantId());
				productCategoryList = productDao.getCategoryList(merchant.getMerchantId());
				response.setCategory(productCategoryList);
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			response.setStatus(AVMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String responseString = null;
		try {
			responseString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			 throw e;
			
		}
		log.info("\n******************************************\n"
				+ "Response of the product category list "+ responseString );
		return responseString;
	}
	
	@Path("/gettaxbyshop")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getShopTax(JSONObject storeObj) {
		JSONObject responseObj = new JSONObject(); 
		try{
			List<TaxModel> taxList = productDao.getShopTax(storeObj);
			if(taxList.size()>0){
				Double totalTax= 0.00;
				for( TaxModel tax:taxList){
					totalTax=totalTax+tax.getRate();
				}
				responseObj.put("tax", totalTax);
				responseObj.put(AVMessageProperties.STATUS.getValue(),AVMessageStatus.SUCCESS.getValue());
			}else{
				responseObj.put(AVMessageProperties.STATUS.getValue(),AVMessageStatus.FAILURE.getValue());
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			responseObj=CommonUtil.addStatusMessage(e);
		}
		return responseObj;
	}

	@Path("/gettaxdetailsbyshop")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getShopTaxDetails(JSONObject storeObj) {
		JSONObject responseObj = new JSONObject(); 
		try{
			List<TaxModel> taxList = productDao.getShopTax(storeObj);
			JSONArray taxDetails = new JSONArray();
			if(taxList.size()>0){
				for( TaxModel tax:taxList){
					if(tax.isSummary())
						continue;
					JSONObject taxObj = new JSONObject(); 
					taxObj.put("taxName", tax.getTaxName());
					taxObj.put("rate", tax.getRate());
					taxObj.put("taxId", tax.getTaxId());
					taxDetails.put(taxObj);
				}
				responseObj.put("taxDetails", taxDetails);
				responseObj.put(AVMessageProperties.STATUS.getValue(), AVMessageStatus.SUCCESS.getValue());
			}else{
				responseObj.put(AVMessageProperties.STATUS.getValue(), AVMessageStatus.FAILURE.getValue());
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			responseObj=CommonUtil.addStatusMessage(e);
		}
		return responseObj;
	}


	@Path("/getmerchantproducts")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductResponse getProductsByMerchant(JSONObject merchantId) {
		ProductResponse responseObj = new ProductResponse();
		List<ProductDetails> products = new  ArrayList<ProductDetails>();
		try{
			log.info("initialize get products by merchent"+merchantId.getLong("merchantId"));
			products = productDao.getMerchantProducts(merchantId.getLong("merchantId"));
			if(!products.isEmpty()){
				responseObj.setProducts(products);
				responseObj.setStatus(AVMessageStatus.SUCCESS.getValue());
			}else{
				responseObj.setStatus(AVMessageStatus.FAILURE.getValue());
				responseObj.setErrorString(AVErrorMessage.PRODUCT_NOT_AVAILABLE.getMessage());
				responseObj.setErrorCode(AVErrorMessage.PRODUCT_NOT_AVAILABLE.getCode());
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.info("Response of get products by merchent");
		return responseObj;
	}


	@Path("/getshippingcharge")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getShippingCharges(JSONObject storeObj) {
		JSONObject responseObj = new JSONObject(); 
		try{
			Long storeId = Long.parseLong(storeObj.getString("storeId"));
			List<ShippingChargeModel> shippingCharge = productDao.getShippingCharges(storeId);
			if(shippingCharge.size() > 0){
				//TODO:need to be fix later
				ShippingChargeModel shippingCharges = shippingCharge.get(0); 
				responseObj.put("storeId", shippingCharges.getStoreId());
				responseObj.put("shippingCompany", shippingCharges.getShippingCompany());
				responseObj.put("shippingChargeId", shippingCharges.getShippingChargeId());
				responseObj.put("shippingService", shippingCharges.getShippingService());
				responseObj.put("shippingCharge", shippingCharges.getShippingCharge());
				responseObj.put(AVMessageProperties.STATUS.getValue(),AVMessageStatus.SUCCESS.getValue());
			}else{
				responseObj.put(AVMessageProperties.STATUS.getValue(),AVMessageStatus.FAILURE.getValue());
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			responseObj=CommonUtil.addStatusMessage(e);
		}
		return responseObj;
	}

	@Path("/getproducts")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductResponse getProducts(JSONObject productTypeIdObj) {
		ProductResponse products = new ProductResponse();
		List<ProductDetails> productList = new ArrayList<ProductDetails>();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get product list in store using sub-category Id "+productTypeIdObj.getString("productTypeId"));
			productList = productDao.getProductList(Long.parseLong(productTypeIdObj.getString("productTypeId")));
			products.setProducts(productList);
			products.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			products.setStatus(AVMessageStatus.FAILURE.getValue());
			products.setErrorString(e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the product list");
		return products;
	}
	
	@Path("/getfutureproducts")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductResponse getFutureProducts(JSONObject futureProducts) {
		ProductResponse products = new ProductResponse();
		List<ProductDetails> productList = new ArrayList<ProductDetails>();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get product list in store using");
			
			StoreDao storeDao = new StoreDaoImpl();
			StoreModel storeModal = storeDao.getStoreDetailsById(Long.parseLong(futureProducts.getString("storeId")));
			productList = productDao.getFutureProducts(storeModal.getMerchantId());
			products.setProducts(productList);
			products.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			products.setStatus(AVMessageStatus.FAILURE.getValue());
			products.setErrorString(e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the product list");
		return products;
	}

	@Path("/gettopproducts")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductResponse getTopProducts(JSONObject merchantId) {
		ProductResponse products = new ProductResponse();
		List<ProductDetails> productList = new ArrayList<ProductDetails>();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get product list in store using");
			
			StoreDao storeDao = new StoreDaoImpl();
			StoreModel storeModal = storeDao.getStoreDetailsById(Long.parseLong(merchantId.getString("storeId")));
			productList = productDao.getTopCategories(storeModal.getMerchantId());
			products.setProducts(productList);
			products.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			products.setStatus(AVMessageStatus.FAILURE.getValue());
			products.setErrorString(e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the product list");
		return products;
	}
	
	
	@Path("/getallproductsbycategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductResponse getAllProductsByCategoryId(JSONObject categoryId) {
		ProductResponse products = new ProductResponse();
		List<ProductDetails> productList = new ArrayList<ProductDetails>();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get product list in store using category Id "+categoryId.getString("categoryId"));
			productList = productDao.getAllProductListByCategory(Long.parseLong(categoryId.getString("categoryId")));
			products.setProducts(productList);
			products.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			products.setStatus(AVMessageStatus.FAILURE.getValue());
			products.setErrorString(e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the product list");
		return products;
	}

	@Path("/getnextlevelcategorybyid")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryResponse getNextLevelCategoryById(JSONObject categoryId) {
		ProductCategoryResponse productCategoryResponse = new ProductCategoryResponse();
		List<ProductCategory> categoryList = new ArrayList<ProductCategory>();
		List<ProductType> productTypeList = new ArrayList<ProductType>();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get next level category list in store using category Id "+categoryId.getString("categoryId"));

			categoryList = productDao.getCategoryListById(Long.parseLong(categoryId.getString("categoryId")));
			if(categoryList.isEmpty()){
				ProductUpdateDao productUpdateDao = new ProductUpdateDaoImpl();
				List<Long> categoryIds = new ArrayList<Long>();
				categoryIds.add(Long.parseLong(categoryId.getString("categoryId")));
				productTypeList = productUpdateDao.getProductType(categoryIds);
				productCategoryResponse.setProductTypes(productTypeList);
			}else{
				List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
				for(ProductCategory pc: categoryList){
					ProductCategory cl = new ProductCategory();
					cl.setCategoryId(pc.getCategoryId());
					cl.setCategoryName(pc.getCategoryName());
					productCategoryList.add(cl);
				}
				productCategoryResponse.setCategories(productCategoryList);	
			}
			productCategoryResponse.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			productCategoryResponse.setStatus(AVMessageStatus.FAILURE.getValue());
			productCategoryResponse.setErrorString(e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the get next level category list");
		return productCategoryResponse;
	}

	@Path("/getproductdetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductDetails getProductDetails(JSONObject productId) {
		ProductDetails productDetails = new ProductDetails();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get product details, Product id is: "+productId.getString("productId"));
			productDetails = productDao.getProductDetails(Long.parseLong(productId.getString("productId")));
			productDetails.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			productDetails.setStatus(AVMessageStatus.FAILURE.getValue());
			productDetails.setErrorString(e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the product details");
		return productDetails;
	}

	@Path("/addlistofproductstocart")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addListOfProductsToCart(MyCartRequestModel myCartObj) {
		ResponseModel response = new ResponseModel();
		try{
			log.info("\n******************************************\n"
					+ "Initiated my cart service");
			for(MyCartModel myCart:myCartObj.getMyCartList()){
				myCart.setCustomerId(myCartObj.getCustomerId());
				myCart.setStoreId(myCartObj.getStoreId());
				productDao.updateCart(myCart);
			}
			response.setStatus(AVMessageStatus.SUCCESS.getValue());

		}catch(Exception e){
			log.error(e.getMessage());
			response.setStatus(AVMessageStatus.FAILURE.getValue());
			response.setErrorCode(AVErrorMessage.ADD_CART_ERORR.getCode());
			response.setErrorString(AVErrorMessage.ADD_CART_ERORR.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the add my cart service");
		return response;
	}

	@Path("/addtocart")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addToCart(MyCartModel myCartObj) {
		ResponseModel response = new ResponseModel();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the add to cart service");
			productDao.updateCart(myCartObj);
			response.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			response.setStatus(AVMessageStatus.FAILURE.getValue());
			response.setErrorCode(AVErrorMessage.ADD_CART_ERORR.getCode());
			response.setErrorString(e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the add my cart service");
		return response;
	}

	@Path("/deletefromcart")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel removeFromCart(JSONObject reqObj) {
		try{
			log.info("\n******************************************\n"
					+ "Initializing the delete my cart service");
			boolean flag = productDao.removeFromCart(reqObj);
			if(flag)
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
			else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
			}
		}catch(Exception e){
			log.error(e.getMessage());
			response.setStatus(AVMessageStatus.FAILURE.getValue());
			response.setErrorCode(AVErrorMessage.CART_PRODUCT_DELETE_ERROR.getCode());
			response.setErrorString(AVErrorMessage.CART_PRODUCT_DELETE_ERROR.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the delete my cart service");
		return response;
	}

	@Path("/getmycartlist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MyCartResponseModel getMyCartList(JSONObject reqObj) {
		List<MyCartModel> cartList = new ArrayList<MyCartModel>();
		MyCartResponseModel myCartList = new MyCartResponseModel();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get my cart list service");
			cartList = productDao.getMyCartList(Integer.parseInt(reqObj.getString("customerId")), Long.parseLong(reqObj.getString("storeId")));
			myCartList.setMyCartList(cartList);
			myCartList.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			myCartList.setStatus(AVMessageStatus.FAILURE.getValue());
			myCartList.setErrorString(e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the get my cart list service");
		return myCartList;
	}

	@Path("/searchproducts")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResponse getSearchProducts(JSONObject reqObj) {
		List<ProductCategory> productCategory = new ArrayList<ProductCategory>();
		List<ProductDetails> productDetails = new ArrayList<ProductDetails>();
		List<ProductType> productType = new ArrayList<ProductType>();
		SearchResponse searchresponse = new SearchResponse();


		try {
			Long storeId = reqObj.getLong("storeId");
			String searchString = reqObj.getString("searchString");
			productDetails = productDao.SearchProductResponse(storeId,searchString);
			productCategory = productDao.SearchProductCategoryResponse(storeId,searchString);
			productType = productDao.SearchProductTypeResponse(storeId,searchString);
			searchresponse.setProducts(productDetails);
			List<ProductCategory> productCategory1 = new ArrayList<ProductCategory>();
			for(ProductCategory category: productCategory){
				ProductCategory productCategoryName = new ProductCategory();
				productCategoryName.setCategoryId(category.getCategoryId());
				productCategoryName.setCategoryName(category.getCategoryName());
				productCategory1.add(productCategoryName);
			}
			searchresponse.setCategory(productCategory1);
			searchresponse.setProductsTypes(productType);
			searchresponse.setStatus(AVMessageStatus.SUCCESS.getValue());
		} catch (JSONException e) {
			log.error(e.getMessage());
			searchresponse.setStatus(AVMessageStatus.FAILURE.getValue());
			searchresponse.setErrorString(e.getMessage());
		}

		return searchresponse;
	}
}
