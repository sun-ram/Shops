package com.mitosis.shopsbacker.admin.dao;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;

public interface  MovementDao<T> {

	public void addMovement(Movement movement);
	public void deleteMovement(Movement movement);
	public void updateMovement(Movement movement);
	public boolean uniqueNameChecking(Store store,Warehouse warehouse,String movementName, Merchant merchant);
	public Movement getMovement(String movementId);
	public void updateMovements(Movement uniqueMovementList);
}
