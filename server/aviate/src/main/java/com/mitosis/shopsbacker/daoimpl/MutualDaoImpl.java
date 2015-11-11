package com.mitosis.shopsbacker.daoimpl;

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.persistence.sessions.factories.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mitosis.shopsbacker.dao.MutualDao;

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
