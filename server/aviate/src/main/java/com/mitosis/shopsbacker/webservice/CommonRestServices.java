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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.common.service.CommonService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.model.Area;
import com.mitosis.shopsbacker.model.City;
import com.mitosis.shopsbacker.model.Country;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.PasswordResetRequest;
import com.mitosis.shopsbacker.model.State;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.responsevo.AreaResponseVo;
import com.mitosis.shopsbacker.responsevo.CountryResponse;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.AreaVo;
import com.mitosis.shopsbacker.vo.common.CityVo;
import com.mitosis.shopsbacker.vo.common.CountryVo;
import com.mitosis.shopsbacker.vo.common.PasswordResetRequestVo;
import com.mitosis.shopsbacker.vo.common.StateVo;

/**
 * @author prabakaran
 *
 * @param <T>
 * 
 *            Reviewed by Sundaram 27/11/2015
 */
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

	CountryVo countryVo = null;
	StateVo stateVo = null;
	CityVo cityVo = null;
	ResponseModel response = null;
	UserVo userVo = null;
	User user = null;
	Customer customer = null;
	PasswordResetRequest passwordResetRequest = null;

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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CountryResponse getCountry() {
		CountryResponse countryResponse = new CountryResponse();
		try {
			List<Country> countries = getAddressService().getCountry();
			List<CountryVo> countryList = new ArrayList<CountryVo>();
			for (Country country : countries) {
				countryVo = new CountryVo();
				countryVo.setCode(country.getCode());
				countryVo.setCountryId(country.getCountryId());
				countryVo.setCurrencyCode(country.getCurrencyCode());
				countryVo.setCurrencyName(country.getCurrencyName());
				countryVo.setName(country.getName());
				// List<State> states =
				// getAddressService().getState(country.getCountryId());
				List<StateVo> stateList = new ArrayList<StateVo>();
				for (State state : country.getStates()) {
					stateVo = new StateVo();
					stateVo.setName(state.getName());
					stateVo.setStateId(state.getStateId());
					stateList.add(stateVo);
					countryVo.setStates(stateList);
					List<CityVo> cityList = new ArrayList<CityVo>();
					for (City city : state.getCities()) {
						cityVo = new CityVo();
						cityVo.setCityId(city.getCityId());
						cityVo.setName(city.getName());
						cityList.add(cityVo);
					}
					stateVo.setCity(cityList);
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

	/*
	 * @Path("/state")
	 * 
	 * @POST
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public CountryResponse
	 * getState(Country country) { CountryResponse countryResponse = new
	 * CountryResponse(); try { List<State> states =
	 * getAddressService().getState(country.getCountryId()); List<StateVo>
	 * stateList = new ArrayList<StateVo>(); for (State state :
	 * country.getStates()) { StateVo stateVo = new StateVo();
	 * stateVo.setName(state.getName()); stateVo.setStateId(state.getStateId());
	 * stateList.add(stateVo); countryVo.setStates(stateList); }
	 * countryResponse.setCountries(countryList); } catch (Exception e) {
	 * e.printStackTrace();
	 * countryResponse.setStatus(SBMessageStatus.FAILURE.getValue());
	 * countryResponse.setErrorString(e.getMessage()); } return countryResponse;
	 * 
	 * }
	 */

	@Path("/forgetpassword")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel forgetPassword(
			PasswordResetRequestVo passwordResetRequestVo) {
		response = new ResponseModel();
		try {
			if (passwordResetRequestVo != null) {
				String userType = passwordResetRequestVo.getUserType();
				userVo = passwordResetRequestVo.getUser();
				if (userVo != null) {
					String userId = null;
					String emailId = null;
					if (userType.equals("admin")) {
						user = userService.getUserByUserName(
								userVo.getUserName(), true);
						if (user != null) {
							userId = user.getUserId();
							emailId = user.getEmailid();
						} else {
							response.setErrorCode(SBErrorMessage.INVALID_USER_ACCOUNT
									.getCode());
							response.setErrorString(SBErrorMessage.INVALID_USER_ACCOUNT
									.getMessage());
							response.setStatus(SBMessageStatus.FAILURE
									.getValue());

						}
					} else if (userType.equals("customer")) {
						customer = customerService
								.getCustomerInfoByEmail(userVo.getEmailid());
						if (customer != null) {
							userId = customer.getCustomerId();
							emailId = customer.getEmail();
						} else {
							response.setErrorCode(SBErrorMessage.INVALID_CUSTOMER_ACCOUNT
									.getCode());
							response.setErrorString(SBErrorMessage.INVALID_CUSTOMER_ACCOUNT
									.getMessage());
							response.setStatus(SBMessageStatus.FAILURE
									.getValue());
						}
					}

					if (emailId != null) {
						String subject = "Password Reset Request - ShopsBacker";
						passwordResetRequest = commonService
								.savePasswordResetRequest(userId, userType);
						
						String body ="Web users, please <a href=\""+passwordResetRequestVo
								.getPasswordResetUrl()
								+ passwordResetRequest.getTokenId()+"\">click here</a> to reset your password.";
						String androidResetLink = "intent:#Intent;action=com.shopsbacker.android.MY_CUSTOM_ACTION;S.some_variable="+passwordResetRequest.getTokenId()+";end";
						body += "<br><br>Android users, please <a href=\"intent:#Intent;action=com.shopsbacker.android.MY_CUSTOM_ACTION;S.some_variable="+passwordResetRequest.getTokenId()+";end\">click here</a> to load android app to reset password. If not able to click copy paste this url to your browser " + androidResetLink;
						//body += "<br>intent:#Intent;action=com.shopsbacker.android.MY_CUSTOM_ACTION;S.some_variable="+passwordResetRequest.getTokenId()+";end";
						
						String iosResetLink = "shopsbacker://tokenid="+passwordResetRequest.getTokenId();
						body += "<br><br>Iphone users, please <a title=\"Verify\" href=\""+ iosResetLink + "\">click here</a> to load IOS app to reset password. If not able to click copy paste this url to your browser " + iosResetLink;
						
						//body += "<br><br>Test, <a href=\"testing.html\">Click Here</a>";
						
						//body += "<br>shopsbacker://tokenid=" + passwordResetRequest.getTokenId();
						boolean flag = CommonUtil.sendMail(emailId, subject,
								body);
						if (flag) {
							response.setStatus(SBMessageStatus.SUCCESS
									.getValue());
						} else {
							response.setErrorCode(SBErrorMessage.PROBLEM_IN_SENDING_EMAIL
									.getCode());
							response.setErrorString(SBErrorMessage.PROBLEM_IN_SENDING_EMAIL
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel verifyTokenId(
			PasswordResetRequestVo passwordResetRequestVo) {
		response = new ResponseModel();
		try {
			if (passwordResetRequestVo != null) {
				passwordResetRequest = commonService
						.getPasswordResetRequestByTokenId(passwordResetRequestVo
								.getTokenId());
				if (passwordResetRequest != null) {
					response.setStatus(SBMessageStatus.SUCCESS.getValue());
				} else {
					response.setErrorCode(SBErrorMessage.INVALID_TOKEN
							.getCode());
					response.setErrorString(SBErrorMessage.INVALID_TOKEN
							.getMessage());
					response.setStatus(SBMessageStatus.FAILURE.getValue());
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel resetPassword(
			PasswordResetRequestVo passwordResetRequestVo) {
		response = new ResponseModel();
		try {
			if (passwordResetRequestVo != null
					&& passwordResetRequestVo.getUser() != null) {
				passwordResetRequest = commonService
						.getPasswordResetRequestByTokenId(passwordResetRequestVo
								.getTokenId());
				userVo = passwordResetRequestVo.getUser();
				if (passwordResetRequest != null) {
					String userType = passwordResetRequest.getUserType();
					String userId = passwordResetRequest.getUserId();
					String encPassword = CommonUtil.passwordEncoder(userVo
							.getPassword());
					if (userType.equals("admin")) {
						user = userService.getUser(userId);
						if (user != null) {
							user.setPassword(encPassword);
							userService.updateUser(user);
						}
					} else if (userType.equals("customer")) {
						customer = customerService.getCustomerInfoById(userId);
						if (customer != null) {
							customer.setPassword(encPassword);
							customerService.updateCustomer(customer);
						}
					}
					commonService
							.deletePasswordResetRequest(passwordResetRequest);
					response.setStatus(SBMessageStatus.SUCCESS.getValue());
				} else {
					response.setErrorCode(SBErrorMessage.INVALID_TOKEN
							.getCode());
					response.setErrorString(SBErrorMessage.INVALID_TOKEN
							.getMessage());
					response.setStatus(SBMessageStatus.FAILURE.getValue());
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

	@Path("/city")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CountryResponse getCity() {
		CountryResponse countryResponse = new CountryResponse();
		List<City> city = new ArrayList<City>();
		try {
			city = getAddressService().getCities();
		} catch (Exception e) {
			e.printStackTrace();
			countryResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			countryResponse.setErrorString(e.getMessage());
		}
		return countryResponse;

	}

	@Path("/areas")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getAreas(AreaVo areaVo) {
		AreaResponseVo areaResponseVo = new AreaResponseVo();
		String responseStr = null;
		try {
			String cityId = null;
			if (areaVo.getCity() != null
					&& areaVo.getCity().getCityId() != null) {
				cityId = areaVo.getCity().getCityId();
			} else if (areaVo.getCityId() != null) {
				cityId = areaVo.getCityId();
			} else {
				areaResponseVo.setErrorString(SBErrorMessage.CITY_ID_NOT_AVAILABLE.getMessage());
				areaResponseVo.setErrorCode(SBErrorMessage.CITY_ID_NOT_AVAILABLE.getCode());
				areaResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
				try {
					responseStr = CommonUtil.getObjectMapper(areaResponseVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return responseStr;
			}

			City city = addressService.getCity(cityId);
			if (city == null) {
				areaResponseVo.setErrorString(SBErrorMessage.CITY_ID_NOT_VALID.getMessage());
				areaResponseVo.setErrorCode(SBErrorMessage.CITY_ID_NOT_VALID.getCode());
				areaResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
				try {
					responseStr = CommonUtil.getObjectMapper(areaResponseVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return responseStr;
			}
			List<Area> areas = city.getAreas();
			List<AreaVo> areaVos = new ArrayList<AreaVo>();
			for (Area area : areas) {
				AreaVo areavo = new AreaVo();
				areavo.setAreaId(area.getAreaId());
				areavo.setName(area.getName());
				CityVo cityVo = new CityVo();
				cityVo.setCityId(city.getCityId());
				cityVo.setName(city.getName());
				areavo.setCity(cityVo);
				areaVos.add(areavo);
			}
			areaResponseVo.setAreas(areaVos);
		} catch (Exception e) {
			e.printStackTrace();
			areaResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
			areaResponseVo.setErrorString(e.getMessage());
		}
		try {
			responseStr = CommonUtil.getObjectMapper(areaResponseVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseStr;

	}
}
