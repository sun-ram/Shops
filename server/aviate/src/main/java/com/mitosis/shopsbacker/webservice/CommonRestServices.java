package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.model.Country;
import com.mitosis.shopsbacker.model.State;
import com.mitosis.shopsbacker.responsevo.CountryResponse;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.common.CountryVo;
import com.mitosis.shopsbacker.vo.common.StateVo;

@Path("/common")
public class CommonRestServices<T> {
	Logger log = Logger.getLogger(CommonRestServices.class);

	@Autowired
	AddressService<T> addressService;

	public AddressService<T> getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService<T> addressService) {
		this.addressService = addressService;
	}

	@Path("/country")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CountryResponse getCountry() {
		CountryResponse countryResponse = new CountryResponse();
		try {
			List<Country> countries = getAddressService().getCountry();
			List<CountryVo> countryList = new ArrayList<CountryVo>();
			for (Country country : countries) {
				CountryVo countryVo = new CountryVo();
				countryVo.setCode(country.getCode());
				countryVo.setCountryId(country.getCountryId());
				countryVo.setCurrencyCode(country.getCurrencyCode());
				countryVo.setCurrencyName(country.getCurrencyName());
				countryVo.setName(country.getName());
				//List<State> states = getAddressService().getState(country.getCountryId());
				List<StateVo> stateList = new ArrayList<StateVo>();
				for (State state : country.getStates()) {
					StateVo stateVo = new StateVo();
					stateVo.setName(state.getName());
					stateVo.setStateId(state.getStateId());
					stateList.add(stateVo);
					countryVo.setStates(stateList);
				}
				countryList.add(countryVo);
			}
			countryResponse.setCountries(countryList);
		} catch (Exception e) {
			e.printStackTrace();
			countryResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			countryResponse.setErrorString(e.getMessage());
		}
		return countryResponse;

	}
	
	/*@Path("/state")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CountryResponse getState(Country country) {
		CountryResponse countryResponse = new CountryResponse();
		try {
			List<State> states = getAddressService().getState(country.getCountryId());
				List<StateVo> stateList = new ArrayList<StateVo>();
				for (State state : country.getStates()) {
					StateVo stateVo = new StateVo();
					stateVo.setName(state.getName());
					stateVo.setStateId(state.getStateId());
					stateList.add(stateVo);
					countryVo.setStates(stateList);
				}
			countryResponse.setCountries(countryList);
		} catch (Exception e) {
			e.printStackTrace();
			countryResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			countryResponse.setErrorString(e.getMessage());
		}
		return countryResponse;

	}*/
}
