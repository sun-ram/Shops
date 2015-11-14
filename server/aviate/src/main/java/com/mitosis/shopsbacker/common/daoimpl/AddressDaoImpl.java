package com.mitosis.shopsbacker.common.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mitosis.shopsbacker.common.dao.AddressDao;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.User;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public class AddressDaoImpl<T> extends CustomHibernateDaoSupport<T> implements AddressDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void saveAddress(Address address) {
		try{
			save((T) address);
		}catch(Exception e){
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateAddress(Address address) {
		try{
			update((T) address);
		}catch(Exception e){
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteAddress(Address address) {
		try{
			delete((T) address);
		}catch(Exception e){
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAddress(String id) {
		return (List<Address>) getSession().get(Address.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAddress(Customer customer) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Address.class);
		criteria.add(Restrictions.eq("customer", customer));
		return (List<Address>) findAll(criteria);
	}

	@Override
	public Address getAddress(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Address.class);
		criteria.add(Restrictions.eq("user", user));
		return (Address) findAll(criteria);
	}

}
