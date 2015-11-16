package com.mitosis.shopsbacker.responsevo;

import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;

public class MerchantResponseVo extends ResponseModel{
	
	public List<MerchantVo> merchant;

	public List<MerchantVo> getMerchant() {
		return merchant;
	}

	public void setMerchant(List<MerchantVo> merchants) {
		this.merchant = merchants;
	}

}
