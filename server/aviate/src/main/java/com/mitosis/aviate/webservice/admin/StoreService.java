package com.mitosis.aviate.webservice.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.mitosis.aviate.dao.CheckoutDao;
import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.CustomerDao;
import com.mitosis.aviate.dao.StoreDao;
import com.mitosis.aviate.dao.daoimpl.CheckoutDaoImpl;
import com.mitosis.aviate.dao.daoimpl.CommonDaoImpl;
import com.mitosis.aviate.dao.daoimpl.CustomerDaoImpl;
import com.mitosis.aviate.dao.daoimpl.StoreDaoImpl;
import com.mitosis.aviate.model.Address;
import com.mitosis.aviate.model.CustomerDetailsModel;
import com.mitosis.aviate.model.CustomerModel;
import com.mitosis.aviate.model.MerchantDetails;
import com.mitosis.aviate.model.SalesOrderModel;
import com.mitosis.aviate.model.StoreModel;
import com.mitosis.aviate.model.service.CustomerDetailsResponse;
import com.mitosis.aviate.model.service.ResponseModel;
import com.mitosis.aviate.model.service.SalesOrderResponseModel;
import com.mitosis.aviate.util.AVErrorMessage;
import com.mitosis.aviate.util.AVMessageStatus;
import com.mitosis.aviate.util.CommonUtil;
import com.mitosis.aviate.webservice.ecommerce.CustomerServices;
import com.mitosis.aviate.model.service.StoreResponseModel;
import com.mitosis.aviate.dao.daoimpl.CustomerDaoImpl;

@Path("store")
public class StoreService {

	HttpServlet http;

	final static Logger log = Logger
			.getLogger(StoreService.class.getName());
	CommonDao commonDao = new CommonDaoImpl();
	ResponseModel response = new ResponseModel();
	StoreDao storeDao = new StoreDaoImpl();
	CheckoutDao checkoutDao = new CheckoutDaoImpl();
	CustomerDao customerDao = new CustomerDaoImpl();
	Properties properties = new Properties();
	CustomerDaoImpl customerDAO = new CustomerDaoImpl();

	public String nextSessionId() {
		Random rand = new Random(1234567890);
		String pass = "";
		for (int j = 0; j<5; j++)
		{
			int nextRandom = rand.nextInt(5);
			pass += nextRandom;
		}
		return pass;
	}

