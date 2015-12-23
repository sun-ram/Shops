package com.mitosis.shopsbacker.vo.inventory;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;

public class ProductUploadVO extends ResponseModel{
	
	int rowNo;
	
	List<ProductUploadDataVo> newData=new ArrayList<ProductUploadDataVo>();
	
	List<ProductUploadDataVo> existingData=new ArrayList<ProductUploadDataVo>();
	
	List<ProductUploadDataVo> rejectedData=new ArrayList<ProductUploadDataVo>();
	
	MerchantVo merchant=new MerchantVo();

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public List<ProductUploadDataVo> getNewData() {
		return newData;
	}

	public void setNewData(List<ProductUploadDataVo> newData) {
		this.newData = newData;
	}

	public List<ProductUploadDataVo> getExistingData() {
		return existingData;
	}

	public void setExistingData(List<ProductUploadDataVo> existingData) {
		this.existingData = existingData;
	}

	public List<ProductUploadDataVo> getRejectedData() {
		return rejectedData;
	}

	public void setRejectedData(List<ProductUploadDataVo> rejectedData) {
		this.rejectedData = rejectedData;
	}

	public MerchantVo getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	
	
	
	
}
