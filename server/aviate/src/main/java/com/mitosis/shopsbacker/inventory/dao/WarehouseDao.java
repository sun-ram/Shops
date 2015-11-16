package com.mitosis.shopsbacker.inventory.dao;

/**
 * @author JAI BHARATHI
 * 
 */
import java.util.List;

import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;

public interface WarehouseDao<T> {

	public void addWarehouse(Warehouse warehouse);

	public void deleteWarehouse(String warehouseId);

	public List<Warehouse> getWarehouse(String warehouseName, Store store);

	public Warehouse getWarehouse(String warehouseId);

	public void updateWarehouse(Warehouse warehouse);
}
