package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mitosis.shopsbacker.inventory.dao.ProductOfferDao;
import com.mitosis.shopsbacker.inventory.service.ProductOfferService;
import com.mitosis.shopsbacker.model.ProductOffer;
/**
 * @author RiyazKhan.M
 */
public class ProductOfferServiceImpl<T> implements ProductOfferService<T>, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	ProductOfferDao<T> productOfferDao;

	@Override
	public void addProductOffer(ProductOffer productOffer) {
		productOfferDao.addProductOffer(productOffer);
		
	}

	@Override
	public void updateProductOffer(ProductOffer productOffer) {
		productOfferDao.updateProductOffer(productOffer);
		
	}

	@Override
	public void deleteProductOffer(ProductOffer productOffer) {
		productOfferDao.deleteProductOffer(productOffer);
		
	}

	@Override
	public List<ProductOffer> getAllProductOffer() {
		return productOfferDao.getAllProductOffer();
	}

}
