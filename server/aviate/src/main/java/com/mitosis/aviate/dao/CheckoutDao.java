package com.mitosis.aviate.dao;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.model.Address;
import com.mitosis.aviate.model.OrderTax;
import com.mitosis.aviate.model.SalesOrderModel;

public interface CheckoutDao {
	
	public boolean update(Address address);
	
	public boolean removeAddress(Long addressId);
	
	public JSONObject getStoreNameMaxOrderId(Long storeId);
	
	public boolean saveOrder(SalesOrderModel orderedItems);
	
	public List<Address> getAddress(int customerId);
	
	public SalesOrderModel conformPayment(String orderId,String transactionNo,String paymentMethod);

	boolean saveOrderTax(OrderTax orderTax);
	

}
