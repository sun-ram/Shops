package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.inventory.dao.ProductInventoryDao;
import com.mitosis.shopsbacker.inventory.service.ProductInventoryService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.inventory.ProductInventoryVo;

@Service("productInventoryServiceImpl") 
public class ProductInventoryServiceImpl<T> implements
		ProductInventoryService<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ProductInventoryDao<T> productInventoryDao;
	
	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	MerchantService<T> merchantService;

	public ProductInventoryDao<T> getProductInventoryDao() {
		return productInventoryDao;
	}

	public void setProductInventoryDao(
			ProductInventoryDao<T> productInventoryDao) {
		this.productInventoryDao = productInventoryDao;
	}
	
	public StoreService<T> getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService<T> storeService) {
		this.storeService = storeService;
	}
	
	public MerchantService<T> getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService<T> merchantService) {
		this.merchantService = merchantService;
	}

	@Override
	@Transactional
	public List<ProductInventory> getProductInventory(Store store,
			Product product) {
		return getProductInventoryDao().getProductInventory(store, product);
	}

	@Override
	@Transactional
	public List<ProductInventory> getProductInventoryByStore(Store store) {
		return getProductInventoryDao().getProductInventoryByStore(store);
	}

	@Override
	@Transactional
	public List<ProductInventory> getProductInventoryByMerchant(
			Merchant merchant) {
		return getProductInventoryDao().getProductInventoryByMerchant(merchant);
	}
	
	public ProductInventoryVo setProductInventoryVo (ProductInventory productInventory) throws Exception {
		ProductInventoryVo productInventoryVo = new ProductInventoryVo();
		productInventoryVo.setStorevo(getStoreService().setStoreVo(productInventory.getStore()));
		productInventoryVo.setAvailableQty(productInventory.getAvailableQty());
		productInventoryVo.setMerchantVo(getMerchantService().setMerchantVo(productInventory.getMerchant()));
		productInventoryVo.setProduct(productInventory.getProduct());
		productInventoryVo.setStoragebin(productInventory.getStoragebin());
		return productInventoryVo;
	}

}
