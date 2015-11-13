package com.mitosis.shopsbacker.customer.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.customer.dao.MyCartDao;
import com.mitosis.shopsbacker.customer.service.MyCartService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.Store;

public class MyCartServiceImpl<T> extends CustomHibernateDaoSupport<T>
implements MyCartService<T>, Serializable {

	@Autowired
	MyCartDao myCartDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void updateCart(MyCart mycart) {
		myCartDao.updateCart(mycart);
		
	}

	@Override
	public List<MyCart> getMyCartList(Customer customer, Store store) {
		return myCartDao.getMyCartList(customer, store);
	}

	@Override
	public void removeFromCart(MyCart mycart) {
		myCartDao.updateCart(mycart);
		
	}

}
