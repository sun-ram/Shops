package com.mitosis.shopsbacker.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.responsevo.AddressResponseVo;
import com.mitosis.shopsbacker.responsevo.CustomerLoginResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.common.AddressVo;
import com.mitosis.shopsbacker.vo.customer.CustomerVo;

/**
 * @author Fayaz
 *
 * @param <T>
 * 
 *            Reviewed by Sundaram 27/11/2015
 */

@Path("customer")
@Controller("customerRestService")
public class CustomerRestService<T> {

	@Autowired
	CustomerService<T> customerService;

	@Autowired
	AddressService<T> addressService;

	ResponseModel response = null;
	CustomerLoginResponseVo customerLoginResponseVo = null;
	CustomerVo customerDetails, customerVoSet = null;
	Customer customer, customerEmailChecking, customerPhoneNoChecking,
			newCustomer = null;
	Address address = null;
	AddressResponseVo addressResponseList = null;
	CustomerVo customerVo = null;

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String userLogin(CustomerVo customerVo) throws Exception {
		boolean flag = false;
		customerLoginResponseVo = new CustomerLoginResponseVo();
		customerDetails = new CustomerVo();
		if (customerVo != null) {
			customer = customerService.getCustomerInfoByEmail(customerVo
					.getEmail());
			if (customer != null) {
				flag = CommonUtil.passwordVerification(
						customerVo.getPassword(), customer.getPassword());
			}
			if (customer != null && flag) {
				customerDetails = customerService.setCustomerVo(customer);
				customerLoginResponseVo.setCustomer(customerDetails);
				customerLoginResponseVo.setStatus(SBMessageStatus.SUCCESS
						.getValue());
			} else {
				customerLoginResponseVo
						.setErrorCode(SBErrorMessage.INVALID_USERNAME.getCode());
				customerLoginResponseVo
						.setErrorString(SBErrorMessage.INVALID_USERNAME
								.getMessage());
				customerLoginResponseVo.setStatus(SBMessageStatus.FAILURE
						.getValue());
				return CommonUtil.getObjectMapper(customerLoginResponseVo);
			}
		}
		return CommonUtil.getObjectMapper(customerLoginResponseVo);
	}

	@Path("/signup")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String userSignUp(CustomerVo customerVo) throws Exception {
		customerLoginResponseVo = new CustomerLoginResponseVo();
		if (customerVo.getEmail() != null) {
			customerEmailChecking = new Customer();
			customerPhoneNoChecking = new Customer();
			customerEmailChecking = customerService
					.getCustomerInfoByEmail(customerVo.getEmail());
			customerPhoneNoChecking = customerService
					.getCustomerInfoByPhoneNo(customerVo.getPhoneNo());
			if (customerEmailChecking == null
					&& customerPhoneNoChecking == null) {
				newCustomer = new Customer();
				newCustomer = customerDetails(customerVo);
				newCustomer.setIsactive('Y');
				customerService.saveCustomer(newCustomer);
				if (newCustomer.getCustomerId() != null) {
					customerVoSet = new CustomerVo();
					customerVoSet.setCustomerId(newCustomer.getCustomerId());
					customerVoSet.setEmail(newCustomer.getEmail());
					customerLoginResponseVo.setCustomer(customerVoSet);
					customerLoginResponseVo
							.setErrorCode(SBErrorMessage.SIGNUP_SUCCESS
									.getCode());
					customerLoginResponseVo
							.setErrorString(SBErrorMessage.SIGNUP_SUCCESS
									.getMessage());
					customerLoginResponseVo.setStatus(SBMessageStatus.SUCCESS
							.getValue());
					return CommonUtil.getObjectMapper(customerLoginResponseVo);
				}
			} else {
				if (customerEmailChecking != null) {
					customerLoginResponseVo
							.setErrorCode(SBErrorMessage.EMAILID_EXISTS
									.getCode());
					customerLoginResponseVo
							.setErrorString(SBErrorMessage.EMAILID_EXISTS
									.getMessage());
					customerLoginResponseVo.setStatus(SBMessageStatus.FAILURE
							.getValue());
					return CommonUtil.getObjectMapper(customerLoginResponseVo);
				} else {
					customerLoginResponseVo
							.setErrorCode(SBErrorMessage.MOBILNO_EXISTS
									.getCode());
					customerLoginResponseVo
							.setErrorString(SBErrorMessage.MOBILNO_EXISTS
									.getMessage());
					customerLoginResponseVo.setStatus(SBMessageStatus.FAILURE
							.getValue());
					return CommonUtil.getObjectMapper(customerLoginResponseVo);
				}
			}
		}
		return CommonUtil.getObjectMapper(customerLoginResponseVo);

	}

	@Path("/addAddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String addAddress(AddressVo addressVo) {
		response = new ResponseModel();
		String responseStr = "";
		try {
			if (addressVo.getCustomer().getCustomerId() != null) {
				JsonNode location = addressService
						.getLatLongByAddress(addressVo);
				if (location == null) {
					response.setErrorCode(SBErrorMessage.INVALID_ADDRESS
							.getCode());
					response.setErrorString(SBErrorMessage.INVALID_ADDRESS
							.getMessage());
					response.setStatus(SBMessageStatus.FAILURE.getValue());
					return CommonUtil.getObjectMapper(response);
				}
				JsonNode loc = location.findValue("lat".toString());
				addressVo.setLatitude(loc.toString());
				loc = location.findValue("lng".toString());
				addressVo.setLongitude(loc.toString());
				Customer customer = customerService
						.getCustomerInfoById(addressVo.getCustomer()
								.getCustomerId());
				customer.setName(addressVo.getCustomer().getName());
				customerService.updateCustomer(customer);
				Address address = addressService.setAddress(addressVo);
				address.setCustomer(customer);
				if (addressVo.getAddressId() == null) {
					addressService.saveAddress(address);
				} else {
					addressService.updateAddress(address);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		try {
			responseStr = CommonUtil.getObjectMapper(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseStr;

	}

	@Path("/getaddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getCustomerAddress(CustomerVo customerVo) {
		addressResponseList = new AddressResponseVo();
		String responseStr = "";
		try {
			Customer customer = customerService.getCustomerInfoById(customerVo
					.getCustomerId());
			List<Address> addressList = customer.getAddresses();
			for (Address address : addressList) {
				if(address.getIsactive()=='Y'){
					AddressVo addresVo = addressService.setAddressVo(address);
					addresVo.getCustomer().setName(address.getCustomer().getName());
					addressResponseList.getAddressList().add(addresVo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addressResponseList.setStatus(SBMessageStatus.FAILURE.getValue());
			addressResponseList.setErrorString(e.getMessage());
		}
		try {
			responseStr = CommonUtil.getObjectMapper(addressResponseList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseStr;

	}

	@Path("/deleteaddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String deleteAddress(AddressVo addressVo) {
		response = new ResponseModel();
		String responseStr = "";
		try {
			if (addressVo.getAddressId() != null) {
				Address address = addressService.getAddress(addressVo
						.getAddressId());
				address.setIsactive('N');
				addressService.updateAddress(address);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}

		try {
			responseStr = CommonUtil.getObjectMapper(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseStr;

	}

	public Customer customerDetails(CustomerVo customerVo) throws Exception {
		customer = (Customer) CommonUtil.setAuditColumnInfo(Customer.class
				.getName());
		customer.setEmail(customerVo.getEmail());
		customer.setPhoneNo(customerVo.getPhoneNo());
		customer.setPassword(CommonUtil.passwordEncoder(customerVo
				.getPassword()));
		customer.setName(customerVo.getEmail());
		return customer;
	}


}
