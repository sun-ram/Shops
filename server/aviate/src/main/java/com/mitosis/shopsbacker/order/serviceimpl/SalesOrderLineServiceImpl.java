package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.order.dao.SalesOrderLineDao;
import com.mitosis.shopsbacker.order.service.SalesOrderLineService;
import com.mitosis.shopsbacker.vo.order.SalesOrderLineVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;

@Service("salesOrderLineServiceImpl")
public class SalesOrderLineServiceImpl<T> implements
						SalesOrderLineService<T>, Serializable{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	SalesOrderLineDao<T> salesOrderLineDao;
	
	
	public SalesOrderLineDao<T> getSalesOrderLineDao() {
		return salesOrderLineDao;
	}


	public void setSalesOrderLineDao(SalesOrderLineDao<T> salesOrderLineDao) {
		this.salesOrderLineDao = salesOrderLineDao;
	}


	@Override
	public List<SalesOrderLine> getSalesOrderLineById(String id) {
		return salesOrderLineDao.getSalesOrderLineById(id);
	}
	
	public SalesOrderLineVo setSalesOrderLineVo (SalesOrderLine salesOrderLine) {
		SalesOrderLineVo salesOrderLineVo = new SalesOrderLineVo();
		salesOrderLineVo.setSalesOrderLineId(salesOrderLine.getSalesOrderId());
		salesOrderLineVo.setSalesOrderId(salesOrderLine.getSalesOrderId());
		salesOrderLineVo.setQty(salesOrderLine.getQty());
		salesOrderLine.setGrossAmount(salesOrderLine.getGrossAmount());
		salesOrderLine.setNetAmount(salesOrderLine.getNetAmount());
		salesOrderLine.setPrice(salesOrderLine.getPrice());
		salesOrderLine.setProduct(salesOrderLine.getProduct());
		return salesOrderLineVo;
	}


}
