package com.mitosis.shopsbacker.admin.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Storagebin;
public interface StoragebinDao<T> {

	public void addStorageBin(Storagebin storagebin);
	public void removeStorageBin(Storagebin storagebin);
	public Storagebin getStoragebinById(String storagebinId);
	public List<Storagebin> uniqueNameChecking(String binName,String warehouseId); 
	
}
