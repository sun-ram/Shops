package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;
import com.mitosis.shopsbacker.vo.inventory.MovementLineVo;
import com.mitosis.shopsbacker.vo.inventory.MovementVo;
/**
 * @author RiyazKhan.M
 */
public interface MovementService<T> {
	
	public void addMovement(Movement movement);
	
	public void deleteMovement(String movementId);
	
	public void updateMovement(Movement movement);
	
	public boolean uniqueNameChecking(Store store,Warehouse warehouse,String movementName, Merchant merchant);
	
	public Movement getMovement(String movementId);
	
	public List<Movement> getMovementListByStore(Store store);

	public void processMovement(String string) throws Exception;
	
	public MovementVo setMovementVo(Movement movement);
	
	public MovementLineVo setMovementLineVo(MovementLine movementLine);
	
	public Movement setMovement(MovementVo movementVo, boolean isUpdateProcess)
			throws Exception;
	
	public MovementLine setMovementLine(MovementLineVo movementLineVo,
			boolean isUpdate) throws Exception;
	
	

}
