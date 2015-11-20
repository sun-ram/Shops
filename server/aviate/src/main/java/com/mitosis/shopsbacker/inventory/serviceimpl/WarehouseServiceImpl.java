package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.dao.AddressDao;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.inventory.dao.WarehouseDao;
import com.mitosis.shopsbacker.inventory.service.WarehouseService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.inventory.WarehouseVo;

/**
 * @author JAI BHARATHI
 * 
 */
@Service("WarehouseServiceImpl")
public class WarehouseServiceImpl<T> implements WarehouseService<T>,
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	WarehouseDao<T> warehouseDao;
	
	@Autowired
	AddressService<T> addressService;

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
		Warehouse warehouse = warehouseDao.getWarehouse(warehouseId);
		warehouseDao.deleteWarehouse(warehouse);
	}

	@Override
	@Transactional
	public Warehouse getWarehouse(String warehouseId) {
		return warehouseDao.getWarehouse(warehouseId);
	}

	@Override
	@Transactional
	public void updateWarehouse(Warehouse warehouse) {
		warehouseDao.updateWarehouse(warehouse);
	}

	@Override
	@Transactional
	public List<Warehouse> getWarehouse(String warehouseName, Store store) {
		
		return warehouseDao.getWarehouse(warehouseName, store);
	}
	

	/**
	 * @author Anbukkani Gajendran
	 * @param warehouseVo
	 * @param store
	 * @param isUpdateProcess
	 * @return
	 * @throws Exception
	 */
	public Warehouse setWarehouse(WarehouseVo warehouseVo, Store store,
			boolean isUpdateProcess) throws Exception {
		Warehouse warehouse = null;
		if (!isUpdateProcess) {
			warehouse = (Warehouse) CommonUtil
					.setAuditColumnInfo(Warehouse.class.getName());
			warehouse.setIsactive('Y');
		} else {
			warehouse = warehouseDao.getWarehouse(warehouseVo
					.getWarehouseId());
			warehouse.setUpdated(new Date());
			//TODO:Need to get user from session and set as update by 
			warehouse.setUpdatedby("12345");
		}
		warehouse.setMerchant(store.getMerchant());
		warehouse.setName(warehouseVo.getName());
		warehouse.setStore(store);
		warehouse.setDescription(warehouseVo.getDescription());
		Address address = addressService.setAddress(warehouseVo.getAddress());
		warehouse.setAddress(address);
		return warehouse;
	}
	
	@Override
	public List<Warehouse> getWarehouse(Store store){
		return warehouseDao.getWarehouse(store);
	}

	@Override
	@Transactional
	public List<Warehouse> getWarehouse(String warehouseId, String warehouseName, Store store) {
		
		return warehouseDao.getWarehouse(warehouseId,warehouseName, store);
	}
}
