package com.mitosis.shopsbacker.order.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Billing;
import com.mitosis.shopsbacker.model.Merchant;

public interface BillingDao<T> {

	public List<Billing> getBillsByMerchant(Merchant merchant,char isPaid);

	public void addNewBill(Billing billing);

}
