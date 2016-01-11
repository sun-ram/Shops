/**
 * 
 */
package com.mitosis.shopsbacker.order.service;

import java.math.BigDecimal;
import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Transaction;
import com.mitosis.shopsbacker.util.TransactionStatus;
import com.mitosis.shopsbacker.util.TransactionType;

/**
 * @author Anbukkani Gajendiran
 *
 */
public interface TransactionService<T> {

	public List<Transaction> getTransactions();

	public void save(Transaction transaction);

	public void update(Transaction transaction);

	public void save(String recordId, String transactionNo,
			String paymentMethod, String requestId, BigDecimal amount,
			Merchant merchant, String paymentId, TransactionStatus status, Store store,
			String responseCode, String responseMessage, TransactionType transactionType, String referenceNo)
			throws Exception;

}
