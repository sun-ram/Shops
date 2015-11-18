package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;

public interface MerchantService<T> {

	public void saveMerchant(Merchant merchant);
	public void updateMerchant(Merchant merchant);
	public List<Merchant> getMerchantList();
	public void deleteMerchant(String id);
	public List<Merchant> getMerchantListByName(String param);
	public Merchant getMerchantById(String id);
	public Merchant setMerchant(MerchantVo merchantVo)throws Exception;
	public MerchantVo setMerchantVo(Merchant merchant);
	
}
