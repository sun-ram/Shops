package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mitosis.shopsbacker.admin.dao.StoragebinDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Storagebin;

public class StoragebinDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
StoragebinDao<T>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public List<T> findAll(DetachedCriteria criteria) {
		try {
			return criteria.getExecutableCriteria(getSession()).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw(e);
		}
	}

	
	@Override
	public void addStorageBin(Storagebin storagebin) {
		try {
			save((T) storagebin);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	
	@Override
	public void removeStorageBin(Storagebin storagebin) {
		try {
			delete((T) storagebin);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	
	@Override
	public List<Storagebin> uniqueNameChecking(String binName,
			String warehouseId) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Storagebin.class);
			criteria.add(Restrictions.eq("name", binName));
			//TODO: Need to add warehouse object as a restriction 
			/*criteria.add(Restrictions.eq("warehouseId", warehouseId));*/
			return (List<Storagebin>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	
	@Override
	public Storagebin getStoragebinById(String storagebinId) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Storagebin.class);
			criteria.add(Restrictions.eq("storagebinId", storagebinId));
			return ((List<Storagebin>) findAll(criteria)).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

}
