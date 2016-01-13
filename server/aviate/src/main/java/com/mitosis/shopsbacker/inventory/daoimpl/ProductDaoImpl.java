package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.ProductDao;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Store;
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
			criteria.add(Restrictions.eq("isKit", 'N'));
			criteria.add(Restrictions.eq("isBundle", 'N'));
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
			criteria.add(Restrictions.eq("isKit", 'N'));
			criteria.add(Restrictions.eq("isBundle", 'N'));
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
			criteria.addOrder(Order.desc("created"));
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
	
	@SuppressWarnings("unchecked")
	@Override
	public Product getProductByName(String param, Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
			criteria.add(Restrictions.eq("name", param));
			criteria.add(Restrictions.eq("merchant", merchant));
			return (Product) findUnique(criteria);
		} catch (Throwable e) {
			e.printStackTrace();
			throw(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductListByName(String name,Merchant merchant) {
		try{
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("name", name));

		criteria.add(Restrictions.eq("merchant", merchant));
		
		return (List<Product>) findAll(criteria);
		}
		catch (Throwable e) {
			e.printStackTrace();
			throw(e);
		}
	}
	
	@Override
	public List<Product> getProductName(String productId,String name, Merchant merchant) {
		DetachedCriteria criteria = null;
		try {
			criteria = DetachedCriteria.forClass(Product.class);
			criteria.add(Restrictions.ne("productId", productId));
			criteria.add(Restrictions.eq("name", name));
			criteria.add(Restrictions.eq("merchant", merchant));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Product>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Product> getIsBundleProduct(Merchant merchant) {

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
			criteria.add(Restrictions.eq("merchant",merchant));
			criteria.add(Restrictions.eq("isBundle", 'Y'));
			criteria.add(Restrictions.eq("isKit", 'N'));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Product>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	
	}

	@Override
	public List<Product> getComboOffer(Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ProductOffer.class,"productOffer");
			criteria.add(Restrictions.eq("store",store));
			criteria.createAlias("productOffer.product","product");
			criteria.add(Restrictions.eq("product.isKit", 'Y'));
			criteria.add(Restrictions.eq("product.isactive", 'Y'));
			criteria.add(Restrictions.eq("product.isBundle", 'N'));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			ProjectionList proList = Projections.projectionList();
			proList.add(Projections.property("product"));
			criteria.setProjection(proList);
			return ((List<Product>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	
	
	}
}
