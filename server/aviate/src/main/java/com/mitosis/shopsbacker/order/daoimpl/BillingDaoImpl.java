package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Billing;
import com.mitosis.shopsbacker.model.BillingPayment;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.order.dao.BillingDao;

@SuppressWarnings("hiding")
@Repository
public class BillingDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
		BillingDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Billing> getBillsByMerchant(Merchant merchant, char isPaid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Billing.class);
		criteria.add(Restrictions.eq("merchant", merchant));
		criteria.add(Restrictions.eq("isPaid", isPaid));
		return (List<Billing>) findAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addNewBill(Billing billing) {
		save((T) billing);
	}

	@Override
	public Billing getBillingById(String billingId) {
		try {
			return (Billing) getSession().get(Billing.class, billingId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveBillingPayment(BillingPayment billingPayment) {
		save((T) billingPayment);
	}

	@Override
	public void update(List<String> billingIds, BillingPayment billingPayment) {

		Query query = getSession()
				.createQuery(
						"update Billing bil set bil.billingPayment = :billingPayment where bil.billingId in (:billingIds)");
		query.setParameter("billingPayment", billingPayment);
		query.setParameterList("billingIds", billingIds);
		query.executeUpdate();
		getSession().flush();
	}

	@Override
	public void update(BillingPayment billingPayment) {

		Query query = getSession()
				.createQuery(
						"update Billing set isPaid ='Y', paidDate = :date where billingPayment = :billingPayment");
		query.setParameter("billingPayment", billingPayment);
		query.setParameter("date", new Date());
		query.executeUpdate();
		getSession().flush();
	}
	
	@Override
	public BillingPayment getBillingPaymentById(String billingNo){
		
		try {
			return (BillingPayment) getSession().get(BillingPayment.class, billingNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
