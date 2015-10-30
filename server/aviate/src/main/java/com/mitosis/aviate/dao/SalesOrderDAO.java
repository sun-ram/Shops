package com.mitosis.aviate.dao;

import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.model.SalesOrderModel;

public interface SalesOrderDAO {
	
	public List<SalesOrderModel> getUserDetails(JSONObject requestObj);
	public List<SalesOrderModel> getOrderList(JSONObject requestObj);
	public List<SalesOrderModel> salesOrderDetailList(JSONObject requestObj);

}
