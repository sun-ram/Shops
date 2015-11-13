package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;

public interface WarehouseService<T>{
	public void addWarehouse(Warehouse warehouse);
	public void deleteWarehouse(String warehouseId);
	public List<Storagebin> listOfStorageBins(Warehouse warehouse);
	public List<Warehouse> checkUniqueName(String warehouseName,Store store);
	public Warehouse getSingleWarehouse(String warehouseId);
	public void updateWarehouse(Warehouse warehouse);
}
