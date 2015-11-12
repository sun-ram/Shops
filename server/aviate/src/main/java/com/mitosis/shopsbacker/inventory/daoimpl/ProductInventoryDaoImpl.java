package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.ProductInventoryDao;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.Store;

@Repository
public class ProductInventoryDaoImpl<T> extends CustomHibernateDaoSupport<T>
		implements ProductInventoryDao<T>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<ProductInventory> getProductInventory(Store store,
			Product product) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ProductInventory.class);
		criteria.add(Restrictions.eq("store", store));
		criteria.add(Restrictions.eq("product", product));
		return ((List<ProductInventory>) findAll(criteria));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductInventory> getProductInventoryByStore(Store store) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ProductInventory.class);
		criteria.add(Restrictions.eq("store", store));
		return ((List<ProductInventory>) findAll(criteria));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductInventory> getProductInventoryByMerchant(Merchant merchant) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ProductInventory.class);
		criteria.add(Restrictions.eq("merchant", merchant));
		return ((List<ProductInventory>) findAll(criteria));
	}

}
