package com.mitosis.aviate.dao;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.model.CustomerModel;
import com.mitosis.aviate.model.SalesOrderModel;
import com.mitosis.aviate.model.StoreModel;

public interface StoreDao {

	public boolean addUpdateStoreDetails(StoreModel storeModel);
	
	public boolean removeStore(long storeId);

	public List<CustomerModel> getEmployeeList(String storeId , List<String> roles);

	public List<SalesOrderModel> getUserDetails(JSONObject requestObj);

	public List<SalesOrderModel> getOrderList(JSONObject requestObj);

	public boolean updateOrderStatus(SalesOrderModel salesOrderModel);

	boolean removeEmployee(Long customerId);
	
	public StoreModel getStoreLogoByImageName(String imageName);
	
	public List<StoreModel> getStoreByMerchant(JSONObject merchantObj);

	public StoreModel getStoreDetailsById(long storeId);
	
}
