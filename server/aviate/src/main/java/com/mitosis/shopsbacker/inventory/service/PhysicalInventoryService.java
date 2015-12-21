package com.mitosis.shopsbacker.inventory.service;

import com.mitosis.shopsbacker.responsevo.MovementResponseVo;

public interface PhysicalInventoryService<T> {

	MovementResponseVo getInventory(String storeId);
	
	MovementResponseVo getInventoryById(String movementId);

	void updatePhysicalInventory(String movementId) throws Exception;

}
