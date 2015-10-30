package com.mitosis.aviate.dao.daoimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.model.ProductCategory;
import com.mitosis.aviate.util.Constants;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;




public class CommonDaoImpl  implements CommonDao {
	final static Logger log =Logger.getLogger(CommonDaoImpl.class); 
	@Override
	public void applePushNotification(String orderId, String deviceId){
		try{
			ApnsService service =
					APNS.newService()
					.withCert(this.getClass().getResourceAsStream(
							Constants.APNS_CERTIFICATE), Constants.APNS_KEY)
							.withSandboxDestination()
							.build();
			String payload = APNS.newPayload().alertBody(orderId).sound("test.mp3").build();
			service.push(deviceId, payload);
			Map<String, Date> inactiveDevices = service.getInactiveDevices();
			for (String deviceToken : inactiveDevices.keySet()) {
				Date inactiveAsOf = inactiveDevices.get(deviceToken);
				System.out.println(inactiveAsOf);
			}
		}catch(Exception e){

		}
	}
	
	@Override
	public void androidPushNotification(String orderId, String deviceId){
		try{
			orderId = "Order id = "+orderId;
			//deviceId = "APA91bHgWJdi7gWFUqOcOXB7piEueVBOSPu_GvrArmsku_dPu1bzIqKkY7TWqe8cujKzsCeSHaCxgMm6SPazYXetC0tN5VeHyE-Pe1wwZJYfY9NflMeHCYHpCubyH-KCy9Uywj5tjooAC0__KcxSAmERtijPb1v9tlJj7Neg4H3f3i6MlzwsNMs";
			Result result = null;
			Sender sender = new Sender(Constants.GOOGLE_SERVER_KEY);
			Message message = new Message.Builder().timeToLive(30)
					.delayWhileIdle(true).addData(Constants.MESSAGE_KEY, orderId).build();
			result = sender.send(message, deviceId, 1);
			System.out.println(result.getErrorCodeName());
		}catch(Exception e){

		}
	}
	
	@Override
	public void getCategoriesInList(List<ProductCategory> productCategoryList,List<ProductCategory> categoriesInList){
		for(ProductCategory productCategory:productCategoryList){
			ProductCategory pc = new ProductCategory();
			pc.setCategoryId(productCategory.getCategoryId());
			pc.setCategoryName(productCategory.getCategoryName());
			pc.setParentCategoryId(productCategory.getParentCategoryId());
			categoriesInList.add(pc);
			if(productCategory.getCategory().size()>0){
				getCategoriesInList(productCategory.getCategory(),categoriesInList);
			}
		}
	}
	
	@Override
	public boolean isValidProperty(JSONObject obj,String key) throws JSONException{
		boolean flag = false; 
		if( obj.has(key) && obj.getString(key)!=null && !obj.getString(key).equals("")){
			flag = true;
		}
		return flag;
	}

	@Override
	public JsonNode getLatLong(String full_address) {
		JsonNode location = null;
		try{
		full_address=full_address.replaceAll(" ", "%20");
		full_address=full_address.replaceAll("#", "");
		Client client=Client.create();
		WebResource resource = client.resource("http://maps.google.com/maps/api/geocode/json?address="+full_address+"&sensor=false");
		JSONObject res =new JSONObject(resource.accept(MediaType.APPLICATION_JSON).get(String.class));
		JsonNode rootNode = new ObjectMapper().readTree(res.toString());
		location = rootNode.findValue("location");
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return location;
	}

	@Override
	public Date convertStringToDate(String dateString) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		try{
			date = df.parse(dateString);
			// df.format(date);
		}
		catch ( Exception ex ){
			System.out.println(ex);
		}
		return date;	
	}
	@Override
	public String convertDateToStrint(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Date convertStringToTime(String time) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String convertTimeToString(Date time) {
		// TODO Auto-generated method stub
		return null;
	}

}
