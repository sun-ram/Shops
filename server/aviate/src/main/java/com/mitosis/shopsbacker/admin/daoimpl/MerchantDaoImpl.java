package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.MerchantDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Country;
import com.mitosis.shopsbacker.model.Merchant;

@Repository
@Transactional
public class MerchantDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
		MerchantDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@SuppressWarnings("unchecked")
	@Override
	public void saveMerchant(Merchant merchant) {
		save((T) merchant);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateMerchant(Merchant merchant) {
		try {
			update((T) merchant);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Merchant> getMerchantList() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Merchant.class);
		return (List<Merchant>) findAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteMerchant(Merchant merchant) {
		delete((T) merchant);
	}

	@Override
	public Merchant getMerchantById(String id) {
		return(Merchant) getSession().get(Merchant.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Merchant> getMerchantListByName(String param) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Merchant.class);
		criteria.add(Restrictions.eq("name", param));
		return (List<Merchant>) findAll(criteria);
	}

}
