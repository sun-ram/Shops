package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.UserDao;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;

/**
 * @author prabakaran
 *
 * @param <T>
 */
@Service("userServiceImpl")
public class UserServiceImpl<T> implements UserService<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	UserDao<T> userDao;

	public UserDao<T> getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao<T> userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		getUserDao().saveUser(user);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		getUserDao().updateUser(user);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		getUserDao().deleteUser(user);

	}

	@Override
	@Transactional
	public User getUser(String id) {
		return getUserDao().getUser(id);
	}

	@Override
	@Transactional
	public List<User> getUser(Store store) {
		return getUserDao().getUserByStore(store);
	}

	@Override
	@Transactional
	public List<User> getUser(Merchant merchant) {
		return getUserDao().getUserByMerchant(merchant);
	}

	@Override
	@Transactional
	public List<User> getUsers(Store store, Role role) {
		return getUserDao().getUsersByStoreAndRole(store, role);
	}

	@Override
	public List<User> getUserByUserName(String userName) {
		return getUserDao().getUserByName(userName);
	}

}
