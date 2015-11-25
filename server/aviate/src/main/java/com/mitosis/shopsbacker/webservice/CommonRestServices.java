package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.common.service.CommonService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.model.Country;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.PasswordResetRequest;
import com.mitosis.shopsbacker.model.State;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.responsevo.CountryResponse;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.CountryVo;
import com.mitosis.shopsbacker.vo.common.PasswordResetRequestVo;
import com.mitosis.shopsbacker.vo.common.StateVo;

@Path("/common")
@Controller("commonRestServices")
public class CommonRestServices<T> {
	Logger log = Logger.getLogger(CommonRestServices.class);

	@Autowired
	AddressService<T> addressService;
	
	@Autowired
	CommonService<T> commonService;
	
	@Autowired
	UserService<T> userService;
	
	@Autowired
	CustomerService<T> customerService;

	public AddressService<T> getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService<T> addressService) {
		this.addressService = addressService;
	}

	@Path("/country")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CountryResponse getCountry() {
		CountryResponse countryResponse = new CountryResponse();
		try {
			List<Country> countries = getAddressService().getCountry();
			List<CountryVo> countryList = new ArrayList<CountryVo>();
			for (Country country : countries) {
				CountryVo countryVo = new CountryVo();
				countryVo.setCode(country.getCode());
				countryVo.setCountryId(country.getCountryId());
				countryVo.setCurrencyCode(country.getCurrencyCode());
				countryVo.setCurrencyName(country.getCurrencyName());
				countryVo.setName(country.getName());
				//List<State> states = getAddressService().getState(country.getCountryId());
				List<StateVo> stateList = new ArrayList<StateVo>();
				for (State state : country.getStates()) {
					StateVo stateVo = new StateVo();
					stateVo.setName(state.getName());
					stateVo.setStateId(state.getStateId());
					stateList.add(stateVo);
					countryVo.setStates(stateList);
				}
				countryList.add(countryVo);
			}
			countryResponse.setCountries(countryList);
		} catch (Exception e) {
			e.printStackTrace();
			countryResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			countryResponse.setErrorString(e.getMessage());
		}
		return countryResponse;

	}
	
	/*@Path("/state")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CountryResponse getState(Country country) {
		CountryResponse countryResponse = new CountryResponse();
		try {
			List<State> states = getAddressService().getState(country.getCountryId());
				List<StateVo> stateList = new ArrayList<StateVo>();
				for (State state : country.getStates()) {
					StateVo stateVo = new StateVo();
					stateVo.setName(state.getName());
					stateVo.setStateId(state.getStateId());
					stateList.add(stateVo);
					countryVo.setStates(stateList);
				}
			countryResponse.setCountries(countryList);
		} catch (Exception e) {
			e.printStackTrace();
			countryResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			countryResponse.setErrorString(e.getMessage());
		}
		return countryResponse;

	}*/
	
	@Path("/forgetpassword")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel forgetPassword(PasswordResetRequestVo passwordResetRequestVo) {
		ResponseModel response = new ResponseModel();
		try {
			if (passwordResetRequestVo != null) {
				String userType = passwordResetRequestVo.getUserType();
				UserVo userVo = passwordResetRequestVo.getUser();
				if(userVo != null){
					String userId = null;
					String emailId = null;
					if(userType.equals("admin")){
						User user = userService.getUserByUserName(userVo.getUserName());
						if (user != null) {
							userId = user.getUserId();
							emailId = user.getEmailid();
						}else {
							response
							.setErrorCode(SBErrorMessage.INVALID_USER_ACCOUNT
									.getCode());
							response
									.setErrorString(SBErrorMessage.INVALID_USER_ACCOUNT
											.getMessage());
							response.setStatus(SBMessageStatus.FAILURE
									.getValue());

						}
					}else if(userType.equals("customer")){
						Customer customer = customerService.getCustomerInfoByEmail(userVo.getEmailid());
						if(customer!=null){
							userId = customer.getCustomerId();
							emailId = customer.getEmail();
						}else{
							response
							.setErrorCode(SBErrorMessage.INVALID_CUSTOMER_ACCOUNT
									.getCode());
							response
									.setErrorString(SBErrorMessage.INVALID_CUSTOMER_ACCOUNT
											.getMessage());
							response.setStatus(SBMessageStatus.FAILURE
									.getValue());
						}
					}
					
					if(emailId!=null){
							String subject = "Password Reset Request - ShopsBacker";
							PasswordResetRequest passwordResetRequest = commonService.savePasswordResetRequest(userId,userType);
							String body = passwordResetRequestVo.getPasswordResetUrl()+passwordResetRequest.getTokenId();
						    boolean flag = CommonUtil.sendMail(emailId, subject, body);
							if(flag){
								response.setStatus(SBMessageStatus.SUCCESS
										.getValue());
							}else{
								response
								.setErrorCode(SBErrorMessage.PROBLEM_IN_SENDING_EMAIL
										.getCode());
						response
								.setErrorString(SBErrorMessage.PROBLEM_IN_SENDING_EMAIL
										.getMessage());
						response.setStatus(SBMessageStatus.FAILURE
								.getValue());
							}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.toString());
			return response;
		}
		return response;
	}
	
	@Path("/verifytoken")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel verifyTokenId(PasswordResetRequestVo passwordResetRequestVo) {
		ResponseModel response = new ResponseModel();
		try {
			if (passwordResetRequestVo != null) {
				PasswordResetRequest passwordResetRequest = commonService.getPasswordResetRequestByTokenId(passwordResetRequestVo.getTokenId());
				if (passwordResetRequest != null) {
						response.setStatus(SBMessageStatus.SUCCESS
								.getValue());
				}else{
						response
						.setErrorCode(SBErrorMessage.INVALID_TOKEN
								.getCode());
				response
						.setErrorString(SBErrorMessage.INVALID_TOKEN
								.getMessage());
				response.setStatus(SBMessageStatus.FAILURE
						.getValue());
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.toString());
			return response;
		}
		return response;
	}
	
	@Path("/resetpassword")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel resetPassword(PasswordResetRequestVo passwordResetRequestVo) {
		ResponseModel response = new ResponseModel();
		try {
			if (passwordResetRequestVo != null && passwordResetRequestVo.getUser()!=null) {
				PasswordResetRequest passwordResetRequest = commonService.getPasswordResetRequestByTokenId(passwordResetRequestVo.getTokenId());
				UserVo userVo = passwordResetRequestVo.getUser();
				if (passwordResetRequest != null) {
						String userType = passwordResetRequest.getUserType();
						String userId = passwordResetRequest.getUserId();
						String encPassword = CommonUtil.passwordEncoder(userVo.getPassword());
						if(userType.equals("admin")){
						    User user = userService.getUser(userId);
						    if(user!=null){
						    	user.setPassword(encPassword);
						    	userService.updateUser(user);
						    }
						}else if(userType.equals("customer")){
							Customer customer = customerService.getCustomerInfoById(userId);
							if(customer!=null){
								customer.setPassword(encPassword);
						    	customerService.updateCustomer(customer);
							}
						}
						commonService.deletePasswordResetRequest(passwordResetRequest);
				    	response.setStatus(SBMessageStatus.SUCCESS
				    			.getValue());
				}else{
						response
						.setErrorCode(SBErrorMessage.INVALID_TOKEN
								.getCode());
						response
								.setErrorString(SBErrorMessage.INVALID_TOKEN
										.getMessage());
						response.setStatus(SBMessageStatus.FAILURE
						.getValue());
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.toString());
			return response;
		}
		return response;
	}
}
