package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.model.SalesOrderReturn;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.dao.SalesOrderReturnDao;
import com.mitosis.shopsbacker.order.service.SalesOrderReturnService;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.vo.order.SalesOrderReturnVo;

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
	
	@Autowired
	SalesOrderService<T> salesOrderService;

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

	@Override
	public List<SalesOrderReturn> getSalesOrderByStore(Store store) {
		return salesOrderReturnDao.getSalesOrderByStore(store);
	}

	@Override
	public SalesOrderReturnVo setSalesOrderToVo(
			SalesOrderReturn salesOrderReturn) {
		SalesOrderReturnVo salesOrderReturnVo=new SalesOrderReturnVo();
		try {
			salesOrderReturnVo.setSalesOrder(salesOrderService.setSalesOrderVo(salesOrderReturn.getSalesOrder(), false));
			salesOrderReturnVo.setReturnReason(salesOrderReturn.getReturnReason());
			salesOrderReturnVo.setReturnTotalAmount(salesOrderReturn.getReturnTotalAmount());
			salesOrderReturnVo.setReturnTaxAmount(salesOrderReturn.getReturnTaxAmount());
			salesOrderReturnVo.setReturnStatus(salesOrderReturn.getReturnStatus());
			salesOrderReturnVo.setShippingCharge(salesOrderReturn.getShippingCharge());
			salesOrderReturnVo.setIspaid(salesOrderReturn.getIspaid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salesOrderReturnVo;
	}

}
