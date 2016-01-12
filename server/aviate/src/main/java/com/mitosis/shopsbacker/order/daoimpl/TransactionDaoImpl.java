/**
 * 
 */
package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Transaction;
import com.mitosis.shopsbacker.order.dao.TransactionDao;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Repository
public class TransactionDaoImpl<T> extends CustomHibernateDaoSupport<T> implements TransactionDao<T>,Serializable{

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.order.dao.TransactionDao#getTransactions()
	 */
	@Override
	public List<Transaction> getTransactions() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.order.dao.TransactionDao#save(com.mitosis.shopsbacker.model.Transaction)
	 */
	@Override
	public void save(Transaction transaction) {
	save((T)transaction);
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.order.dao.TransactionDao#update(com.mitosis.shopsbacker.model.Transaction)
	 */
	@Override
	public void update(Transaction transaction) {
	update((T)transaction);

	}
	
	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.order.dao.TransactionDao#update(com.mitosis.shopsbacker.model.Transaction)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> getTransactionsByRecordId(String recordId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Transaction.class);
		criteria.add(Restrictions.eq("recordId", recordId));
		return (List<Transaction>) findAll(criteria);
	}

}
