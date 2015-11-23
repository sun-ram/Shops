/**
 * 
 */
package com.mitosis.shopsbacker.responsevo;

import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.StoragebinVo;
import com.mitosis.shopsbacker.vo.inventory.WarehouseVo;

/**
 * @author Anbukkani Gajendran
 *
 */
public class StoragebinResponseVo extends ResponseModel {

	private List<StoragebinVo> storagebins;
	private List<WarehouseVo> Warehouses;
	
	public List<StoragebinVo> getStoragebins() {
		return storagebins;
	}
	public void setStoragebins(List<StoragebinVo> storagebins) {
		this.storagebins = storagebins;
	}
	public List<WarehouseVo> getWarehouses() {
		return Warehouses;
	}
	public void setWarehouses(List<WarehouseVo> warehouses) {
		Warehouses = warehouses;
	}
}
