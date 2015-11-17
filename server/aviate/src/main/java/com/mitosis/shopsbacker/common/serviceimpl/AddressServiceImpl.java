package com.mitosis.shopsbacker.common.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.dao.AddressDao;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Country;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.State;
import com.mitosis.shopsbacker.model.User;

@Service("addressServiceImpl")
public class AddressServiceImpl<T> implements AddressService<T> {

	@Autowired
	AddressDao<T> addressDao;
	
	public AddressDao<T> getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDao<T> addressDao) {
		this.addressDao = addressDao;
	}

	@Override
	@Transactional
	public void saveAddress(Address address) {
		getAddressDao().saveAddress(address);
		
	}

	@Override
	@Transactional
	public void updateAddress(Address address) {
		getAddressDao().updateAddress(address);
	}

	@Override
	@Transactional
	public void deleteAddress(Address address) {
		getAddressDao().deleteAddress(address);
	}

	@Override
	@Transactional
	public List<Address> getAddress(String id) {
		return getAddressDao().getAddress(id);
	}

	@Override
	@Transactional
	public List<Address> getAddress(Customer customer) {
		return getAddressDao().getAddress(customer);
	}

	@Override
	@Transactional
	public Address getAddress(User user) {
		return getAddressDao().getAddress(user);
	}

	@Override
	@Transactional
	public List<Country> getCountry() {
		return getAddressDao().getCountry();
	}

	@Override
	@Transactional
	public List<State> getState(String countryId) {
		return getAddressDao().getState(getAddressDao().getCountry(countryId));
	}

	@Override
	public Country getCountry(String id) {
		return getAddressDao().getCountry(id);
	}

	@Override
	public State getStateById(String id) {
		return getAddressDao().getStateById(id);
	}

}
