package com.mitosis.shopsbacker.customer.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.customer.dao.MyCartDao;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;

@Repository
public class MyCartDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
MyCartDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void updateCart(MyCart mycart) {
		try {
			update((T) mycart);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MyCart> getMyCartList(Customer customer, Store store) {
		try {
		DetachedCriteria criteria = DetachedCriteria.forClass(MyCart.class);
		criteria.add(Restrictions.eq("customer", customer));
		criteria.add(Restrictions.eq("store", store));
		return (List<MyCart>) findAll(criteria);
		}  catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeFromCart(MyCart mycart) {
		try {
			delete((T) mycart);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addToCart(MyCart mycart) {
		try {
			save((T) mycart);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
		
	}

	@Override
	public MyCart getCartDetailFromId(String myCartId) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MyCart.class);
			criteria.add(Restrictions.eq("myCartId", myCartId));
			return (MyCart) findUnique(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public MyCart getCartByCustomerStoreanProductId(Customer customer,
			Product product, Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MyCart.class);
			criteria.add(Restrictions.eq("customer", customer));
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("product", product));
			return (MyCart) findUnique(criteria);
			}  catch (Exception e) {
				e.printStackTrace();
				throw(e);
			}
	}

	/**
	 * 
	 * @author Prabakaran
	 * 
	 * @Return This method will return number of record can be deleted
	 * 
	 */
	public int deleteFromCart(String customerId, String storeId) {
		Query deleteQuery = getSession().createQuery("delete from MyCart where store.storeId = :storeId and customer.customerId = :customerId");
		deleteQuery.setParameter("storeId", storeId);
		deleteQuery.setParameter("customerId", customerId);
		return deleteQuery.executeUpdate();
	}

}
