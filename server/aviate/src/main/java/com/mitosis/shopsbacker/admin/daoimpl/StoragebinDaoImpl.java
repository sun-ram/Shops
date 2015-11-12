package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mitosis.shopsbacker.admin.dao.StoragebinDao;
import com.mitosis.shopsbacker.common.dao.CommonDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Storagebin;

public class StoragebinDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
StoragebinDao<T>, CommonDao<T>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(DetachedCriteria criteria) {
		return criteria.getExecutableCriteria(getSession()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addStorageBin(Storagebin storagebin) {
		save((T) storagebin);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeStorageBin(Storagebin storagebin) {
		delete((T) storagebin);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Storagebin> uniqueNameChecking(String binName,
			String warehouseId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Storagebin.class);
		criteria.add(Restrictions.eq("name", binName));
		//TODO: Need to add warehouse object as a restriction 
		/*criteria.add(Restrictions.eq("warehouseId", warehouseId));*/
		return (List<Storagebin>) findAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Storagebin getStoragebinById(String storagebinId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Storagebin.class);
		criteria.add(Restrictions.eq("storagebinId", storagebinId));
		return ((List<Storagebin>) findAll(criteria)).get(0);
	}

}
