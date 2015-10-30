package com.mitosis.aviate.dao;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.model.CustomerDetailsModel;
import com.mitosis.aviate.model.CustomerModel;
import com.mitosis.aviate.model.CustomerProductList;

public interface CustomerDao extends Serializable {

	public void save(CustomerModel customerModel);
	
	public void saveOtherRole(CustomerModel customerModel);
	
	public boolean remove(long id);
	
	public void edit(CustomerModel customerModel);

	public CustomerModel checkLogin(String emailId, String password);

	public void updateCustomerDetails(CustomerDetailsModel credencial);

	public CustomerDetailsModel getCusotmerByEmail(JSONObject credentials) throws Exception;

	public List<CustomerDetailsModel> getPickerDeviceId(JSONObject storeIdRole) throws Exception;

	public void addMyList(CustomerProductList customerProduct);

	public boolean removeMyList(CustomerProductList customerProductList);

	public List<CustomerProductList> getMyList(Long customerId, Long storeId);

	public boolean updateDeviceDetails(CustomerDetailsModel customerDetailsModel);
	
	public CustomerModel getCustomerPass(String emailId);
	
	public List<CustomerModel> getListByQuery(String parm,String role);
	
	public List<CustomerModel> getListByQuery2(String parm,String role);
	
	public List<CustomerDetailsModel> getListByStoreId(Long store_id);
	
	public CustomerDetailsModel getListBycustomerId(Long customerId);
}
