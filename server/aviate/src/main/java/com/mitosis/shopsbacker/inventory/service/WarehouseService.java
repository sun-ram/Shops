package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;
import com.mitosis.shopsbacker.vo.inventory.WarehouseVo;

/**
 * @author JAI BHARATHI
 * 
 */
public interface WarehouseService<T> {
	public void addWarehouse(Warehouse warehouse);

	public void deleteWarehouse(String warehouseId);

	public List<Warehouse> getWarehouse(String warehouseName, Store store);

	public Warehouse getWarehouse(String warehouseId);

	public void updateWarehouse(Warehouse warehouse);
	
	public Warehouse setWarehouse(WarehouseVo warehouseVo, Store store,
			boolean isUpdateProcess) throws Exception ;
	
	public List<Warehouse> getWarehouse(Store store);
	
	public List<Warehouse> getWarehouse(String WarehouseId,String warehouseName, Store store);
}
