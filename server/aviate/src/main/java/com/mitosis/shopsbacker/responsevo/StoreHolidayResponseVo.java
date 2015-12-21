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

	StoreHolidayVo storeHoliday;

	public  StoreHolidayVo  getStoreHoliday() {
		return storeHoliday ;
	}

	public void setStoreHoliday( StoreHolidayVo  storeHoliday ) {
		this.storeHoliday = storeHoliday ;
	}
}
