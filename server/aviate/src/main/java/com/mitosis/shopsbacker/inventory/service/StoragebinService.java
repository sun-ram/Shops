package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Warehouse;

/**
 * @author fayaz
 */
public interface StoragebinService<T> {

	public void addStorageBin(Storagebin storagebin);

	public void removeStorageBin(Storagebin storagebin);

	public List<Storagebin> getStoragebin(String binName, Warehouse warehouse, String stack, String row, String level);

	public List<Storagebin> listOfStorageBins(Warehouse warehouse);
	
	public  Storagebin  getStoragebinById(String storagebinId);
	
	public void updateStorageBin(Storagebin storagebin);
	
	public List<Storagebin> getStoragebin(String storagebinId,String binName, Warehouse warehouse, String stack, String row, String level);
}
