package com.mitosis.shopsbacker.order.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ShippingCharges;

/**
 * @author RiyazKhan.M
 */
public interface ShippingChargesService<T> {

	public List<ShippingCharges> getShippingCharges(Merchant merchant);

	public void saveShippingCharges(ShippingCharges shippingCharges);

	public void updateShippingCharges(ShippingCharges shippingCharges);

}
