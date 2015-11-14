package com.mitosis.shopsbacker.admin.dao;

/**
 * @author JAI BHARATHI
 * 
 */
import java.util.List;

import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;
import com.mitosis.shopsbacker.model.Storagebin;

public interface WarehouseDao<T> {

	public void addWarehouse(Warehouse warehouse);

	public void deleteWarehouse(String warehouseId);

	public List<Warehouse> getWarehouse(String warehouseName, Store store);

	public Warehouse getWarehouse(String warehouseId);

	public void updateWarehouse(Warehouse warehouse);
}
