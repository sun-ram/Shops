package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.admin.dao.StoreDao;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Tax;
import com.mitosis.shopsbacker.model.User;

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
		return storeDao.getStoreById(storeId);
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
	public List<String> getShopCityList() {
		return storeDao.getShopCityList();
	}

}
