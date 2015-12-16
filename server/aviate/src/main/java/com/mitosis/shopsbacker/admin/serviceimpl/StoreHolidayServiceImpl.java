/**
 * 
 */
package com.mitosis.shopsbacker.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.admin.dao.StoreHolidayDao;
import com.mitosis.shopsbacker.admin.service.StoreHolidayService;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.StoreHoliday;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Service("storeHolidayServiceImpl")
public class StoreHolidayServiceImpl<T> implements StoreHolidayService<T> {
	
	@Autowired
	StoreHolidayDao<T> storeHolidayDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.admin.service.StoreHolidayService#add(com.mitosis
	 * .shopsbacker.model.StoreHoliday)
	 */
	@Override
	public void add(StoreHoliday storeHoliday) {
		storeHolidayDao.add(storeHoliday);;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.admin.service.StoreHolidayService#update(com.
	 * mitosis.shopsbacker.model.StoreHoliday)
	 */
	@Override
	public void update(StoreHoliday storeHoliday) {
		storeHolidayDao.update(storeHoliday);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.admin.service.StoreHolidayService#delete(com.
	 * mitosis.shopsbacker.model.StoreHoliday)
	 */
	@Override
	public void delete(String id) {
		storeHolidayDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.admin.service.StoreHolidayService#get(java.lang
	 * .String)
	 */
	@Override
	public StoreHolidayDao get(String id) {
		return storeHolidayDao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.admin.service.StoreHolidayService#get(com.mitosis
	 * .shopsbacker.model.Store)
	 */
	@Override
	public List<StoreHolidayDao> get(Store store) {
		return storeHolidayDao.get(store);
	}

}
