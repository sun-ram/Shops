package com.mitosis.aviate.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.daoimpl.BaseService;
import com.mitosis.aviate.model.service.ResponseModel;


public final class CommonUtil {


	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*:: This function to find near by based on latitude and longitude  :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

	public static double distance(double lat1, double lon1, double lat2, double lon2)
	{
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;

		return (dist);
	}
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::  This function converts decimal degrees to radians             :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::  This function converts radians to decimal degrees             :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	public static JSONObject statusMessage(boolean flag){
		JSONObject response = new JSONObject();
		try{			
			if(flag){
				response.put("status", AVMessageStatus.SUCCESS.getValue());				
			}else{
				response.put("status", AVMessageStatus.FAILURE.getValue());
				response.put("errorString", "");
				response.put("errorCode", "");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
	public static JSONObject addStatusMessage(Exception e){
		JSONObject response = new JSONObject();
		try {
				response.put("status", AVMessageStatus.FAILURE.getValue());
				response.put("errorString", e.getMessage());
				response.put("errorCode", "");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return response;
	}
	
	public static JSONObject addStatusMessage(String e){
		JSONObject response = new JSONObject();
		try {
				response.put("status", AVMessageStatus.FAILURE.getValue());
				response.put("errorString", e);
				response.put("errorCode", "");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return response;
	}
	/**
	 * adding error messages to ResponseModel
	 * @author Anbukkani G
	 * @param e
	 * @param response
	 * @return ResponseModel
	 */
	public static ResponseModel addStatusMessage(Exception e,ResponseModel response){
				response.setStatus(AVMessageStatus.FAILURE.getValue());
				response.setErrorString(e.getMessage());
				response.setErrorCode("");
		return response;
	}
	
	public static boolean sendMail(String to, String subject, String body){
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
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress toAddress = new InternetAddress();
			toAddress = new InternetAddress(to);
			message.addRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject(subject);
			message.setContent(message, "text/html");
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			flag = true;
		}catch (AddressException ae) {
			ae.printStackTrace();
		}catch (MessagingException me) {
			me.printStackTrace();
		}
		return flag;
	}
	
	public Date stringToDate(String date){
		Date dateformat =null;
		try{			
			DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			dateformat = sdf.parse(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return dateformat;
	}
	
	public String dateToString(String date){
		String dateString =null;
		try{			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			dateString = sdf.format(date); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return dateString;
	}
	
	
}
