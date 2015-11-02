package com.mitosis.aviate.webservice.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.CustomerDao;
import com.mitosis.aviate.dao.MerchantDAO;
import com.mitosis.aviate.dao.daoimpl.CommonDaoImpl;
import com.mitosis.aviate.dao.daoimpl.CustomerDaoImpl;
import com.mitosis.aviate.dao.daoimpl.MerchantDAOImpl;
import com.mitosis.aviate.model.CustomerDetailsModel;
import com.mitosis.aviate.model.CustomerModel;
import com.mitosis.aviate.model.MerchantDetails;
import com.mitosis.aviate.model.ProductImages;
import com.mitosis.aviate.model.service.MerchantResponseModel;
import com.mitosis.aviate.model.service.ResponseModel;
import com.mitosis.aviate.util.AVErrorMessage;
import com.mitosis.aviate.util.AVMessageStatus;
import com.mitosis.aviate.util.CommonUtil;
import com.mitosis.aviate.webservice.ecommerce.CustomerServices;


@Path("merchant")
public class MerchantServices  {
	final static Logger log = Logger
			.getLogger(MerchantServices.class.getName());
	CommonDao commonDao = new CommonDaoImpl();
	ResponseModel response = new ResponseModel();
	CustomerDao customerDao = new CustomerDaoImpl();
	MerchantDAO merchantDAO = new MerchantDAOImpl();
	Properties properties = new Properties();
	
