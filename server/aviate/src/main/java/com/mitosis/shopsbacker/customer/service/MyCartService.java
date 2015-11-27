package com.mitosis.shopsbacker.customer.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;

public interface MyCartService<T> {
	
	public void updateCart(MyCart mycart);
	
	public List<MyCart> getMyCartList(Customer customer, Store store);
	
	public void removeFromCart(MyCart mycart);
	
	public void addToCart(MyCart mycart);
	
	public MyCart getCartDetailFromId(String myCartId);
	
	public MyCart getCartByCustomerStoreanProductId(Customer customer, Product product, Store store);

	public int deleteCartProduct(String customerId, String storeId);
	

}
