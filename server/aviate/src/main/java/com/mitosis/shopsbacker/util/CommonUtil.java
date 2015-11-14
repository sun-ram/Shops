package com.mitosis.shopsbacker.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CommonUtil {

	/**
	 * changing date format
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
}
