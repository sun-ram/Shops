package com.mitosis.shopsbacker.order.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Billing;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.vo.order.BillingVo;
import com.mitosis.shopsbacker.vo.order.TransactionDetailVo;

public interface BillingService<T> {

	public List<Billing> getBillsByMerchant(Merchant merchant, char isPaid);

	public BillingVo setBillingVo(Billing bills) throws Exception;
	
	public void addNewBill(SalesOrder salesOrder);

	public TransactionDetailVo setTransactionDetails(List<Billing> selected) throws Exception;

	public boolean updateBilling(String billingNo, String paymentId,
			String paymentMethod, String requestId, String transactionNo,
			String responseCode, String responseMessage, String referenceNo);


}
