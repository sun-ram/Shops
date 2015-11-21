package com.mitosis.shopsbacker.customer.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.customer.dao.MyCartDao;
import com.mitosis.shopsbacker.customer.service.MyCartService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.Store;

@Service("myCartServiceImpl")
public class MyCartServiceImpl<T> extends CustomHibernateDaoSupport<T>
implements MyCartService<T>, Serializable {

	@Autowired
	MyCartDao myCartDao;
	
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional
	public void updateCart(MyCart mycart) {
		myCartDao.updateCart(mycart);
		
	}

	@Override
	@Transactional
	public List<MyCart> getMyCartList(Customer customer, Store store) {
		return myCartDao.getMyCartList(customer, store);
	}

	@Override
	@Transactional
	public void removeFromCart(MyCart mycart) {
		myCartDao.updateCart(mycart);
		
	}

	@Override
	@Transactional
	public void addToCart(MyCart mycart) {
		myCartDao.addToCart(mycart);
		
	}

	@Override
	@Transactional
	public MyCart getCartDetailFromId(String myCartId) {
		// TODO Auto-generated method stub
		return myCartDao.getCartDetailFromId(myCartId);
	}

}
