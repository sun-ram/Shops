package com.mitosis.shopsbacker.admin.webservice;

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
import org.springframework.beans.factory.annotation.Autowired;

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
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;


@Path("merchant")
public class MerchantRestServices<T>  {
	final static Logger log = Logger
			.getLogger(MerchantRestServices.class.getName());
		
	@Autowired
	MerchantService<T> merchantService;
	
	@Autowired
	CustomerService<T>  customerService;
	
	
	public CustomerService<T> getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService<T> customerService) {
		this.customerService = customerService;
	}

	public MerchantService<T> getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService<T> merchantService) {
		this.merchantService = merchantService;
	}
	
	CommonDao commonDao = new CommonDaoImpl();
	ResponseModel response = new ResponseModel();
	/*CustomerDao customerDao = new CustomerDaoImpl();
	MerchantDAO merchantDAO = new MerchantDAOImpl();*/
	Properties properties = new Properties();
	
	@Path("/addmerchant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addMerchantDetails(MerchantVo merchantVo){
		try{
			boolean flag=false;
			MerchantVo merchant = new MerchantVo();
			
			/*Already Image is there and user without change the merchan
			 * t logo- Condition*/
			/*if(merchantVo.getMerchantImage()==null && merchantVo.getProductImages().!=null){
				ProductImages productImage = new ProductImages();
				productImage=merchantVo.getProductImages();
				merchantVo.setProductImages(productImage);
			}*/
			
			/*Already Image is there and user change the merchant logo- Condition*/
			 if(merchantVo.getLogo()!=null && ){
				String imageLocation,imageName,imageString,imageUrl;
				ProductImages productImageOldId=new ProductImages();
				ProductImages productImage = new ProductImages();
				productImageOldId=merchantVo.getProductImages();
				byte[] byeImage;
				properties.load(getClass().getResourceAsStream(
							"/properties/serverurl.properties"));
				 imageLocation = properties.getProperty("imageUrl");
				 imageName=UUID.randomUUID().toString().replace("-", "");
				 imageString=merchantVo.getMerchantImage();
				 byeImage = Base64.decodeBase64(imageString);
				 productImage.setImage(byeImage);
				 productImage.setImageName(imageName);
				 productImage.setImageType(merchantVo.getMerchantImageType());
				 imageUrl=imageLocation+imageName;
				 productImage.setImageUrl(imageUrl);
				 productImage.setImageId(productImageOldId.getImageId());
				 merchantVo.setProductImages(productImage);
			}
			/*There is no merchant logo and user change logo- condition*/
			else if(merchantVo.getMerchantImage()!=null && merchantVo.getProductImages()==null){
				ProductImages productImage = new ProductImages();
				String imageLocation,imageName,imageString,imageUrl;
				byte[] byeImage;
				properties.load(getClass().getResourceAsStream(
							"/properties/serverurl.properties"));
				 imageLocation = properties.getProperty("imageUrl");
				 imageName=UUID.randomUUID().toString().replace("-", "");
				 imageString=merchantVo.getMerchantImage();
				 byeImage = Base64.decodeBase64(imageString);
				 productImage.setImage(byeImage);
				 productImage.setImageName(imageName);
				 productImage.setImageType(merchantVo.getMerchantImageType());
				 imageUrl=imageLocation+imageName;
				 productImage.setImageUrl(imageUrl);
				 merchantVo.setProductImages(productImage);
			}
			log.info("merchant - "+merchantVo);
			String full_address=merchantVo.getAddress()+","+merchantVo.getAddress1()+","+merchantVo.getCity()+","+merchantVo.getState()+","+merchantVo.getCountry()+","+merchantVo.getPostalCode();
			JsonNode location = commonDao.getLatLong(full_address);
			if(location==null){

				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorCode(AVErrorMessage.ADDRESS_NOT_VALID.getCode());
				response.setErrorString(AVErrorMessage.ADDRESS_NOT_VALID.getMessage());
				return response;

			}

			JsonNode loc = location.findValue("lat".toString());
			merchantVo.setLatitude(loc.toString());
			loc = location.findValue("lng".toString());
			merchantVo.setLongtitude(loc.toString());
			/*---------------SignUp Service Calling---------------------*/

			List<CustomerModel> toCheckSignupData=new ArrayList<CustomerModel>();
			List<CustomerModel> toCheckSignupDataNotCustomer=new ArrayList<CustomerModel>();
			toCheckSignupDataNotCustomer=customerDao.getListByQuery2(merchantVo.getUserName(),"CUSTOMER");
			toCheckSignupData=customerDao.getListByQuery(merchantVo.getUserName(),"CUSTOMER");
			if(toCheckSignupData.isEmpty() && toCheckSignupDataNotCustomer.isEmpty()){
				flag = merchantDAO.addMerchant(merchantVo);
				CustomerServices signup=new CustomerServices();
				JSONObject credentials=new JSONObject();
				credentials.put("merchantId", merchantVo.getMerchantId());
				credentials.put("emailId",merchantVo.getUserName());
				credentials.put("password", merchantVo.getPassword());
				credentials.put("role", "MERCHANTADMIN");
				signup.signUp(credentials);
			}
			else{
				if(!toCheckSignupData.isEmpty() && toCheckSignupDataNotCustomer.isEmpty()){
					CustomerModel cm=new CustomerModel();
					CustomerDetailsModel customerDetailModel=new CustomerDetailsModel();
					flag = merchantDAO.addMerchant(merchantVo);
					cm=toCheckSignupData.get(0);
					customerDetailModel=toCheckSignupData.get(0).getCustomerDetails();
					customerDetailModel.setMerchantId((long)merchantVo.getMerchantId());
					cm.setRole("MERCHANTADMIN");
					customerDao.edit(cm);
					customerDao.updateCustomerDetails(customerDetailModel);
				}
				else{
					if(toCheckSignupDataNotCustomer.isEmpty()){
						if(merchantVo.getMerchantId()==0){
							flag = merchantDAO.addMerchant(merchantVo);
							CustomerServices signup=new CustomerServices();
							JSONObject credentials=new JSONObject();
							credentials.put("emailId",merchantVo.getUserName());
							credentials.put("password", merchantVo.getPassword());
							credentials.put("role", "MERCHANTADMIN");
							signup.signUp(credentials);
						}
						else{
							flag = merchantDAO.addMerchant(merchantVo);
						}
					}
					else{
						if(merchantVo.getMerchantId()!=0){
							flag = merchantDAO.addMerchant(merchantVo);
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
		
		List<MerchantVo> merchantlist 
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
