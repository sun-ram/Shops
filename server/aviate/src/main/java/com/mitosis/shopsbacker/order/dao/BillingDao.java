package com.mitosis.shopsbacker.order.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Billing;
import com.mitosis.shopsbacker.model.BillingPayment;
import com.mitosis.shopsbacker.model.Merchant;

public interface BillingDao<T> {

	public List<Billing> getBillsByMerchant(Merchant merchant,char isPaid);

	public void addNewBill(Billing billing);

	public Billing getBillingById(String billingId);

	public void saveBillingPayment(BillingPayment billingPayment);
	
	public void update(List<String> billingIds, BillingPayment billingPayment);
	
	public void update(BillingPayment billingPayment);

	public BillingPayment getBillingPaymentById(String billingNo);

}
