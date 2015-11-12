package com.mitosis.shopsbacker.admin.dao;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;

public interface StoreDao<T> {

	public void addUpdateStoreDetails(Store storeModel);
	
	public void removeStore(Store store);

	public List<Customer> getEmployeeList(String storeId , List<String> roles);

	public List<SalesOrder> getUserDetails(JSONObject requestObj);

	public List<SalesOrder> getOrderList(JSONObject requestObj);

	public void updateOrderStatus(SalesOrder salesOrderModel);

	public void removeEmployee(Customer customer);
	
	public Store getStoreLogoByImageName(String imageName);
	
	public List<Store> getStoreByMerchant(Merchant merchant);

	public Store getStoreDetailsById(String storeId);
	
}
