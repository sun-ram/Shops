package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.StoragebinDao;
import com.mitosis.shopsbacker.admin.service.StoragebinService;
import com.mitosis.shopsbacker.model.Storagebin;

@Service("storagebinServiceImpl")
public class StoragebinServiceImpl<T> implements StoragebinService<T>, Serializable{

	/**
	 * 
	 */
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
	public List<Storagebin> uniqueNameChecking(String binName,
			String warehouseId) {
		return storagebinDao.uniqueNameChecking(binName, warehouseId);
	}

}
