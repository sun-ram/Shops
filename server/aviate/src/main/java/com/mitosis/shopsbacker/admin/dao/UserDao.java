package com.mitosis.shopsbacker.admin.dao;

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
public interface UserDao<T> {

	public void saveUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public User getUser(String id);

	public List<User> getUserByStore(Store store);

	public List<User> getUserByMerchant(Merchant merchant);

	public List<User> getUsersByStoreAndRole(Store store, Role role);

	public User getUserByName(String userName, boolean isFromLogin);

	public List<User> getUsers(List<Role> role, Store store);

	public List<User> getUsers(List<Role> role, Merchant merchant);

	public int inActiveUsers(Merchant merchant);

	public List<User> filterAssignedShoppers(Store store);
	
	public List<User>  getUsers(Merchant merchant,Store store,String roleName);
}
