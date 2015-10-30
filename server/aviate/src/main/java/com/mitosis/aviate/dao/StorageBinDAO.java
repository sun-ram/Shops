package com.mitosis.aviate.dao;

import java.util.List;

import com.mitosis.aviate.model.StorageBinModel;

public interface StorageBinDAO {
	public boolean addStorageBin(StorageBinModel StorageBin);
	public boolean removeStorageBin(String storageBin,boolean removalFlag);
	public List<StorageBinModel> uniqueNameChecking(String binName,String warehouseId);
}
