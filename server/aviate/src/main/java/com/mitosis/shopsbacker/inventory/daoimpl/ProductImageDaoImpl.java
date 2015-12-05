package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.ProductImageDao;
import com.mitosis.shopsbacker.model.ProductImage;
@Repository
public class ProductImageDaoImpl<T> extends CustomHibernateDaoSupport<T> implements ProductImageDao<T>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8996264591103109877L;

	@Override
	public ProductImage getProductImage(String productImageId) {
		try {
			return (ProductImage) getSession().get(ProductImage.class, productImageId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteProductImage(ProductImage productImage) {
		try {
			delete((T) productImage);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}
	
	

}
