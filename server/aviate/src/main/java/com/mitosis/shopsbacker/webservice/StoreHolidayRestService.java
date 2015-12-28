/**
 * 
 */
package com.mitosis.shopsbacker.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.StoreHolidayService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.StoreHoliday;
import com.mitosis.shopsbacker.responsevo.StoreHolidayResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.admin.StoreHolidayVo;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Controller("storeHolidayRestService")
@Path("storeholiday")
public class StoreHolidayRestService {
public Logger log=Logger.getLogger(StoreHolidayRestService.class);

@Autowired
StoreHolidayService<T> storeHolidayService;

@Autowired
StoreService<T> storeService;

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String addStoreHoliday(StoreHolidayVo storeHolidayVo) {
		StoreHolidayResponseVo response = new StoreHolidayResponseVo();
	String	responseStr="";
		try {
			StoreHoliday storeHoliday=null;
			if(storeHolidayVo.getStoreHolidayId()!=null){
				storeHoliday=(StoreHoliday) CommonUtil.setAuditColumnInfo(StoreHoliday.class.getName(), null);
				storeHoliday.setIsactive('Y');
				storeHoliday.setReason(storeHolidayVo.getReason());
				storeHoliday.setHolidayDate(storeHolidayVo.getHolidayDate());
				storeHolidayService.add(storeHoliday);
			}else{
				storeHoliday.setReason(storeHolidayVo.getReason());
				storeHoliday.setHolidayDate(storeHolidayVo.getHolidayDate());
				storeHoliday.setUpdated(new Date());
				storeHoliday.setUpdatedby("123");
				storeHolidayService.update(storeHoliday);
			}
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue()); 
			response.setErrorString(e.getMessage());
		}
		try {
			responseStr=CommonUtil.getObjectMapper(response);
		} catch (Exception e) {
			log.error(CommonUtil.getErrorMessage(e));
			e.printStackTrace();
		}
		return responseStr;
	}
	@Path("/storeholidays")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getStoreHolidays(StoreHolidayVo storeHolidayVo) {
		StoreHolidayResponseVo response = new StoreHolidayResponseVo();
	String	responseStr="";
		try {
			String storeId = storeHolidayVo.getStoreId();
			Store store = storeService.getStoreById(storeId);
			List<StoreHoliday> storeHolidays = store.getStoreHolidays();
			StoreHolidayVo storeHolidayvo= new StoreHolidayVo();
			if(!storeHolidays.isEmpty()){
				StoreHoliday storeHoliday = storeHolidays.get(0);
				String holidayDate = storeHoliday.getHolidayDate();
				List<Date> holidays = new ArrayList<Date>();
				if (!holidayDate.isEmpty()) {
					String[] holidayDates = holidayDate.split(",");
					for (String holiday : holidayDates) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"E MMM dd HH:mm:ss Z yyyy");
						Date date = formatter.parse(holiday);
						holidays.add(date);
					}
				}
				storeHolidayvo.setStoreHolidayId(storeHoliday.getStoreHolidayId());
				storeHolidayvo.setReason(storeHoliday.getReason());
				storeHolidayvo.setHolidays(holidays);
			}
			
			response.setStoreHoliday(storeHolidayvo);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue()); 
			response.setErrorString(e.getMessage());
		}
		try {
			responseStr=CommonUtil.getObjectMapperWithSerializationFeature(response);
		} catch (Exception e) {
			log.error(CommonUtil.getErrorMessage(e));
			e.printStackTrace();
		}
		return responseStr;
	}
	
	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String deleteStoreHoliday(StoreHolidayVo storeHolidayVo) {
		StoreHolidayResponseVo response = new StoreHolidayResponseVo();
	String	responseStr="";
		try {
			storeHolidayService.delete(storeHolidayVo.getStoreHolidayId());
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue()); 
			response.setErrorString(e.getMessage());
		}
		try {
			responseStr=CommonUtil.getObjectMapper(response);
		} catch (Exception e) {
			log.error(CommonUtil.getErrorMessage(e));
			e.printStackTrace();
		}
		return responseStr;
	}
}
