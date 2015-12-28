package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.inventory.dao.MovementDao;
import com.mitosis.shopsbacker.inventory.dao.ProductInventoryDao;
import com.mitosis.shopsbacker.inventory.service.MovementService;
import com.mitosis.shopsbacker.inventory.service.PhysicalInventoryService;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.responsevo.MovementResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.inventory.MovementVo;

@Service("physicalInventoryServiceImpl")
public class PhysicalInventoryServiceImpl<T> implements
		PhysicalInventoryService<T> {
	
	@Autowired
	ProductInventoryDao<T> productInventoryDao;
	
	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	MovementDao<T> movementDao;
	
	@Autowired
	MovementService<T> movementService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MovementResponseVo getInventory(String storeId) {
		MovementResponseVo response = new MovementResponseVo();
		Store store = storeService.getStoreById(storeId);
		List<Movement> movements = movementDao.getInventories(store);
		List<MovementVo> movementVos = new ArrayList<MovementVo>();
		for (Movement movement : movements) {
			MovementVo movementvo = movementService.setMovementVo(movement);
			movementVos.add(movementvo);
		}
		response.setMovements(movementVos);
		return response;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MovementResponseVo getInventoryById(String movementId) {
		MovementResponseVo response = new MovementResponseVo();
		Movement movement = movementService.getMovement(movementId);
		MovementVo movementvo = movementService.setMovementVo(movement);
		response.setMovement(movementvo);
		return response;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updatePhysicalInventory(String movementId) throws Exception {
		Movement movement = movementDao.getMovement(movementId);
		List<MovementLine> movementLines = movement.getMovementLines();		
		for (MovementLine movementLine : movementLines) {
			List<ProductInventory> productInventories = productInventoryDao.getProductInventory(movementLine.getProduct(), movementLine.getStoragebinByToBinId());
			ProductInventory productInventory = (ProductInventory) CommonUtil.setAuditColumnInfo(ProductInventory.class.getName(), null);
			productInventory.setIsactive('Y');
			productInventory.setMerchant(movement.getMerchant());
			productInventory.setStore(movement.getStore());
			productInventory.setStoragebin(movementLine.getStoragebinByToBinId());
			productInventory.setProduct(movementLine.getProduct());
			if(!productInventories.isEmpty()){
				productInventory = productInventories.get(0);
				productInventory.setAvailableQty(movementLine.getQty());
			}else{
				productInventory.setAvailableQty(movementLine.getQty());
			}
			productInventoryDao.updateInventory(productInventory);	
		}
		movement.setIsupdated('Y');
		movementDao.updateMovement(movement);
	}


}
