package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.UserDao;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.common.dao.AddressDao;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.RoleName;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.AddressVo;

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
	
	@Autowired
	AddressService<T> addressService;

	public AddressService<T> getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService<T> addressService) {
		this.addressService = addressService;
	}

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
	public User getUserByUserName(String userName) {
		return getUserDao().getUserByName(userName);
	}

	@Override
	public User getUserByUserName(String userName, String password) {
		return getUserDao().getUserByName(userName, password);
	}
	
	public User setUser(UserVo userVo,Role role) throws Exception{
		User user = (User) CommonUtil.setAuditColumnInfo(User.class.getName());
		user.setName(userVo.getName());
		user.setUserName(userVo.getUserName());
		user.setPassword(CommonUtil.passwordEncoder(userVo.getPassword()));
		user.setEmailid(userVo.getEmailid());
		user.setPhoneNo(userVo.getPhoneNo());
		user.setRole(role);
		AddressVo addressVo = userVo.getAddress();
		
		Address address = addressService.setAddress(addressVo);
		
		//addessService.saveAddress(address);
		
		user.setAddress(address);
		
		return user;
	}
	
	public UserVo setUserVo(User user) {
		UserVo userVo = new UserVo();
		userVo.setName(user.getName());
		userVo.setUserName(user.getUserName());
		userVo.setPassword(user.getPassword());
		userVo.setEmailid(user.getEmailid());
		userVo.setPhoneNo(user.getPhoneNo());
		userVo.setUserId(user.getUserId());
		userVo.setAddress(addressService.setAddressVo(user.getAddress()));
		return userVo;
	}


}
