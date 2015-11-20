/**
 * 
 */
package com.mitosis.shopsbacker.responsevo;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.StoragebinVo;

/**
 * @author Anbukkani Gajendran
 *
 */
public class StoragebinResponseVo extends ResponseModel {

	private StoragebinVo storagebinVo;

	/**
	 * @return the storagebinVo
	 */
	public StoragebinVo getStoragebinVo() {
		return storagebinVo;
	}

	/**
	 * @param storagebinVo
	 *            the storagebinVo to set
	 */
	public void setStoragebinVo(StoragebinVo storagebinVo) {
		this.storagebinVo = storagebinVo;
	}
}
