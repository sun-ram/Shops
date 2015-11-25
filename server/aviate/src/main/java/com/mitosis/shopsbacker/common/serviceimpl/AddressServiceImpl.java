package com.mitosis.shopsbacker.common.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.dao.AddressDao;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.customer.dao.CustomerDao;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Country;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.State;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.common.AddressVo;
import com.mitosis.shopsbacker.vo.common.CountryVo;
import com.mitosis.shopsbacker.vo.common.StateVo;
import com.mitosis.shopsbacker.vo.customer.CustomerVo;

@Service("addressServiceImpl")
public class AddressServiceImpl<T> implements AddressService<T> {

	@Autowired
	AddressDao<T> addressDao;
	
	@Autowired
	CustomerService<T> customerService;

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
	public Address getAddress(String id) {
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

	@Override
	public Address setAddress(AddressVo addressVo) throws Exception {
		Address address = null;
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
		AddressVo addressVo = new AddressVo();
		addressVo.setAddressId(address.getAddressId());
		addressVo.setAddress1(address.getAddress1());
		addressVo.setAddress2(address.getAddress2());
		addressVo.setCity(address.getCity());
		addressVo.setPhoneNo(address.getPhoneNo());
		addressVo.setPinCode(address.getPinCode());
		addressVo.setLatitude(address.getLatitude());
		addressVo.setLongitude(address.getLongitude());
		addressVo.setCountry(setCountryVo(address.getCountry()));
		addressVo.setState(setCountryVo(address.getState()));
		addressVo.setCustomer(setCustomerVo(address.getCustomer()));
		return addressVo;
	}
	
	public CountryVo setCountryVo(Country country) {
		CountryVo countryVo = new CountryVo();
		countryVo.setName(country.getName());
		countryVo.setCountryId(country.getCountryId());
		countryVo.setCurrencyCode(country.getCurrencyCode());
		countryVo.setCurrencyName(country.getCurrencyName());
		return countryVo;
	}

	public StateVo setCountryVo(State state) {
		StateVo stateVo = new StateVo();
		stateVo.setName(state.getName());
		stateVo.setStateId(state.getStateId());
		return stateVo;
	}
	
	public CustomerVo setCustomerVo(Customer customer) {
		CustomerVo customerVo = new CustomerVo();
		customerVo.setCustomerId(customer.getCustomerId());
		customerVo.setName(customer.getName());
		return customerVo;
	}


}
