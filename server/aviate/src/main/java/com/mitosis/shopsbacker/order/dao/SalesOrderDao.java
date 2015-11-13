package com.mitosis.shopsbacker.order.dao;

import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.model.OrderTax;
import com.mitosis.aviate.model.SalesOrderModel;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public interface SalesOrderDao<T> {
	
	public JSONObject getStoreNameMaxOrderId(Long storeId);
	
	public void saveOrder(SalesOrder salesOrder);
	
	public void updateOrder(SalesOrder salesOrder);
	
	public void getOrder(SalesOrder salesOrder);
	
	public void getOrder(Store store);
	
	public SalesOrderModel conformPayment(String orderId,String transactionNo,String paymentMethod);

	boolean saveOrderTax(OrderTax orderTax);
	

}
