package com.mitosis.aviate.dao;

import java.util.List;

import com.mitosis.aviate.model.InventoryLineModel;
import com.mitosis.aviate.model.InventoryModel;
import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.ProductUOM;
import com.mitosis.aviate.model.StorageBinModel;

public interface PhysicalInventoryLineDAO {
	public boolean addInventoryLine(InventoryLineModel inventoryLine);
	public boolean removeInventory(String inventoryLineId);
	public boolean removeInventoryById(int inventoryId);
	public List<ProductDetails> productList(String storeId);
	public List<StorageBinModel> storageBinList(String warehouseId);
	public List<ProductUOM> unitOfMeasureList(String productId);
	public List<InventoryModel> listOfInventoryies(String storeId);
	public List<InventoryLineModel> getInventoryLineList(int inventoryLineId);
}
