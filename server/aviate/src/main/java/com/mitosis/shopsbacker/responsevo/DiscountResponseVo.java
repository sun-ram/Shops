package com.mitosis.shopsbacker.responsevo;

/**
 * @author JAI BHARATHI.S
 */
import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.DiscountVo;

public class DiscountResponseVo extends ResponseModel{
	
	public List<DiscountVo> discountVos = new ArrayList<DiscountVo>();
	
	public DiscountVo discount;

	public List<DiscountVo> getDiscountVos() {
		return discountVos;
	}

	public void setDiscountVos(List<DiscountVo> discountVos) {
		this.discountVos = discountVos;
	}

	public DiscountVo getDiscount() {
		return discount;
	}

	public void setDiscount(DiscountVo discount) {
		this.discount = discount;
	}

}
