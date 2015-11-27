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
 *  Reviewed by Sundaram 27/11/2015
 */

@Path("customer")
@Controller("customerRestService")
public class CustomerRestService<T> {

	@Autowired
	CustomerService<T> customerService;
	
	@Autowired
	AddressService<T> addressService;
	
	ResponseModel response = null;
	CustomerLoginResponseVo customerLoginResponseVo=null;
	CustomerVo customerDetails,customerVoSet=null;
	Customer customer,customerEmailChecking,customerPhoneNoChecking,newCustomer = null;
	Address address =null;
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
			customer = customerService
					.getCustomerInfoByEmail(customerVo.getEmail());
			if (customer != null) {
				flag = CommonUtil.passwordVerification(
						customerVo.getPassword(), customer.getPassword());
			}
			if (customer != null && flag) {
				customerDetails = setCustomerDetails(customer);
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
	public String userSignUp(CustomerVo customerVo)
			throws Exception {
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
					return  CommonUtil.getObjectMapper(customerLoginResponseVo);
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
					return  CommonUtil.getObjectMapper(customerLoginResponseVo);
				} else {
					customerLoginResponseVo
							.setErrorCode(SBErrorMessage.MOBILNO_EXISTS
									.getCode());
					customerLoginResponseVo
							.setErrorString(SBErrorMessage.MOBILNO_EXISTS
									.getMessage());
					customerLoginResponseVo.setStatus(SBMessageStatus.FAILURE
							.getValue());
					return  CommonUtil.getObjectMapper(customerLoginResponseVo);
				}
			}
		}
		return  CommonUtil.getObjectMapper(customerLoginResponseVo);

	}
	
	@Path("/addAddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel addAddress(AddressVo addressVo) throws Exception {
		response=new ResponseModel();
		if (addressVo.getCustomer().getCustomerId() != null) {
			String full_address = addressVo.getAddress1()+","+addressVo.getAddress2()+","+
								  addressVo.getCity()+","+addressVo.getCountry();	
			JsonNode location = CommonUtil.getLatLong(full_address);
			customer = customerService
					.getCustomerInfoById(addressVo.getCustomer().getCustomerId());
			address = addressService.setAddress(addressVo);
			address.setCustomer(customer);
			if(addressVo.getAddressId()==null){
				addressService.saveAddress(address);
			}else{
				addressService.updateAddress(address);
			}
		}
		return response;

	}
	
	@Path("/getaddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AddressResponseVo getCustomerAddress(CustomerVo customerVo) throws Exception {
		addressResponseList = new AddressResponseVo();
			customer = customerService
					.getCustomerInfoById(customerVo.getCustomerId());
			List<Address> addressList = addressService.getAddress(customer);
			for(Address address: addressList){
				AddressVo addresVo = addressService.setAddressVo(address);
				addressResponseList.getAddressList().add(addresVo);
			}
			return addressResponseList;
			
		}
	
	@Path("/deleteaddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteAddress(AddressVo addressVo) throws Exception {
		response=new ResponseModel();
		if (addressVo.getAddressId() != null) {
			Address address = addressService.getAddress(addressVo.getAddressId());
			addressService.deleteAddress(address);
		}
		return response;

	}


	public Customer customerDetails(CustomerVo customerVo) throws Exception {
		customer = (Customer) CommonUtil
				.setAuditColumnInfo(Customer.class.getName());
		customer.setEmail(customerVo.getEmail());
		customer.setPhoneNo(customerVo.getPhoneNo());
		customer.setPassword(CommonUtil.passwordEncoder(customerVo
				.getPassword()));
		customer.setName(customerVo.getEmail());
		return customer;
	}

	public CustomerVo setCustomerDetails(Customer customer) {
		customerVo = new CustomerVo();
		customerVo.setCustomerId(customer.getCustomerId());
		customerVo.setName(customer.getName());
		customerVo.setEmail(customer.getEmail());
		customerVo.setImageId(customer.getImageId());
		customerVo.setDeviceid(customer.getDeviceid());
		customerVo.setDeviceType(customer.getDeviceType());
		return customerVo;
	}

}
