package com.mitosis.shopsbacker.customer.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.customer.dao.CustomerDao;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductInventory;

/**
 * @author prabakaran
 *
 * @param <T>
 * 
 *  Reviewed by Sundaram 27/11/2015
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
		Criteria criteria = getSession().createCriteria(Customer.class,"customer");
		criteria.createAlias("customer.addresses", "address");
		criteria.add(Restrictions.eq("customer.customerId", id));
		criteria.add(Restrictions.eq("address.isactive", 'Y'));
		return (Customer) findAll(criteria).get(0);
	}

	@Override
	public Customer getCustomerInfoByEmail(String email) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.eq("email", email));
		return (Customer) findUnique(criteria);
	}

	@Override
	public Customer getCustomerInfoByEmail(String email, String password) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("password", password));
		return (Customer) findUnique(criteria);
	}

	@Override
	public Customer getCustomerInfoByEmailWithPhoneno(String email,
			String phoneNo) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("phoneNo", phoneNo));
		return (Customer) findUnique(criteria);
	}

	@Override
	public Customer getCustomerInfoByPhoneNo(String phoneNo) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.eq("phoneNo", phoneNo));
		return (Customer) findUnique(criteria);
	}

}
