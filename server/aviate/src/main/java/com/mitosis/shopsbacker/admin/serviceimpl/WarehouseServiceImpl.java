package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.WarehouseDao;
import com.mitosis.shopsbacker.admin.service.WarehouseService;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Warehouse;

@Service
public class WarehouseServiceImpl<T> implements WarehouseService<T>, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	WarehouseDao<T> warehouseDao;
	
	public WarehouseDao<T> getWarehouseDao() {
		return warehouseDao;
	}

	public void setWarehouseDao(WarehouseDao<T> warehouseDao) {
		this.warehouseDao = warehouseDao;
	}

	@Override
	@Transactional
	public void addWarehouse(Warehouse warehouse) {
		
		warehouseDao.addWarehouse(warehouse);
	}

	@Override
	@Transactional
	public void deleteWarehouse(String warehouseId) {
		warehouseDao.deleteWarehouse(warehouseId);		
	}

	@Override
	@Transactional
	public List<Storagebin> listOfStorageBins(String warehouseId) {
		return warehouseDao.listOfStorageBins(warehouseId);		
	}

	@Override
	@Transactional
	public List<Warehouse> checkUniqueName(String warehouseName, String storeId) {
		return warehouseDao.checkUniqueName(warehouseName, storeId);
	}

	@Override
	@Transactional
	public Warehouse getSingleWarehouse(String warehouseId) {
		return warehouseDao.getSingleWarehouse(warehouseId);
	}

	@Override
	@Transactional
	public void updateWarehouse(Warehouse warehouse) {
		warehouseDao.updateWarehouse(warehouse);
	}
	

}
