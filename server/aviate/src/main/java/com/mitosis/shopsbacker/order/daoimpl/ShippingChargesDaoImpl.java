package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ShippingCharges;
import com.mitosis.shopsbacker.order.dao.ShippingChargesDao;

/**
 * @author RiyazKhan.M
 */
@Repository
@Transactional
public class ShippingChargesDaoImpl<T> extends CustomHibernateDaoSupport<T>
implements ShippingChargesDao<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<ShippingCharges> getShippingCharges(Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(ShippingCharges.class);
			criteria.add(Restrictions.eq("merchant", merchant));
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return ((List<ShippingCharges>) findAll(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveShippingCharges(ShippingCharges shippingCharges) {
		try {
			save((T) shippingCharges);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@Override
	public void updateShippingCharges(ShippingCharges shippingCharges) {
		try {
			update((T) shippingCharges);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@Override
	public ShippingCharges getShippingChargesById(String id) {
		try {
			return (ShippingCharges) getSession().get(ShippingCharges.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteShippingCharges(String id) {
		try {
			ShippingCharges shippingCharge = getShippingChargesById(id);
			delete((T) shippingCharge);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BigDecimal getShippingCharges(BigDecimal orderAmount, Merchant merchant) {
		Criteria criteria = getSession().createCriteria(ShippingCharges.class);
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("chargingAmount"), "chargingAmount");
        criteria.setProjection(proList);
		criteria.add(Restrictions.eq("merchant", merchant));
		criteria.add(Restrictions.and(Restrictions.ge("amountRange", orderAmount)));
		criteria.addOrder(Order.asc("amountRange"));
		return (BigDecimal) criteria.list().get(0);
	}


}
