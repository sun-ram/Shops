package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.StoreDao;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Tax;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * @author JAI BHARATHI
 * 
 */
@Service("StoreServiceImpl")
public class StoreServiceImpl<T> implements StoreService<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	StoreDao<T> storeDao;

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
	@Transactional
	public Store getStoreById(String storeId) {
		return storeDao.getStoreById(storeId);
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


}
