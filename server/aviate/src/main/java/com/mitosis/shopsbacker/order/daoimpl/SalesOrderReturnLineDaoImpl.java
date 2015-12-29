package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.SalesOrderReturnLine;
import com.mitosis.shopsbacker.order.dao.SalesOrderReturnLineDao;

/**
 * @author Kartheeswaran
 *
 * @param <T>
 */
@Repository
public class SalesOrderReturnLineDaoImpl<T> extends CustomHibernateDaoSupport<T> 
	implements SalesOrderReturnLineDao<T>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6314243110555667399L;

	@SuppressWarnings("unchecked")
	@Override
	public String createSalesOrderReturnLine(SalesOrderReturnLine orderReturnLine) {
		try {
			save((T) orderReturnLine);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return orderReturnLine.getSalesOrderReturnLineId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateSalesOrderReturnLine(
			SalesOrderReturnLine orderReturnLine) {
		boolean result = false;
		try {
			update((T) orderReturnLine);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteSalesOrderReturnLine(
			SalesOrderReturnLine orderReturnLine) {
		boolean result = false;
		try {
			delete((T) orderReturnLine);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
		return result;
	}

	@Override
	public SalesOrderReturnLine getSalesOrderReturnLineById(String id) {
		try {
			return (SalesOrderReturnLine) getSession().get(SalesOrderReturnLine.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

}
