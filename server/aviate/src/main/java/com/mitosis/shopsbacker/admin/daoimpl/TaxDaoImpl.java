package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.TaxDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ShippingCharges;
import com.mitosis.shopsbacker.model.Tax;
/**
 * @author RiyazKhan.M
 */
@Repository
@Transactional
public class TaxDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
TaxDao<T>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void addTax(Tax tax) {
		try {
			save((T) tax);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeTax(Tax tax) {
		try {
			delete((T) tax);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateTax(Tax tax) {
		try {
			update((T) tax);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Tax> getTax(Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(Tax.class);
			criteria.add(Restrictions.eq("merchant", merchant));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<Tax>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
		
	}

	@Override
	public Tax getTaxById(String id) {
		try {
			return (Tax) getSession().get(Tax.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Tax> getTaxListByName(String param) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Tax.class);
			criteria.add(Restrictions.eq("name", param));
			return (List<Tax>) findAll(criteria);
		}

}
