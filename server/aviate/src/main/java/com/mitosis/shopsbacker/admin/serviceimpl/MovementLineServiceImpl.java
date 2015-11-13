package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mitosis.shopsbacker.admin.dao.MovementLineDao;
import com.mitosis.shopsbacker.admin.service.MovementLineService;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;


public class MovementLineServiceImpl <T> implements MovementLineService<T>, Serializable {
	
	@Autowired
	MovementLineDao<T> movementLineDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void addMovementLine(MovementLine movementLine) {
		movementLineDao.addMovementLine(movementLine);
		
	}

	@Override
	public void removeMovementLine(MovementLine movementLine) {
		movementLineDao.removeMovementLine(movementLine);
		
	}

	@Override
	public List<MovementLine> getMovementLineList(Movement movement) {
		return movementLineDao.getMovementLineList(movement);
		
	}

	@Override
	public void removeMovementLineByMovement(Movement movement) {
		movementLineDao.removeMovementLineByMovement(movement);
		
	}

}
