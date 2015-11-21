package com.mitosis.shopsbacker.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.responsevo.CustomerLoginResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.customer.CustomerVo;

@Path("customer")
@Controller("customerRestService")
public class CustomerRestService<T> {

	ResponseModel response = new ResponseModel();

	@Autowired
	CustomerService<T> customerService;

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

	public Customer customerDetails(CustomerVo customerVo) throws Exception {
		Customer customer = (Customer) CommonUtil
				.setAuditColumnInfo(Customer.class.getName());
		customer.setEmail(customerVo.getEmail());
		customer.setPhoneNo(customerVo.getPhoneNo());
		customer.setPassword(CommonUtil.passwordEncoder(customerVo
				.getPassword()));
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
