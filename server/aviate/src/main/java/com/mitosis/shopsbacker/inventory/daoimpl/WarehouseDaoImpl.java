package com.mitosis.shopsbacker.inventory.daoimpl;

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

/**
 * @author JAI BHARATHI
 * 
 */
@SuppressWarnings({ "serial", "rawtypes" })
@Repository
public class WarehouseDaoImpl<T> extends CustomHibernateDaoSupport implements
		WarehouseDao<T>, Serializable {

	@Override
	public void addWarehouse(Warehouse warehouse) {
		try {
			save((T) warehouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteWarehouse(String id) {
		try {
			Warehouse warehouse = getWarehouse(id);
			delete((T) warehouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Warehouse> getWarehouse(String warehouseName, Store store) {
		DetachedCriteria criteria = null;
		try {
			criteria = DetachedCriteria.forClass(Warehouse.class);
			criteria.add(Restrictions.eq("name", warehouseName));
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<Warehouse>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Warehouse getWarehouse(String id) {
		DetachedCriteria criteria = null;
		try {
			return (Warehouse) getSession().get(Warehouse.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateWarehouse(Warehouse warehouse) {
		try {
			update((T) warehouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
