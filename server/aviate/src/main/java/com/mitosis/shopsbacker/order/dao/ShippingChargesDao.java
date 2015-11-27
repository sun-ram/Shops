package com.mitosis.shopsbacker.order.dao;

import java.math.BigDecimal;
import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ShippingCharges;

/**
 * @author RiyazKhan.M
 */
public interface ShippingChargesDao<T> {

	public List<ShippingCharges> getShippingCharges(Merchant merchant);

	public void saveShippingCharges(ShippingCharges shippingCharges);

	public void updateShippingCharges(ShippingCharges shippingCharges);

	public ShippingCharges getShippingChargesById(String id);

	public void deleteShippingCharges(String id);

	public BigDecimal getShippingCharges(BigDecimal orderAmount, Merchant merchant);

	public List<ShippingCharges> getShippingChargesList(String id,
			BigDecimal amountRange, Merchant merchant);

}
