package com.mitosis.shopsbacker.admin.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.City;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;

/**
 * @author JAI BHARATHI
 * 
 */
public interface StoreDao<T> {

	public void saveStore(Store store);

	public void updateStore(Store store);

	public void removeStore(Store store);

	public List<Store> getStoreByMerchant(Merchant merchant);

	public Store getStoreById(String storeId);

	public List<Store> getShopList(String city);

	public List<Store> getShopList(String city, String address);

	public List<City> getShopCityList();
	
	public List<Store> getStoreListByName(String name, Merchant merchant);

	public List<Store> getStoreList();
	
	public int inActiveStores(Merchant merchant);
	
}
