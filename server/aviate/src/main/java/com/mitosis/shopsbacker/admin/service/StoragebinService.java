package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Storagebin;

public interface StoragebinService<T> {

	public void addStorageBin(Storagebin storagebin);
	public void removeStorageBin(Storagebin storagebin);
	public List<Storagebin> uniqueNameChecking(String binName,String warehouseId); 
}
