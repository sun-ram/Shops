package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.inventory.dao.StoragebinDao;
import com.mitosis.shopsbacker.inventory.service.StoragebinService;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Warehouse;

/**
 * @author fayaz
 */
@Service("storagebinServiceImpl")
public class StoragebinServiceImpl<T> implements StoragebinService<T>,
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	StoragebinDao<T> storagebinDao;

	public StoragebinDao<T> getStoragebinDao() {
		return storagebinDao;
	}

	public void setStoragebinDao(StoragebinDao<T> storagebinDao) {
		this.storagebinDao = storagebinDao;
	}

	@Override
	@Transactional
	public void addStorageBin(Storagebin storagebin) {
		storagebinDao.addStorageBin(storagebin);

	}

	@Override
	@Transactional
	public void removeStorageBin(Storagebin storagebin) {
		storagebinDao.removeStorageBin(storagebin);

	}

	@Override
	@Transactional
	public List<Storagebin> getStoragebin(String binName, Warehouse warehouse,
			String stack, String row, String level) {
		return storagebinDao.getStoragebin(binName, warehouse);
	}

	@Override
	@Transactional
	public List<Storagebin> listOfStorageBins(Warehouse warehouse) {
		return storagebinDao.listOfStorageBins(warehouse);
	}

	@Override
	@Transactional
	public Storagebin getStoragebinById(String storagebinId) {
		return storagebinDao.getStoragebinById(storagebinId);
	}

	@Override
	public void updateStorageBin(Storagebin storagebin) {
		storagebinDao.updateStorageBin(storagebin);
	}

	@Override
	public List<Storagebin> getStoragebin(String storagebinId, String binName,
			Warehouse warehouse, String stack, String row, String level) {
		return storagebinDao.getStoragebin(storagebinId, binName, warehouse,
				stack, row, level);
	}

}
