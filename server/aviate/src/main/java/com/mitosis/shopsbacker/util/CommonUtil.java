package com.mitosis.shopsbacker.util;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CommonUtil {

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
			DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
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
	public static Object setAuditColumnInfo(String fullyQualifiedName)
			throws Exception {
		Class cls = Class.forName(fullyQualifiedName);
		Object obj = cls.newInstance();
		Class[] paramString = new Class[1];
		paramString[0] = String.class;
		// TODO:Need to get user from session and set here
		Method method = cls.getDeclaredMethod("setCreatedby", paramString);
		method.invoke(obj, "123");
		method = cls.getDeclaredMethod("setUpdatedby", paramString);
		method.invoke(obj, "123");
		Class[] paramString1 = new Class[1];
		paramString1[0] = Date.class;
		method = cls.getDeclaredMethod("setCreated", paramString);
		method.invoke(obj, new Date());
		method = cls.getDeclaredMethod("setCreated", paramString);
		method.invoke(obj, new Date());
		return obj;
	}
}
