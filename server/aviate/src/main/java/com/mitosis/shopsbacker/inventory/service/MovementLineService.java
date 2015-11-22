package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;

/**
 * @author RiyazKhan.M
 */
public interface MovementLineService<T> {
	
	public MovementLine getMovementLine(String id);

	public void addMovementLine(MovementLine movementLine);

	public void removeMovementLine(MovementLine movementLine);

	public void removeMovementLineByMovement(Movement movement);

	public List<MovementLine> getMovementLineList(Movement movement);

}
