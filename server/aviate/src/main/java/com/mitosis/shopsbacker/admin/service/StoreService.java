package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

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
}
