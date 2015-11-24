package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.dao.SalesOrderDao;
import com.mitosis.shopsbacker.order.service.SalesOrderLineService;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.OrderStatus;
import com.mitosis.shopsbacker.vo.common.AddressVo;
import com.mitosis.shopsbacker.vo.customer.CustomerVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;

/**
 * @author fayaz
 */
@Service("salesOrderServiceImpl")
public class SalesOrderServiceImpl<T> implements SalesOrderService<T>,
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	SalesOrderDao<T> salesOrderDao;
	
	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	SalesOrderLineService<T> salesOrderLine;
	
	public SalesOrderDao<T> getSalesOrderDao() {
		return salesOrderDao;
	}

	public void setSalesOrderDao(SalesOrderDao<T> salesOrderDao) {
		this.salesOrderDao = salesOrderDao;
	}
	
	
	public StoreService<T> getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService<T> storeService) {
		this.storeService = storeService;
	}
	

	public SalesOrderLineService<T> getSalesOrderLine() {
		return salesOrderLine;
	}

	public void setSalesOrderLine(SalesOrderLineService<T> salesOrderLine) {
		this.salesOrderLine = salesOrderLine;
	}

	@Override
	public List<SalesOrder> getSalesOrders(Store store) {
		return salesOrderDao.getSalesOrders(store, true);
	}

	@Override
	public List<SalesOrder> getOrderList(Store store) {
		return salesOrderDao.getOrderList(store);
	}

	@Override
	public List<SalesOrder> getOrderList(Store store, OrderStatus status) {
		return salesOrderDao.getOrderList(store, status);
	}

	@Override
	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, Store store) {
		return salesOrderDao.salesOrderDetailList(fromDate, toDate, store);
	}

	@Override
	public SalesOrder salesOrderById(String salesOrderId) {
		return salesOrderDao.salesOrderById(salesOrderId);
	}

	@Override
	public void updateSalesOrder(SalesOrder salesOrder) {
		salesOrderDao.updateSalesOrder(salesOrder);

	}

	@Override
	public void conformPayment(String salesOrderId, String transactionNo,
			String paymentMethod) {
		SalesOrder salesOrder = salesOrderDao.salesOrderById(salesOrderId);
		if (salesOrder != null) {
			salesOrder.setIspaid('Y');
			salesOrder.setStatus(OrderStatus.Initialized.toString());
			salesOrder.setPaymentMethod(paymentMethod);
			salesOrder.setTransactionNo(transactionNo);
			salesOrderDao.updateSalesOrder(salesOrder);
		}

	}

	@Override
	public void saveSalesOrder(SalesOrder salesOrder) {
		salesOrderDao.saveSalesOrder(salesOrder);
		
	}
	
	@Override
	public List<SalesOrder> getOrderList(String merchantId) {
		return salesOrderDao.getOrderList(merchantId);
	}

	@Override
	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, String merchantId) {
			return salesOrderDao.salesOrderDetailList(fromDate, toDate, merchantId);
	}
	
	public SalesOrderVo setSalesOrderVo (SalesOrder salesOrder) throws Exception {
		SalesOrderVo salesOrderVo = new SalesOrderVo();
		salesOrderVo.setSalesOrderId(salesOrder.getSalesOrderId());
		salesOrderVo.setAmount(salesOrder.getAmount());
		salesOrderVo.setCustomerVo(setCustomerDetails(salesOrder.getCustomer()));
		salesOrderVo.setDeliveryDate(CommonUtil.dateToString(salesOrder.getDeliveryDate()));
		salesOrderVo.setDeliveryFlag(salesOrder.getDeliveryFlag());
		salesOrderVo.setDeliveryTime(salesOrder.getDeliveryTime());
		salesOrderVo.setStoreVo(getStoreService().setStoreVo((salesOrder.getStore())));
		salesOrderVo.setDiscountAmount(salesOrder.getDiscountAmount());
		salesOrderVo.setTotalTaxAmount(salesOrder.getTotalTaxAmount());
		salesOrderVo.setTransactionNo(salesOrder.getTransactionNo());
		salesOrderVo.setShippingCharge(salesOrder.getShippingCharge());
		salesOrderVo.setMerchantId(salesOrder.getMerchantId());
		salesOrderVo.setNetAmount(salesOrder.getNetAmount());
		salesOrderVo.setOrderNo(salesOrder.getOrderNo());
		salesOrderVo.setAddressVo(setAddress(salesOrder.getAddress()));
		salesOrderVo.setStatus(salesOrder.getStatus());
		salesOrderVo.setFromDate(CommonUtil.dateToString(salesOrder.getCreated()));
		salesOrderVo.setSalesOrderLineVo(salesOrderLine.setSalesOrderLineVo(salesOrder.getSalesOrderLines()));
		return salesOrderVo;
	}
	
	public CustomerVo setCustomerDetails(Customer customer) {
		CustomerVo customerVo = new CustomerVo();
		customerVo.setCustomerId(customer.getCustomerId());
		customerVo.setName(customer.getName());
		customerVo.setEmail(customer.getEmail());
		customerVo.setImageId(customer.getImageId());
		customerVo.setDeviceid(customer.getDeviceid());
		customerVo.setDeviceType(customer.getDeviceType());
		return customerVo;

	}
	
	public AddressVo setAddress(Address address) throws Exception {
		AddressVo addressVo = new AddressVo();
		addressVo.setAddress1(address.getAddress1());
		addressVo.setAddress2(address.getAddress2());
		addressVo.setCity(address.getCity());
		addressVo.setPhoneNo(address.getPhoneNo());
		addressVo.setLandmark(address.getLandmark());
		return addressVo;

	}

}
