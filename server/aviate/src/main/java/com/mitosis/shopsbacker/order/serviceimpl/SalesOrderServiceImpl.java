package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.order.service.SalesOrderService;

@Service("salesOrderServiceImpl")
public class SalesOrderServiceImpl<T> implements SalesOrderService<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
