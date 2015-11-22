package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.MovementVo;

/**
 * @author Anbukkani Gajendiran
 *
 */
public class MovementResponseVo extends ResponseModel {

	List<MovementVo> movements=new ArrayList<MovementVo>();

	public List<MovementVo> getMovements() {
		return movements;
	}

	public void setMovements(List<MovementVo> movements) {
		this.movements = movements;
	} 
}
