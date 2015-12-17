package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.DiscountDao;
import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;
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
	public List<Discount> getAllDiscountByMerchant(Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Discount.class);
			criteria.add(Restrictions.eq("merchant", merchant));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Discount>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@Override
	public Discount getDiscountById(String discountId) {
		try {
			return (Discount) getSession().get(Discount.class, discountId);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Discount> getAllDiscountByStore(Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Discount.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Discount>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Discount> getUniqeName(Store store,String name) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Discount.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("name", name));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Discount>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

}
