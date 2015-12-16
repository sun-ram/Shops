package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.ProductInventoryDao;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;

@Repository
@Transactional
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
		Criteria criteria = getSession().createCriteria(ProductInventory.class,"productInventory");
		
		criteria.createAlias("productInventory.product", "product");
		criteria.createAlias("productInventory.storagebin", "storagebin");
		criteria.createAlias("storagebin.warehouse", "warehouse");
		
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("productInventory.id"), "id");
        proList.add(Projections.property("productInventory.availableQty"), "availableQty");
        proList.add(Projections.property("product.name"), "name");
        proList.add(Projections.property("product.productId"), "productId");
        proList.add(Projections.property("storagebin.name"), "binname");
        proList.add(Projections.property("storagebin.row"), "row");
        proList.add(Projections.property("storagebin.stack"), "stack");
        proList.add(Projections.property("storagebin.level"), "level");
        proList.add(Projections.property("warehouse.name"), "warehouseName");
        criteria.setProjection(proList);
        
		criteria.add(Restrictions.eq("store", store));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductInventory> getProductInventoryByMerchant(Merchant merchant) {
		Criteria criteria = getSession().createCriteria(ProductInventory.class,"productInventory");
		
		criteria.createAlias("productInventory.product", "product");
		criteria.createAlias("productInventory.storagebin", "storagebin");
		criteria.createAlias("storagebin.warehouse", "warehouse");
		
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("productInventory.id"), "id");
        proList.add(Projections.property("productInventory.availableQty"), "availableQty");
        proList.add(Projections.property("product.name"), "name");
        proList.add(Projections.property("product.productId"), "productId");
        proList.add(Projections.property("storagebin.name"), "binname");
        proList.add(Projections.property("storagebin.row"), "row");
        proList.add(Projections.property("storagebin.stack"), "stack");
        proList.add(Projections.property("storagebin.level"), "level");
        proList.add(Projections.property("warehouse.name"), "warehouseName");
        criteria.setProjection(proList);
        
		criteria.add(Restrictions.eq("merchant", merchant));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<ProductInventory> getProductInventory(Product product,
			Storagebin storagebinByToBinId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ProductInventory.class);
		criteria.add(Restrictions.eq("storagebin", storagebinByToBinId));
		criteria.add(Restrictions.eq("product", product));
		return ((List<ProductInventory>) findAll(criteria));
	}

	@SuppressWarnings("unchecked")
	public void updateInventory(ProductInventory productInventory) {
		try {
			update((T) productInventory);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<ProductInventory[]> getProductInventoryByMerchant(Merchant merchant) {
		String hql =   "select pin.id, pin.availableQty,product.name,product.productId,sbin.name,sbin.row,sbin.stack,sbin.level,warehouse.name"+
					  "from com.mitosis.shopsbacker.model.ProductInventory pin"+
					  "join pin.product product"+
					  "join product.storagebin sbin"+
					  "join sbin.warehouse warehouse"+
					  "where pin.merchant = :merchant";
		Query query = getSession().createQuery(hql);
        List<ProductInventory[]> list = query.list();
        for(Object[] arr : list){
            System.out.println(Arrays.toString(arr));
        }
		return list;
	}*/
	
}
