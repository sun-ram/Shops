package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.inventory.dao.MovementLineDao;
import com.mitosis.shopsbacker.inventory.service.MovementLineService;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;

/**
 * @author RiyazKhan.M
 */
@Service("movementLineServiceImpl")
public class MovementLineServiceImpl<T> implements MovementLineService<T>,
Serializable {

	@Autowired
	MovementLineDao<T> movementLineDao;

	private static final long serialVersionUID = 1L;

	@Override
	public void addMovementLine(MovementLine movementLine) {
		movementLineDao.addMovementLine(movementLine);

	}
	
	@Override
	public void updateMovementLine(MovementLine movementLine) {
		movementLineDao.updateMovementLine(movementLine);

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

	@Override
	public MovementLine getMovementLine(String id) {
		return movementLineDao.getMovementLine(id);
	}

}
