package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.order.dao.SalesOrderLineDao;
import com.mitosis.shopsbacker.order.service.SalesOrderLineService;
import com.mitosis.shopsbacker.vo.order.SalesOrderLineVo;

@Service("salesOrderLineServiceImpl")
public class SalesOrderLineServiceImpl<T> implements
						SalesOrderLineService<T>, Serializable{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	SalesOrderLineDao<T> salesOrderLineDao;
	
	@Autowired
	ProductService<T> productService;
	
	public SalesOrderLineDao<T> getSalesOrderLineDao() {
		return salesOrderLineDao;
	}


	public void setSalesOrderLineDao(SalesOrderLineDao<T> salesOrderLineDao) {
		this.salesOrderLineDao = salesOrderLineDao;
	}

	@Override
	public SalesOrderLine getSalesOrderLineById(String id) {
		return salesOrderLineDao.getSalesOrderLineById(id);
	}
	
	public ProductService<T> getProductService() {
		return productService;
	}


	public void setProductService(ProductService<T> productService) {
		this.productService = productService;
	}

	@Override
	public List<SalesOrderLineVo> setSalesOrderLineVo(List<SalesOrderLine> salesOrderLines) throws Exception {
		List<SalesOrderLineVo> salesOrderLineList = new ArrayList<SalesOrderLineVo>();
		for(SalesOrderLine salesOrderLine:salesOrderLines){
			SalesOrderLineVo salesOrderLineVo = new SalesOrderLineVo();
			salesOrderLineVo.setSalesOrderLineId(salesOrderLine.getSalesOrderLineId());
			salesOrderLineVo.setQty(salesOrderLine.getQty());
			salesOrderLineVo.setGrossAmount(salesOrderLine.getGrossAmount());
			salesOrderLineVo.setNetAmount(salesOrderLine.getNetAmount());
			salesOrderLineVo.setPrice(salesOrderLine.getPrice());
			salesOrderLineVo.setProductVo(productService.setProductVo(salesOrderLine.getProduct()));
			salesOrderLineList.add(salesOrderLineVo);
		}
		return salesOrderLineList;
	}


	@Override
	public List<SalesOrderLine> getSalesOrderLineBysalesOrder(
			SalesOrder salesOrderId) {
		return salesOrderLineDao.getSalesOrderLineBysalesOrder(salesOrderId);
	}


}
