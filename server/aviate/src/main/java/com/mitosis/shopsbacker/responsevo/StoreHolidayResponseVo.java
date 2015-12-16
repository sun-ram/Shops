/**
 * 
 */
package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.StoreHolidayVo;

/**
 * @author Anbukkani Gajendiran
 *
 */
public class StoreHolidayResponseVo extends ResponseModel {

	List<StoreHolidayVo> StoreHolidays=new ArrayList<StoreHolidayVo>();

	public List<StoreHolidayVo> getStoreHolidays() {
		return StoreHolidays;
	}

	public void setStoreHolidays(List<StoreHolidayVo> storeHolidays) {
		StoreHolidays = storeHolidays;
	}
}
