package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.responsevo.ProductStockResponseVo;

public interface ProductInventoryService<T> {
	
	public List<ProductInventory> getProductInventory(Store store, Product product);

	public List<ProductInventory> getProductInventoryByStore(Store store);

	public List<ProductInventory> getProductInventoryByMerchant(Merchant merchant);
	
	public ProductStockResponseVo setProductStockVo (List productInventoryList);
	
	public void updateInventory(ProductInventory productInventory);

}
