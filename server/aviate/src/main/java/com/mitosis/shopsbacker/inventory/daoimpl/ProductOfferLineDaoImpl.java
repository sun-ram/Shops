package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.ProductOfferLineDao;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.ProductOfferLine;

/**
 * @author RiyazKhan.M
 */

@Repository("ProductOfferLineDaoImpl")
public class ProductOfferLineDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
ProductOfferLineDao<T>, Serializable{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void addProductOfferLine(ProductOfferLine productOfferLine) {
		try {
			save((T) productOfferLine);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateProductOfferLine(ProductOfferLine productOfferLine) {
		try {
			update((T) productOfferLine);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteProductOfferLine(ProductOfferLine productOfferLine) {
		try {
			delete((T) productOfferLine);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@Override
	public ProductOfferLine getProductOfferLine(String id) {
		try {
			return (ProductOfferLine) getSession().get(ProductOfferLine.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

}
