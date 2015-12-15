package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.DiscountDao;
import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.Product;
/**
 * @author RiyazKhan.M
 */
@Repository("discountDaoImpl")
public class DiscountDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
DiscountDao<T>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void addDiscount(Discount discount) {
		try {
			save((T) discount);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateDiscount(Discount discount) {
		try {
			update((T) discount);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteDiscount(Discount discount) {
		try {
			delete((T) discount);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Discount> getAllDiscount() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Discount.class);
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Discount>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

}
