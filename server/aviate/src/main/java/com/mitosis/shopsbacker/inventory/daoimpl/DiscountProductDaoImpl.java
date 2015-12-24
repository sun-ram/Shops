package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.DiscountProductsDao;
import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.DiscountProduct;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
/**
 * @author RiyazKhan.M
 */
@Repository("discountProductDaoImpl")
public class DiscountProductDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
DiscountProductsDao<T>, Serializable{

	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void addDiscountProduct(DiscountProduct discountProduct) {
		try {
			save((T) discountProduct);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateDiscountProduct(DiscountProduct discountProduct) {
		try {
			update((T) discountProduct);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteDiscountProduct(DiscountProduct discountProduct) {
		try {
			delete((T) discountProduct);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@Override
	public DiscountProduct getDiscountProductById(String discountProductId) {
		try {
			return (DiscountProduct) getSession().get(DiscountProduct.class, discountProductId);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountProduct> getDiscountProductByMerchant(Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DiscountProduct.class);
			criteria.add(Restrictions.eq("merchant", merchant));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<DiscountProduct>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountProduct> getDiscountProductByStore(Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DiscountProduct.class);
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<DiscountProduct>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public List<DiscountProduct> getDiscountProductByDiscount(Discount discount) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DiscountProduct.class);
			criteria.add(Restrictions.eq("discount", discount));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<DiscountProduct>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

}
