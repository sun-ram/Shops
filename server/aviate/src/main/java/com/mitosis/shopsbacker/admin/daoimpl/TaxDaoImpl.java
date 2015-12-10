package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.admin.dao.TaxDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Tax;
/**
 * @author RiyazKhan.M
 */
@Repository
public class TaxDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
TaxDao<T>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void addTax(Tax tax) {
		try {
			save((T) tax);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeTax(Tax tax) {
		try {
			delete((T) tax);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateTax(Tax tax) {
		try {
			update((T) tax);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tax> getTax(Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(Tax.class);
			criteria.add(Restrictions.eq("merchant", merchant));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			criteria.addOrder(Order.desc("created"));
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Tax> getTaxListByName(String texId, String name,
			Merchant merchant) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Tax.class);
			if(texId != null){
				criteria.add(Restrictions.ne("taxId", texId));
			}
			criteria.add(Restrictions.eq("name", name));
			criteria.add(Restrictions.eq("merchant", merchant));
			return (List<Tax>) findAll(criteria);
		}

}
