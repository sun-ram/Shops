package com.mitosis.shopsbacker.admin.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
/**
 * @author prabakaran
 *
 * @param <T>
 * 
 * Reviewed by Sundaram 27/11/2015
 */

public interface MerchantDao<T> {
	
	public void saveMerchant(Merchant merchant);
	public void updateMerchant(Merchant merchant);
	public List<Merchant> getMerchantList();
	public void deleteMerchant(Merchant merchant);
	public List<Merchant> getMerchantListByName(String param);
	public Merchant getMerchantById(String id);  
}

