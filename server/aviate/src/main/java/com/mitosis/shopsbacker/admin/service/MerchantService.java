package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;

public interface MerchantService<T> {

	public void saveMerchant(Merchant merchant);
	public void updateMerchant(Merchant merchant);
	public List<Merchant> getMerchantList();
	public void deleteMerchant(Merchant merchant);
	public List<Merchant> getMerchantListByName(String param);
	public Merchant getMerchantById(String id);
}
