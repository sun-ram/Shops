package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.ShippingCharges;
import com.mitosis.shopsbacker.order.dao.ShippingChargesDao;

/**
 * @author RiyazKhan.M
 */
@Repository
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

}
