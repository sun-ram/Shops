package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.SalesOrderReturn;
import com.mitosis.shopsbacker.order.dao.SalesOrderReturnDao;

/**
 * @author Kartheeswaran
 *
 * @param <T>
 */
@Repository
public class SalesOrderReturnDaoImpl<T> extends CustomHibernateDaoSupport<T> 
	implements SalesOrderReturnDao<T>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6314243110555667399L;

	@SuppressWarnings("unchecked")
	@Override
	public String createSalesOrderReturn(SalesOrderReturn orderReturn) {
		try {
			save((T) orderReturn);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return orderReturn.getSalesOrderReturnId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateSalesOrderReturn(
			SalesOrderReturn orderReturn) {
		boolean result = false;
		try {
			update((T) orderReturn);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteSalesOrderReturn(
			SalesOrderReturn orderReturn) {
		boolean result = false;
		try {
			delete((T) orderReturn);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return result;
	}

	@Override
	public SalesOrderReturn getSalesOrderReturnById(String id) {
		try {
			return (SalesOrderReturn) getSession().get(SalesOrderReturn.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

}
