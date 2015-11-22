package com.mitosis.shopsbacker.common.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author prabakaran
 *
 * @param <T>
 */
@Repository
public class CustomHibernateDaoSupport<T> {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T entity) {
		getSession().save(entity);
		getSession().flush();

	}

	public void update(T entity) {
		getSession().saveOrUpdate(entity);
		getSession().flush();
	}

	public void delete(T entity) {
		getSession().delete(entity);
		getSession().flush();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(DetachedCriteria criteria) {
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		return criteria.getExecutableCriteria(getSession()).list();
	}
	
	/**
	 * This method will return unique Result.
	 * @param criteria
	 * @return Object
	 */
	public Object findUnique(DetachedCriteria criteria) {
		return criteria.getExecutableCriteria(getSession()).uniqueResult();
	}

	public List<T> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Criteria criteria) {
		return criteria.list();
	}
	
	public List<T> findAllEh(DetachedCriteria criteria) {
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		criteria.getExecutableCriteria(getSession()).setCacheable(true);
		return ((DetachedCriteria) criteria).getExecutableCriteria(getSession()).list();
	}

}
