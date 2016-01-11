/**
 * 
 */
package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Transaction;
import com.mitosis.shopsbacker.order.dao.TransactionDao;
import com.mitosis.shopsbacker.order.service.TransactionService;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.TransactionStatus;
import com.mitosis.shopsbacker.util.TransactionType;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Service("transactionServiceImpl")
public class TransactionServiceImpl<T> implements TransactionService<T>,
		Serializable {

	@Autowired
	TransactionDao<T> transactionDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.order.service.TransactionService#getTransactions
	 * ()
	 */
	@Override
	public List<Transaction> getTransactions() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.order.service.TransactionService#save(com.mitosis
	 * .shopsbacker.model.Transaction)
	 */
	@Override
	public void save(Transaction transaction) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.order.service.TransactionService#update(com.mitosis
	 * .shopsbacker.model.Transaction)
	 */
	@Override
	public void update(Transaction transaction) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mitosis.shopsbacker.order.service.TransactionService#save(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void save(String recordId, String transactionNo,
			String paymentMethod, String requestId, BigDecimal amount,
			Merchant merchant, String paymentId, TransactionStatus status, Store store,
			String responseCode, String responseMessage, TransactionType transactionType, String referenceNo)
			throws Exception {
		Transaction transaction = (Transaction) CommonUtil.setAuditColumnInfo(
				Transaction.class.getName(), null);
		transaction.setAmount(amount);
		transaction.setIsactive('Y');
		transaction.setMerchant(merchant);
		transaction.setPaymentId(paymentId);
		transaction.setRecordId(recordId);
		transaction.setResponseCode(responseCode);
		transaction.setResponseMessage(responseMessage);
		transaction.setStatus(status.getValue());
		transaction.setStore(store);
		transaction.setReferenceNo(referenceNo);
		transaction.setTransactionNo(transactionNo);
		transaction.setTransactionType(transactionType.getValue());
		transactionDao.save(transaction);

	}

}
