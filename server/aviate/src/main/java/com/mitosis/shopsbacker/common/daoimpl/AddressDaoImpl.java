package com.mitosis.shopsbacker.common.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.dao.AddressDao;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Area;
import com.mitosis.shopsbacker.model.City;
import com.mitosis.shopsbacker.model.Country;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.State;
import com.mitosis.shopsbacker.model.User;

/**
 * @author prabakaran
 *
 * @param <T>
 * 
 *            Reviewed by Sundaram 27/11/2015
 */
@Repository
public class AddressDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
		AddressDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void saveAddress(Address address) {
		try {
			save((T) address);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateAddress(Address address) {
		try {
			update((T) address);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteAddress(Address address) {
		try {
			delete((T) address);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Address getAddress(String id) {
		return (Address) getSession().get(Address.class, id);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> getCountry() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Country.class);
		criteria.add(Restrictions.eq("isactive", 'Y'));
		return (List<Country>) findAllEh(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getState(Country country) {
		DetachedCriteria criteria = DetachedCriteria.forClass(State.class);
		criteria.add(Restrictions.eq("country", country));
		return (List<State>) findAll(criteria);
	}

	@Override
	public Country getCountry(String id) {
		return (Country) getSession().get(Country.class, id);
	}

	@Override
	public State getStateById(String id) {
		return (State) getSession().get(State.class, id);
	}

	public List<City> getCities() {
		DetachedCriteria criteria = DetachedCriteria.forClass(City.class);
		return (List<City>) findAll(criteria);
	}

	public List<Area> getAreas(City city) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Area.class);
		criteria.add(Restrictions.eq("isactive", 'Y'));
		criteria.add(Restrictions.eq("city", city));
		return (List<Area>) findAll(criteria);
	}

	public City getCity(String id) {
		return (City) getSession().get(City.class, id);
	}
}
