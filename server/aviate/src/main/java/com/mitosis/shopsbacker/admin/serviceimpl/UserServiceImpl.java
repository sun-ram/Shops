package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.MerchantDao;
import com.mitosis.shopsbacker.admin.dao.RoleDao;
import com.mitosis.shopsbacker.admin.dao.StoreDao;
import com.mitosis.shopsbacker.admin.dao.UserDao;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
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

	@Autowired
	RoleDao<T> roleDao;

	@Autowired
	RoleService<T> roleService;
	
	@Autowired
	StoreDao<T> storeDao;
	
	@Autowired
	MerchantDao<T> merchantDao;
	
	public RoleDao<T> getRoleDao() {
		return roleDao;
	}

	public StoreDao<T> getStoreDao() {
		return storeDao;
	}

	public void setRoleDao(RoleDao<T> roleDao) {
		this.roleDao = roleDao;
	}

	public void setStoreDao(StoreDao<T> storeDao) {
		this.storeDao = storeDao;
	}

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
	public void deleteUser(String id) {
		getUserDao().deleteUser(getUser(id));
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
	
	

	/* @author prabakaran
	 * This method will return List of users
	 * @Param request parms List Of roles and store id
	 */
	@Override
	public List<User> getUsers(List<String> roles, String storeId) {
		return userDao.getUsers(roleDao.getRole(roles),
				storeDao.getStoreById(storeId));
	}

	/* @author prabakaran
	 * This method will return List of users
	 * @Param request parms List Of roles and merchant id
	 */
	@Override
	public List<User> getUsersByMerchantId(List<String> roles, String merchantId) {
		return userDao.getUsers(roleDao.getRole(roles),
				merchantDao.getMerchantById(merchantId));
	}

	public User setUser(UserVo userVo, Role role) throws Exception {
		User user = null;
		if(userVo.getUserId() == null){
			user = (User) CommonUtil.setAuditColumnInfo(User.class.getName());
			user.setIsactive('Y');
			user.setPassword(CommonUtil.passwordEncoder(userVo.getPassword()));
		}else{
			user = userDao.getUser(userVo.getUserId());
			user.setUpdated(new Date());
			//TODO need to get user from session and set to updatedby
			user.setUpdatedby("123");
		}
		user.setName(userVo.getName());
		user.setUserName(userVo.getUserName());
		user.setEmailid(userVo.getEmailid());
		user.setPhoneNo(userVo.getPhoneNo());
		user.setRole(role);
		AddressVo addressVo = userVo.getAddress();
		Address address = addressService.setAddress(addressVo);
		// addessService.saveAddress(address);
		user.setAddress(address);

		return user;
	}

	public UserVo setUserVo(User user) throws Exception {
		UserVo userVo = new UserVo();
		userVo.setName(user.getName());
		userVo.setUserName(user.getUserName());
		userVo.setPassword(user.getPassword());
		userVo.setEmailid(user.getEmailid());
		userVo.setPhoneNo(user.getPhoneNo());
		userVo.setUserId(user.getUserId());
		userVo.setRole(roleService.setRoleVo(user.getRole()));
		userVo.setAddress(addressService.setAddressVo(user.getAddress()));
		userVo.setStore(setStoreVo(user.getStore()));
		return userVo;
	}
	
	public StoreVo setStoreVo(Store store) throws Exception {
		StoreVo storeVo = new StoreVo();
		storeVo.setName(store.getName());
		storeVo.setStoreId(store.getStoreId());
		return storeVo;
	}
	

}
