package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.model.Billing;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.dao.BillingDao;
import com.mitosis.shopsbacker.order.service.BillingService;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.order.BillingVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;

@Service("billingServiceImpl")
public class BillingServiceImpl implements BillingService<T>, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	SalesOrderService<T> salesOrderService;
	
	@Autowired
	BillingDao<T> billingDao;
	
	public BillingDao<T> getBillingDao() {
		return billingDao;
	}

	public void setBillingDao(BillingDao<T> billingDao) {
		this.billingDao = billingDao;
	}

	BillingVo billingVo = null;
	StoreVo storeVo = null;
	MerchantVo merchantVo = null;
	SalesOrderVo salesOrderVo = null;
	
	public List<Billing> getBillsByMerchant(Merchant merchant,char isPaid){
		return getBillingDao().getBillsByMerchant(merchant, isPaid);
	}
	
	public void addNewBill(SalesOrder salesOrder){
		
		Billing billing = new Billing();
		BigDecimal feesPercent = salesOrder.getMerchant().getFeesPercentage();
		BigDecimal amount = salesOrder.getAmount();
		BigDecimal fees = (feesPercent.multiply(amount)).divide(new BigDecimal(100));
		fees = fees.setScale(2, RoundingMode.HALF_UP);
		billing.setAmount(amount);
		billing.setFees(fees);
		billing.setIsactive('Y');
		billing.setIsPaid('N');
		billing.setMerchant(salesOrder.getMerchant());
		billing.setOrderedDate(salesOrder.getCreated());
		billing.setSalesOrder(salesOrder);
		billing.setStore(salesOrder.getStore());
		
		billingDao.addNewBill(billing);
	}
	
	@Override
	public BillingVo setBillingVo(Billing billing) throws Exception {
		
		billingVo = new BillingVo();
		Merchant merchant = billing.getMerchant(); 
		Store store = billing.getStore();
		SalesOrder salesOrder = billing.getSalesOrder();
		
		if(merchant != null){
			merchantVo = merchantService.setMerchantVo(merchant);
			billingVo.setMerchant(merchantVo);
		}
		if(store != null){
			storeVo = storeService.setStoreVo(store);
			billingVo.setStore(storeVo);
		}
		if(salesOrder != null){
			salesOrderVo = salesOrderService.setSalesOrderVo(salesOrder);
			billingVo.setSalesOrder(salesOrderVo);
		}
		billingVo.setBillingId(billing.getBillingId());
		billingVo.setAmount(billing.getAmount());
		billingVo.setFees(billing.getFees());
		billingVo.setIsPaid(billing.getIsPaid());
		String orderedDate = CommonUtil.dateToString(billing.getOrderedDate());
		billingVo.setOrderedDate(orderedDate);
		String paidDate = CommonUtil.dateToString(billing.getPaidDate());
		billingVo.setPaidDate(paidDate);
		
		return billingVo;
	}

}
