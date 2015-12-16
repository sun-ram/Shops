package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.inventory.dao.MovementDao;
import com.mitosis.shopsbacker.inventory.dao.ProductInventoryDao;
import com.mitosis.shopsbacker.inventory.service.MovementService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.ResponseModel;
/**
 * @author RiyazKhan.M
 */
@Service("movementServiceImpl")
public class MovementServiceImpl<T> implements MovementService<T>, Serializable {

	@Autowired
	MovementDao<T> movementDao;
	
	@Autowired
	ProductInventoryDao<T> productInventoryDao;
	
	
	public MovementDao<T> getMovementDao() {
		return movementDao;
	}

	public void setMovementDao(MovementDao<T> movementDao) {
		this.movementDao = movementDao;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void addMovement(Movement movement) {
		movementDao.addMovement(movement); 
		
	}

	@Override
	public void deleteMovement(String movementId) {
		movementDao.deleteMovement(getMovement(movementId));
	}

	@Override
	public void updateMovement(Movement movement) {
		movementDao.updateMovement(movement);
		
	}

	@Override
	public boolean uniqueNameChecking(Store store, Warehouse warehouse,
			String movementName, Merchant merchant) {
		return movementDao.uniqueNameChecking(store,warehouse,movementName, merchant);
	}

	@Override
	public Movement getMovement(String movementId) {

		return movementDao.getMovement(movementId);
	}

	@Override
	public List<Movement> getMovementListByStore(Store store) {
		return movementDao.getMovementListByStore(store);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void processMovement(String movementId) throws Exception {
		Movement movement = movementDao.getMovement(movementId);
		List<MovementLine> movementLines = movement.getMovementLines();		
		for (MovementLine movementLine : movementLines) {
			List<ProductInventory> productInventories = productInventoryDao.getProductInventory(movementLine.getProduct(), movementLine.getStoragebinByToBinId());
			ProductInventory productInventory = (ProductInventory) CommonUtil.setAuditColumnInfo(ProductInventory.class.getName());
			productInventory.setIsactive('Y');
			productInventory.setMerchant(movement.getMerchant());
			productInventory.setStore(movement.getStore());
			productInventory.setStoragebin(movementLine.getStoragebinByToBinId());
			productInventory.setProduct(movementLine.getProduct());
			if(!productInventories.isEmpty()){
				productInventory = productInventories.get(0);
				productInventory.setAvailableQty(movementLine.getQty() + productInventory.getAvailableQty());
			}else{
				productInventory.setAvailableQty(movementLine.getQty());
			}
			productInventoryDao.updateInventory(productInventory);	
		}
		movement.setIsmoved('Y');
		movementDao.updateMovement(movement);
	}

}
