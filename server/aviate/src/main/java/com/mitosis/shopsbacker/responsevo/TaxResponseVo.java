package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.TaxVo;

public class TaxResponseVo extends ResponseModel{
	
	public List<TaxVo> taxList = new ArrayList<TaxVo>();

	public List<TaxVo> getTaxList() {
		return taxList;
	}

	public void setTaxList(List<TaxVo> taxList) {
		this.taxList = taxList;
	}
	
}
