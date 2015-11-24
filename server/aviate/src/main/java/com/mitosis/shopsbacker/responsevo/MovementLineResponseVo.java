package com.mitosis.shopsbacker.responsevo;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.MovementLineVo;

public class MovementLineResponseVo extends ResponseModel {
	
	public MovementLineVo movementLine = new MovementLineVo();

	public MovementLineVo getMovementLine() {
		return movementLine;
	}

	public void setMovementLine(MovementLineVo movementLine) {
		this.movementLine = movementLine;
	}
}
