package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.order.dao.SalesOrderDao;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public class SalesOrderDaoImpl<T> extends CustomHibernateDaoSupport<T>
		implements SalesOrderDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
