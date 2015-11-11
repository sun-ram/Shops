package com.mitosis.shopsbacker.serviceimpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/*import com.mitosis.shopsbacker.model.CityMaster;
import com.mitosis.shopsbacker.model.CompanyMaster;
import com.mitosis.shopsbacker.model.CountryMaster;
import com.mitosis.shopsbacker.model.DistrictMaster;
import com.mitosis.shopsbacker.model.NationalityMaster;
import com.mitosis.shopsbacker.model.StateMaster;*/
import com.mitosis.shopsbacker.dao.MutualDao;
import com.mitosis.shopsbacker.service.MutualService;

public class MutualServiceImpl<T> implements MutualService<T> {
	@Autowired
	MutualDao mutualDao;
	
	public MutualDao getMutualDao() {
		return mutualDao;
	}

	public void setMutualDao(MutualDao mutualDao) {
		this.mutualDao = mutualDao;
	}

	/*@Override
	public List<NationalityMaster> getNationality() {
		// TODO Auto-generated method stub
		return null;
	}*/

/*	@Override
	public List<CompanyMaster> getCompany(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StateMaster> getState(String country) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public List<CityMaster> getCity(BigDecimal districtId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CountryMaster> getCountry() {
		// TODO Auto-generated method stub
		return getMutualDao().getCountry();
	}*/
/*
	@Override
	public List<DistrictMaster> getDistrict(String state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StateMaster> getState() {
		// TODO Auto-generated method stub
		return null;
	}
*/
	/*@Override
	public List<CompanyMaster> getCompany(BigDecimal countryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StateMaster> getState(BigDecimal countryId) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	/*@Override
	public List<DistrictMaster> getDistrict(BigDecimal stateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StateMaster> getState() {
		// TODO Auto-generated method stub
		return null;
	}*/
}
