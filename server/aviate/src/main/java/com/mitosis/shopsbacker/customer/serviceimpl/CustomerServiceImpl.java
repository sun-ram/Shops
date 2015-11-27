package com.mitosis.shopsbacker.customer.serviceimpl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.customer.dao.CustomerDao;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.model.Customer;

/**
 * @author prabakaran
 *
 * @param <T>
 *  Reviewed by Sundaram 27/11/2015
 */
@Service("customerServiceImpl")
public class CustomerServiceImpl<T> implements CustomerService<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	CustomerDao<T> customerDao;

	public CustomerDao<T> getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao<T> customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void saveCustomer(Customer customer) {
		getCustomerDao().saveCustomer(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		getCustomerDao().updateCustomer(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		getCustomerDao().deleteCustomer(customer);
	}

	@Override
	public Customer getCustomerInfoById(String id) {
		return getCustomerDao().getCustomerInfoById(id);
	}

	@Override
	public Customer getCustomerInfoByEmail(String email) {
		return getCustomerDao().getCustomerInfoByEmail(email);
	}

	@Override
	public Customer getCustomerInfoByEmail(String email, String password) {
		return getCustomerDao().getCustomerInfoByEmail(email,password);
	}

	@Override
	public Customer getCustomerInfoByEmailWithPhoneno(String email,
			String phoneNo) {
		return getCustomerDao().getCustomerInfoByEmail(email, phoneNo);
	}

	@Override
	public Customer getCustomerInfoByPhoneNo(String phoneNo) {
		// TODO Auto-generated method stub
		return getCustomerDao().getCustomerInfoByPhoneNo(phoneNo);
	}

}
