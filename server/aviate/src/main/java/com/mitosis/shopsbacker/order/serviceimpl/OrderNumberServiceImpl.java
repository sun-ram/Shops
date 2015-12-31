package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mitosis.shopsbacker.admin.service.BannerService;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.OrderNumber;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.dao.OrderNumberDao;
import com.mitosis.shopsbacker.order.service.OrderNumberService;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.order.OrderNumberVo;

/**
 * @author prabakaran
 *
 * @param <T>
 */
@Service("orderNumberServiceImpl")
public class OrderNumberServiceImpl<T> implements OrderNumberService<T>,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	OrderNumberDao<T> orderNumberDao;
	
	@Autowired
	BannerService<T> bannerService;
		
	@Autowired
	StoreService<T> storeService;
			
	@Autowired
	MerchantService<T> merchantService;
		
	OrderNumber orderNumber = null;
	Merchant merchant = null;
	Store store = null;
	MerchantVo merchantVo =null;
	StoreVo storeVo = null;
	 

	public OrderNumberDao<T> getOrderNumberDao() {
		return orderNumberDao;
	}

	public void setOrderNumberDao(OrderNumberDao<T> orderNumber) {
		this.orderNumberDao = orderNumber;
	}

	@Override
	public void saveOrderNumber(OrderNumber orderNumber) {
		getOrderNumberDao().saveOrderNumber(orderNumber);
	}

	@Override
	public void updateOrderNumber(OrderNumber orderNumber) {
		getOrderNumberDao().updateOrderNumber(orderNumber);
	}

	@Override
	public OrderNumber getOrderNumber(Store store) {
		return getOrderNumberDao().getOrderNumber(store);
	}

	@Override
 	public void deleteOrderNumber(String orderNumberId) {
		orderNumber = orderNumberDao.getOrderNumberById(orderNumberId);
		Store store = orderNumber.getStore();
		store.setIsActivated('N');
		storeService.updateStore(store);
 		getOrderNumberDao().deleteOrderNumber(orderNumber);
 	}
	

	@Override
	public OrderNumber setOrderNumber(OrderNumberVo orderNumberVo) throws Exception {
		 
		if(orderNumberVo.getOrderNumberId() == null){
			orderNumber = (OrderNumber) CommonUtil.setAuditColumnInfo(OrderNumber.class
					.getName(), orderNumberVo.getUserId());
			orderNumber.setIsactive('Y');
			
		} else {
			orderNumber = orderNumberDao.getOrderNumberById(orderNumberVo.getOrderNumberId());
			orderNumber.setUpdated(new Date());
			orderNumber.setUpdatedby(orderNumberVo.getUserId());
		}
		orderNumber.setStartingNumber(orderNumberVo.getStartingNumber());
		orderNumber.setPrefix(orderNumberVo.getPrefix());
		orderNumber.setSuffix(orderNumberVo.getSuffix());
		orderNumber.setNextNumber(orderNumberVo.getNextNumber());
		
		merchant = merchantService.getMerchantById(orderNumberVo.getMerchant().getMerchantId());
		orderNumber.setMerchant(merchant);
		
		store = storeService.getStoreById(orderNumberVo.getStore().getStoreId());
		orderNumber.setStore(store);
		
		return orderNumber;
		
	}
	
	@Override
	public OrderNumberVo setOrderNumberVo(OrderNumber orderNumber) throws Exception{
		merchantVo = null;
		storeVo =null;
		OrderNumberVo orderNumVo = new OrderNumberVo();
		orderNumVo.setOrderNumberId(orderNumber.getOrderNumberId());
		orderNumVo.setStartingNumber(orderNumber.getStartingNumber());
		orderNumVo.setPrefix(orderNumber.getPrefix());
		orderNumVo.setSuffix(orderNumber.getSuffix());
		orderNumVo.setNextNumber(orderNumber.getNextNumber());
		merchantVo = setMerchantVo(orderNumber);
		orderNumVo.setMerchant(merchantVo);
		storeVo = setStoreVo(orderNumber);
		orderNumVo.setStore(storeVo);
		
		return orderNumVo;
	}
	
	@Override
	public MerchantVo setMerchantVo(OrderNumber orderNumber) throws Exception {
		merchantVo = new MerchantVo();
		merchantVo.setMerchantId(orderNumber.getMerchant().getMerchantId());
		merchantVo.setName(orderNumber.getMerchant().getName());
		return merchantVo;
	}

	@Override
	public StoreVo setStoreVo(OrderNumber orderNumber) throws Exception {
		storeVo = new StoreVo();
		storeVo.setStoreId(orderNumber.getStore().getStoreId());
		storeVo.setName(orderNumber.getStore().getName());
		return storeVo;
	}

	public String getSalesOrderNumber(Store store) {
		String salesOrderNumber = "";
		try {
			
		
		OrderNumber orderNumber = getOrderNumberDao().getOrderNumber(store);
		salesOrderNumber = salesOrderNumber
				+ (orderNumber.getPrefix() != null ? orderNumber.getPrefix()
						: "")
				+ (orderNumber.getNextNumber() != null ? orderNumber
						.getNextNumber() : "")
				+ (orderNumber.getSuffix() != null ? orderNumber.getSuffix()
						: "");
		if(orderNumber.getNextNumber() == null){
			orderNumber.setNextNumber(orderNumber.getStartingNumber() + 1);
		}else{
			orderNumber.setNextNumber(orderNumber.getNextNumber() + 1);
		}
		
		getOrderNumberDao().updateOrderNumber(orderNumber);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return salesOrderNumber;
	}
	
}
