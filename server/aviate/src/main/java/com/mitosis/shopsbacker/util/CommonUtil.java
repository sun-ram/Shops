package com.mitosis.shopsbacker.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
public final class CommonUtil {

	static String delivery_time_format = "hh:mm a";
	
	/**
	 * changing date format
	 * 
	 * @author fayaz
	 * @param Date
	 * @return Date
	 */
	public static Date dateFormat(Date date) {
		Date dateformat = null;
		try {
			String date1 = date.toString();
			DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			dateformat = sdf.parse(date1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateformat;
	}

	/**
	 * Convert String to date
	 * 
	 * @author fayaz
	 * @param String
	 * @return Date
	 */
	public static Date stringToDate(String strDate) {
		Date dateformat = null;
		try {
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dateformat = sdf.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateformat;
	}

	/**
	 * This method adding audit column values and returns new object of give
	 * class
	 * 
	 * @author Anbukkani Gajendran
	 * @param fullyQualifiedName
	 * @return Object(new instance for give class)
	 * @throws Exception
	 */
	public static Object setAuditColumnInfo(String fullyQualifiedName,String userId)
			throws Exception {
		Class cls = Class.forName(fullyQualifiedName);
		Object obj = cls.newInstance();
		Class[] paramString = new Class[1];
		paramString[0] = String.class;
		// TODO:Need to get user from session and set here
		Method method = cls.getDeclaredMethod("setCreatedby", paramString);
		if(userId==null){
						method.invoke(obj, "123");
				}else{
					method.invoke(obj,userId);
				}
		method = cls.getDeclaredMethod("setUpdatedby", paramString);
		if(userId==null){
				method.invoke(obj, "123");
					}else{
						method.invoke(obj,userId);	
					}
		Class[] paramString1 = new Class[1];
		paramString1[0] = Date.class;
		method = cls.getDeclaredMethod("setUpdated", paramString1);
		method.invoke(obj, new Date());
		method = cls.getDeclaredMethod("setCreated", paramString1);
		method.invoke(obj, new Date());
		/*Class[] paramString2 = new Class[1];
		paramString2[0] = Character.class;
		method = cls.getDeclaredMethod("setIsactive", paramString2);
		method.invoke(obj, 'Y');*/
		return obj;
	}
	

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function to find near by based on latitude and longitude : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */

	public static double distance(double lat1, double lon1, double lat2,
			double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;

		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	public static JSONObject statusMessage(boolean flag) {
		JSONObject response = new JSONObject();
		try {
			if (flag) {
				response.put("status", SBMessageStatus.SUCCESS.getValue());
			} else {
				response.put("status", SBMessageStatus.FAILURE.getValue());
				response.put("errorString", "");
				response.put("errorCode", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public static JSONObject addStatusMessage(Exception e) {
		JSONObject response = new JSONObject();
		try {
			response.put("status", SBMessageStatus.FAILURE.getValue());
			response.put("errorString", e.getMessage());
			response.put("errorCode", "");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return response;
	}

	public static JSONObject addStatusMessage(String e) {
		JSONObject response = new JSONObject();
		try {
			response.put("status", SBMessageStatus.FAILURE.getValue());
			response.put("errorString", e);
			response.put("errorCode", "");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return response;
	}

	/**
	 * adding error messages to ResponseModel
	 * 
	 * @author Anbukkani G
	 * @param e
	 * @param response
	 * @return ResponseModel
	 */
	public static ResponseModel addStatusMessage(Exception e,
			ResponseModel response) {
		response.setStatus(SBMessageStatus.FAILURE.getValue());
		response.setErrorString(e.getMessage());
		response.setErrorCode("");
		return response;
	}

	public static boolean sendMail(String to, String subject, String body) {
		boolean flag = false;
		String from = "prabakaran.a@mitosistech.com";
		String pass = "praba123";
		String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props);
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress toAddress = new InternetAddress();
			toAddress = new InternetAddress(to);
			message.addRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject(subject);
			message.setContent(body, "text/html");
			//message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			flag = true;
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		return flag;
	}

	public static String dateToString(Date date) {
		String dateString = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a") ;
			dateString = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateString;
	}
	
	public static String dateToStringForSalesOrder(Date date) {
		String dateString = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
			dateString = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateString;
	}
	
	
	public static Date convertStringToTime(String strDeliveryTime) {
		Date deliveryTime = null;	
		SimpleDateFormat formatter = new  SimpleDateFormat(delivery_time_format);
		Date date = new Date();
	
		try {
		Calendar cal = Calendar.getInstance();
        deliveryTime = formatter.parse(strDeliveryTime);
        cal.setTime(deliveryTime);
        Calendar calNew = Calendar.getInstance();
    
        calNew.setTime(date);
        calNew.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
        calNew.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
        calNew.set(Calendar.SECOND, cal.get(Calendar.SECOND));
        date.setTime(calNew.getTimeInMillis());
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String convertTimeToString(Date time) {
		SimpleDateFormat formatter = new  SimpleDateFormat("hh:mm a");
		String  strTime = formatter.format(time);
		return strTime;
	}

	/**
	 * public boolean uploadImagr(String imageString, String imageType,
			String imagePath, String imageName) returns true if the ImageIO.write is success. 
			
	 * @author prabakaran
	 * @param String imageString, String imageType,
			String imagePath, String imageName
	 * @return boolean
	 * @throws Exception 
	 * 
	 */
	public static boolean uploadImage(String imageString, String imageType,
			String imagePath, String imageName) throws Exception {
		boolean success = false;
		try {
			byte[] byeImage = Base64.decodeBase64(imageString.getBytes());
			InputStream in = new ByteArrayInputStream(byeImage);
			BufferedImage bufferedImage = ImageIO.read(in);
			imageName = imageName + "." + imageType;
			File file = new File(imagePath);
			if (!file.exists()) {
				file.mkdir();
			}
			File imageFile = new File(imagePath + "/" + imageName);
			FileUtils.forceMkdir(imageFile);
			success = ImageIO.write(bufferedImage, imageType, imageFile);
			in.close();
		} catch (Exception e) {
			throw e;
		}
		return success;
	}
	
	/**
	 * Read image from url path
	 * 
	 * @author prabakaran
     * @param image url
     * @return BufferedImage
     */
	public static BufferedImage readImageFromUrl(String imgUrl)
			throws MalformedURLException, IOException {
		URL url = new URL(imgUrl);
		BufferedImage image = ImageIO.read(url);
		return image;
	}
	
	/**
	 * Encode image to string
	 * 
	 * @author prabakaran
     * @param image The image to encode
     * @param type jpeg, bmp, ...
     * @return encoded string
     */
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            imageString = Base64.encodeBase64String(imageBytes);

            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageString;
    }
    
    /**
     * Decode string to image
     * 
     * @author prabakaran
     * @param imageString The string to decode
     * @return decoded image
     */
    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            imageByte = Base64.decodeBase64(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

	/**
	 * 
	 * @author prabakaran
	 * @param imagePath
	 * @return boolean
	 * 
	 */
	public static boolean removeImage(String imagePath) {
		boolean success = false;
		try {
			File file = new File(imagePath);
			success = file.delete();
		} catch (Exception e) {
			throw e;
		}
		return success;
	}
	
	/**
	 * public JsonNode getLatLong(String full_address) returns JsonNode it contains lat and long.
	 * @param full_address
	 * @return JsonNode
	 */
	public static Map<String, JsonNode> getLatLong(String full_address) {
		Map<String, JsonNode> addressNodeMap=new HashMap<String, JsonNode>();
		JsonNode location = null;
		JsonNode formattedAddress = null;
		try{
		full_address=full_address.replaceAll(" ", "%20");
		full_address=full_address.replaceAll("#", "");
		Client client=Client.create();
		WebResource resource = client.resource("http://maps.google.com/maps/api/geocode/json?address="+full_address+"&sensor=false");
		JSONObject res =new JSONObject(resource.accept(MediaType.APPLICATION_JSON).get(String.class));
		JsonNode rootNode = new ObjectMapper().readTree(res.toString());
		location = rootNode.findValue("location");
		addressNodeMap.put("location", location);
		formattedAddress = rootNode.findValue("formatted_address");
		addressNodeMap.put("formattedAddress", formattedAddress);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return addressNodeMap;
	}
	
	/**
	 * public static String passwordEncoder(String password).
	 * @param password - password given by the user when signup
	 * @return hashedPassword - returns encoded string using bcrypt
	 */
	public static String passwordEncoder(String password){	
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
	
	/**
	 *public static boolean passwordVerification(String password,String hashedPassword).
	 * @param password - password given by the user when login
	 * @param hashedPassword - encoded password from database
	 * @return status - returns true or false based upon password match
	 */
	public static boolean passwordVerification(String password,String hashedPassword){
		boolean status = false;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		status = passwordEncoder.matches(password, hashedPassword);
		return status;
	}
	
	public static boolean isValidProperty(JSONObject obj,String key) throws JSONException{
		boolean flag = false; 
		if( obj.has(key) && obj.getString(key)!=null && !obj.getString(key).equals("")){
			flag = true;
		}
		return flag;
	}

	/**
	 * Get proper error message from exception 
	 * 
	 * @author Anbukkani Gajendran
	 * @param Exception
	 * @return errormMessage
	 */
	public static String getErrorMessage(Exception e) {
		String errormMessage=e.getMessage();
		while(e != null){
			if(e instanceof MySQLIntegrityConstraintViolationException ){
			 errormMessage= e.getLocalizedMessage();
			 break;
			}
			e=(Exception) e.getCause();
		}
		return errormMessage;
	}
	
	/**
	 * This method used for removed null and empty value objects.
	 * @author Anbukkani Gajendran
	 * @return ObjectMapper
	 */
	public static ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		return mapper;
	}
	
	/**
	 * This method used for removed null and empty value objects.
	 * @author Prabakaran
	 * @return json string
	 */
	public static String getObjectMapper(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String res = "";
		try{
			mapper.setSerializationInclusion(Include.NON_NULL);
			//mapper.setSerializationInclusion(Include.NON_EMPTY);
			res = mapper.writeValueAsString(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * This method used for removed null and formated the date.
	 * @author Prabakaran
	 * @return json string 
	 */
	public static String getObjectMapperWithSerializationFeature(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String res = "";
		try{
			mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			mapper.setSerializationInclusion(Include.NON_NULL);
			res = mapper.writeValueAsString(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static void applePushNotification(String orderId, String deviceId){/*
		try{
			ApnsService service =
					APNS.newService().withCert(this.getClass().getResourceAsStream("/apnscertificate/Deve_Certificates.p12"), "123456")
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
	*/}
	
	public static void androidPushNotification(String messageStr, String deviceId, String flag){
		try{
			Properties properties = new Properties();
			properties.load(CommonUtil.class.getResourceAsStream(
					"/properties/gsmkey.properties"));
			Result result = null;
			if(flag.equalsIgnoreCase("Shopper")){
				Sender sender = new Sender(properties.getProperty("shopperKey"));
				com.google.android.gcm.server.Message message = new com.google.android.gcm.server.Message.Builder().timeToLive(30)
						.delayWhileIdle(true).addData("message", messageStr).build();
				result = sender.send(message, deviceId, 1);
			}else if (flag.equalsIgnoreCase("Backer")) {
				Sender sender = new Sender(properties.getProperty("backerKey"));
				com.google.android.gcm.server.Message message = new com.google.android.gcm.server.Message.Builder().timeToLive(30)
						.delayWhileIdle(true).addData("message", messageStr).build();
				result = sender.send(message, deviceId, 1);
			}
		}catch(Exception e){

		}
	}
	
	public static Boolean validDiscount(Date startDate,Date endDate,Date startTime,Date endTime){
		
	   Date date = new Date();
		   Boolean checkDate;
		   Boolean checkTime;
		   
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(startDate);
		   cal.add(Calendar.DATE, -1);
		   Date fromDate = cal.getTime();
		   startDate.setTime(startTime.getTime());
		   if(fromDate !=null && endDate !=null){
		    checkDate = fromDate.compareTo(date) * date.compareTo(endDate) > 0;
		   } else if(fromDate ==null && endDate !=null){
			   checkDate = date.compareTo(endDate) > 0;
		   }
		   else{
			   checkDate =true;
		   }
		   
		   cal.setTime(date);  
		   int currentTimeInMinute= (cal.get(Calendar.HOUR_OF_DAY)*60)+cal.get(Calendar.MINUTE);
		   cal.setTime(startTime);
		   int startTimeInMinute= (cal.get(Calendar.HOUR_OF_DAY)*60)+cal.get(Calendar.MINUTE);
		   cal.setTime(endTime);
		   int endTimeInMinute= (cal.get(Calendar.HOUR_OF_DAY)*60)+cal.get(Calendar.MINUTE);
		   
		   if(startTime !=null && endTime !=null){
		   checkTime = ((startTimeInMinute <= currentTimeInMinute) && (currentTimeInMinute <= endTimeInMinute));
		   }else if(startTime ==null && endTime !=null){
			   checkTime = (currentTimeInMinute <= endTimeInMinute);
		   }else{
			   checkTime=true;
		   }
		   
		   if(checkDate && checkTime){
			   return true;
		   }
		   return checkDate;
	}
	
	  /**
	 * Converting time to one time Zone to another time zone.
	 * @author Anbukkani Gajendran
	 * @param date
	 * @param fromTZ
	 * @param toTZ
	 * @return Date
	 */
	public static Date convertTimeZone(Date date, TimeZone fromTZ , TimeZone toTZ)
		{
		    long fromTZDst = 0;
		    if(fromTZ.inDaylightTime(date))
		    {
		        fromTZDst = fromTZ.getDSTSavings();
		    }
		 
		    long fromTZOffset = fromTZ.getRawOffset() + fromTZDst;
		 
		    long toTZDst = 0;
		    if(toTZ.inDaylightTime(date))
		    {
		        toTZDst = toTZ.getDSTSavings();
		    }
		    long toTZOffset = toTZ.getRawOffset() + toTZDst;
		 
		    return new java.util.Date(date.getTime() + (toTZOffset - fromTZOffset));
		}
	
	/**
	 * Get time zone as string from date 
	 * @author Anbukkani Gajendran
	 * @param date
	 * @return TimeZone
	 */
	public static String getTimeZone(Date date) {
		String strDate	=date.toString();
		String[] strDates=strDate.split(" ");
		String strZome = strDates[strDates.length-2];
		return strZome;
	}
}