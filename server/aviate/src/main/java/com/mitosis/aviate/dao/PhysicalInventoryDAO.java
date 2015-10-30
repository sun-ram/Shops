package com.mitosis.aviate.dao;

import java.util.List;

import com.mitosis.aviate.model.InventoryModel;
import com.mitosis.aviate.model.WarehouseModel;

public interface PhysicalInventoryDAO {
	public InventoryModel addInventory(InventoryModel inventory);
	public boolean removeInventory(String inventoryId);
	public List<WarehouseModel> warehouseList(String storeId);
	public List<InventoryModel> uniqueNameChecking(String storeId,String warehouseId,String inventoryName, String merchantId);
	public boolean updateInventroy(int inventoryId);
	public InventoryModel getInventory(long long1);
	public void updateInventories(InventoryModel uniqueInventoryList);
}
