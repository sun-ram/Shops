package com.mitosis.shopsbacker.common.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.mitosis.shopsbacker.common.dao.AddressDao;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.City;
import com.mitosis.shopsbacker.model.Country;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.State;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.common.AddressVo;
import com.mitosis.shopsbacker.vo.common.CountryVo;
import com.mitosis.shopsbacker.vo.common.StateVo;
import com.mitosis.shopsbacker.vo.customer.CustomerVo;

/**
 * @author prabakaran
 *
 * @param <T>
 * 
 *            Reviewed by Sundaram 27/11/2015
 */

@Service("addressServiceImpl")
public class AddressServiceImpl<T> implements AddressService<T> {

	@Autowired
	AddressDao<T> addressDao;

	@Autowired
	CustomerService<T> customerService;

	CustomerVo customerVo = null;
	Address address = null;
	AddressVo addressVo = null;
	CountryVo countryVo = null;
	StateVo stateVo = null;

	public AddressDao<T> getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDao<T> addressDao) {
		this.addressDao = addressDao;
	}

	@Override
	public void saveAddress(Address address) {
		getAddressDao().saveAddress(address);

	}

	@Override
	public void updateAddress(Address address) {
		getAddressDao().updateAddress(address);
	}

	@Override
	public void deleteAddress(Address address) {
		getAddressDao().deleteAddress(address);
	}

	@Override
	public Address getAddress(String id) {
		return getAddressDao().getAddress(id);
	}

	@Override
	public List<Address> getAddress(Customer customer) {
		return getAddressDao().getAddress(customer);
	}

	@Override
	public Address getAddress(User user) {
		return getAddressDao().getAddress(user);
	}

	@Override
	public List<Country> getCountry() {
		return getAddressDao().getCountry();
	}

	@Override
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

	@Override
	public Address setAddress(AddressVo addressVo) throws Exception {
		if (addressVo.getAddressId() == null) {
			address = (Address) CommonUtil.setAuditColumnInfo(Address.class
					.getName());
			address.setIsactive('Y');
		} else {
			address = addressDao.getAddress(addressVo.getAddressId());
			address.setUpdated(new Date());
			// TODO need to get user from session and set to updatedby
			address.setUpdatedby("123");
		}
		address.setName(addressVo.getName());
		address.setAddress1(addressVo.getAddress1());
		address.setAddress2(addressVo.getAddress2());
		address.setCity(addressVo.getCity());
		address.setPhoneNo(addressVo.getPhoneNo());
		address.setPinCode(addressVo.getPinCode());
		address.setLatitude(addressVo.getLatitude());
		address.setLongitude(addressVo.getLongitude());
		address.setCountry(getCountry(addressVo.getCountry().getCountryId()));
		address.setState(getStateById(addressVo.getState().getStateId()));
		return address;

	}

	@Override
	public AddressVo setAddressVo(Address address) {
		addressVo = new AddressVo();
		addressVo.setAddressId(address.getAddressId());
		addressVo.setName(address.getName());
		addressVo.setAddress1(address.getAddress1());
		addressVo.setAddress2(address.getAddress2());
		addressVo.setCity(address.getCity());
		addressVo.setPhoneNo(address.getPhoneNo());
		addressVo.setPinCode(address.getPinCode());
		addressVo.setLatitude(address.getLatitude());
		addressVo.setLongitude(address.getLongitude());
		addressVo.setCountry(setCountryVo(address.getCountry()));
		addressVo.setState(setCountryVo(address.getState()));
		Customer customer = address.getCustomer();
		if (customer != null) {
			addressVo.setCustomer(setCustomerVo(customer));
		}
		return addressVo;
	}

	public CountryVo setCountryVo(Country country) {
		countryVo = new CountryVo();
		countryVo.setName(country.getName());
		countryVo.setCountryId(country.getCountryId());
		countryVo.setCurrencyCode(country.getCurrencyCode());
		countryVo.setCurrencyName(country.getCurrencyName());
		return countryVo;
	}

	public StateVo setCountryVo(State state) {
		stateVo = new StateVo();
		stateVo.setName(state.getName());
		stateVo.setStateId(state.getStateId());
		return stateVo;
	}

	public CustomerVo setCustomerVo(Customer customer) {
		customerVo = new CustomerVo();
		customerVo.setCustomerId(customer.getCustomerId());
		customerVo.setName(customer.getName());
		return customerVo;
	}

	public JsonNode getLatLongByAddress(AddressVo addressVo) {
		String full_address = addressVo.getAddress1() + ","
				+ addressVo.getAddress2() != null ? addressVo.getAddress2()
				+ "," : "" + addressVo.getCity() + ","
				+ addressVo.getState().getName() + ","
				+ addressVo.getCountry().getName() + ","
				+ addressVo.getPinCode();
		JsonNode location = CommonUtil.getLatLong(full_address);
		return location;
	}

	@Override
	public List<City> getCity() {
		return addressDao.getCity();
	}

}
