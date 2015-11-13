package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.admin.dao.WarehouseDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;

@SuppressWarnings({ "serial", "rawtypes" })
@Repository
public class WarehouseDaoImpl<T> extends CustomHibernateDaoSupport implements
WarehouseDao<T>, Serializable {

	@SuppressWarnings("unchecked")
	@Override
	public void addWarehouse(Warehouse warehouse) {
		save((T) warehouse);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteWarehouse(String id) {
		Warehouse criteria = getSingleWarehouse(id);
		delete((T) criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Storagebin> listOfStorageBins(Warehouse warehouse) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Storagebin.class);
		criteria.add(Restrictions.eq("warehouse", warehouse));
		return ((List<Storagebin>) findAll(criteria));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> checkUniqueName(String warehouseName, Store store) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Warehouse.class);
		criteria.add(Restrictions.eq("name", warehouseName));
		criteria.add(Restrictions.eq("store", store));
		return ((List<Warehouse>) findAll(criteria));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Warehouse getSingleWarehouse(String id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Warehouse.class);
		criteria.add(Restrictions.eq("warehouseId", id));
		return ((List<Warehouse>) findAll(criteria)).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateWarehouse(Warehouse warehouse) {
		try {
			update((T) warehouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
