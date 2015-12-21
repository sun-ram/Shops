package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.DiscountProductsDao;
import com.mitosis.shopsbacker.model.DiscountProduct;
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

}
