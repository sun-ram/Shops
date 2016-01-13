package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.customer.service.MyCartService;
import com.mitosis.shopsbacker.inventory.dao.ProductInventoryDao;
import com.mitosis.shopsbacker.inventory.dao.WarehouseDao;
import com.mitosis.shopsbacker.inventory.service.ProductInventoryService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.order.dao.SalesOrderDao;
import com.mitosis.shopsbacker.order.service.SalesOrderLineService;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.order.service.TransactionService;
import com.mitosis.shopsbacker.socket.SocketMessage;
import com.mitosis.shopsbacker.socket.SocketServer;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.HashGeneratorUtils;
import com.mitosis.shopsbacker.util.OrderStatus;
import com.mitosis.shopsbacker.util.TransactionStatus;
import com.mitosis.shopsbacker.util.TransactionType;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderLineVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;
import com.mitosis.shopsbacker.vo.order.TransactionDetailVo;

/**
 * @author fayaz
 */
@Service("salesOrderServiceImpl")
public class SalesOrderServiceImpl<T> implements SalesOrderService<T>,
		Serializable {
	final static Logger log = Logger.getLogger(SalesOrderServiceImpl.class
			.getName());
	private static final long serialVersionUID = 1L;

	@Autowired
	SalesOrderDao<T> salesOrderDao;

	@Autowired
	StoreService<T> storeService;

	@Autowired
	SalesOrderLineService<T> salesOrderLineService;

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

	@Autowired
	WarehouseDao<T> warehouseDao;

	@Autowired
	ProductInventoryService<T> productInventoryService;

	@Autowired
	ProductInventoryDao<T> productInventoryDao;

	@Autowired
	TransactionService<T> transactionService;

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
		return salesOrderLineService;
	}

	public void setSalesOrderLine(SalesOrderLineService<T> salesOrderLine) {
		this.salesOrderLineService = salesOrderLine;
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
	public List<SalesOrder> getSalesOrdersByBackerId(String shoperId) {
		return salesOrderDao.getSalesOrdersByBackerId(shoperId);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean paymentConfimation(String salesOrderNo, String paymentId,
			String paymentMethod, String accountId, String transactionNo,
			String responseCode, String responseMessage, String referenceNo) {
		boolean flag = false;
		SalesOrderVo salesOrderVo = new SalesOrderVo();
		SocketMessage message = new SocketMessage();
		SocketServer socket = new SocketServer();
		try {
			SalesOrder salesOrder = salesOrderDao.salesOrderById(salesOrderNo);
			salesOrder.setIspaid('Y');
			salesOrder.setStatus(OrderStatus.Placed.toString());
			//salesOrder.setTransactionNo(paymentId);
			// salesOrder.setPaymentMethod(paymentMethod);
			//salesOrder.setRequestId(requestId);
			updateSalesOrder(salesOrder);
			transactionService.save(salesOrder.getSalesOrderId(),
					transactionNo, paymentMethod, accountId,
					salesOrder.getNetAmount(), salesOrder.getMerchant(),
					paymentId, TransactionStatus.SUCCESS,
					salesOrder.getStore(), responseCode, responseMessage,
					TransactionType.SALES_ORDER, referenceNo);
			int numberOfEntityDeleted = mycartService.deleteCartProduct(
					salesOrder.getCustomer().getCustomerId(), salesOrder
							.getStore().getStoreId());
			log.info("No Of Row Deleted deleted from cart in Payment success: "
					+ numberOfEntityDeleted);
			productStockReduce(salesOrder);
			flag = true;
			salesOrderVo = setSalesOrderVo(salesOrder, false);
			message.setMessage("Update");
			message.setTag("SalesOrder");
			message.setSalesOrder(CommonUtil.getObjectMapper(salesOrderVo));
			message.setToUser(salesOrder.getMerchant().getMerchantId());
			socket.message(message, null);
			message.setToUser(salesOrder.getStore().getStoreId());
			socket.message(message, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public SalesOrderVo setSalesOrderVo(SalesOrder salesOrder, boolean excludeReturnQty) throws Exception {
		SalesOrderVo salesOrderVo = new SalesOrderVo();
		salesOrderVo.setSalesOrderId(salesOrder.getSalesOrderId());
		salesOrderVo.setAmount(salesOrder.getAmount());
		salesOrderVo.setCustomer(customerService.setCustomerVo(salesOrder
				.getCustomer()));
		salesOrderVo.setDeliveryDate(CommonUtil
				.dateToStringForSalesOrder(salesOrder.getDeliveryDate()));
		salesOrderVo.setDeliveryFlag(salesOrder.getDeliveryFlag());
		salesOrderVo.setStore(getStoreService().setStoreVo(
				(salesOrder.getStore())));
		salesOrderVo.setDiscountAmount(salesOrder.getDiscountAmount());
		salesOrderVo.setTotalTaxAmount(salesOrder.getTotalTaxAmount());
		salesOrderVo.setTransactionNo(salesOrder.getTransactionNo());
		salesOrderVo.setShippingCharge(salesOrder.getShippingCharge());
		salesOrderVo.setOrderPlacedTime(salesOrder.getCreated());
		salesOrderVo.setPaymentMethod(salesOrder.getPaymentMethod());
		Date deliveryTime = salesOrder.getDeliveryTimeSlot();
		String strDeliveryTime = CommonUtil.convertTimeToString(deliveryTime);
		salesOrderVo.setDeliveryTimeSlot(strDeliveryTime);
		salesOrderVo.setNetAmount(salesOrder.getNetAmount());
		MerchantVo merchantVo = new MerchantVo();
		Merchant merchant = salesOrder.getMerchant();
		if (salesOrder.getDeliveredTime() != null) {
			salesOrderVo.setDeliveredTime(salesOrder.getDeliveredTime());
		}
		if (salesOrder.getPickupStartTime() != null) {
			salesOrderVo.setPickupStartTime(salesOrder.getPickupStartTime());
		}
		if (salesOrder.getPackedTime() != null) {
			salesOrderVo.setPackedTime(salesOrder.getPackedTime());
		}
		if (salesOrder.getDeliveryStartTime() != null) {
			salesOrderVo
					.setDeliveryStartTime(salesOrder.getDeliveryStartTime());
		}
		if (salesOrder.getShopperAssignedTime() != null) {
			salesOrderVo.setShopperAssignedTime(salesOrder
					.getShopperAssignedTime());
		}
		if (salesOrder.getBackerAssignedTime() != null) {
			salesOrderVo.setBackerAssignedTime(salesOrder
					.getBackerAssignedTime());
		}
		if (salesOrder.getShopper() != null) {
			UserVo userVo = new UserVo();
			userVo.setName(salesOrder.getShopper().getName());
			salesOrderVo.setShoper(userVo);
		}
		if (salesOrder.getBacker() != null) {
			UserVo userVo = new UserVo();
			User backer = salesOrder.getBacker();
			userVo.setName(backer.getName());
			userVo.setPhoneNo(backer.getPhoneNo());
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
		salesOrderVo.setFromDate(CommonUtil.dateToString(salesOrder
				.getCreated()));
		List<SalesOrderLineVo> salesOrderLineVos = salesOrderLineService.setSalesOrderLineVo(salesOrder.getSalesOrderLines(), excludeReturnQty);
		int size = salesOrderLineVos.size();
		if(size==0 && excludeReturnQty){
			return null;
		}else{
			salesOrderVo.setSalesOrderLineVo(salesOrderLineVos);
		}
		return salesOrderVo;
	}

	// deployed DEC-19 need to remove this method after mobile team okay
	public SalesOrderVo setSalesOrderVoEmp(SalesOrder salesOrder)
			throws Exception {
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

	/*
	 * public CustomerVo setCustomerDetails(Customer customer) { CustomerVo
	 * customerVo = new CustomerVo();
	 * customerVo.setCustomerId(customer.getCustomerId());
	 * customerVo.setName(customer.getName());
	 * customerVo.setEmail(customer.getEmail());
	 * customerVo.setImageId(customer.getImageId());
	 * customerVo.setDeviceid(customer.getDeviceid());
	 * customerVo.setDeviceType(customer.getDeviceType()); return customerVo; }
	 */

	/*
	 * public AddressVo setAddressVo(Address address) throws Exception {
	 * AddressVo addressVo = new AddressVo();
	 * addressVo.setAddress1(address.getAddress1());
	 * addressVo.setAddress2(address.getAddress2());
	 * addressVo.setCity(addessService.setCityVo(address.getCity()));
	 * addressVo.setPhoneNo(address.getPhoneNo());
	 * addressVo.setLandmark(address.getLandmark());
	 * addressVo.setLatitude(address.getLatitude());
	 * addressVo.setLongitude(address.getLongitude()); return addressVo; }
	 */

	public void productStockReduce(SalesOrder salesOrder) throws Exception {
		for (SalesOrderLine salesOrderLine : salesOrder.getSalesOrderLines()) {

			Store store = salesOrder.getStore();
			Product product = salesOrderLine.getProduct();

			List<ProductInventory> productInventories = productInventoryDao
					.getProductInventory(store, product);
			int soldOutQuantity = salesOrderLine.getQty();
			if (!productInventories.isEmpty()) {
				int balanceQty = soldOutQuantity;
				for (int i = 0; i < productInventories.size(); i++) {
					ProductInventory pi = productInventories.get(i);
					if (balanceQty > 0) {
						if (balanceQty <= pi.getAvailableQty()) {
							if (i == productInventories.size() - 1) {
								balanceQty = pi.getAvailableQty() - balanceQty;
								pi.setAvailableQty(balanceQty);
							} else {
								balanceQty = pi.getAvailableQty() - balanceQty;
								pi.setAvailableQty(balanceQty);
							}
						} else if (balanceQty > pi.getAvailableQty()) {
							// balanceQty = pi.getAvailableQty()-balanceQty;
							pi.setAvailableQty(0);
						}
					} else {
						break;
					}
					productInventoryService.updateInventory(pi);
				}
			} else {/*
					 * ProductInventory productInventory = (ProductInventory)
					 * CommonUtil
					 * .setAuditColumnInfo(ProductInventory.class.getName());
					 * Storagebin storagebinForSalesorder = new Storagebin();
					 * if(!store.getWarehouses().isEmpty()){ Warehouse warehouse
					 * = store.getWarehouses().get(0);
					 * if(!store.getWarehouses().
					 * get(0).getStoragebins().isEmpty()){
					 * storagebinForSalesorder =
					 * store.getWarehouses().get(0).getStoragebins().get(0);
					 * }else{ Storagebin storagebin =
					 * createNewBinForNegativeStock(warehouse);
					 * storagebin.setStore(store);
					 * warehouse.getStoragebins().add(storagebin);
					 * warehouseDao.updateWarehouse(warehouse);
					 * storagebinForSalesorder = storagebin; } }else{ Warehouse
					 * warehouse = (Warehouse)
					 * CommonUtil.setAuditColumnInfo(Warehouse.class.getName());
					 * warehouse.setName("Sales Order");
					 * warehouse.setIsactive('Y');
					 * warehouse.setDescription("Sales Order Does not have Stock"
					 * ); Storagebin storagebin =
					 * createNewBinForNegativeStock(warehouse);
					 * storagebin.setStore(store);
					 * storagebin.setMerchant(store.getMerchant());
					 * warehouse.getStoragebins().add(storagebin);
					 * warehouse.setMerchant(salesOrder.getMerchant());
					 * warehouse.setStore(store);
					 * 
					 * //TODO: Need clarification
					 * warehouse.setAddress(store.getUser().getAddress());
					 * storagebin.setWarehouse(warehouse);
					 * warehouseDao.addWarehouse(warehouse);
					 * storagebinForSalesorder = storagebin; }
					 * productInventory.setIsactive('Y');
					 * productInventory.setMerchant(salesOrder.getMerchant());
					 * productInventory.setStore(salesOrder.getStore());
					 * productInventory
					 * .setAvailableQty(-salesOrderLine.getQty());
					 * productInventory.setStoragebin(storagebinForSalesorder);
					 * productInventory.setProduct(salesOrderLine.getProduct());
					 * productInventoryService
					 * .updateInventory(productInventory);
					 */
			}
		}
	}

	/*
	 * private Storagebin createNewBinForNegativeStock(Warehouse warehouse)
	 * throws Exception { Storagebin storagebin = (Storagebin)
	 * CommonUtil.setAuditColumnInfo(Storagebin.class.getName());
	 * storagebin.setIsactive('Y'); storagebin.setName("Sales Order");
	 * storagebin.setDescription("Sales order has created this bin");
	 * storagebin.setRow("0-row"); storagebin.setLevel("0-Level");
	 * storagebin.setStack("0-Stack"); return storagebin; }
	 */

	public TransactionDetailVo setTransactionDetails(SalesOrder salesOrder,
			Address deliveryAddress, Address shippingAddress, Customer customer) {

		TransactionDetailVo transaction = new TransactionDetailVo();
		// TODO: Need clarification this field move to db or property file
		Properties properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
		String secret_key = "2abfa552700f8cf9b9cbdb22909dacfa";
		int account_Id = 18984;
		String algorithm = "MD5";
		String mode = "LIVE";
		int channel = 0; // 0-standerd mode 1-Direct mode

		// HDFC Merchant account Id
		transaction.setAccountId(account_Id);
		transaction.setAddress(deliveryAddress.getAddress1()
				+ (deliveryAddress.getAddress2() != null ? ", "
						+ deliveryAddress.getAddress2() : ""));
		// Secure transaction algorithm type
		transaction.setAlgo(algorithm);
		transaction.setAmount(salesOrder.getNetAmount());
		transaction.setChannel(channel);
		transaction.setCity(deliveryAddress.getCity().getName());
		transaction.setCountry(deliveryAddress.getCountry().getCode());
		transaction.setCurrency(deliveryAddress.getCountry().getCurrencyCode());
		transaction.setCurrencyCode(deliveryAddress.getCountry()
				.getCurrencyCode());
		transaction.setDescription(salesOrder.getSalesOrderId());
		// TODO: need to clarify with HDFC
		transaction.setEmail((customer.getEmail() != null ? customer.getEmail()
				: "prabakaran.a@mitosistech.com"));
		transaction.setMode(mode);
		transaction.setName(customer.getName());
		transaction.setPhone(deliveryAddress.getPhoneNo());
		transaction.setPostalCode(deliveryAddress.getPinCode());
		transaction.setReferenceNo(salesOrder.getOrderNo());
		transaction.setReturnUrl(properties
				.getProperty("paymentGatewayRedirectUrl"));
		/*
		 * transaction.setShipAddress(shippingAddress.getAddress1()+(shippingAddress
		 * .getAddress2()!=null ? ", "+shippingAddress.getAddress2() : ""));
		 * transaction.setShipCity(shippingAddress.getCity().getName());
		 * transaction.setShipCountry(shippingAddress.getCountry().getCode());
		 * transaction.setShipName(salesOrder.getStore().getName());
		 * transaction.setShipPhone(shippingAddress.getPhoneNo());
		 * transaction.setShipPostalCode(shippingAddress.getPinCode());
		 * transaction.setShipState(shippingAddress.getState().getName());
		 */
		transaction.setState(deliveryAddress.getState().getName());

		return generateHashCode(transaction, secret_key);
	}

	private TransactionDetailVo generateHashCode(
			TransactionDetailVo transaction, String secret_key) {
		String hashMessage = "";
		String message = secret_key + "|" + transaction.getAccountId() + "|"
				+ transaction.getAddress() + "|" + transaction.getAlgo() + "|"
				+ transaction.getAmount() + "|" + transaction.getChannel()
				+ "|" + transaction.getCity() + "|" + transaction.getCountry()
				+ "|" + transaction.getCurrency() + "|"
				+ transaction.getCurrencyCode() + "|"
				+ transaction.getDescription() + "|" + transaction.getEmail()
				+ "|" + transaction.getMode() + "|" + transaction.getName()
				+ "|" + transaction.getPhone() + "|"
				+ transaction.getPostalCode() + "|"
				+ transaction.getReferenceNo() + "|"
				+ transaction.getReturnUrl() + "|" + transaction.getState();
		/*
		 * +"|"+transaction.getShipAddress() +"|"+transaction.getShipCity()
		 * +"|"+transaction.getShipCountry() +"|"+transaction.getShipName()
		 * +"|"+((transaction.getShipPhone() != null) ?
		 * transaction.getShipPhone()+"|":"") +transaction.getShipPostalCode()
		 * +"|"+transaction.getShipState()
		 */
		try {
			hashMessage = HashGeneratorUtils.generateMD5(message);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		transaction.setSecureHash(hashMessage.toUpperCase());
		return transaction;
	}

}