package com.mitosis.shopsbacker.customer.daoimpl;

import java.io.Serializable;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.customer.dao.CustomerDao;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Merchant;

/**
 * @author prabakaran
 *
 * @param <T>
 */
@Repository
public class CustomerDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
		CustomerDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void saveCustomer(Customer customer) {
		try {
			save((T) customer);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateCustomer(Customer customer) {
		try {
			update((T) customer);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteCustomer(Customer customer) {
		try {
			delete((T) customer);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Customer getCustomerInfoById(String id) {
		return (Customer) getSession().get(Customer.class, id);
	}

	@Override
	public Customer getCustomerInfoByEmail(String email) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Merchant.class);
		criteria.add(Restrictions.eq("email", email));
		return (Customer) findAll(criteria);
	}

}
