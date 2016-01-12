/**
 * 
 */
package com.mitosis.shopsbacker.order.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Transaction;

/**
 * @author Anbukkani Gajendiran
 *
 */
public interface TransactionDao<T> {

	public List<Transaction> getTransactions();

	public void save(Transaction transaction);

	public void update(Transaction transaction);
	
	public List<Transaction> getTransactionsByRecordId(String recordId);
	
}
