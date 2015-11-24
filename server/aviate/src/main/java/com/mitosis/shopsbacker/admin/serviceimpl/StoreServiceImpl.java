package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.StoreDao;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.admin.service.UserService;
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
	@Transactional
	public void saveStore(Store store) {
		storeDao.saveStore(store);
	}

	@Override
	@Transactional
	public void updateStore(Store store) {
		storeDao.updateStore(store);
	}

	@Override
	@Transactional
	public void removeStore(Store store) {
		storeDao.removeStore(store);
	}

	@Override
	@Transactional
	public List<Store> getStoreByMerchant(Merchant merchant) {
		return storeDao.getStoreByMerchant(merchant);

	}

	@Override
	public Store getStoreById(String storeId) {
		Store store = storeDao.getStoreById(storeId);
		return store;
	}

	@Override
	@Transactional
	public List<Store> getShopList(String city) {
		return storeDao.getShopList(city);
	}

	@Override
	@Transactional
	public List<Store> getShopList(String city, String address) {
		return storeDao.getShopList(city, address);
	}

	@Override
	public List<String> getShopCityList() {
		return storeDao.getShopCityList();
	}

	@Override
	@Transactional
	public List<Store> getStoreListByName(String name, Merchant merchant) {
		return storeDao.getStoreListByName(name,merchant);
	}

	@Override
	public Store setStore(StoreVo storeVo) throws Exception {
		
		Store store = null;
		if(storeVo.getStoreId() == null){
			store = (Store) CommonUtil.setAuditColumnInfo(Store.class.getName());
			store.setIsactive('Y');
		}else{
			store = storeDao.getStoreById(storeVo.getStoreId());
			store.setUpdated(new Date());
			//TODO need to get user from session and set to updatedby
			store.setUpdatedby("123");
		}
		
		UserVo userVo = storeVo.getUser();
		Role role = roleService.getRole(RoleName.STOREADMIN.toString());
		User user = userService.setUser(userVo,role);
		user.setStore(store);
		store.setUser(user);
		store.setName(storeVo.getName());
		return store;
	
	}

	@Override
	public StoreVo setStoreVo(Store store) throws Exception {
		StoreVo storeVo = new StoreVo();
		storeVo.setName(store.getName());
		storeVo.setStoreId(store.getStoreId());
		User user = store.getUser();
		UserVo userVo = userService.setUserVo(user);
		storeVo.setUser(userVo);
		return storeVo;
	}
	
	@Override
	public void setStore(Store store, StoreVo storeVo)
			throws Exception {
		store = (Store) CommonUtil.setAuditColumnInfo(Store.class
				.getName());
		store.setName(storeVo.getName());
		Role role = (Role) CommonUtil
				.setAuditColumnInfo(Role.class.getName());
		role.setName("STOREADMIN");
		UserVo userVo = storeVo.getUser();
		User user = userService.setUser(userVo,role);
		user.setStore(store);
		store.setUser(user);
	}

	@Override
	public List<Store> getStoreList() {
		return storeDao.getStoreList();
	}

	/**
	 * @author Anbukkani Gajendran
	 * @param storagebin
	 * @return StoragebinVo
	 */
	public StoragebinVo setStoragebinVO(Storagebin storagebin) {
		StoragebinVo storagebinVo=new StoragebinVo();
		storagebinVo.setName(storagebin.getName());
		storagebinVo.setDescription(storagebin.getDescription());
		storagebinVo.setLevel(storagebin.getLevel());
		storagebinVo.setRow(storagebin.getRow());
		storagebinVo.setStack(storagebin.getStack());
		storagebinVo.setStoragebinId(storagebin.getStoragebinId());
		return storagebinVo;
	}

}
