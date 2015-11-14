package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.admin.dao.UserDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;

/**
 * @author prabakaran
 *
 * @param <T>
 */

@Repository
public class UserDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
		UserDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void saveUser(User user) {
		try {
			save((T) user);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateUser(User user) {
		try {
			update((T) user);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteUser(User user) {
		try {
			delete((T) user);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User getUser(String id) {
		return (User) getSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByStore(Store store) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("store", store));
		return (List<User>) findAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByMerchant(Merchant merchant) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("merchant", merchant));
		return (List<User>) findAll(criteria);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByStoreAndRole(Store store,
			Role role) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("store", store));
		criteria.add(Restrictions.eq("role", role));
		return (List<User>) findAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByName(String userName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		return (List<User>) findAll(criteria);
	}

}
