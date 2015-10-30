package com.mitosis.aviate.webservice.admin.warehouse;

import java.util.List;

import com.mitosis.aviate.model.StorageBinModel;
import com.mitosis.aviate.model.WarehouseModel;
import com.mitosis.aviate.model.service.ResponseModel;

public class WarehouseResponseModel extends ResponseModel {
	List<StorageBinModel> storageBinList;
	List<WarehouseModel> warehouseList;
	
	public List<StorageBinModel> getStorageBinList() {
		return storageBinList;
	}
	public void setStorageBinList(List<StorageBinModel> storageBinList) {
		this.storageBinList = storageBinList;
	}
	public List<WarehouseModel> getWarehouseList() {
		return warehouseList;
	}
	public void setWarehouseList(List<WarehouseModel> warehouseList) {
		this.warehouseList = warehouseList;
	}
	

}
