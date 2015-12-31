package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.model.SalesOrderReturnLine;
import com.mitosis.shopsbacker.order.dao.SalesOrderReturnLineDao;
import com.mitosis.shopsbacker.order.service.SalesOrderReturnLineService;

/**
 * @author Kartheeswaran
 *
 * @param <T>
 */
@Service("salesOrderReturnLineServiceImpl")
public class SalesOrderReturnLineServiceImpl<T> implements
						SalesOrderReturnLineService<T>, Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1073281613702334384L;
	
	@Autowired
	SalesOrderReturnLineDao<T> salesOrderReturnLineDao;

	@Override
	public String createSalesOrderReturnLine(SalesOrderReturnLine orderReturnLine) {
		String orderReturnLineId = salesOrderReturnLineDao.createSalesOrderReturnLine(orderReturnLine);
		return orderReturnLineId;
	}

	@Override
	public boolean updateSalesOrderReturnLine(
			SalesOrderReturnLine orderReturnLine) {
		return salesOrderReturnLineDao.updateSalesOrderReturnLine(orderReturnLine);
	}

	@Override
	public boolean deleteSalesOrderReturnLine(
			SalesOrderReturnLine orderReturnLine) {
		return salesOrderReturnLineDao.deleteSalesOrderReturnLine(orderReturnLine);
	}

	@Override
	public SalesOrderReturnLine getSalesOrderReturnLineById(String id) {
		return salesOrderReturnLineDao.getSalesOrderReturnLineById(id);
	}

}
