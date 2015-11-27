package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.vo.admin.UserVo;

/**
 * @author prabakaran
 *
 * @param <T>
 * Reviewed by Sundaram  27/11/2015
 */
public interface UserService<T> {

	public void saveUser(User user);

	public void updateUser(User user);

	public void deleteUser(String id);

	public User getUser(String id);

	public User getUserByUserName(String userName);

	public User getUserByUserName(String userName, String password);

	public List<User> getUser(Store store);

	public List<User> getUser(Merchant merchant);

	public List<User> getUsers(Store store, Role role);
	
	public List<User> getUsers(List<String> roles, String storeId);
	
	public User setUser(UserVo userVo,Role role)throws Exception;
	
	public UserVo setUserVo(User user);

	public List<User> getUsersByMerchantId(List<String> roles, String merchantId);
	


}
