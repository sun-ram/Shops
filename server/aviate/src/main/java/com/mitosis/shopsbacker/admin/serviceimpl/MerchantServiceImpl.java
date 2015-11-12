package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.MerchantDao;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.model.Merchant;

@Service
public class MerchantServiceImpl<T> implements MerchantService<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	MerchantDao<T> merchantDao;

	public MerchantDao<T> getMerchantDao() {
		return merchantDao;
	}

	public void setMerchantDao(MerchantDao<T> merchantDao) {
		this.merchantDao = merchantDao;
	}

	@Override
	@Transactional
	public void saveMerchant(Merchant merchant) {
		getMerchantDao().saveMerchant(merchant);
	}

	@Override
	@Transactional
	public void updateMerchant(Merchant merchant) {
		getMerchantDao().updateMerchant(merchant);
	}

	@Override
	@Transactional
	public List<Merchant> getMerchantList() {
		return getMerchantDao().getMerchantList();
	}

	@Override
	@Transactional
	public void deleteMerchant(Merchant merchant) {
		getMerchantDao().deleteMerchant(merchant);
	}

	@Override
	@Transactional
	public List<Merchant> getMerchantListByName(String name) {
		return getMerchantDao().getMerchantListByName(name);
	}

	@Override
	@Transactional
	public Merchant getMerchantById(String id) {
		return getMerchantDao().getMerchantById(id);
	}

}
