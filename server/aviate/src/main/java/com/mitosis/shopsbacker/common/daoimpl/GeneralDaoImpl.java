package com.mitosis.shopsbacker.common.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.dao.GeneralDao;
/**
 * @author Sundaram
 *
 * @param <T>
 * 
 * Reviewed by Sundaram 27/11/2015
 */

@Repository
public class GeneralDaoImpl<T>  extends CustomHibernateDaoSupport<T> implements GeneralDao<T>,Serializable {
 
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

	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> load(DetachedCriteria criteria){
		
		
		return criteria.getExecutableCriteria(getSession()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(DetachedCriteria criteria) {

		return criteria.getExecutableCriteria(getSession()).list();
	}

	}
