package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.admin.StoreVo;

/**
 * @author JAI BHARATHI
 * 
 */
public interface StoreService<T> {
	public void saveStore(Store store);

	public void updateStore(Store store);

	public void removeStore(Store store);

	public List<Store> getStoreByMerchant(Merchant merchant);

	public Store getStoreById(String storeId);

	public List<Store> getShopList(String city);

	public List<Store> getShopList(String city, String address);

	public List<String> getShopCityList();

	public List<Store> getStoreListByName(String name, Merchant merchant);
	
	public Store setStore(StoreVo storeVo)throws Exception;
	
	public StoreVo setStoreVo(Store store) throws Exception;
	
	public void setStore(Store store, StoreVo storeVo)throws Exception;
	
	
}
