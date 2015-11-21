package com.mitosis.shopsbacker.customer.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.Store;


public interface MyCartDao<T> {
	
	public void addToCart(MyCart mycart);
	
	public void updateCart(MyCart mycart);
	
	public List<MyCart> getMyCartList(Customer customer, Store store);
	
	public void removeFromCart(MyCart mycart);
	
	public MyCart getCartDetailFromId(String myCartId);

}
