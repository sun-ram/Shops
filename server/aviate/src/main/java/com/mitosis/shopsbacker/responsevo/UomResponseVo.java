/**
 * 
 */
package com.mitosis.shopsbacker.responsevo;

import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.UomVo;

/**
 * @author Anbukkani Gajendran
 *
 */
public class UomResponseVo extends ResponseModel {

	public List<UomVo> uom;

	public List<UomVo> getUom() {
		return uom;
	}

	public void setUom(List<UomVo> uom) {
		this.uom = uom;
	}
}
