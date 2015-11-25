package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.ProductDao;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Uom;
/**
 * @author RiyazKhan.M
 */
@Repository
public class ProductDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
ProductDao<T>, Serializable{
  
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	@Override
	public void deleteProduct(Product product) {
		try {
			delete((T) product);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addProduct(Product product) {
		try {
			save((T) product);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateProduct(Product product) {
		try {
			update((T) product);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductByUom(Uom uom) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
			criteria.add(Restrictions.eq("uom", uom));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Product>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getTopProduct(Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
			criteria.add(Restrictions.eq("merchant",merchant));
			criteria.add(Restrictions.eq("isYourHot", 'Y'));
			return ((List<Product>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductByMerchant(Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
			criteria.add(Restrictions.eq("merchant",merchant));
			return ((List<Product>) findAll(criteria));
		} catch (Throwable e) {
			e.printStackTrace();
			throw(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Product getProductByName(String param) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
			criteria.add(Restrictions.eq("name", param));
			return (Product) findUnique(criteria);
		} catch (Throwable e) {
			e.printStackTrace();
			throw(e);
		}
	}
}
