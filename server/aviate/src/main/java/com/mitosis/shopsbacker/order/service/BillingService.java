package com.mitosis.shopsbacker.order.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Billing;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.vo.order.BillingVo;

public interface BillingService<T> {

	public List<Billing> getBillsByMerchant(Merchant merchant, char isPaid);

	public BillingVo setBillingVo(Billing bills) throws Exception;
	
	public void addNewBill(SalesOrder salesOrder);
}