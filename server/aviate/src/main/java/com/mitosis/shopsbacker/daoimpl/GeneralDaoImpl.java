package com.mitosis.shopsbacker.daoimpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.dao.GeneralDao;
/*
import com.mitosis.shopsbacker.model.CountryMaster;
import com.mitosis.shopsbacker.model.CountryMasterDesc;
import com.mitosis.shopsbacker.model.StateMaster;
import com.mitosis.shopsbacker.model.StateMasterDesc;*/

@Repository
public class GeneralDaoImpl<T>  extends CustomHibernateDaoSupport implements GeneralDao<T>,Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@Override
	public List<T> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<T> load(DetachedCriteria criteria){
		
		
		return criteria.getExecutableCriteria(getSession()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(DetachedCriteria criteria) {

		return criteria.getExecutableCriteria(getSession()).list();
	}

	/*

	@Override
	public List<CityMaster> getCity(BigDecimal districtId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CountryMaster> getCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StateMaster> getState() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<StateMaster> getState(BigDecimal countryId) {
		// TODO Auto-generated method stub
		return null;
	}

	
*/
	/*@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<CountryMasterDesc> getCountryList(BigDecimal languageId) {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CountryMasterDesc.class,"countryMasterDesc");
		
		// Join Language Type table
		detachedCriteria.setFetchMode("countryMasterDesc.fsLanguageType", FetchMode.JOIN);
		detachedCriteria.createAlias("countryMasterDesc.fsLanguageType", "fsLanguageType", CriteriaSpecification.INNER_JOIN);
		
		// Add Language Condition
		detachedCriteria.add(Restrictions.eq("fsLanguageType.languageId", languageId));
		
		// Join Country Master Table
		detachedCriteria.setFetchMode("countryMasterDesc.fsCountryMaster", FetchMode.JOIN);
		detachedCriteria.createAlias("countryMasterDesc.fsCountryMaster", "fsCountryMaster", CriteriaSpecification.INNER_JOIN);
		
		// Join Rule Application Description Table
		detachedCriteria.setFetchMode("fsCountryMaster.fsRuleApplicationDescs", FetchMode.JOIN);
		detachedCriteria.createAlias("fsCountryMaster.fsRuleApplicationDescs", "fsRuleApplicationDescs", CriteriaSpecification.INNER_JOIN);
		
		detachedCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		return (List<CountryMasterDesc>)findAll(detachedCriteria);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<CountryMasterDesc> getNationalityList(BigDecimal languageId) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CountryMasterDesc.class,"countryMasterDesc");
		
		// Join Language Type table
		detachedCriteria.setFetchMode("countryMasterDesc.fsLanguageType", FetchMode.JOIN);
		detachedCriteria.createAlias("countryMasterDesc.fsLanguageType", "fsLanguageType", CriteriaSpecification.INNER_JOIN);
		
		// Add Language Condition
		detachedCriteria.add(Restrictions.eq("fsLanguageType.languageId", languageId));
		
		
		// Join Country Master Table
		detachedCriteria.setFetchMode("countryMasterDesc.fsCountryMaster", FetchMode.JOIN);
		detachedCriteria.createAlias("countryMasterDesc.fsCountryMaster", "fsCountryMaster", CriteriaSpecification.INNER_JOIN);
		detachedCriteria.add(Restrictions.isNotNull("countryMasterDesc.nationality"));
		// Join Rule Application Description Table
		detachedCriteria.setFetchMode("fsCountryMaster.fsRuleApplicationDescs", FetchMode.JOIN);
		detachedCriteria.createAlias("fsCountryMaster.fsRuleApplicationDescs", "fsRuleApplicationDescs", CriteriaSpecification.INNER_JOIN);
		
		detachedCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		return (List<CountryMasterDesc>)findAll(detachedCriteria);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<StateMasterDesc> getStateList(BigDecimal languageId, BigDecimal countryId) {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StateMasterDesc.class,"stateMasterDesc");
		
		// Join FS_LANGUAGE_TYPE table
		detachedCriteria.setFetchMode("stateMasterDesc.fsLanguageType", FetchMode.JOIN);
		detachedCriteria.createAlias("stateMasterDesc.fsLanguageType", "fsLanguageType", CriteriaSpecification.INNER_JOIN);
		
		// Add FS_LANGUAGE_TYPE Condition
		detachedCriteria.add(Restrictions.eq("fsLanguageType.languageId", languageId));
		
		// Join FS_STATE_MASTER table
		detachedCriteria.setFetchMode("stateMasterDesc.fsStateMaster", FetchMode.JOIN);
		detachedCriteria.createAlias("stateMasterDesc.fsStateMaster", "fsStateMaster", CriteriaSpecification.INNER_JOIN);
		
		// Join FS_COUNTRY_MASTER table
		detachedCriteria.setFetchMode("fsStateMaster.fsCountryMaster", FetchMode.JOIN);
		detachedCriteria.createAlias("fsStateMaster.fsCountryMaster", "fsCountryMaster", CriteriaSpecification.INNER_JOIN);
		
		// Add FS_COUNTRY_MASTER Filter Condition
		detachedCriteria.add(Restrictions.eq("fsCountryMaster.countryId", countryId));
		
		detachedCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		return (List<StateMasterDesc>)findAll(detachedCriteria);
	}

	
	

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<StateMasterDesc> getStateList(BigDecimal languageId) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(StateMasterDesc.class, "stateMasterDesc");
		
		criteria.setFetchMode("stateMasterDesc.fsLanguageType", FetchMode.JOIN);
		criteria.createAlias("stateMasterDesc.fsLanguageType", "fsLanguageType", CriteriaSpecification.INNER_JOIN);
		criteria.add(Restrictions.eq("fsLanguageType.languageId", languageId));
		
		criteria.setFetchMode("stateMasterDesc.fsStateMaster", FetchMode.JOIN);
		criteria.createAlias("stateMasterDesc.fsStateMaster", "fsStateMaster", CriteriaSpecification.INNER_JOIN);
		  
		return (List<StateMasterDesc>)findAll(criteria);
	}

	
*/
	/*@SuppressWarnings("unchecked")
	@Override
	public Map<String, BussComponentConfDetail> getComponentBehavior(
			List<String> componentList, BigDecimal levelId, BigDecimal applicationId, BigDecimal companyId, BigDecimal countryId, BigDecimal pageId) {
		
		String alias = "fsbusiness1_";
		Map<String,BussComponentConfDetail> mapReturn = new HashMap<String, BussComponentConfDetail>();

		DetachedCriteria criteria = DetachedCriteria.forClass(BussComponentConfDetail.class, "bussComponentConfDetail");
		
		criteria.setFetchMode("bussComponentConfDetail.fsBusinessComponentConf", FetchMode.JOIN);
		criteria.createAlias("bussComponentConfDetail.fsBusinessComponentConf", "fsBusinessComponentConf", JoinType.INNER_JOIN);

		criteria.setFetchMode("fsBusinessComponentConf.fsBusinessComponent", FetchMode.JOIN);
		criteria.createAlias("fsBusinessComponentConf.fsBusinessComponent", "fsBusinessComponent", JoinType.INNER_JOIN);


		//Left join Application Master
		criteria.setFetchMode("fsBusinessComponentConf.fsRuleApplicationMaster", FetchMode.JOIN);
		criteria.createAlias("fsBusinessComponentConf.fsRuleApplicationMaster", "fsRuleApplicationMaster", JoinType.LEFT_OUTER_JOIN);

		//Left join Company Master
		criteria.setFetchMode("fsBusinessComponentConf.fsCompanyMaster", FetchMode.JOIN);
		criteria.createAlias("fsBusinessComponentConf.fsCompanyMaster", "fsCompanyMaster", JoinType.LEFT_OUTER_JOIN);

		//Left join Country Master
		criteria.setFetchMode("fsBusinessComponentConf.fsCountryMaster", FetchMode.JOIN);
		criteria.createAlias("fsBusinessComponentConf.fsCountryMaster", "fsCountryMaster", JoinType.LEFT_OUTER_JOIN);
		
		//Left join page master
		criteria.setFetchMode("fsBusinessComponentConf.fsRulePageMaster", FetchMode.JOIN);
		criteria.createAlias("fsBusinessComponentConf.fsRulePageMaster", "fsRulePageMaster", JoinType.LEFT_OUTER_JOIN);
		
		
		criteria.add(Restrictions.in("fsBusinessComponent.componentName", componentList));  
		
		
		StringBuilder sbf = new StringBuilder("(CASE ").append(alias).append(".LEVEL_ID "); 
		
		sbf.append(" WHEN 0 THEN 1");
		sbf.append(" WHEN 1 THEN (CASE WHEN ").append(alias).append(".PAGE_ID=").append(pageId.intValue()).append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 2 THEN (CASE WHEN ").append(alias).append(".COUNTRY_ID=").append(countryId.intValue()).append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 3 THEN (CASE WHEN ")
			.append(alias).append(".COUNTRY_ID=").append(countryId.intValue()).append(" AND ")
			.append(alias).append(".PAGE_ID=").append(pageId.intValue())
			.append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 4 THEN (CASE WHEN ").append(alias).append(".COMPANY_ID=").append(companyId.intValue()).append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 5 THEN (CASE WHEN ")
			.append(alias).append(".COMPANY_ID=").append(companyId.intValue()).append(" AND ")
			.append(alias).append(".PAGE_ID=").append(pageId.intValue())
			.append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 6 THEN (CASE WHEN ")
			.append(alias).append(".COMPANY_ID=").append(companyId.intValue()).append(" AND ")
			.append(alias).append(".COUNTRY_ID=").append(countryId.intValue())
			.append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 7 THEN (CASE WHEN ")
			.append(alias).append(".COMPANY_ID=").append(companyId.intValue()).append(" AND ")
			.append(alias).append(".COUNTRY_ID=").append(countryId.intValue()).append(" AND ")
			.append(alias).append(".PAGE_ID=").append(pageId.intValue())
			.append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 8 THEN (CASE WHEN ").append(alias).append(".APPLICATION_ID=").append(applicationId.intValue()).append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 9 THEN (CASE WHEN ")
			.append(alias).append(".APPLICATION_ID=").append(applicationId.intValue()).append(" AND ")
			.append(alias).append(".PAGE_ID=").append(pageId.intValue())
			.append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 10 THEN (CASE WHEN ")
			.append(alias).append(".APPLICATION_ID=").append(applicationId.intValue()).append(" AND ")
			.append(alias).append(".COUNTRY_ID=").append(countryId.intValue())
			.append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 11 THEN (CASE WHEN ")
			.append(alias).append(".APPLICATION_ID=").append(applicationId.intValue()).append(" AND ")
			.append(alias).append(".COUNTRY_ID=").append(countryId.intValue()).append(" AND ")
			.append(alias).append(".PAGE_ID=").append(pageId.intValue())
			.append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 12 THEN (CASE WHEN ")
			.append(alias).append(".APPLICATION_ID=").append(applicationId.intValue()).append(" AND ")
			.append(alias).append(".COMPANY_ID=").append(companyId.intValue())
			.append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 13 THEN (CASE WHEN ")
			.append(alias).append(".APPLICATION_ID=").append(applicationId.intValue()).append(" AND ")
			.append(alias).append(".COMPANY_ID=").append(companyId.intValue()).append(" AND ")
			.append(alias).append(".PAGE_ID=").append(pageId.intValue())
			.append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 14 THEN (CASE WHEN ")
			.append(alias).append(".APPLICATION_ID=").append(applicationId.intValue()).append(" AND ")
			.append(alias).append(".COMPANY_ID=").append(companyId.intValue()).append(" AND ")
			.append(alias).append(".COUNTRY_ID=").append(countryId.intValue())
			.append(" THEN 1 ELSE 0 END)");
		sbf.append(" WHEN 15 THEN (CASE WHEN ")
			.append(alias).append(".APPLICATION_ID=").append(applicationId.intValue()).append(" AND ")
			.append(alias).append(".COMPANY_ID=").append(companyId.intValue()).append(" AND ")
			.append(alias).append(".COUNTRY_ID=").append(countryId.intValue()).append(" AND ")
			.append(alias).append(".PAGE_ID=").append(pageId.intValue())
			.append(" THEN 1 ELSE 0 END)");
		sbf.append("ELSE 1 END)=1");
		criteria.add(Restrictions.sqlRestriction(sbf.toString()));
		criteria.addOrder(Order.desc("fsBusinessComponentConf.levelId"));
		
		
		List<BussComponentConfDetail> lst = null;
		
		try{
			criteria.getExecutableCriteria(getSession()).setCacheable(true);
			
			lst =  (List<BussComponentConfDetail>)findAll(criteria);
			for(BussComponentConfDetail bean:lst){
				if(mapReturn.containsKey(bean.getFsBusinessComponentConf().getFsBusinessComponent().getComponentName())){}else{
					mapReturn.put(bean.getFsBusinessComponentConf().getFsBusinessComponent().getComponentName(), bean);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//Logger.getLogger(this.getClass()).log(Level.ERROR, "Component List "+componentList+" behaviour not available !" , null);
		}
		
		return mapReturn;
	} 
	@Override
	@SuppressWarnings("unchecked")
	public Map<BigDecimal, String> getAllComponentComboData(BigDecimal componentConfId, BigDecimal languageId) {

		Map<BigDecimal, String> mapComponentComboData = new HashMap<BigDecimal, String>();
		DetachedCriteria criteria = DetachedCriteria.forClass(BussComponentComboData.class, "bussComponentComboData");
		criteria.add(Restrictions.eq("bussComponentComboData.active", "Y"));
		
		criteria.setFetchMode("bussComponentComboData.fsBusinessComponentConf", FetchMode.JOIN);
		criteria.createAlias("bussComponentComboData.fsBusinessComponentConf", "fsBusinessComponentConf", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("fsBusinessComponentConf.componentConfId", componentConfId));
		
		criteria.setFetchMode("bussComponentComboData.fsLanguageType", FetchMode.JOIN);
		criteria.createAlias("bussComponentComboData.fsLanguageType", "fsLanguageType", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("fsLanguageType.languageId", languageId));
		
		ProjectionList projectionList = Projections.projectionList(); 
		projectionList.add(Projections.property("bussComponentComboData.componentComboDataId"));
		projectionList.add(Projections.property("bussComponentComboData.componentData"));
		
		criteria.setProjection(projectionList);
		
		criteria.addOrder(Order.asc("bussComponentComboData.componentData"));
		
		List<Object[]> tempList = (List<Object[]>)findAll(criteria);
		for(Object[] row:tempList){
			mapComponentComboData.put((BigDecimal)row[0], (String)row[1]);
		}
		
		return mapComponentComboData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<BigDecimal, String> getAllCountry(BigDecimal languageId) {
		
		Map<BigDecimal, String> mapCountryList = new LinkedHashMap<BigDecimal, String>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CountryMasterDesc.class,"countryMasterDesc");
		
		// Join Language Type table
		detachedCriteria.setFetchMode("countryMasterDesc.fsLanguageType", FetchMode.JOIN);
		detachedCriteria.createAlias("countryMasterDesc.fsLanguageType", "fsLanguageType", JoinType.INNER_JOIN);
		
		// Add Language Condition
		detachedCriteria.add(Restrictions.eq("fsLanguageType.languageId", languageId));
		
		// Join Country Master Table
		detachedCriteria.setFetchMode("countryMasterDesc.fsCountryMaster", FetchMode.JOIN);
		detachedCriteria.createAlias("countryMasterDesc.fsCountryMaster", "fsCountryMaster", JoinType.INNER_JOIN);
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("fsCountryMaster.countryId"));
		projectionList.add(Projections.property("countryMasterDesc.countryName"));
		
		detachedCriteria.setProjection(projectionList);
		detachedCriteria.addOrder(Order.asc("countryMasterDesc.countryName"));
		
		for(Object[] row:(List<Object[]>)findAll(detachedCriteria)){
			mapCountryList.put((BigDecimal)row[0], (String)row[1]);
		}
		
		return mapCountryList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<BigDecimal, String> getAllNationality(BigDecimal languageId) {
		
		Map<BigDecimal, String> mapCountryList = new LinkedHashMap<BigDecimal, String>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CountryMasterDesc.class,"countryMasterDesc");
		
		// Join Language Type table
		detachedCriteria.setFetchMode("countryMasterDesc.fsLanguageType", FetchMode.JOIN);
		detachedCriteria.createAlias("countryMasterDesc.fsLanguageType", "fsLanguageType", JoinType.INNER_JOIN);
		
		// Add Language Condition
		detachedCriteria.add(Restrictions.eq("fsLanguageType.languageId", languageId));
		
		// Join Country Master Table
		detachedCriteria.setFetchMode("countryMasterDesc.fsCountryMaster", FetchMode.JOIN);
		detachedCriteria.createAlias("countryMasterDesc.fsCountryMaster", "fsCountryMaster", JoinType.INNER_JOIN);
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("fsCountryMaster.countryId"));
		projectionList.add(Projections.property("countryMasterDesc.nationality"));
		
		detachedCriteria.setProjection(projectionList);
		detachedCriteria.addOrder(Order.asc("countryMasterDesc.nationality"));
		
		for(Object[] row:(List<Object[]>)findAll(detachedCriteria)){
			mapCountryList.put((BigDecimal)row[0], (String)row[1]);
		}
		
		return mapCountryList;
	}

	
*/}
