package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.model.OrderNumber;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.dao.OrderNumberDao;
import com.mitosis.shopsbacker.order.service.OrderNumberService;

/**
 * @author prabakaran
 *
 * @param <T>
 */
@Service("orderNumberServiceImpl")
public class OrderNumberServiceImpl<T> implements OrderNumberService<T>,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	OrderNumberDao<T> orderNumberDao;

	public OrderNumberDao<T> getOrderNumberDao() {
		return orderNumberDao;
	}

	public void setOrderNumberDao(OrderNumberDao<T> orderNumber) {
		this.orderNumberDao = orderNumber;
	}

	@Override
	@Transactional
	public void saveOrderNumber(OrderNumber orderNumber) {
		getOrderNumberDao().saveOrderNumber(orderNumber);
	}

	@Override
	@Transactional
	public void updateOrderNumber(OrderNumber orderNumber) {
		getOrderNumberDao().updateOrderNumber(orderNumber);
	}

	@Override
	@Transactional
	public OrderNumber getOrderNumber(Store store) {
		return getOrderNumberDao().getOrderNumber(store);
	}

	@Override
	@Transactional
	public void deleteOrderNumber(OrderNumber orderNumber) {
		getOrderNumberDao().deleteOrderNumber(orderNumber);
	}

	@Override
	@Transactional
	public String getSalesOrderNumber(Store store) {
		String salesOrderNumber = "";
		OrderNumber orderNumber = getOrderNumberDao().getOrderNumber(store);
		salesOrderNumber = salesOrderNumber
				+ (orderNumber.getPrefix() != null ? orderNumber.getPrefix()
						: "")
				+ (orderNumber.getNextNumber() != null ? orderNumber
						.getNextNumber() : "")
				+ (orderNumber.getSuffix() != null ? orderNumber.getSuffix()
						: "");
		if(orderNumber.getNextNumber() == null){
			orderNumber.setNextNumber(orderNumber.getStartingNumber() + 1);
		}else{
			orderNumber.setNextNumber(orderNumber.getNextNumber() + 1);
		}
		
		getOrderNumberDao().updateOrderNumber(orderNumber);
		return salesOrderNumber;
	}

}
