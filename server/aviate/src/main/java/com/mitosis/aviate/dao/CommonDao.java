package com.mitosis.aviate.dao;

import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.mitosis.aviate.model.ProductCategory;

public interface CommonDao {

	public void applePushNotification(String orderId, String deviceId);

	public void androidPushNotification(String orderId, String deviceId);
	
	void getCategoriesInList(List<ProductCategory> productCategoryList,
			List<ProductCategory> categoriesInList);

	public boolean isValidProperty(JSONObject obj,String key) throws JSONException;

	public Date convertStringToDate(String dateString);

	public String convertDateToStrint(Date date);

	public Date convertStringToTime(String time);

	public String convertTimeToString(Date time);
	
	public JsonNode getLatLong(String full_address);

	
}
