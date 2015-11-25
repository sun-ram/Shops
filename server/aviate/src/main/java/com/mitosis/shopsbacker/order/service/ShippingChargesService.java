package com.mitosis.shopsbacker.order.service;

import java.math.BigDecimal;
import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ShippingCharges;
import com.mitosis.shopsbacker.vo.order.ShippingChargesVo;

/**
 * @author RiyazKhan.M
 */
public interface ShippingChargesService<T> {

	public List<ShippingCharges> getShippingCharges(Merchant merchant);
	
	public BigDecimal getShippingCharge(BigDecimal orderAmount, Merchant merchant);

	public void saveShippingCharges(ShippingCharges shippingCharges);

	public void updateShippingCharges(ShippingCharges shippingCharges);

	public ShippingCharges getShippingChargesById(String id);

	public void deleteShippingCharges(String id);
	
	public ShippingChargesVo setShippingChargesVo (ShippingCharges shippingCharge);
	
	public ShippingCharges setShippingCharges(ShippingChargesVo shippingChargesVo) throws Exception;

}
