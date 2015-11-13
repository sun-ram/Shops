package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.MovementDao;
import com.mitosis.shopsbacker.admin.service.MovementService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;

@Service("movementServiceImpl")
public class MovementServiceImpl<T> implements MovementService<T>, Serializable {

	@Autowired
	MovementDao<T> movementDao;
	
	
	public MovementDao<T> getMovementDao() {
		return movementDao;
	}

	public void setMovementDao(MovementDao<T> movementDao) {
		this.movementDao = movementDao;
	}

	private static final long serialVersionUID = 1L;

	@Override
	@Transactional
	public void addMovement(Movement movement) {
		movementDao.addMovement(movement); 
		
	}

	@Override
	@Transactional
	public void deleteMovement(Movement movement) {
		movementDao.deleteMovement(movement);
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


}
