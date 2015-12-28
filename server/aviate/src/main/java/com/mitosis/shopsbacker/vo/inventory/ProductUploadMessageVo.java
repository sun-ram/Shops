package com.mitosis.shopsbacker.vo.inventory;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;

public class ProductUploadMessageVo extends ResponseModel{

	List<ProductRejectedVo> productRejectedVo=new ArrayList<ProductRejectedVo>();

	public List<ProductRejectedVo> getProductRejectedVo() {
		return productRejectedVo;
	}

	public void setProductRejectedVo(List<ProductRejectedVo> productRejectedVo) {
		this.productRejectedVo = productRejectedVo;
	}
	
	
}
