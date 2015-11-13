package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.MovementLineDao;
import com.mitosis.shopsbacker.admin.service.MovementLineService;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;

@Service("movementLineServiceImpl")
public class MovementLineServiceImpl <T> implements MovementLineService<T>, Serializable {
	
	@Autowired
	MovementLineDao<T> movementLineDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional
	public void addMovementLine(MovementLine movementLine) {
		movementLineDao.addMovementLine(movementLine);
		
	}

	@Override
	@Transactional
	public void removeMovementLine(MovementLine movementLine) {
		movementLineDao.removeMovementLine(movementLine);
		
	}

	@Override
	@Transactional
	public List<MovementLine> getMovementLineList(Movement movement) {
		return movementLineDao.getMovementLineList(movement);
		
	}

	@Override
	@Transactional
	public void removeMovementLineByMovement(Movement movement) {
		movementLineDao.removeMovementLineByMovement(movement);
		
	}

}
