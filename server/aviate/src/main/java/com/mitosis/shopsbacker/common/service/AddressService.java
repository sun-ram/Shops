package com.mitosis.shopsbacker.common.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Country;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.State;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.AddressVo;

public interface AddressService<T> {
	
	public void saveAddress(Address address);
	
	public void updateAddress(Address address);
	
	public void deleteAddress(Address address);
	
	public Address getAddress(String id);
	
	public List<Address> getAddress(Customer customer);
	
	public Address getAddress(User user);
	
	public List<Country> getCountry();
	
	public Country getCountry(String id);
	
	public List<State> getState(String countryId);
	
	public State getStateById(String id);
	
	public AddressVo setAddressVo(Address address);
	
	public Address setAddress(AddressVo addressVo) throws Exception;

}