	@Path("/addmerchant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addMerchantDetails(MerchantDetails merchantDetails){
		try{
			boolean flag=false;
			MerchantDetails merchant = new MerchantDetails();
			/*Already Image is there and user without change the merchant logo- Condition*/
			if(merchantDetails.getMerchantImage()==null && merchantDetails.getProductImages()!=null){
				ProductImages productImage = new ProductImages();
				productImage=merchantDetails.getProductImages();
				merchantDetails.setProductImages(productImage);
			}
			/*Already Image is there and user change the merchant logo- Condition*/
			else if(merchantDetails.getMerchantImage()!=null && merchantDetails.getProductImages()!=null){
				String imageLocation,imageName,imageString,imageUrl;
				ProductImages productImageOldId=new ProductImages();
				ProductImages productImage = new ProductImages();
				productImageOldId=merchantDetails.getProductImages();
				byte[] byeImage;
				properties.load(getClass().getResourceAsStream(
							"/properties/serverurl.properties"));
				 imageLocation = properties.getProperty("imageUrl");
				 imageName=UUID.randomUUID().toString().replace("-", "");
				 imageString=merchantDetails.getMerchantImage();
				 byeImage = Base64.decodeBase64(imageString);
				 productImage.setImage(byeImage);
				 productImage.setImageName(imageName);
				 productImage.setImageType(merchantDetails.getMerchantImageType());
				 imageUrl=imageLocation+imageName;
				 productImage.setImageUrl(imageUrl);
				 productImage.setImageId(productImageOldId.getImageId());
				 merchantDetails.setProductImages(productImage);
			}
			/*There is no merchant logo and user change logo- condition*/
			else if(merchantDetails.getMerchantImage()!=null && merchantDetails.getProductImages()==null){
				ProductImages productImage = new ProductImages();
				String imageLocation,imageName,imageString,imageUrl;
				byte[] byeImage;
				properties.load(getClass().getResourceAsStream(
							"/properties/serverurl.properties"));
				 imageLocation = properties.getProperty("imageUrl");
				 imageName=UUID.randomUUID().toString().replace("-", "");
				 imageString=merchantDetails.getMerchantImage();
				 byeImage = Base64.decodeBase64(imageString);
				 productImage.setImage(byeImage);
				 productImage.setImageName(imageName);
				 productImage.setImageType(merchantDetails.getMerchantImageType());
				 imageUrl=imageLocation+imageName;
				 productImage.setImageUrl(imageUrl);
				 merchantDetails.setProductImages(productImage);
			}
			log.info("merchant - "+merchant);
			String full_address=merchantDetails.getAddress()+","+merchantDetails.getAddress1()+","+merchantDetails.getCity()+","+merchantDetails.getState()+","+merchantDetails.getCountry()+","+merchantDetails.getPostalCode();
			JsonNode location = commonDao.getLatLong(full_address);
			if(location==null){

				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorCode(AVErrorMessage.ADDRESS_NOT_VALID.getCode());
				response.setErrorString(AVErrorMessage.ADDRESS_NOT_VALID.getMessage());
				return response;

			}

			JsonNode loc = location.findValue("lat".toString());
			merchantDetails.setLatitude(loc.toString());
			loc = location.findValue("lng".toString());
			merchantDetails.setLongtitude(loc.toString());
			/*---------------SignUp Service Calling---------------------*/

			List<CustomerModel> toCheckSignupData=new ArrayList<CustomerModel>();
			List<CustomerModel> toCheckSignupDataNotCustomer=new ArrayList<CustomerModel>();
			toCheckSignupDataNotCustomer=customerDao.getListByQuery2(merchantDetails.getUserName(),"CUSTOMER");
			toCheckSignupData=customerDao.getListByQuery(merchantDetails.getUserName(),"CUSTOMER");
			if(toCheckSignupData.isEmpty() && toCheckSignupDataNotCustomer.isEmpty()){
				flag = merchantDAO.addMerchant(merchantDetails);
				CustomerServices signup=new CustomerServices();
				JSONObject credentials=new JSONObject();
				credentials.put("merchantId", merchantDetails.getMerchantId());
				credentials.put("emailId",merchantDetails.getUserName());
				credentials.put("password", merchantDetails.getPassword());
				credentials.put("role", "MERCHANTADMIN");
				signup.signUp(credentials);
			}
			else{
				if(!toCheckSignupData.isEmpty() && toCheckSignupDataNotCustomer.isEmpty()){
					CustomerModel cm=new CustomerModel();
					CustomerDetailsModel customerDetailModel=new CustomerDetailsModel();
					flag = merchantDAO.addMerchant(merchantDetails);
					cm=toCheckSignupData.get(0);
					customerDetailModel=toCheckSignupData.get(0).getCustomerDetails();
					customerDetailModel.setMerchantId((long)merchantDetails.getMerchantId());
					cm.setRole("MERCHANTADMIN");
					customerDao.edit(cm);
					customerDao.updateCustomerDetails(customerDetailModel);
				}
				else{
					if(toCheckSignupDataNotCustomer.isEmpty()){
						if(merchantDetails.getMerchantId()==0){
							flag = merchantDAO.addMerchant(merchantDetails);
							CustomerServices signup=new CustomerServices();
							JSONObject credentials=new JSONObject();
							credentials.put("emailId",merchantDetails.getUserName());
							credentials.put("password", merchantDetails.getPassword());
							credentials.put("role", "MERCHANTADMIN");
							signup.signUp(credentials);
						}
						else{
							flag = merchantDAO.addMerchant(merchantDetails);
						}
					}
					else{
						if(merchantDetails.getMerchantId()!=0){
							flag = merchantDAO.addMerchant(merchantDetails);
						}
					}
				}
			}
			/*End*/

			if(flag){
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
			}else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorCode(AVErrorMessage.EMAILID_ALREADY_EXIT.getCode());
				response.setErrorString(AVErrorMessage.EMAILID_ALREADY_EXIT.getMessage());
				response.setStatus(com.mitosis.aviate.util.Constants.FAILURE);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			response=CommonUtil.addStatusMessage(e,response);
		}
		return response;
	}

	@Path("/getmerchant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MerchantResponseModel getMerchantList(){
		MerchantResponseModel merchantList = new MerchantResponseModel();
		try{
			List<MerchantDetails> merchantDetails = merchantDAO.getMerchantList();
			merchantList.setMerchantList(merchantDetails);
			merchantList.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			response=CommonUtil.addStatusMessage(e,response);
		}
		return merchantList;
	}
	@Path("/deletemerchant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel deleteMerchant(JSONObject requestObj)
	{
		try{
			log.info("\n******************************************\n"
					+ "Initializing the removce store service");
			merchantDAO.removeMerchant(Long.parseLong(requestObj.getString("merchantId")));
			response.setStatus(AVMessageStatus.SUCCESS.getValue());

		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			response=CommonUtil.addStatusMessage(e,response);
		}
		log.info("\n******************************************\n"
				+ "Response of the remove store service");

		return response;
	}


}
