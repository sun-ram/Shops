package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Billing;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.order.dao.BillingDao;

@SuppressWarnings("hiding")
@Repository
public class BillingDaoImpl<T> extends CustomHibernateDaoSupport<T>
implements BillingDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<Billing> getBillsByMerchant(Merchant merchant, char isPaid){
		DetachedCriteria criteria = DetachedCriteria.forClass(Billing.class);
		criteria.add(Restrictions.eq("merchant", merchant));
		criteria.add(Restrictions.eq("isPaid", isPaid));
		return (List<Billing>) findAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addNewBill(Billing billing) {
		save((T)billing);
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

}
