package com.mitosis.shopsbacker.common.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomHibernateDaoSupport<T>  {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
	public void save(T entity) {
		getSession().save(entity);  

	}
    
}
