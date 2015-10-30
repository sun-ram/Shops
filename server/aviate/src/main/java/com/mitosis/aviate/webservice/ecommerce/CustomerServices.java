package com.mitosis.aviate.webservice.ecommerce;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.CustomerDao;
import com.mitosis.aviate.dao.daoimpl.CommonDaoImpl;
import com.mitosis.aviate.dao.daoimpl.CustomerDaoImpl;
import com.mitosis.aviate.model.CustomerDetailsModel;
import com.mitosis.aviate.model.CustomerModel;
import com.mitosis.aviate.model.CustomerProductList;
import com.mitosis.aviate.model.service.CustomerMyListResponse;
import com.mitosis.aviate.model.service.ResponseModel;
import com.mitosis.aviate.util.AVErrorMessage;
import com.mitosis.aviate.util.AVMessageProperties;
import com.mitosis.aviate.util.AVMessageStatus;
import com.mitosis.aviate.util.CommonUtil;

@Path("customer")
public class CustomerServices {
	final static Logger log = Logger
			.getLogger(CustomerServices.class);
	CommonDao commondao = new CommonDaoImpl();
	CustomerDao customerDao = new CustomerDaoImpl();
	ResponseModel response = new ResponseModel();

	/*
	 * User signup service
	 */

	@Path("/signup")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject signUp(JSONObject credentials) throws JSONException{
		CustomerModel customerModel=new CustomerModel();
		JSONObject response = new JSONObject();
		CustomerDetailsModel customerDetail= new CustomerDetailsModel();
		try{
			log.info("\n******************************\n "
					+ "Customer signup service initiated with the following input "+credentials.getString("emailId") );
			if(commondao.isValidProperty(credentials, "emailId") 
					&& commondao.isValidProperty(credentials, "password")
					&& commondao.isValidProperty(credentials, "role")){
				customerModel.setEmailId(credentials.getString("emailId"));
				customerModel.setPassword(credentials.getString("password"));
				customerModel.setRole(credentials.getString("role"));
				if(credentials.has("store_id")) {
				    //it has it, do appropriate processing
					customerDetail.setStoreId(Long.parseLong(credentials.getString("store_id")));
				}
				
				if(credentials.has("merchantId")) {
				    //it has it, do appropriate processing
					customerDetail.setMerchantId(Long.parseLong(credentials.getString("merchantId")));
				}
				
				if(customerModel.getRole().equalsIgnoreCase("CUSTOMER"))
				{
				customerDao.save(customerModel);
				}
				else
				{
					customerDao.saveOtherRole(customerModel);
				}
				if(customerModel.getStatus().equals(AVMessageStatus.SUCCESS.getValue())){
					customerDetail.setEmailId(credentials.getString("emailId"));
					customerDetail.setCustomerId(customerModel.getCustomerId());
					customerDao.updateCustomerDetails(customerDetail);
					customerDetail.setStatus(AVMessageStatus.SUCCESS.getValue());
				}else {
					customerDetail.setErrorCode(AVErrorMessage.CUSTOMER_ALREADY_EXITS.getCode());
					customerDetail.setErrorString(AVErrorMessage.CUSTOMER_ALREADY_EXITS.getMessage());
					customerDetail.setStatus(AVMessageStatus.FAILURE.getValue());
				}
			}else {
				customerDetail.setErrorCode(AVErrorMessage.MANDATORY_FIELDS_MISSING.getCode());
				customerDetail.setErrorString(AVErrorMessage.MANDATORY_FIELDS_MISSING.getMessage());
				customerDetail.setStatus(AVMessageStatus.FAILURE.getValue());
			}
		}catch(Exception e){
			log.debug("Exception in user signup service "+ e.getMessage());
			response=CommonUtil.addStatusMessage(e);
		}
		response.put("customerId",customerDetail.getCustomerId());
		response.put("role",customerModel.getRole());
		response.put(AVMessageProperties.STATUS.getValue(),customerDetail.getStatus());
		response.put(AVMessageProperties.CODE.getValue(),customerDetail.getErrorCode());
		response.put(AVMessageProperties.MESSAGE.getValue(),customerDetail.getErrorString());
		log.info("\n************ Response for the customer signup service  ******************\n "
				+ response);
		return response;
	}

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject login(JSONObject credentials) throws JSONException {
		JSONObject response = new JSONObject();
		try
		{
			log.info("\n******************************\n "
					+ "Customer login service initiated with the following input"
					+ " " + credentials.getString("emailId"));
			if (commondao.isValidProperty(credentials, "emailId") 
					&& commondao.isValidProperty(credentials, "password")) {
				String emailId = credentials.getString("emailId");
				String password = credentials.getString("password");
				CustomerModel customerModel =customerDao.checkLogin(emailId, password);
				if(customerModel.getStatus().equals("SUCCESS")){
					CustomerDetailsModel customerDetails = customerDao.getCusotmerByEmail(credentials);
					if(customerDetails != null){
						response.put(AVMessageProperties.STATUS.getValue(),com.mitosis.aviate.util.Constants.SUCCESS);
						response.put("customerId",customerDetails.getCustomerId());
						response.put("role",customerModel.getRole());
						if(customerDetails.getStoreId() != null)
							response.put("storeId",customerDetails.getStoreId());
						if(customerDetails.getMerchantId() != null)
							response.put("merchantId",customerDetails.getMerchantId());
					}else{
						log.error("No detials available for this email id: "+emailId);
						response.put(AVMessageProperties.STATUS.getValue(),AVMessageStatus.FAILURE.getValue());
						response.put(AVMessageProperties.CODE.getValue(),AVErrorMessage.INVALID_ACCOUNT.getCode());
						response.put(AVMessageProperties.MESSAGE.getValue(),AVErrorMessage.INVALID_ACCOUNT.getMessage());
					}
				}else{
					log.error("No detials available for this email id: "+emailId);
					response.put(AVMessageProperties.STATUS.getValue(),AVMessageStatus.FAILURE.getValue());
					response.put(AVMessageProperties.CODE.getValue(),AVErrorMessage.INVALID_LOGIN.getCode());
					response.put(AVMessageProperties.MESSAGE.getValue(),AVErrorMessage.INVALID_LOGIN.getMessage());
				}
			}else {
				response.put(AVMessageProperties.CODE.getValue(),AVErrorMessage.MANDATORY_FIELDS_MISSING.getCode());
				response.put(AVMessageProperties.MESSAGE.getValue(),AVErrorMessage.MANDATORY_FIELDS_MISSING.getMessage());
				response.put(AVMessageProperties.STATUS.getValue(),AVMessageStatus.FAILURE.getValue());
			}
		} catch (Exception e) {
			log.debug("Exception in customer login service "+ e.getMessage());
			response.put(AVMessageProperties.CODE.getValue(),AVErrorMessage.INVALID_LOGIN.getCode());
			response.put(AVMessageProperties.MESSAGE.getValue(),AVErrorMessage.INVALID_LOGIN.getMessage());
			response.put(AVMessageProperties.STATUS.getValue(),AVMessageStatus.FAILURE.getValue());
		}
		log.info("\n*************   Response for the customer login service   *****************\n "
				+response);
		return response;
	}


