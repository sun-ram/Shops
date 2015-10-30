package com.mitosis.aviate.dao;

import java.util.List;

import com.mitosis.aviate.model.MerchantDetails;

public interface MerchantDAO {
	
	public boolean addMerchant(MerchantDetails merchantDetails);
	public List<MerchantDetails> getMerchantList();
	public boolean removeMerchant(long parseLong);
	public List<MerchantDetails> getMerchantListByQuery(String param);
	public MerchantDetails getMerchantById(long parseLong);
}
