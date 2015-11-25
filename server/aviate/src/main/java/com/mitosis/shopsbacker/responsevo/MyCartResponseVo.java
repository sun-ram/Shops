package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.customer.MyCartVo;

public class MyCartResponseVo extends ResponseModel{
	
	List<MyCartVo> myCart=new ArrayList<MyCartVo>();

	public List<MyCartVo> getMyCart() {
		return myCart;
	}

	public void setMyCart(List<MyCartVo> myCart) {
		this.myCart = myCart;
	}

	
	

}
