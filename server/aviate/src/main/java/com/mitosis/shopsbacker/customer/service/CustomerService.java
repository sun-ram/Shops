package com.mitosis.shopsbacker.customer.service;

import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.vo.customer.CustomerVo;

/**
 * @author prabakaran
 *
 * @param <T>
 * 
 *  Reviewed by Sundaram 27/11/2015
 */
public interface CustomerService<T> {

	public void saveCustomer(Customer customer);

	public void updateCustomer(Customer customer);

	public void deleteCustomer(Customer customer);

	public Customer getCustomerInfoById(String id);

	public Customer getCustomerInfoByEmail(String email);
	
	public Customer getCustomerInfoByEmailWithPhoneno(String email,String phoneNo);
	
	public Customer getCustomerInfoByEmail(String email, String password);
	
	public Customer getCustomerInfoByPhoneNo(String phoneNo);
	
	public CustomerVo setCustomerVo(Customer customer);

}
