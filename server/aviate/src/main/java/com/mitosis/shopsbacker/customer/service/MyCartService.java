package com.mitosis.shopsbacker.customer.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.Store;

public interface MyCartService<T> {
	
	public void updateCart(MyCart mycart);
	
	public List<MyCart> getMyCartList(Customer customer, Store store);
	
	public void removeFromCart(MyCart mycart);
	

}
