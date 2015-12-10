package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.admin.dao.StoreDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;

/**
 * @author JAI BHARATHI
 * 
 * Reviewed by Sundaram 28/11/2015
 */
@Repository
public class StoreDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
		StoreDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void saveStore(Store store) {
		try {
			save((T) store);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStore(Store store) {
		try {
			update((T) store);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeStore(Store store) {
		try {
			delete((T) store);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Store getStoreById(String id) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Store.class);
			criteria.add(Restrictions.eq("storeId", id));
			Store store=(Store)findUnique(criteria);
		//	Store store=(Store) getSession().get(Store.class, id);
		//	Hibernate.initialize(store.getWarehouses());
			return store;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Store> getStoreByMerchant(Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Store.class);
			criteria.add(Restrictions.eq("merchant", merchant));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			criteria.addOrder(Order.desc("created"));
			return ((List<Store>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Store> getShopList(String city) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Store.class,
					"store");
			criteria.createAlias("store.users", "user");
			criteria.createAlias("user.address", "address");
			criteria.add(Restrictions.like("address.city", "%" + city + "%"));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Store>) findAll(criteria));
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Store> getShopList(String city, String address) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Store.class,
					"store");
			criteria.createAlias("store.users", "user");
			criteria.createAlias("user.address", "address");
			criteria.add(Restrictions.like("address.city", "%" + city + "%"));
			criteria.add(Restrictions.like("address.address1", "%" + address
					+ "%"));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Store>) findAll(criteria));
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<String> getShopCityList() {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(Address.class);
			ProjectionList proList = Projections.projectionList();
			proList.add(Projections.property("city"));
			criteria.setProjection(proList);
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<String>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Store> getStoreListByName(String name, Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Store.class,
					"store");
			
			criteria.add(Restrictions.eq("name", name));
			criteria.add(Restrictions.eq("merchant", merchant));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Store>) findAll(criteria));
		} catch (HibernateException e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getStoreList() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Store.class);
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Store>) findAll(criteria));
		} catch (HibernateException e) {
			e.printStackTrace();
			throw(e);
		}
	}

	public int inActiveStores(Merchant merchant) {
				Query updateQuery = getSession().createQuery(
						"update Store set isactive='N' where merchant = :merchant");
				updateQuery.setParameter("merchant", merchant);
				return updateQuery.executeUpdate();
			}
	
}
