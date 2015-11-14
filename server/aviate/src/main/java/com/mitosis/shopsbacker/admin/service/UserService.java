package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public interface UserService<T> {

	public void saveUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public User getUser(String id);
	
	public List<User> getUserByUserName(String userName);

	public List<User> getUser(Store store);

	public List<User> getUser(Merchant merchant);

	public List<User> getUsers(Store store, Role role);

}
