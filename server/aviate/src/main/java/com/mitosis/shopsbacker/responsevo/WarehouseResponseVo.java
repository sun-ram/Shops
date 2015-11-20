/**
 * 
 */
package com.mitosis.shopsbacker.responsevo;

import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.WarehouseVo;

/**
 * @author Anbukkani Gajendran
 *
 */
public class WarehouseResponseVo extends ResponseModel {

 private List<WarehouseVo> warehouses;

/**
 * @return the warehouses
 */
public List<WarehouseVo> getWarehouses() {
	return warehouses;
}

/**
 * @param warehouses the warehouses to set
 */
public void setWarehouses(List<WarehouseVo> warehouses) {
	this.warehouses = warehouses;
}


}
