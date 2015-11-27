package com.mitosis.shopsbacker.customer.dao;

import com.mitosis.shopsbacker.model.Customer;

/**
 * @author prabakaran
 *
 * @param <T>
 * 
 *  Reviewed by Sundaram 27/11/2015
 */
public interface CustomerDao<T> {

	public void saveCustomer(Customer customer);

	public void updateCustomer(Customer customer);

	public void deleteCustomer(Customer customer);

	public Customer getCustomerInfoById(String id);

	public Customer getCustomerInfoByEmail(String email);
	
	public Customer getCustomerInfoByEmail(String email,String password);
	
	public Customer getCustomerInfoByEmailWithPhoneno(String email,String phoneNo);
	
	public Customer getCustomerInfoByPhoneNo(String phoneNo);

}
