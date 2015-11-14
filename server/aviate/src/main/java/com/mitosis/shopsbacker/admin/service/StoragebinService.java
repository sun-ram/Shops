package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Warehouse;

/**
 * @author fayaz
 */
public interface StoragebinService<T> {

	public void addStorageBin(Storagebin storagebin);

	public void removeStorageBin(Storagebin storagebin);

	public List<Storagebin> getStoragebin(String binName, Warehouse warehouse);
}