	@Path("/forgetpassword")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel forGetPassword(JSONObject reqObj){
		try
		{
			log.info("\n******************************\n"
					+ " Customer save My List service Initiated ");
			String to = reqObj.getString("emailId");
			CustomerModel customerModel = customerDao.getCustomerPass(to);
			if(customerModel == null){
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorString(reqObj.getString("emailId")+"This is not valid user Email-Id");
			}
			String subject = "Password Recovery";
			String body = "Welcome! Your password is : "+customerModel.getPassword();
			boolean flag=CommonUtil.sendMail(to, subject, body);
			if(flag)
				response.setStatus(com.mitosis.aviate.util.Constants.SUCCESS);
			else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorString(reqObj.getString("emailId")+"This is not valid Email-Id");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.setStatus(AVMessageStatus.FAILURE.getValue());
			response.setErrorString("User Email-Id is incorrect");
		}
		log.info("\n******************************\n"
				+ " Response of the MyList services ");
		return response;
	}

	@Path("/deviceregister")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel deviceIdUpdate(CustomerDetailsModel customerDetailsModel){
		try
		{
			log.info("\n******************************\n"
					+ " Customer save My List service Initiated ");
			boolean flag = customerDao.updateDeviceDetails(customerDetailsModel);
			if(flag)
				response.setStatus(com.mitosis.aviate.util.Constants.SUCCESS);
			else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorCode("");
				response.setErrorString("");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************\n"
				+ " Response of the MyList services ");
		return response;
	}

	@Path("/addmylist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel saveMyList(CustomerProductList customerProduct){
		try
		{
			log.info("\n******************************\n"
					+ " Customer save My List service Initiated ");
			customerDao.addMyList(customerProduct);

			if(customerProduct.getStatus().equals("SUCCESS")){
				response.setStatus(com.mitosis.aviate.util.Constants.SUCCESS);

			}else if(customerProduct.getStatus().equals("FAILURE")){
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorString(com.mitosis.aviate.util.Constants.E0007Error);
				response.setErrorCode(com.mitosis.aviate.util.Constants.E0007);
			}else{

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************\n"
				+ " Response of the MyList services ");
		return response;
	}

	@Path("/removemylist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel removeMyList(CustomerProductList customerProduct){
		try
		{
			log.info("\n******************************\n"
					+ " Customer Remove My List service Initiated ");
			//customerDao.addMyList(customerProduct);
			boolean flag = customerDao.removeMyList(customerProduct);
			if(flag)
				response.setStatus(com.mitosis.aviate.util.Constants.SUCCESS);
			else{
				response.setStatus("FAILURE");
				response.setErrorString("");
				response.setErrorCode("");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************\n"
				+ " Response of the Remove MyList services ");
		return response;
	}

	@Path("/getmylist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerMyListResponse getMyList(JSONObject requestJson){
		List<CustomerProductList> myList = new ArrayList<CustomerProductList>();
		CustomerMyListResponse customerMylistResponse = new CustomerMyListResponse();
		try
		{
			log.info("\n******************************\n"
					+ " Initialized get customer my list service");
			myList = customerDao.getMyList(Long.parseLong(requestJson.getString("customerId")),
					Long.parseLong(requestJson.getString("storeId")));
			if(myList.isEmpty()){
				customerMylistResponse.setStatus(AVMessageStatus.FAILURE.getValue());
				customerMylistResponse.setErrorString(com.mitosis.aviate.util.Constants.E0008Error);
				customerMylistResponse.setErrorCode(com.mitosis.aviate.util.Constants.E0008);
			}else{
				customerMylistResponse.setCustomerMyList(myList);
				customerMylistResponse.setStatus(com.mitosis.aviate.util.Constants.SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************\n"
				+ " Response of the get MyList services ");
		return customerMylistResponse;
	}
}

