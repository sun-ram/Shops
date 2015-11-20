package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.StoragebinDao;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Warehouse;

/**
 * @author fayaz
 */
@Repository
@Transactional
public class StoragebinDaoImpl<T> extends CustomHibernateDaoSupport<T>
		implements StoragebinDao<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<T> findAll(DetachedCriteria criteria) {
		try {
			return criteria.getExecutableCriteria(getSession()).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public void addStorageBin(Storagebin storagebin) {
		try {
			save((T) storagebin);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public void removeStorageBin(Storagebin storagebin) {
		try {
			delete((T) storagebin);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public List<Storagebin> getStoragebin(String binName, Warehouse warehouse) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(Storagebin.class);
			criteria.add(Restrictions.eq("name", binName));
			criteria.add(Restrictions.eq("warehouse", warehouse));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<Storagebin>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public Storagebin getStoragebinById(String storagebinId) {
		try {

			return (Storagebin) getSession()
					.get(Storagebin.class, storagebinId);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public List<Storagebin> listOfStorageBins(Warehouse warehouse) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(Storagebin.class);
			criteria.add(Restrictions.eq("warehouse", warehouse));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<Storagebin>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
	
	@Override
	public void updateStorageBin(Storagebin storagebin) {
		try {
			update((T) storagebin);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

}
