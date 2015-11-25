package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.Date;
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
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.responsevo.AddressResponseVo;
import com.mitosis.shopsbacker.responsevo.CustomerLoginResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.RoleName;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.AddressVo;
import com.mitosis.shopsbacker.vo.customer.CustomerVo;

@Path("customer")
@Controller("customerRestService")
public class CustomerRestService<T> {

	ResponseModel response = new ResponseModel();

	@Autowired
	CustomerService<T> customerService;
	
	@Autowired
	AddressService<T> addressService;
	
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerLoginResponseVo userLogin(CustomerVo customerVo) {
		boolean flag = false;
		CustomerLoginResponseVo customerLoginResponseVo = new CustomerLoginResponseVo();
		CustomerVo customerDetails = new CustomerVo();
		if (customerVo != null) {
			Customer customer = customerService
					.getCustomerInfoByEmail(customerVo.getEmail());
			if (customer != null) {
				flag = CommonUtil.passwordVerification(
						customerVo.getPassword(), customer.getPassword());
			}
			if (customer != null && flag) {
				customerDetails = setCustomerDetails(customer);
				customerLoginResponseVo.setCustomerVo(customerDetails);
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
				return customerLoginResponseVo;
			}
		}
		return customerLoginResponseVo;

	}

	@Path("/signup")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerLoginResponseVo userSignUp(CustomerVo customerVo)
			throws Exception {
		CustomerLoginResponseVo customerLoginResponseVo = new CustomerLoginResponseVo();
		if (customerVo.getEmail() != null) {
			Customer customerEmailChecking = new Customer();
			Customer customerPhoneNoChecking = new Customer();
			customerEmailChecking = customerService
					.getCustomerInfoByEmail(customerVo.getEmail());
			customerPhoneNoChecking = customerService
					.getCustomerInfoByPhoneNo(customerVo.getPhoneNo());
			if (customerEmailChecking == null
					&& customerPhoneNoChecking == null) {
				Customer newCustomer = new Customer();
				newCustomer = customerDetails(customerVo);
				newCustomer.setIsactive('Y');
				customerService.saveCustomer(newCustomer);
				if (newCustomer.getCustomerId() != null) {
					CustomerVo customerVoSet = new CustomerVo();
					customerVoSet.setCustomerId(newCustomer.getCustomerId());
					customerVoSet.setEmail(newCustomer.getEmail());
					customerLoginResponseVo.setCustomerVo(customerVoSet);
					customerLoginResponseVo
							.setErrorCode(SBErrorMessage.SIGNUP_SUCCESS
									.getCode());
					customerLoginResponseVo
							.setErrorString(SBErrorMessage.SIGNUP_SUCCESS
									.getMessage());
					customerLoginResponseVo.setStatus(SBMessageStatus.SUCCESS
							.getValue());
					return customerLoginResponseVo;
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
					return customerLoginResponseVo;
				} else {
					customerLoginResponseVo
							.setErrorCode(SBErrorMessage.MOBILNO_EXISTS
									.getCode());
					customerLoginResponseVo
							.setErrorString(SBErrorMessage.MOBILNO_EXISTS
									.getMessage());
					customerLoginResponseVo.setStatus(SBMessageStatus.FAILURE
							.getValue());
					return customerLoginResponseVo;
				}
			}
		}
		return customerLoginResponseVo;

	}
	
	@Path("/addAddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseModel addAddress(AddressVo addressVo) throws Exception {
		if (addressVo.getCustomer().getCustomerId() != null) {
			String full_address = addressVo.getAddress1()+","+addressVo.getAddress2()+","+
								  addressVo.getCity()+","+addressVo.getCountry();	
			JsonNode location = CommonUtil.getLatLong(full_address);
			Customer customer = customerService
					.getCustomerInfoById(addressVo.getCustomer().getCustomerId());
			Address address = addressService.setAddress(addressVo);
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
	@Transactional(propagation=Propagation.REQUIRED)
	public AddressResponseVo getCustomerAddress(CustomerVo customerVo) throws Exception {
		AddressResponseVo addressResponseList = new AddressResponseVo();
			Customer customer = customerService
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
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseModel deleteAddress(AddressVo addressVo) throws Exception {
		if (addressVo.getAddressId() != null) {
			Address address = addressService.getAddress(addressVo.getAddressId());
			addressService.deleteAddress(address);
		}
		return response;

	}


	public Customer customerDetails(CustomerVo customerVo) throws Exception {
		Customer customer = (Customer) CommonUtil
				.setAuditColumnInfo(Customer.class.getName());
		customer.setEmail(customerVo.getEmail());
		customer.setPhoneNo(customerVo.getPhoneNo());
		customer.setPassword(CommonUtil.passwordEncoder(customerVo
				.getPassword()));
		customer.setName(customerVo.getEmail());
		return customer;
	}

	public CustomerVo setCustomerDetails(Customer customer) {
		CustomerVo customerVo = new CustomerVo();
		customerVo.setCustomerId(customer.getCustomerId());
		customerVo.setName(customer.getName());
		customerVo.setEmail(customer.getEmail());
		customerVo.setImageId(customer.getImageId());
		customerVo.setDeviceid(customer.getDeviceid());
		customerVo.setDeviceType(customer.getDeviceType());
		return customerVo;
	}

}
