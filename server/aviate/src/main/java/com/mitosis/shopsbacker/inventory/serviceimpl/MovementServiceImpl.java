package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.inventory.dao.MovementDao;
import com.mitosis.shopsbacker.inventory.dao.ProductInventoryDao;
import com.mitosis.shopsbacker.inventory.service.MovementLineService;
import com.mitosis.shopsbacker.inventory.service.MovementService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.inventory.service.StoragebinService;
import com.mitosis.shopsbacker.inventory.service.WarehouseService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.inventory.MovementLineVo;
import com.mitosis.shopsbacker.vo.inventory.MovementVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;
import com.mitosis.shopsbacker.vo.inventory.StoragebinVo;
import com.mitosis.shopsbacker.vo.inventory.WarehouseVo;
/**
 * @author RiyazKhan.M
 */
@Service("movementServiceImpl")
public class MovementServiceImpl<T> implements MovementService<T>, Serializable {

	@Autowired
	MovementDao<T> movementDao;
	
	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	WarehouseService<T> warehouseService;
	
	@Autowired
	ProductInventoryDao<T> productInventoryDao;
	
	@Autowired
	MovementLineService<T> movementLineService;
	
	@Autowired
	StoragebinService<T> storeagebinService;
	
	@Autowired
	ProductService<T> productService;
	
	@Autowired
	StoreService<T> storeService;
	
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
			ProductInventory productInventory = (ProductInventory) CommonUtil.setAuditColumnInfo(ProductInventory.class.getName(), null);
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
	
	/**
	 * @author Anbukkani Gajendran
	 * @param movement
	 * @return MovementVo
	 */
	public MovementVo setMovementVo(Movement movement) {
		MovementVo movementvo = new MovementVo();
		movementvo.setMovementId(movement.getMovementId());
		movementvo.setIsmoved(movement.getIsmoved());
		movementvo.setIsupdated(movement.getIsupdated());
		movementvo.setName(movement.getName());
		movementvo.setIsMovement(movement.getIsMovement());
		WarehouseVo warehouseVo = new WarehouseVo();
		Warehouse warehouse = movement.getWarehouse();
		warehouseVo.setName(warehouse.getName());
		warehouseVo.setWarehouseId(movement.getWarehouse().getWarehouseId());
		
		List<Storagebin> bins = new ArrayList<Storagebin>();
		List<StoragebinVo> binsVo = new ArrayList<StoragebinVo>();
		bins = warehouse.getStoragebins();
		for(Storagebin bin : bins){
			StoragebinVo binVo = new StoragebinVo();
			binVo.setStoragebinId(bin.getStoragebinId());
			binVo.setName(bin.getName());
			binVo.setLevel(bin.getLevel());
			binVo.setRow(bin.getRow());
			binVo.setStack(bin.getStack());
			binsVo.add(binVo);
		}
		warehouseVo.setStoragebins(binsVo);
		movementvo.setWarehouse(warehouseVo);
		List<MovementLine> movementLines = movement.getMovementLines();
		List<MovementLineVo> movementLineVos = new ArrayList<MovementLineVo>();
		for (MovementLine movementLine : movementLines) {
			MovementLineVo movementLineVo = setMovementLineVo(movementLine);
			movementLineVos.add(movementLineVo);
		}
		movementvo.setMovementLines(movementLineVos);
		return movementvo;
	}

	/**
	 * @author Anbukkani Gajendran
	 * @param movementLine
	 * @return MovementLineVo
	 */
	public MovementLineVo setMovementLineVo(MovementLine movementLine) {
		MovementLineVo movementLineVo = new MovementLineVo();
		movementLineVo.setMovementLineId(movementLine.getMovementLineId());
		movementLineVo.setQty(movementLine.getQty());
		ProductVo productVo = new ProductVo();
		productVo.setName(movementLine.getProduct().getName());
		productVo.setProductId(movementLine.getProduct().getProductId());
		movementLineVo.setProduct(productVo);
		StoragebinVo storagebinVo = new StoragebinVo();
		storagebinVo.setName(movementLine.getStoragebinByToBinId().getName());
		storagebinVo.setStoragebinId(movementLine.getStoragebinByToBinId()
				.getStoragebinId());
		storagebinVo.setLevel(movementLine.getStoragebinByToBinId().getLevel());
		storagebinVo.setStack(movementLine.getStoragebinByToBinId().getStack());
		storagebinVo.setRow(movementLine.getStoragebinByToBinId().getRow());
		movementLineVo.setToStoragebin(storagebinVo);
		return movementLineVo;
	}

	/**
	 * @author Anbukkai Gajendran
	 * @param movementVo
	 * @param isUpdateProcess
	 * @return Movement
	 * @throws Exception
	 */
	public Movement setMovement(MovementVo movementVo, boolean isUpdateProcess)
			throws Exception {
		Movement movement = null;
		if (!isUpdateProcess) {
			movement = (Movement) CommonUtil.setAuditColumnInfo(Movement.class
					.getName(), null);
			movement.setIsactive('Y');
		} else {
			movement = getMovement(movementVo.getMovementId());
			movement.setUpdated(new Date());
			// TODO: Need to get user from session and set to updated by.
			movement.setUpdatedby("123");
		}
		Date date = new Date();
		movement.setName(CommonUtil.dateToString(date));
		movement.setIsmoved('N');
		movement.setIsupdated('N');
		Merchant merchant = merchantService.getMerchantById(movementVo
				.getMerchant().getMerchantId());
		Warehouse warehouse = warehouseService.getWarehouse(movementVo
				.getWarehouse().getWarehouseId());
		Store store = storeService.getStoreById(movementVo.getStore()
				.getStoreId());
		movement.setMerchant(merchant);
		movement.setStore(store);
		movement.setWarehouse(warehouse);

		List<MovementLine> movementLines = new ArrayList<MovementLine>();
		for (MovementLineVo movementLineVo : movementVo.getMovementLines()) {

			boolean isUpdate = movementLineVo.getMovementLineId() != null ? true
					: false;
			MovementLine movementLine = setMovementLine(movementLineVo,
					isUpdate);
			movementLine.setMovement(movement);
			movementLines.add(movementLine);
		}
		movement.setMovementLines(movementLines);
		return movement;
	}

	/**
	 * @author Anbukkani Gajendran
	 * @param movementLineVo
	 * @param isUpdate
	 * @return MovementLine
	 * @throws Exception
	 */
	public MovementLine setMovementLine(MovementLineVo movementLineVo,
			boolean isUpdate) throws Exception {
		MovementLine movementLine = null;
		if (!isUpdate) {
			movementLine = (MovementLine) CommonUtil
					.setAuditColumnInfo(MovementLine.class.getName(), null);
			movementLine.setIsactive('Y');
		} else {
			movementLine = movementLineService.getMovementLine(movementLineVo
					.getMovementLineId());
			movementLine.setCreated(new Date());
			// TODO: Need to get user from session and set to updated by.
			movementLine.setCreatedby("123");
		}

		Storagebin storagebin = storeagebinService
				.getStoragebinById(movementLineVo.getToStoragebin()
						.getStoragebinId());
		movementLine.setStoragebinByToBinId(storagebin);
		movementLine.setProduct(productService.getProduct(movementLineVo
				.getProduct().getProductId()));
		;
		movementLine.setQty(movementLineVo.getQty());
		movementLine.setStoragebinByToBinId(storeagebinService
				.getStoragebinById(movementLineVo.getToStoragebin()
						.getStoragebinId()));
		return movementLine;
	}

}
