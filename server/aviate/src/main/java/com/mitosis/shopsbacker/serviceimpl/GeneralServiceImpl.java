package com.mitosis.shopsbacker.serviceimpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*import org.springframework.transaction.annotation.Transactional;*/

import com.mitosis.shopsbacker.dao.GeneralDao;
/*import com.mitosis.shopsbacker.model.BankBranch;
 import com.mitosis.shopsbacker.model.BankMaster;
 import com.mitosis.shopsbacker.model.BusinessComponent;
 import com.mitosis.shopsbacker.model.BussComponentConfDetail;
 import com.mitosis.shopsbacker.model.CityMaster;
 import com.mitosis.shopsbacker.model.CityMasterDesc;
 import com.mitosis.shopsbacker.model.CompanyMaster;
 import com.mitosis.shopsbacker.model.CompanyMasterDesc;
 import com.mitosis.shopsbacker.model.ContactType;
 import com.mitosis.shopsbacker.model.CountryMaster;
 import com.mitosis.shopsbacker.model.CountryMasterDesc;
 import com.mitosis.shopsbacker.model.DistrictMaster;
 import com.mitosis.shopsbacker.model.DistrictMasterDesc;
 import com.mitosis.shopsbacker.model.EmploymentType;
 import com.mitosis.shopsbacker.model.IdentityType;
 import com.mitosis.shopsbacker.model.NationalityMaster;
 import com.mitosis.shopsbacker.model.RuleApplicationMaster;
 import com.mitosis.shopsbacker.model.RulePageMaster;
 import com.mitosis.shopsbacker.model.StateMaster;
 import com.mitosis.shopsbacker.model.StateMasterDesc;*/
import com.mitosis.shopsbacker.service.CommonService;
import com.mitosis.shopsbacker.service.GeneralService;
import com.mitosis.shopsbacker.service.MutualService;

@SuppressWarnings("serial")
@Service("generalServiceImpl")
public class GeneralServiceImpl<T> implements GeneralService<T>,
		CommonService<T>, MutualService<T>, Serializable {

	@Autowired
	GeneralDao<T> generalDao;

	public GeneralDao<T> getGeneralDao() {
		return generalDao;
	}

	public void setGeneralDao(GeneralDao<T> generalDao) {
		this.generalDao = generalDao;
	}

	/*
	 * @Override public List<CountryMaster> getCountry() { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public List<StateMaster> getState() { // TODO Auto-generated
	 * method stub return null; }
	
	 * @Override public List<StateMaster> getState(BigDecimal countryId) { //
	 * TODO Auto-generated method stub return null; }
	 */

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub

	}

	/*
	 * @Transactional
	 * 
	 * @Override public List<CountryMasterDesc> getCountryList(BigDecimal
	 * languageId) {
	 * 
	 * return getGeneralDao().getCountryList(languageId); }
	 * 
	 * @Transactional
	 * 
	 * @Override public List<CountryMasterDesc> getNationalityList(BigDecimal
	 * languageId) {
	 * 
	 * return getGeneralDao().getNationalityList(languageId); }
	 * 
	 * @Transactional
	 * 
	 * @Override public List<StateMasterDesc> getStateList(BigDecimal
	 * languageId, BigDecimal countryId) {
	 * 
	 * return getGeneralDao().getStateList(languageId, countryId); }
	
	 * @Override
	 * 
	 * @Transactional public List<StateMasterDesc> getStateList(BigDecimal
	 * languageId) {
	 * 
	 * return getGeneralDao().getStateList(languageId); }
	
	 * @Override
	 * 
	 * @Transactional public List<CityMasterDesc> getCityList(BigDecimal
	 * languageId) {
	 * 
	 * return getGeneralDao().getCityList(languageId); }
	 */

}
