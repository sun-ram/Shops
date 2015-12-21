package com.mitosis.shopsbacker.inventory.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;

/**
 * @author RiyazKhan.M
 */
public interface MovementDao<T> {

	public void addMovement(Movement movement);

	public void deleteMovement(Movement movement);

	public void updateMovement(Movement movement);

	public boolean uniqueNameChecking(Store store, Warehouse warehouse,
			String movementName, Merchant merchant);

	public Movement getMovement(String movementId);

	public List<Movement> getMovementListByStore(Store store);

	public List<Movement> getInventories(Store store);
}
