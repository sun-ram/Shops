package com.mitosis.shopsbacker.inventory.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;


public interface ProductInventoryDao<T> {
	
	public List<ProductInventory> getProductInventory(Store store, Product productId);

	public List<ProductInventory> getProductInventoryByStore(Store store);

	public List<ProductInventory> getProductInventoryByMerchant(Merchant merchantId);

	public List<ProductInventory> getProductInventory(Product product,
			Storagebin storagebinByToBinId);

	public void updateInventory(ProductInventory productInventory);
}
