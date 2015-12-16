/**
 * 
 */
package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.admin.dao.StoreHolidayDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.StoreHoliday;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Repository
public class StoreHolidayDaoImpl<T> extends CustomHibernateDaoSupport<T>
		implements StoreHolidayDao, Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.admin.dao.StoreHolidayDao#add(com.mitosis.shopsbacker
	 * .model.StoreHoliday)
	 */
	@Override
	public void add(StoreHoliday storeHoliday) {
		save((T) storeHoliday);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.admin.dao.StoreHolidayDao#update(com.mitosis.
	 * shopsbacker.model.StoreHoliday)
	 */
	@Override
	public void update(StoreHoliday storeHoliday) {
		update((T) storeHoliday);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.admin.dao.StoreHolidayDao#delete(com.mitosis.
	 * shopsbacker.model.StoreHoliday)
	 */
	
	public void delete(String id) {

		Query updateQuery = getSession().createQuery(
				"delete StoreHoliday where storeHolidayId = :storeHolidayId");
		updateQuery.setParameter("storeHolidayId", id);
		  updateQuery.executeUpdate();
	

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.admin.dao.StoreHolidayDao#get(java.lang.String)
	 */
	@Override
	public StoreHolidayDao get(String id) {

		return (StoreHolidayDao) getSession().get(StoreHolidayDao.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mitosis.shopsbacker.admin.dao.StoreHolidayDao#get()
	 */
	@Override
	public List<StoreHolidayDao> get(Store store) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(StoreHolidayDao.class);
		criteria.add(Restrictions.eq("store", store));
		criteria.add(Restrictions.eq("isactive", 'Y'));
		return (List<StoreHolidayDao>) findAll(criteria);
	}

}
