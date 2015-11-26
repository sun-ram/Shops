package com.mitosis.shopsbacker.order.service;

import com.mitosis.shopsbacker.model.OrderNumber;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.order.OrderNumberVo;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public interface OrderNumberService<T> {

	public void saveOrderNumber(OrderNumber orderNumber);

	public void updateOrderNumber(OrderNumber orderNumber);

	public OrderNumber getOrderNumber(Store store);
	
	public void deleteOrderNumber(String orderNumberId);

	public OrderNumber setOrderNumber(OrderNumberVo orderNumberVo) throws Exception;

	public OrderNumberVo setOrderNumberVo(OrderNumber orderNo) throws Exception;

	public MerchantVo setMerchantVo(OrderNumber orderNumber) throws Exception;

	public StoreVo setStoreVo(OrderNumber orderNumber) throws Exception;

	public String getSalesOrderNumber(Store store);

}
