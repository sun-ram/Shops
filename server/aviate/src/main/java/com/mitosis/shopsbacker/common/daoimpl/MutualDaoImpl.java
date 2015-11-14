package com.mitosis.shopsbacker.common.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.dao.MutualDao;

@Repository
public class MutualDaoImpl implements MutualDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/*

	@SuppressWarnings("unchecked")
	@Override
	public List<CountryMaster> getCountry() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from CountryMaster").list();
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
/*
	

	@Override
	public List<StateMaster> getState(String country) {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
