package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.inventory.ProductInventoryVo;

public interface ProductInventoryService<T> {
	
	public List<ProductInventory> getProductInventory(Store store, Product product);

	public List<ProductInventory> getProductInventoryByStore(Store store);

	public List<ProductInventory> getProductInventoryByMerchant(Merchant merchant);
	
	public ProductInventoryVo setProductInventoryVo (ProductInventory productInventory) throws Exception;

}
