package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mitosis.shopsbacker.admin.dao.StoreDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;

@SuppressWarnings("serial")
public class StoreDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
StoreDao<T>, Serializable{

	@SuppressWarnings("unchecked")
	@Override
	public void addUpdateStoreDetails(Store store) {
		try {
			update((T) store);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeStore(Store store) {
		delete((T) store);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateOrderStatus(SalesOrder salesOrder) {
		try {
			update((T) salesOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeEmployee(Customer customer) {
		delete((T) customer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Store getStoreDetailsById(String id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Store.class);
		criteria.add(Restrictions.eq("storeId", id));
		return ((List<Store>) findAll(criteria)).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalesOrder> getUserDetails(JSONObject requestObj) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SalesOrder.class);
		try {
			criteria.add(Restrictions.eq("storeId", requestObj.getString("storeId")));
			criteria.add(Restrictions.eq("isPaid","Paid"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return (List<SalesOrder>) findAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getStoreByMerchant(Merchant merchant) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Store.class);
		criteria.add(Restrictions.eq("merchant", merchant));
		return ((List<Store>) findAll(criteria));
	}

	@Override
	public Store getStoreLogoByImageName(String imageName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Customer> getEmployeeList(String storeId, List<String> roles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SalesOrder> getOrderList(JSONObject requestObj) {
		// TODO Auto-generated method stub
		return null;
	}

}
