package com.mitosis.shopsbacker.customer.dao;

import com.mitosis.shopsbacker.model.Customer;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public interface CustomerDao<T> {

	public void saveCustomer(Customer customer);

	public void updateCustomer(Customer customer);

	public void deleteCustomer(Customer customer);

	public Customer getCustomerInfoById(String id);

	public Customer getCustomerInfoByEmail(String email);

}
