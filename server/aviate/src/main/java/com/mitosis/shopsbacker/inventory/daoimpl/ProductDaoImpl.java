package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.ProductDao;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;
/**
 * @author RiyazKhan.M
 */
@Repository
public class ProductDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
ProductDao<T>, Serializable{
  
	private static final long serialVersionUID = 1L;

	@Override
	public List<Product> getProductListByType(ProductType productType) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
			criteria.add(Restrictions.eq("productType", productType));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Product>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public Product getProduct(String productId) {
		try {
			
			return (Product) getSession().get(Product.class, productId);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public List<Product> getProductListByCategoty(ProductCategory productCategory) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
			criteria.add(Restrictions.eq("productCategory", productCategory));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Product>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public void deleteProduct(Product product) {
		try {
			delete((T) product);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@Override
	public void addProduct(Product product) {
		try {
			save((T) product);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@Override
	public void updateProduct(Product product) {
		try {
			update((T) product);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

}
