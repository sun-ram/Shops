/**
 * 
 */
package com.mitosis.shopsbacker.admin.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.StoreHoliday;

/**
 * @author Anbukkani Gajendiran
 *
 */
public interface StoreHolidayDao<T> {

	public void add(StoreHoliday storeHoliday);
	
	public void update(StoreHoliday storeHoliday);
	
	public void delete(String id);
	
	public StoreHolidayDao get(String id);
	
	public List<StoreHolidayDao> get(Store store);
	
}
