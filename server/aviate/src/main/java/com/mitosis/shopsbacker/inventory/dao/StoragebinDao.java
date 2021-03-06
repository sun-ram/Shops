package com.mitosis.shopsbacker.inventory.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Warehouse;

/**
 * @author fayaz
 */
public interface StoragebinDao<T> {

	public void addStorageBin(Storagebin storagebin);

	public void removeStorageBin(Storagebin storagebin);

	public Storagebin getStoragebinById(String storagebinId);

	public List<Storagebin> getStoragebin(String binName, Warehouse warehouse);

	public List<Storagebin> listOfStorageBins(Warehouse warehouse);
	
	public void updateStorageBin(Storagebin storagebin);
	
	public List<Storagebin> getStoragebin(String storagebinId, String binName,
			Warehouse warehouse, String stack, String row, String level);

}
