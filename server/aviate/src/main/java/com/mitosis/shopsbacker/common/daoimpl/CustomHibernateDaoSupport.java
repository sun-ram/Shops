package com.mitosis.shopsbacker.common.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomHibernateDaoSupport<T> {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T entity) {
		getSession().save(entity);

	}

	public void update(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public List<T> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
