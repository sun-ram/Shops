package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.customer.service.MyCartService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.dao.SalesOrderDao;
import com.mitosis.shopsbacker.order.service.SalesOrderLineService;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.OrderStatus;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderLineVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;

/**
 * @author fayaz
 */
@Service("salesOrderServiceImpl")
public class SalesOrderServiceImpl<T> implements SalesOrderService<T>,
		Serializable {
	final static Logger log = Logger.getLogger(SalesOrderServiceImpl.class.getName());
	private static final long serialVersionUID = 1L;

	@Autowired
	SalesOrderDao<T> salesOrderDao;
	
	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	SalesOrderLineService<T> salesOrderLine;
	
	@Autowired
	MyCartService<T> mycartService;
	
	@Autowired
	AddressService<T> addessService;
	
	@Autowired
	AddressService<T> addressService;
	
	@Autowired
	CustomerService<T> customerService;
	
	@Autowired
	ProductService<T> productService;
	
	
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
	public SalesOrder getSalesOrderById(String salesOrderId) {
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
			salesOrder.setStatus(OrderStatus.Placed.toString());
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
	public List<SalesOrder> getOrderList(Merchant merchant) {
		return salesOrderDao.getOrderList(merchant);
	}

	@Override
	public List<SalesOrder> salesOrderDetailList(String fromDate,
			String toDate, Merchant merchant) {
			return salesOrderDao.salesOrderDetailList(fromDate, toDate, merchant);
	}
	
	@Override
	public SalesOrder getSalesOrder(String orderNo) {
		return salesOrderDao.getSalesOrder(orderNo);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SalesOrder paymentConfimation(String orderNo, String transactionNo,
			String paymentMethod) {
		SalesOrder salesOrder = new SalesOrder();
		try{
			salesOrder = salesOrderDao.getSalesOrder(orderNo);
			
			salesOrder.setIspaid('Y');
			salesOrder.setStatus(OrderStatus.Placed.toString());
			salesOrder.setTransactionNo(transactionNo);
			salesOrder.setPaymentMethod(paymentMethod);
			updateSalesOrder(salesOrder);
			int numberOfEntityDeleted = mycartService.deleteCartProduct(salesOrder.getCustomer().getCustomerId(),salesOrder.getStore().getStoreId());
			log.info("No Of Row Deleted deleted from cart in Payment success: "+numberOfEntityDeleted);
			
			/*Set<WarehouseModel> warehouseModelSet=new HashSet<WarehouseModel>();
			Map<WarehouseModel,Map<BinProductModel,Long>> warehouseModelMap=new HashMap<WarehouseModel,Map<BinProductModel,Long>>();
			//Map<Long,Long> productQtyMap=new HashMap<Long, Long>();

			//Select Stocked product with warehouse based on sales order's store and product
			getStockedProductWithStorageBin(salesOrder, warehouseModelSet,
					warehouseModelMap);
			
			//Create Inventory and inventory lines based on available products 
			creatInventoryAndInventoryLines(salesOrder, warehouseModelSet,
					warehouseModelMap);
			CustomerDao customerDao = new CustomerDaoImpl();
			JSONObject pickerSearch = new JSONObject();
			pickerSearch.put("storeId",salesOrder.getStoreId());
			pickerSearch.put("role","PICKER");
			List<CustomerDetailsModel> customerList =	customerDao.getPickerDeviceId(pickerSearch);
			for(CustomerDetailsModel customer : customerList ){
				if(customer.getDeviceType() != null 
						&& customer.getDeviceType().equals("ANDROID")){
					try{
						commonDao.androidPushNotification(orderId, customer.getDeviceId());
					}catch(Exception e){
					}
				}else if(customer.getDeviceType() != null 
						&& customer.getDeviceType().equals("IOS")){
					try{
						commonDao.applePushNotification(orderId, customer.getDeviceId());
					}catch(Exception e){
						e.printStackTrace();
					}
				}else{

				}
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return salesOrder;
	}
	
	public SalesOrderVo setSalesOrderVo(SalesOrder salesOrder) throws Exception {
		SalesOrderVo salesOrderVo = new SalesOrderVo();
		salesOrderVo.setSalesOrderId(salesOrder.getSalesOrderId());
		salesOrderVo.setAmount(salesOrder.getAmount());
		salesOrderVo.setCustomer(customerService.setCustomerVo(salesOrder.getCustomer()));
		salesOrderVo.setDeliveryDate(CommonUtil.dateToString(salesOrder.getDeliveryDate()));
		salesOrderVo.setDeliveryFlag(salesOrder.getDeliveryFlag());
		salesOrderVo.setStore(getStoreService().setStoreVo((salesOrder.getStore())));
		salesOrderVo.setDiscountAmount(salesOrder.getDiscountAmount());
		salesOrderVo.setTotalTaxAmount(salesOrder.getTotalTaxAmount());
		salesOrderVo.setTransactionNo(salesOrder.getTransactionNo());
		salesOrderVo.setShippingCharge(salesOrder.getShippingCharge());
		salesOrderVo.setOrderPlacedTime(salesOrder.getCreated());
		salesOrderVo.setDeliveryTimeSlot(salesOrder.getDeliveryTimeSlot());
		salesOrderVo.setNetAmount(salesOrder.getNetAmount());
		MerchantVo merchantVo=new MerchantVo();
		Merchant merchant = salesOrder.getMerchant();
		if(salesOrder.getDeliveredTime() != null){
			salesOrderVo.setDeliveredTime(salesOrder.getDeliveredTime());
		}
		if(salesOrder.getPickupStartTime() != null){
			salesOrderVo.setPickupStartTime(salesOrder.getPickupStartTime());
		}
		if(salesOrder.getPackedTime() != null){
			salesOrderVo.setPackedTime(salesOrder.getPackedTime());
		}
		if(salesOrder.getDeliveryStartTime() != null){
			salesOrderVo.setDeliveryStartTime(salesOrder.getDeliveryStartTime());
		}
		if(salesOrder.getShopperAssignedTime() != null){
			salesOrderVo.setShopperAssignedTime(salesOrder.getShopperAssignedTime());
		}
		if(salesOrder.getBackerAssignedTime() != null){
			salesOrderVo.setBackerAssignedTime(salesOrder.getBackerAssignedTime());
		}
		if(salesOrder.getShopper()!=null){
			UserVo userVo=new UserVo();
			userVo.setName(salesOrder.getShopper().getName());
			salesOrderVo.setShoper(userVo);
		}
		if(salesOrder.getBacker()!=null){
			UserVo userVo=new UserVo();
			userVo.setName(salesOrder.getBacker().getName());
			salesOrderVo.setBacker(userVo);
		}
		merchantVo.setMerchantId(merchant.getMerchantId()); 
		merchantVo.setName(merchant.getName());
		salesOrderVo.setMerchant(merchantVo);
		salesOrderVo.setNetAmount(salesOrder.getNetAmount());
		salesOrderVo.setOrderNo(salesOrder.getOrderNo());
		salesOrderVo.setAddressVo(addressService.setAddressVo(salesOrder
				.getAddress()));
		salesOrderVo.setStatus(salesOrder.getStatus());
		salesOrderVo.setFromDate(CommonUtil.dateToString(salesOrder.getCreated()));
		salesOrderVo.setSalesOrderLineVo(salesOrderLine.setSalesOrderLineVo(salesOrder.getSalesOrderLines()));
		return salesOrderVo;
	}
	
	//deployed DEC-19 need to remove this method after mobile team okay
	public SalesOrderVo setSalesOrderVoEmp(SalesOrder salesOrder) throws Exception {
		SalesOrderVo salesOrdervo = new SalesOrderVo();
		salesOrdervo.setSalesOrderId(salesOrder.getSalesOrderId());
		salesOrdervo.setOrderNo(salesOrder.getOrderNo());
		salesOrdervo.setStatus(salesOrder.getStatus());
		salesOrdervo.setAddress(addressService.setAddressVo(salesOrder
				.getAddress()));
		salesOrdervo.setCustomer(customerService.setCustomerVo(salesOrder
				.getCustomer()));
		List<SalesOrderLine> orderLines = salesOrder.getSalesOrderLines();
		List<SalesOrderLineVo> orderLineVos = new ArrayList<SalesOrderLineVo>();
		for (SalesOrderLine orderLine : orderLines) {
			SalesOrderLineVo salesOrderLineVo = new SalesOrderLineVo();
			ProductVo productVo = productService.setProductVo(orderLine
					.getProduct());
			salesOrderLineVo.setProductVo(productVo);
			salesOrderLineVo.setQty(orderLine.getQty());
			salesOrderLineVo.setSalesOrderLineId(orderLine
					.getSalesOrderLineId());
			orderLineVos.add(salesOrderLineVo);
		}
		salesOrdervo.setSalesOrderLineVo(orderLineVos);
		return salesOrdervo;
	}
	
	
	/*public CustomerVo setCustomerDetails(Customer customer) {
		CustomerVo customerVo = new CustomerVo();
		customerVo.setCustomerId(customer.getCustomerId());
		customerVo.setName(customer.getName());
		customerVo.setEmail(customer.getEmail());
		customerVo.setImageId(customer.getImageId());
		customerVo.setDeviceid(customer.getDeviceid());
		customerVo.setDeviceType(customer.getDeviceType());
		return customerVo;

	}*/
	
	/*public AddressVo setAddressVo(Address address) throws Exception {
		AddressVo addressVo = new AddressVo();
		addressVo.setAddress1(address.getAddress1());
		addressVo.setAddress2(address.getAddress2());
		addressVo.setCity(addessService.setCityVo(address.getCity()));
		addressVo.setPhoneNo(address.getPhoneNo());
		addressVo.setLandmark(address.getLandmark());
		addressVo.setLatitude(address.getLatitude());
		addressVo.setLongitude(address.getLongitude());
		return addressVo;

	}*/
}
