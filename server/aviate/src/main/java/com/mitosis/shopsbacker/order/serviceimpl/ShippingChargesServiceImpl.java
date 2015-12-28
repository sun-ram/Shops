package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ShippingCharges;
import com.mitosis.shopsbacker.order.dao.ShippingChargesDao;
import com.mitosis.shopsbacker.order.service.ShippingChargesService;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.order.ShippingChargesVo;

/**
 * @author RiyazKhan.M
 */
@Service("shippingChargesServiceImpl")
public class ShippingChargesServiceImpl<T> implements
ShippingChargesService<T>, Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	ShippingChargesDao<T> shippingChargesDao;
	
	@Autowired
	MerchantService<T> merchantService;

	public ShippingChargesDao<T> getShippingChargesDao() {
		return shippingChargesDao;
	}

	public void setShippingChargesDao(ShippingChargesDao<T> shippingChargesDao) {
		this.shippingChargesDao = shippingChargesDao;
	}
	
	
	public MerchantService<T> getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService<T> merchantService) {
		this.merchantService = merchantService;
	}

	@Override
	public List<ShippingCharges> getShippingCharges(Merchant merchant) {
		return shippingChargesDao.getShippingCharges(merchant);
	}

	@Override
	public void saveShippingCharges(ShippingCharges shippingCharges) {
		shippingChargesDao.saveShippingCharges(shippingCharges);

	}

	@Override
	public void updateShippingCharges(ShippingCharges shippingCharges) {
		shippingChargesDao.updateShippingCharges(shippingCharges);

	}

	@Override
	public ShippingCharges getShippingChargesById(String id) {
		return shippingChargesDao.getShippingChargesById(id);
	}

	@Override
	public void deleteShippingCharges(String id) {
		shippingChargesDao.deleteShippingCharges(id);
	}
	
	@Override
	public BigDecimal getShippingCharge(BigDecimal orderAmount, Merchant merchant) {
		return shippingChargesDao.getShippingCharges(orderAmount, merchant);
	}
	
	@Override
	public ShippingChargesVo setShippingChargesVo (ShippingCharges shippingCharge) {
		ShippingChargesVo shippingChargeVo = new ShippingChargesVo();
		//shippingChargeVo.setMerchant(shippingCharge.getMerchant());
		shippingChargeVo.setShippingChargesId(shippingCharge.getShippingChargesId());
		shippingChargeVo.setAmountRange(shippingCharge.getAmountRange());
		shippingChargeVo.setChargingAmount(shippingCharge.getChargingAmount());
		return shippingChargeVo;
	}
	
	@Override
	public ShippingCharges setShippingCharges(ShippingChargesVo shippingChargesVo) throws Exception {
		ShippingCharges shippingCharges = (ShippingCharges) CommonUtil
				.setAuditColumnInfo(ShippingCharges.class.getName(), null);
		shippingCharges.setMerchant(getMerchantService().getMerchantById(shippingChargesVo.getMerchantVo().getMerchantId()));
		shippingCharges.setAmountRange(shippingChargesVo.getAmountRange());
		shippingCharges.setChargingAmount(shippingChargesVo.getChargingAmount());
		shippingCharges.setIsactive('Y');
		return shippingCharges;
	}

	public List<ShippingCharges> getShippingChargesList(String id,BigDecimal amountRange,
			Merchant merchant){
		return shippingChargesDao.getShippingChargesList(id,amountRange,merchant);
	}



}
