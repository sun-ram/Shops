package com.mitosis.shopsbacker.dao.merchant;

import java.util.List;

import com.mitosis.aviate.model.MerchantDetails;

public interface MerchantDao<T> {
	
	public void saveMerchantDetails(MerchantDetails merchantDetails);
	public void updateMerchandDetails(MerchantDetails merchantDetails);
	public List<MerchantDetails> getMerchantList();
	public void deleteMerchant(MerchantDetails merchantDetails);
	public List<MerchantDetails> getMerchantListByName(String param);
	public MerchantDetails getMerchantById(long parseLong);
}

