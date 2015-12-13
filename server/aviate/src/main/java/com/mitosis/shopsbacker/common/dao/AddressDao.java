package com.mitosis.shopsbacker.common.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.City;
import com.mitosis.shopsbacker.model.Country;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.State;
import com.mitosis.shopsbacker.model.User;


public interface AddressDao<T> {
	
	public void saveAddress(Address address);
	
	public void updateAddress(Address address);
	
	public void deleteAddress(Address address);
	
	public Address getAddress(String id);
	
	public List<Address> getAddress(Customer customer);
	
	public Address getAddress(User customer);
	
	public List<Country> getCountry();
	
	public Country getCountry(String id);
	
	public List<State> getState(Country country);
	
	public State getStateById(String id);
	
	public List<City> getCity();


}