	@Path("/addstoreorupdate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addUpdateStoreDetails(StoreModel storeModel){
		try{
			boolean flag=false;
			log.info("\n******************************************\n"
					+ "Initializing the add new store service");
			properties.load(getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
			String full_address=storeModel.getAddress()+","+storeModel.getArea()+","+storeModel.getCity()+","+storeModel.getState()+","+storeModel.getCountry()+","+storeModel.getPostalCode();
			JsonNode location = commonDao.getLatLong(full_address);
			if(location!=null){
				JsonNode loc = location.findValue("lat".toString());
				storeModel.setLatitude(loc.toString());
				storeModel.setLongitude(loc.toString());
				loc = location.findValue("lng".toString());
			}else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorString(AVErrorMessage.ADDRESS_NOT_VALID.getCode());
				response.setErrorString(AVErrorMessage.ADDRESS_NOT_VALID.getMessage());

				response.setErrorCode("");
				return response;
			}
			if(storeModel.getStoreId() == null){
				//String imageLocation = "http://182.74.202.178:8181/aviate/ImageServlet?storeLogoName=";
				String imageLocation = properties.getProperty("imageUrl");
				String imageName = UUID.randomUUID().toString().replace("-", "");
				storeModel.setImageName(imageName);
				String imageUrl = imageLocation + imageName;
				storeModel.setLogoUrl(imageUrl);
				storeModel.setMerchantId(storeModel.getMerchantId());

			}
			/*To call the signup Services*/
			List<CustomerModel> toCheckSignupData=new ArrayList<CustomerModel>();
			List<CustomerModel> toCheckSignupDataNotCustomer=new ArrayList<CustomerModel>();
			toCheckSignupData=customerDao.getListByQuery(storeModel.getEmailId(),"CUSTOMER");
			toCheckSignupDataNotCustomer=customerDao.getListByQuery2(storeModel.getEmailId(),"CUSTOMER");
			if(toCheckSignupData.isEmpty() && toCheckSignupDataNotCustomer.isEmpty()){
				CustomerServices signup=new CustomerServices();
				flag = storeDao.addUpdateStoreDetails(storeModel);
				JSONObject credentials=new JSONObject();
				credentials.put("emailId",storeModel.getEmailId());
				credentials.put("password", storeModel.getPassword());
				credentials.put("role", "STOREADMIN");
				credentials.put("store_id", storeModel.getStoreId());
				credentials.put("merchantId", storeModel.getMerchantId());
				if(flag){
				signup.signUp(credentials);
				}
				else{
					response.setStatus(AVMessageStatus.FAILURE.getValue());
					response.setErrorString(AVErrorMessage.ADDRESS_LENGTH_ERROR.getCode());
					response.setErrorString(AVErrorMessage.ADDRESS_LENGTH_ERROR.getMessage());
					response.setErrorCode("");
					return response;
				}
			}
			else{
				if(!toCheckSignupData.isEmpty() && toCheckSignupDataNotCustomer.isEmpty()){
					CustomerModel cm=new CustomerModel();
					CustomerDetailsModel customerDetails=new CustomerDetailsModel();
					flag = storeDao.addUpdateStoreDetails(storeModel);
					cm=toCheckSignupData.get(0);
					cm.setRole("STOREADMIN");
					customerDao.edit(cm);
					customerDetails=customerDao.getListBycustomerId(cm.getCustomerId());
					customerDetails.setStoreId(storeModel.getStoreId());
					customerDetails.setMerchantId(storeModel.getMerchantId());
					customerDao.updateCustomerDetails(customerDetails);

				} 
				else{
					if(toCheckSignupDataNotCustomer.isEmpty()){
						if(storeModel.getStoreId()==null){
							flag = storeDao.addUpdateStoreDetails(storeModel);
							CustomerServices signup=new CustomerServices();
							JSONObject credentials=new JSONObject();
							credentials.put("emailId",storeModel.getEmailId());
							credentials.put("password", storeModel.getPassword());
							credentials.put("role", "STOREADMIN");
							credentials.put("store_id", storeModel.getStoreId());
							if(flag){
							signup.signUp(credentials);
							}
							else{
								response.setStatus(AVMessageStatus.FAILURE.getValue());
								response.setErrorString(AVErrorMessage.ADDRESS_LENGTH_ERROR.getCode());
								response.setErrorString(AVErrorMessage.ADDRESS_LENGTH_ERROR.getMessage());
								response.setErrorCode("");
								return response;
							}
						}
						else{
							List<CustomerDetailsModel> cm=new ArrayList<CustomerDetailsModel>();
							cm=customerDao.getListByStoreId(storeModel.getStoreId());
							storeModel.setCustomerDetails(cm);
							flag = storeDao.addUpdateStoreDetails(storeModel);
						}
					}
					else{
						if(storeModel.getStoreId()!=null){
							List<CustomerDetailsModel> cm=new ArrayList<CustomerDetailsModel>();
							cm=customerDao.getListByStoreId(storeModel.getStoreId());
							storeModel.setCustomerDetails(cm);
							flag = storeDao.addUpdateStoreDetails(storeModel);
						}
						else{
							flag=false;
						}

					}
				}
			}
			/*end*/

			if(flag){
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
			}else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorString(AVErrorMessage.EMAILID_ALREADY_EXIT.getCode());
				response.setErrorString(AVErrorMessage.EMAILID_ALREADY_EXIT.getMessage());
				response.setErrorCode("");
			}
		}catch(Exception e){
			log.error(e.getMessage());
			CommonUtil.addStatusMessage(e, response);
		}
		log.info("\n******************************************\n"
				+ "Response of the add new store service");
		return response;
	}

	@Path("/removestore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel removeStore(JSONObject requestObj){
		try{
			log.info("\n******************************************\n"
					+ "Initializing the removce store service");
			List<CustomerDetailsModel> customerDetails=new ArrayList<CustomerDetailsModel>();
			customerDetails=customerDao.getListByStoreId(Long.parseLong(requestObj.getString("storeId")));
			boolean flag = storeDao.removeStore(Long.parseLong(requestObj.getString("storeId")));
			if(flag){
				customerDao.remove(customerDetails.get(0).getCustomerId());
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
			}else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorString(AVErrorMessage.EMAILID_ALREADY_EXIT.getCode());
				response.setErrorCode("");
			}
		}catch(Exception e){
			log.error(e.getMessage());
			CommonUtil.addStatusMessage(e, response);
		}
		log.info("\n******************************************\n"
				+ "Response of the remove store service");
		return response;
	}

	@Path("/employeeregister")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel employeeRegister(JSONObject requestObj){
		CustomerModel customerModel = new CustomerModel();
		CustomerDetailsModel customerDetail= new CustomerDetailsModel();
		Address address = new Address();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the removce store service");
			customerModel.setEmailId(requestObj.getString("emailId"));
			customerModel.setRole(requestObj.getString("role"));
			customerModel.setPassword(nextSessionId());
			customerDetail.setStoreId(Long.parseLong(requestObj.getString("storeId")));
			customerDetail.setEmailId(customerModel.getEmailId());
			customerDetail.setPhoneNo(requestObj.getString("phoneNo"));
			address.setFirstName(requestObj.getString("firstName"));
			address.setLastName(requestObj.getString("lastName"));
			address.setAddress(requestObj.getString("address"));
			address.setCity(requestObj.getString("city"));
			address.setState(requestObj.getString("state"));
			address.setCountry(requestObj.getString("country"));
			address.setPostalCode(requestObj.getString("postalCode"));
			address.setPrimaryAddress(true);
			String full_address=address.getAddress()+","+address.getCity()+","+address.getState()+","+address.getCountry()+","+address.getPostalCode();
			JsonNode location = commonDao.getLatLong(full_address);
			if(location==null){

				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorCode(AVErrorMessage.ADDRESS_NOT_VALID.getCode());
				response.setErrorString(AVErrorMessage.ADDRESS_NOT_VALID.getMessage());
				return response;

			}
			JsonNode loc = location.findValue("lat".toString());
			address.setLatitude(loc.toString());
			loc = location.findValue("lng".toString());
			address.setLongitude(loc.toString());
			if(commonDao.isValidProperty(requestObj, "createdBy")){
				customerModel.setCreatedBy(Long.parseLong(requestObj.getString("createdBy")));
				address.setCreatedBy(Long.parseLong(requestObj.getString("createdBy")));
				customerModel.setCreatedBy(Long.parseLong(requestObj.getString("createdBy")));
			}
			if(commonDao.isValidProperty(requestObj, "updatedBy")){
				customerModel.setCreatedBy(Long.parseLong(requestObj.getString("updatedBy")));
				address.setCreatedBy(Long.parseLong(requestObj.getString("updatedBy")));
				customerModel.setCreatedBy(Long.parseLong(requestObj.getString("updatedBy")));
			}
			List<Address> addr = new ArrayList<Address>();
			addr.add(address);
			customerModel.setAddress(addr);
			customerDao.save(customerModel);
			if(customerModel.getStatus().equals(AVMessageStatus.SUCCESS.getValue())){
				customerDetail.setCustomerId(customerModel.getCustomerId());
				customerDao.updateCustomerDetails(customerDetail);
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
			}else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorString(AVErrorMessage.USER_ALREADY_EXIT.getCode());
				response.setErrorString(AVErrorMessage.USER_ALREADY_EXIT.getMessage());
				response.setErrorCode("");
			}
		}catch(Exception e){
			log.error(e.getMessage());
			CommonUtil.addStatusMessage(e, response);
			
		}
		log.info("\n******************************************\n"
				+ "Response of the remove store service");
		return response;
	}
	@Path("/editemployee")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel editEmployee(JSONObject requestObj){
		CustomerModel customerModel = new CustomerModel();
		CustomerDetailsModel customerDetail= new CustomerDetailsModel();
		Address address = new Address();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the removce store service");
			customerDetail = customerDAO.getListBycustomerId(Long.parseLong(requestObj.getString("customerId")));
			customerModel.setEmailId(requestObj.getString("emailId"));
			customerModel.setRole(requestObj.getString("role"));
			customerModel.setCustomerId(Long.parseLong(requestObj.getString("customerId")));
			customerModel.setPassword(nextSessionId());
			customerDetail.setStoreId(Long.parseLong(requestObj.getString("storeId")));
			customerDetail.setEmailId(customerModel.getEmailId());
			customerDetail.setPhoneNo(requestObj.getString("phoneNo"));
			address.setAddressId(requestObj.getLong("customerDetailsId"));
			address.setCustomerId(Long.parseLong(requestObj.getString("customerId")));
			address.setFirstName(requestObj.getString("firstName"));
			address.setLastName(requestObj.getString("lastName"));
			address.setAddress(requestObj.getString("address"));
			address.setCity(requestObj.getString("city"));
			address.setState(requestObj.getString("state"));
			address.setCountry(requestObj.getString("country"));
			address.setPostalCode(requestObj.getString("postalCode"));
			address.setPrimaryAddress(true);
			String full_address=address.getAddress()+","+address.getCity()+","+address.getState()+","+address.getCountry()+","+address.getPostalCode();
			JsonNode location = commonDao.getLatLong(full_address);
			if(location==null){

				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorCode(AVErrorMessage.ADDRESS_NOT_VALID.getCode());
				response.setErrorString(AVErrorMessage.ADDRESS_NOT_VALID.getMessage());
				return response;

			}
			JsonNode loc = location.findValue("lat".toString());
			address.setLatitude(loc.toString());
			loc = location.findValue("lng".toString());
			address.setLongitude(loc.toString());
			if(commonDao.isValidProperty(requestObj, "createdBy")){
				customerModel.setCreatedBy(Long.parseLong(requestObj.getString("createdBy")));
				address.setCreatedBy(Long.parseLong(requestObj.getString("createdBy")));
				customerModel.setCreatedBy(Long.parseLong(requestObj.getString("createdBy")));
			}
			if(commonDao.isValidProperty(requestObj, "updatedBy")){
				customerModel.setCreatedBy(Long.parseLong(requestObj.getString("updatedBy")));
				address.setCreatedBy(Long.parseLong(requestObj.getString("updatedBy")));
				customerModel.setCreatedBy(Long.parseLong(requestObj.getString("updatedBy")));
			}
			List<Address> addr = new ArrayList<Address>();
			addr.add(address);
			customerModel.setAddress(addr);
			customerDao.edit(customerModel);
			customerDetail.setCustomerId(customerModel.getCustomerId());
			customerDao.updateCustomerDetails(customerDetail);
			if(customerModel.getStatus().equals(AVMessageStatus.SUCCESS.getValue())){
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
			}else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorString(AVErrorMessage.USER_ALREADY_EXIT.getMessage());
				response.setErrorCode(AVErrorMessage.USER_ALREADY_EXIT.getCode());
			}
		}catch(Exception e){
			log.error(e.getMessage());
			CommonUtil.addStatusMessage(e, response);
			
		}
		log.info("\n******************************************\n"
				+ "Response of the remove store service");
		return response;
	}


	@Path("/getemployeelist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerDetailsResponse getEmployeeList(JSONObject requestObj){
		CustomerDetailsResponse res = new CustomerDetailsResponse();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the removce store service");
			List<String> roles = new ArrayList<String>();
			String storeId;
			if(commonDao.isValidProperty(requestObj, "storeId")){
				storeId = requestObj.getString("storeId");
			}else{
				storeId = "ALL";
			}
			if(!commonDao.isValidProperty(requestObj, "role") || 
					(commonDao.isValidProperty(requestObj, "role")
							&& requestObj.getString("role").equals("ALL"))){
				roles.add("PICKER");
				roles.add("DELIVERYBOY");
			}else if(commonDao.isValidProperty(requestObj, "role")){
				roles.add(requestObj.getString("role"));
			}
			List<CustomerModel> employeeList = storeDao.getEmployeeList(storeId, roles);
			for(CustomerModel customer : employeeList){
				customer.setCustomerDetails(customerDAO.getListBycustomerId(customer.getCustomerId()));
			}
			res.setCustomerList(employeeList);
			res.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			res.setErrorString(e.getMessage());
			res.setStatus(AVMessageStatus.FAILURE.getValue());

		}
		log.info("\n******************************************\n"
				+ "Response of the remove store service");
		return res;
	}

	@Path("/removeemployee")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel removeEmployee(JSONObject requestObj){
		try{
			log.info("\n******************************************\n"
					+ "Initializing the remove employee service");

			boolean flag = storeDao.removeEmployee(Long.parseLong(requestObj.getString("customerId")));
			if(flag){
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
			}else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
			}
		}catch(Exception e){
			log.error(e.getMessage());
			CommonUtil.addStatusMessage(e , response);
		}
		log.info("\n******************************************\n"
				+ "Response of the remove employee service");
		return response;
	}

	@Path("/getuserlist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SalesOrderResponseModel getUserList(JSONObject requestObj){
		SalesOrderResponseModel res = new SalesOrderResponseModel();

		try{
			log.info("\n******************************************\n"
					+ "Initializing the removce store service");
			List<SalesOrderModel> salesOrderModels = storeDao.getUserDetails(requestObj);
			res.setSalesOrderModel(salesOrderModels);
			res.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			res.setStatus(AVMessageStatus.FAILURE.getValue());
			res.setErrorString(e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the remove store service");
		return res;
	}

	@Path("/getorderlist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SalesOrderResponseModel getOrderList(JSONObject requestObj){
		SalesOrderResponseModel res = new SalesOrderResponseModel();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the removce store service");

			List<SalesOrderModel> salesOrderModels = storeDao.getOrderList(requestObj);
			res.setSalesOrderModel(salesOrderModels);
			res.setStatus(AVMessageStatus.SUCCESS.getValue());

		}catch(Exception e){
			log.error(e.getMessage());
			res.setStatus(AVMessageStatus.FAILURE.getValue());
			res.setErrorString(e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the remove store service");
		return res;
	}

	@Path("/updateorderstatus")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel updateOrderStatus(JSONObject requestObj){
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		String imageName, imageString;
		byte byeImage[];
		try{
			log.info("\n******************************************\n"
					+ "Initializing the removce store service");
			salesOrderModel.setOrderStatus(requestObj.getString("orderStatus"));
			salesOrderModel.setOrderId(requestObj.getString("orderId"));

			if(requestObj.getString("orderStatus").equals("Completed") 
					&& commonDao.isValidProperty(requestObj, "userSign")){
				imageName = UUID.randomUUID().toString().replace("-", "");
				imageString = requestObj.getString("userSign");
				byeImage = Base64.decodeBase64(imageString);			
				salesOrderModel.setUserSign(byeImage);
			}


			boolean flag = storeDao.updateOrderStatus(salesOrderModel);
			if(flag){
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
				if(requestObj.getString("orderStatus").equals("Readytoship")){
					CustomerDao customerDao = new CustomerDaoImpl();
					JSONObject pickerSearch = new JSONObject();
					pickerSearch.put("storeId",requestObj.getString("storeId"));
					pickerSearch.put("role","DELIVERYBOY");
					List<CustomerDetailsModel> customerList =	customerDao.getPickerDeviceId(pickerSearch);
					for(CustomerDetailsModel customer : customerList ){
						if(customer.getDeviceType() != null 
								&& customer.getDeviceType().equals("ANDROID")){
							try{
								commonDao.androidPushNotification(requestObj.getString("orderId"), customer.getDeviceId());
							}catch(Exception e){
							}
						}else if(customer.getDeviceType() != null 
								&& customer.getDeviceType().equals("IOS")){
							try{
								commonDao.applePushNotification(requestObj.getString("orderId"), customer.getDeviceId());
							}catch(Exception e){
								log.error(e.getMessage());
								response.setStatus(AVMessageStatus.FAILURE.getValue());
								response.setErrorString(e.getMessage());
							}
						}else{

						}
					}
				}
			}else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorCode("");
				response.setErrorString("");
			}
		}catch(Exception e){
			log.error(e.getMessage());
			CommonUtil.addStatusMessage(e, response);
		}
		log.info("\n******************************************\n"
				+ "Response of the remove store service");
		return response;
	}

	@Path("/getmerchantstore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StoreResponseModel getStoreByMerchant(JSONObject requestObj){
		StoreResponseModel res = new StoreResponseModel();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the get merchant store service");
			if(requestObj.length()!=0){
				List<StoreModel> merchantStoreList = storeDao.getStoreByMerchant(requestObj);
				res.setStoreList(merchantStoreList);
				res.setStatus(AVMessageStatus.SUCCESS.getValue());
			}else{
				res.setStatus(AVMessageStatus.FAILURE.getValue());
			}
		}catch(Exception e){
			log.error(e.getMessage());
			res.setStatus(AVMessageStatus.FAILURE.getValue());
			res.setErrorString(e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the get merchant store service");
		return res;
	}
}
