package com.mitosis.shopsbacker.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.responsevo.CustomerLoginResponseVo;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.customer.CustomerVo;

@Path("customer")
public class CustomerRestService<T> {

	ResponseModel response = new ResponseModel();

	@Autowired
	CustomerService<T> customerService;

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerLoginResponseVo userLogin(CustomerVo customerVo) {
		CustomerLoginResponseVo customerLoginResponseVo = new CustomerLoginResponseVo();
		CustomerVo customerDetails = new CustomerVo();
		if (customerVo != null) {
			Customer customer = customerService.getCustomerInfoByEmail(
					customerVo.getEmail(), customerVo.getPassword());
			if (customer != null) {
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
