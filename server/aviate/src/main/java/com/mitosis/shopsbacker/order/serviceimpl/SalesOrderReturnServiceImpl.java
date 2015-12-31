package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.model.SalesOrderReturn;
import com.mitosis.shopsbacker.order.dao.SalesOrderReturnDao;
import com.mitosis.shopsbacker.order.service.SalesOrderReturnService;

/**
 * @author Kartheeswaran
 *
 * @param <T>
 */
@Service("salesOrderReturnServiceImpl")
public class SalesOrderReturnServiceImpl<T> implements
						SalesOrderReturnService<T>, Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8232879086937675374L;
	
	@Autowired
	SalesOrderReturnDao<T> salesOrderReturnDao;

	@Override
	public String createSalesOrderReturn(SalesOrderReturn orderReturn) {
		String orderReturnId = salesOrderReturnDao.createSalesOrderReturn(orderReturn);
		return orderReturnId;
	}

	@Override
	public boolean updateSalesOrderReturn(
			SalesOrderReturn orderReturn) {
		return salesOrderReturnDao.updateSalesOrderReturn(orderReturn);
	}

	@Override
	public boolean deleteSalesOrderReturn(
			SalesOrderReturn orderReturn) {
		return salesOrderReturnDao.deleteSalesOrderReturn(orderReturn);
	}

	@Override
	public SalesOrderReturn getSalesOrderReturnById(String id) {
		return salesOrderReturnDao.getSalesOrderReturnById(id);
	}

}
