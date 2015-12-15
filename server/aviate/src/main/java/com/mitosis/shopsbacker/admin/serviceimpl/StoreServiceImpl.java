package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.admin.dao.StoreDao;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.model.City;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.RoleName;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.inventory.StoragebinVo;

/**
 * @author JAI BHARATHI
 * 
 *         Reviewed by Sundaram 28/11/2015.
 * 
 */
@Service("StoreServiceImpl")
public class StoreServiceImpl<T> implements StoreService<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	StoreDao<T> storeDao;

	@Autowired
	UserService<T> userService;

	@Autowired
	RoleService<T> roleService;

	@Autowired
	MerchantService<T> merchantService;

	Store store = null;
	StoreVo storeVo = null;
	UserVo userVo = null;
	Role role = null;
	User user = null;
	StoragebinVo storagebinVo = null;

	public MerchantService<T> getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService<T> merchantService) {
		this.merchantService = merchantService;
	}

	public UserService<T> getUserService() {
		return userService;
	}

	public void setUserService(UserService<T> userService) {
		this.userService = userService;
	}

	public StoreDao<T> getStoreDao() {
		return storeDao;
	}

	public void setStoreDao(StoreDao<T> storeDao) {
		this.storeDao = storeDao;
	}

	@Override
	public void saveStore(Store store) {
		storeDao.saveStore(store);
	}

	@Override
	public void updateStore(Store store) {
		storeDao.updateStore(store);
	}

	@Override
	public void removeStore(Store store) {
		storeDao.removeStore(store);
	}

	@Override
	public List<Store> getStoreByMerchant(Merchant merchant) {
		return storeDao.getStoreByMerchant(merchant);

	}

	@Override
	public Store getStoreById(String storeId) {
		store = storeDao.getStoreById(storeId);
		return store;
	}

	@Override
	public List<Store> getShopList(String city) {
		return storeDao.getShopList(city);
	}

	@Override
	public List<Store> getShopList(String city, String address) {
		return storeDao.getShopList(city, address);
	}

	@Override
	public List<City> getShopCityList() {
		return storeDao.getShopCityList();
	}

	@Override
	public List<Store> getStoreListByName(String name, Merchant merchant) {
		return storeDao.getStoreListByName(name, merchant);
	}

	@Override
	public Store setStore(StoreVo storeVo) throws Exception {

		if (storeVo.getStoreId() == null) {
			store = (Store) CommonUtil
					.setAuditColumnInfo(Store.class.getName());
			store.setIsactive('Y');
		} else {
			store = storeDao.getStoreById(storeVo.getStoreId());
			store.setUpdated(new Date());
			// TODO need to get user from session and set to updatedby
			store.setUpdatedby("123");
		}

		userVo = storeVo.getUser();
		role = roleService.getRole(RoleName.STOREADMIN.toString());
		user = userService.setUser(userVo, role);
		user.setStore(store);
		store.setUser(user);
		store.setName(storeVo.getName());
		return store;

	}

	@Override
	public StoreVo setStoreVo(Store store) throws Exception {
		storeVo = new StoreVo();
		storeVo.setName(store.getName());
		storeVo.setStoreId(store.getStoreId());
		User user = store.getUser();
		UserVo userVo = userService.setUserVo(user);
		storeVo.setUser(userVo);
		return storeVo;
	}

	@Override
	public void setStore(Store store, StoreVo storeVo) throws Exception {
		store = (Store) CommonUtil.setAuditColumnInfo(Store.class.getName());
		store.setName(storeVo.getName());
		role = (Role) CommonUtil.setAuditColumnInfo(Role.class.getName());
		role.setName("STOREADMIN");
		userVo = storeVo.getUser();
		User user = userService.setUser(userVo, role);
		user.setStore(store);
		store.setUser(user);
	}

	@Override
	public List<Store> getStoreList() {
		return storeDao.getStoreList();
	}

	public StoragebinVo setStoragebinVO(Storagebin storagebin) {
		storagebinVo = new StoragebinVo();
		storagebinVo.setName(storagebin.getName());
		storagebinVo.setDescription(storagebin.getDescription());
		storagebinVo.setLevel(storagebin.getLevel());
		storagebinVo.setRow(storagebin.getRow());
		storagebinVo.setStack(storagebin.getStack());
		storagebinVo.setStoragebinId(storagebin.getStoragebinId());
		return storagebinVo;
	}

	public List<Store> getShopList(City city, String areaName) {
		return storeDao.getShopList(city, areaName);
	}
}
