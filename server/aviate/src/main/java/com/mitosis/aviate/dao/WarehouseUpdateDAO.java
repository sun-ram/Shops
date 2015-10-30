package com.mitosis.aviate.dao;

import java.util.List;

import com.mitosis.aviate.model.StorageBinModel;
import com.mitosis.aviate.model.WarehouseModel;


public interface WarehouseUpdateDAO {
	public boolean addWarehouse(WarehouseModel warehouseModel);
	public boolean deleteWarehouse(String warehouseId);
	public List<StorageBinModel> listOfStorgaBins(String warehouseId);
	public List<WarehouseModel> checkUniqueName(String warehouseName,String storeId);
	public WarehouseModel getSingleWarehouse(int warehouseId);
	public boolean updateWarehouse(WarehouseModel warehouseModel);
}
