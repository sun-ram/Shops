package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;

public interface MovementLineService<T> {
	
	public void addMovementLine(MovementLine movementLine);
	
	public void removeMovementLine(MovementLine movementLine);
	
	public void removeMovementLineByMovement(Movement movement);
	
	public List<MovementLine> getMovementLineList(Movement movement);

}
