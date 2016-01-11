package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.inventory.dao.MovementDao;
import com.mitosis.shopsbacker.inventory.dao.ProductInventoryDao;
import com.mitosis.shopsbacker.inventory.service.ProductInventoryService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.responsevo.ProductStockResponseVo;
import com.mitosis.shopsbacker.vo.inventory.ProductInventoryVo;
import com.mitosis.shopsbacker.vo.inventory.ProductStockVo;

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

	public ProductStockResponseVo setProductStockVo(List productInventoryList) {
		ProductStockResponseVo productResponse = new ProductStockResponseVo();
		for (Object productInventory : productInventoryList) {
			ProductStockVo productStockVo = new ProductStockVo();
			Object[] projection = (Object[]) productInventory;
			productStockVo.setProductInventoryId((String) projection[0]);
			productStockVo.setAvailableQty((int) projection[1]);
			productStockVo.setProductName((String) projection[2]);
			productStockVo.setProductId((String) projection[3]);
			productStockVo.setStoragebinName((String) projection[4]);
			productStockVo.setRow((String) projection[5]);
			productStockVo.setStack((String) projection[6]);
			productStockVo.setLevel((String) projection[7]);
			productStockVo.setWarehouseName((String) projection[8]);

			productStockVo.setWarehouseId((String) projection[9]);
			productStockVo.setStoragebinId((String) projection[10]);

			productResponse.getProductListVo().add(productStockVo);
		}
		return productResponse;
	}

	public void updateInventory(ProductInventory productInventory) {
		productInventoryDao.updateInventory(productInventory);
	}

	@Override
	@Transactional
	public ProductInventoryVo setProductInventory(
			List<ProductInventory> productInventoryList) {
		int totalQty = 0;
		ProductInventoryVo productInventoryVo = new ProductInventoryVo();
		for (ProductInventory productInventory : productInventoryList) {
			totalQty = totalQty + productInventory.getAvailableQty();
		}
		productInventoryVo.setAvailableQty(totalQty);
		return productInventoryVo;
	}

}
