package com.mitosis.shopsbacker.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CommonUtil {

	public Date dateFormat(Date date){
		Date dateformat =null;
		try{	
			String date1=date.toString();
			DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			dateformat = sdf.parse(date1);
		}catch(Exception e){
			e.printStackTrace();
		}
		return dateformat;
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
}
