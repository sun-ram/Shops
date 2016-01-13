package com.mitosis.shopsbacker.webservice;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.admin.service.TaxService;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.customer.service.MyCartService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.OrderTax;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Tax;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.order.service.BillingService;
import com.mitosis.shopsbacker.order.service.OrderNumberService;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.order.service.ShippingChargesService;
import com.mitosis.shopsbacker.responsevo.ConfirmOrderResponseVo;
import com.mitosis.shopsbacker.responsevo.SalesOrderResponseVo;
import com.mitosis.shopsbacker.socket.SocketMessage;
import com.mitosis.shopsbacker.socket.SocketServer;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.OrderStatus;
import com.mitosis.shopsbacker.util.PaymentMethod;
import com.mitosis.shopsbacker.util.RoleName;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;
import com.mitosis.shopsbacker.vo.order.TransactionDetailVo;

/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
@Path("sales")
@Controller("salesOrderRestService")
public class SalesOrderRestService<T> {

	final static Logger log = Logger.getLogger(SalesOrder.class.getName());

	@Autowired
	SalesOrderService<T> salesOrderService;

	@Autowired
	StoreService<T> storeService;

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	CustomerService<T> customerService;

	@Autowired
	OrderNumberService<T> orderNumber;

	@Autowired
	AddressService<T> addressService;

	@Autowired
	MyCartService<T> myCartService;

	@Autowired
	TaxService<T> taxService;

	@Autowired
	ShippingChargesService<T> shippingChargesService;

	@Autowired
	UserService<T> userService;

	@Autowired
	ProductService<T> productService;

	@Autowired
	ImageService<T> imageService;
	
	@Autowired
	BillingService<T> billingService;

	public SalesOrderService<T> getSalesOrderService() {
		return salesOrderService;
	}

	public void setSalesOrderService(SalesOrderService<T> salesOrderService) {
		this.salesOrderService = salesOrderService;
	}

	public StoreService<T> getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService<T> storeService) {
		this.storeService = storeService;
	}

	public MerchantService<T> getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService<T> merchantService) {
		this.merchantService = merchantService;
	}

	ResponseModel response = null;
	SalesOrderResponseVo salesOrderResponse = null;
	
	@Path("/getsalesorder")
	@POST
	@Consumes(MediaType.APPLICATION_JSON+";charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getSalesOrderByStore(SalesOrderVo salesOrderVo) {
		response = new ResponseModel();
		salesOrderResponse = new SalesOrderResponseVo();
		String res = "";
		try {
			if (salesOrderVo.getStore() != null) {
				Store store = getStoreService().getStoreById(
						salesOrderVo.getStore().getStoreId());
				List<SalesOrder> salesOrderList = salesOrderService
						.getOrderList(store);
				for (SalesOrder salesOrder : salesOrderList) {
					salesOrderVo = getSalesOrderService().setSalesOrderVo(
							salesOrder, false);
					salesOrderResponse.getSalesOrderList().add(salesOrderVo);
				}
			} else if (salesOrderVo.getMerchant() != null) {
				/*
				 * Merchant merchant =
				 * merchantService.getMerchantById(salesOrderVo
				 * .getMerchantId()); List<Store> stores =
				 * getStoreService().getStoreByMerchant(merchant); for (Store
				 * store : stores) { StoreVo storeVo =
				 * storeService.setStoreVo(store);
				 * salesOrderVo.getStoreList().add(storeVo); }
				 */

				List<SalesOrder> salesOrderList = salesOrderService
						.getOrderList(merchantService
								.getMerchantById(salesOrderVo.getMerchant()
										.getMerchantId()));
				for (SalesOrder salesOrder : salesOrderList) {
					salesOrderVo = getSalesOrderService().setSalesOrderVo(
							salesOrder, false);
					salesOrderResponse.getSalesOrderList().add(salesOrderVo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		try {
			res = CommonUtil.getObjectMapper(salesOrderResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Path("/updateShoperIntoSalesOrder")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SalesOrderResponseVo updateShoperIntoSalesOrder(
			SalesOrderVo salesOrderVo) {
		SalesOrderResponseVo salesOrderResponseVo = new SalesOrderResponseVo();
		if (salesOrderVo.getSalesOrderId() != null) {
			SalesOrder salesOrder = salesOrderService
					.getSalesOrderById(salesOrderVo.getSalesOrderId());
			if (salesOrder != null) {
				
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
				String deliveryDate = DATE_FORMAT.format(salesOrder.getDeliveryDate());
				String currentDate = DATE_FORMAT.format(new Date());
				
				if(deliveryDate.equals(currentDate)){
					User user = userService.getUser(salesOrderVo.getUser()
							.getUserId());
					salesOrder.setStatus(OrderStatus.Shoper_Assigned.toString());
					salesOrder.setShopperAssignedTime(new Date());
					salesOrder.setShopper(user);
					salesOrderService.updateSalesOrder(salesOrder);
					salesOrderResponseVo.setStatus(SBMessageStatus.SUCCESS
							.getValue());

					if ("ANDROID".equalsIgnoreCase(user.getDeviceType())) {
						String message = "Received New Order";
						CommonUtil.androidPushNotification(message,
								user.getDeviceId(),"Shopper");
					} else if (user.getDeviceType()!=null && user.getDeviceType().equalsIgnoreCase("IOS")) {

					}
				}else{
					salesOrderResponseVo.setErrorCode(SBErrorMessage.CANT_ASSIGN_SHOPPER
							.getCode());
					salesOrderResponseVo.setErrorString(SBErrorMessage.CANT_ASSIGN_SHOPPER
							.getMessage());
					salesOrderResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());

					return salesOrderResponseVo;
				}
			}
		}
		return salesOrderResponseVo;

	}

	@Path("/updateBackerIntoSalesOrder")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SalesOrderResponseVo updateBackerIntoSalesOrder(
			SalesOrderVo salesOrderVo) {
		SalesOrderResponseVo salesOrderResponseVo = new SalesOrderResponseVo();
		if (salesOrderVo.getSalesOrderId() != null) {
			SalesOrder salesOrder = salesOrderService
					.getSalesOrderById(salesOrderVo.getSalesOrderId());
			if (salesOrder != null) {
				User user = userService.getUser(salesOrderVo.getUser()
						.getUserId());
				salesOrder.setStatus(OrderStatus.Backer_Assigned.toString());
				salesOrder.setBackerAssignedTime(new Date());
				salesOrder.setBacker(user);
				salesOrderService.updateSalesOrder(salesOrder);
				if(salesOrder.getPaymentMethod().equalsIgnoreCase("COD")){
					   billingService.addNewBill(salesOrder);
				}
				salesOrderResponseVo.setStatus(SBMessageStatus.SUCCESS
						.getValue());
				if (user.getDeviceType().equalsIgnoreCase("ANDROID")) {
					String message = "Received New Order";
					CommonUtil.androidPushNotification(message,
							user.getDeviceId(),"Backer");
				} else if (user.getDeviceType().equalsIgnoreCase("IOS")) {

				}
			}
		}
		return salesOrderResponseVo;

	}

	@Path("/getorderlistbydate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SalesOrderResponseVo getSalesOrderByDate(SalesOrderVo salesOrderVo) {
		response = new ResponseModel();
		salesOrderResponse = new SalesOrderResponseVo();
		try {
			if (salesOrderVo.getStore() != null) {
				Store store = getStoreService().getStoreById(
						salesOrderVo.getStore().getStoreId());
				List<SalesOrder> salesOrderList = salesOrderService
						.salesOrderDetailList(salesOrderVo.getFromDate(),
								salesOrderVo.getDeliveryDate(), store);
				for (SalesOrder salesOrder : salesOrderList) {
					salesOrderVo = getSalesOrderService().setSalesOrderVo(
							salesOrder, false);
					salesOrderResponse.getSalesOrderList().add(salesOrderVo);
				}
			} else if (salesOrderVo.getMerchant() != null) {
				List<SalesOrder> salesOrderList = salesOrderService
						.salesOrderDetailList(salesOrderVo.getFromDate(),
								salesOrderVo.getDeliveryDate(), merchantService
								.getMerchantById(salesOrderVo
										.getMerchant().getMerchantId()));
				for (SalesOrder salesOrder : salesOrderList) {
					salesOrderVo = getSalesOrderService().setSalesOrderVo(
							salesOrder, false);
					salesOrderResponse.getSalesOrderList().add(salesOrderVo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return salesOrderResponse;
	}

	@Path("/confirmorder")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String confirmOrder(SalesOrderVo salesOrderVo) {
		ConfirmOrderResponseVo response = new ConfirmOrderResponseVo();
		String res = "";
		SocketServer socket = new SocketServer();
		SocketMessage message = new SocketMessage();
		try {
			SalesOrder salesOrder = (SalesOrder) CommonUtil
					.setAuditColumnInfo(SalesOrder.class.getName(),salesOrderVo.getUserId());
			salesOrder.setIsactive('Y');
			Customer customer = customerService
					.getCustomerInfoById(salesOrderVo.getCustomer()
							.getCustomerId());
			Store store = storeService.getStoreById(salesOrderVo.getStore()
					.getStoreId());
			String orderNo = orderNumber.getSalesOrderNumber(store);
			if(orderNo.isEmpty())
			{
				response.setErrorCode(SBErrorMessage.NO_ORDER_NUMBER
						.getCode());
				response.setErrorString(SBErrorMessage.NO_ORDER_NUMBER
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				res = CommonUtil.getObjectMapper(response);

				return res;
			}
			Address address = addressService.getAddress(salesOrderVo
					.getAddress().getAddressId());

			salesOrder.setCustomer(customer);
			salesOrder.setAddress(address);
			salesOrder.setOrderNo(orderNo);
			salesOrder.setDeliveryDate(CommonUtil.stringToDate(salesOrderVo
					.getDeliveryDate()));

			String strDeliveryTime = salesOrderVo.getDeliveryTimeSlot();

			Date deliveryTime = CommonUtil.convertStringToTime(strDeliveryTime);
			/*long time = deliveryTime.getTime();
			Date deliveryTimes = new Date();
			deliveryTimes.setTime(time);*/
			salesOrder.setDeliveryTimeSlot(deliveryTime);
			salesOrder.setIspaid('N');
			salesOrder.setDeliveryFlag('N');
			salesOrder.setStore(store);
			salesOrder.setDiscountAmount(new BigDecimal(0));

			if (salesOrderVo.getPaymentMethod() != null
					&& salesOrderVo.getPaymentMethod().equals("COD")) {
				salesOrder.setStatus(OrderStatus.Placed.toString());
				salesOrder.setPaymentMethod(PaymentMethod.COD.toString());
			}else if (salesOrderVo.getPaymentMethod() != null
					&& salesOrderVo.getPaymentMethod().equals("WT")) {
				//salesOrder.setStatus(OrderStatus.Placed.toString());
				salesOrder.setPaymentMethod(PaymentMethod.WT.toString());
			}

			Double totalTaxAmount = 0.0;
			Double orderGrossAmount = 0.0;

			List<MyCart> cartProduct = myCartService.getMyCartList(customer,
					store);
			if (cartProduct.isEmpty()) {
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				response.setErrorCode(SBErrorMessage.CART_IS_EMPTY.getCode());
				response.setErrorString(SBErrorMessage.CART_IS_EMPTY.getMessage());
				res = CommonUtil.getObjectMapper(response);
				return res;
			}

			List<SalesOrderLine> salesOrderLines = new ArrayList<SalesOrderLine>();
			for (MyCart myCart : cartProduct) {
				
				Double discountPrice = 0.0;
				if(myCart.getDiscount() != null){
					if(myCart.getDiscount().getDiscountPercentage() != null){
						 discountPrice = 	myCart.getProduct().getPrice().doubleValue() -(myCart.getProduct().getPrice().doubleValue() * myCart.getDiscount().getDiscountPercentage()/100); 
					}
					if(myCart.getDiscount().getDiscountAmount() != null){
						 discountPrice = myCart.getProduct().getPrice().doubleValue() - myCart.getDiscount().getDiscountAmount().doubleValue();
					}
				}else{
					 discountPrice = 	myCart.getProduct().getPrice().doubleValue();
				}
				BigDecimal price = BigDecimal.valueOf(discountPrice);
				
				SalesOrderLine sol = (SalesOrderLine) CommonUtil
						.setAuditColumnInfo(SalesOrderLine.class.getName(),salesOrderVo.getUserId());
				Double lineAmount = discountPrice.doubleValue() * myCart.getQty();
				BigDecimal lineGrossAmount = new BigDecimal(lineAmount);
				BigDecimal lineNetAmount = new BigDecimal(lineAmount);
				sol.setSalesOrder(salesOrder);
				sol.setIsactive('Y');
				sol.setGrossAmount(lineGrossAmount);
				sol.setNetAmount(lineNetAmount);
				sol.setDiscount(new BigDecimal(0.0));
				sol.setProduct(myCart.getProduct());
				sol.setQty(myCart.getQty());
				sol.setPrice(price);
				orderGrossAmount += lineAmount;

				salesOrderLines.add(sol);
			}
			salesOrder.setSalesOrderLines(salesOrderLines);

			List<Tax> taxs = store.getMerchant().getTaxes();
			List<OrderTax> orderTaxes = new ArrayList<OrderTax>();
			for (Tax tax : taxs) {
				OrderTax orderTax = (OrderTax) CommonUtil
						.setAuditColumnInfo(OrderTax.class.getName(),salesOrderVo.getUserId());

				Double taxAmount = (orderGrossAmount * (tax.getTaxPercentage() / 100));
				orderTax.setTax(tax);
				orderTax.setTaxAmount(new BigDecimal(taxAmount));
				orderTax.setMerchant(store.getMerchant());
				totalTaxAmount += taxAmount;

				orderTaxes.add(orderTax);
			}
			orderGrossAmount+=totalTaxAmount;
			salesOrder.setOrderTaxes(orderTaxes);
			
			salesOrder.setTotalTaxAmount(new BigDecimal(totalTaxAmount));
			BigDecimal ShippingCharge = shippingChargesService.getShippingCharge(new BigDecimal(orderGrossAmount), store.getMerchant());
			BigDecimal totalOrderGrossAmount = new BigDecimal(orderGrossAmount).add(ShippingCharge);
			salesOrder.setShippingCharge(ShippingCharge);
			salesOrder.setMerchant(store.getMerchant());
			
			salesOrder.setAmount(totalOrderGrossAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
			//TODO: when implementing order discount need to reduce discount amount
			salesOrder.setNetAmount(totalOrderGrossAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
						
			salesOrderService.saveSalesOrder(salesOrder);
			
			/*Reduce Stock*/
			salesOrderService.productStockReduce(salesOrder);

			response.setPaymentMethod(salesOrderVo.getPaymentMethod());
			int numberOfEntityDeleted = 0;
			if (salesOrderVo.getPaymentMethod() != null
					&& salesOrderVo.getPaymentMethod().equals("COD")) {
				numberOfEntityDeleted = myCartService.deleteCartProduct(salesOrder.getCustomer().getCustomerId(),salesOrder.getStore().getStoreId());
			}else if(salesOrderVo.getPaymentMethod() != null
					&& salesOrderVo.getPaymentMethod().equals("WT")){ //Wire transfer 
				TransactionDetailVo transactionDetail = salesOrderService.setTransactionDetails(salesOrder, salesOrder.getAddress(), 
						salesOrder.getStore().getUser().getAddress(), salesOrder.getCustomer());
				response.setTransactionDatas(transactionDetail);
			}
			log.info("No Of Row Deleted deleted from cart in sales order complete: "+numberOfEntityDeleted);
			response.setOrderNo(salesOrder.getOrderNo());
			response.setSalesOrderId(salesOrder.getSalesOrderId());
			salesOrderVo = getSalesOrderService().setSalesOrderVo(
					salesOrder, false);
			message.setMessage("New");
			message.setTag("SalesOrder");
			message.setSalesOrder(CommonUtil.getObjectMapper(salesOrderVo));
			message.setToUser(salesOrderVo.getMerchant().getMerchantId());
			socket.message(message, null);
			message.setToUser(salesOrderVo.getStore().getStoreId());
			socket.message(message, null);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		try {
				res = CommonUtil.getObjectMapper(response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return res;
	}
	
	@Path("/confirmorderpayment")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public JSONObject confirmOrderPayment(JSONObject  transactiondatas) {
		JSONObject response = new JSONObject();
		try{ 
			if ("0".equalsIgnoreCase(transactiondatas.getString("ResponseCode"))
					&& "Transaction Successful".equalsIgnoreCase(transactiondatas.getString("ResponseMessage"))) {
				boolean flag = salesOrderService.paymentConfimation(
												transactiondatas.getString("Description"),
												transactiondatas.getString("PaymentId"), 
												transactiondatas.getString("PaymentMode"),
												transactiondatas.getString("AccountId"), 
												transactiondatas.getString("TransactionId"), 
												transactiondatas.getString("ResponseCode"),
												transactiondatas.getString("ResponseMessage"),
												transactiondatas.getString("MerchantRefNo"));
				if(flag){
					response.put("status", SBMessageStatus.SUCCESS.getValue());
					response.put("salesOrderId", transactiondatas.getString("Description"));
				}else{
					response.put("status", SBMessageStatus.FAILURE.getValue());
					response.put("errorString", SBErrorMessage.PARMENT_CONFIRMATION.getMessage());
					response.put("errorCode", SBErrorMessage.PARMENT_CONFIRMATION.getCode());
				}
			}else{
				response.put("status", SBMessageStatus.FAILURE.getValue());
				response.put("errorString", SBErrorMessage.PARMENT_CONFIRMATION.getMessage());
				response.put("errorCode", SBErrorMessage.PARMENT_CONFIRMATION.getCode());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}

	@Path("/getsalesorders")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getSalesOrdersForEmp(SalesOrderVo salesOrderVo) {
		salesOrderResponse = new SalesOrderResponseVo();
		String responseStr = "";
		try {
			User user = new User();
			if (salesOrderVo.getShoperId() != null) {
				user = userService.getUser(salesOrderVo.getShoperId());
				if (user != null) {
					List<SalesOrder> salesOrderList = user
							.getSalesOrdersForShopperId();
					List<SalesOrderVo> salesOrderVoList = new ArrayList<SalesOrderVo>();
					Role role = user.getRole();
					if (RoleName.SHOPPER.toString().equalsIgnoreCase(
							role.getName())) {
						for (SalesOrder salesOrder : salesOrderList) {
							if (OrderStatus.Shoper_Assigned.toString()
									.equalsIgnoreCase(salesOrder.getStatus())
									|| OrderStatus.Picked.toString()
									.equalsIgnoreCase(
											salesOrder.getStatus())) {
								SalesOrderVo salesOrdervo = salesOrderService.setSalesOrderVo(salesOrder, false);
								salesOrderVoList.add(salesOrdervo);
							}
						}
						salesOrderResponse.setSalesOrderList(salesOrderVoList);
					}
					salesOrderResponse.setStatus(SBMessageStatus.SUCCESS
							.getValue());
				} else {
					salesOrderResponse.setStatus(SBMessageStatus.FAILURE
							.getValue());
					salesOrderResponse
					.setErrorString(SBErrorMessage.INVALID_SHOPPER_ID
							.getMessage());
					salesOrderResponse
					.setErrorCode(SBErrorMessage.INVALID_SHOPPER_ID
							.getCode());
				}
			} else if (salesOrderVo.getBackerId() != null) {
				user = userService.getUser(salesOrderVo.getBackerId());
				if (user != null) {
					List<SalesOrder> salesOrderList = salesOrderService
							.getSalesOrdersByBackerId(salesOrderVo.getBackerId());
					List<SalesOrderVo> salesOrderVoList = new ArrayList<SalesOrderVo>();
					Role role = user.getRole();
					if (RoleName.BACKER.toString().equalsIgnoreCase(
							role.getName())) {
						for (SalesOrder salesOrder : salesOrderList) {
							if (OrderStatus.Backer_Assigned.toString()
									.equalsIgnoreCase(salesOrder.getStatus())
									|| OrderStatus.Backer_Started.toString()
									.equalsIgnoreCase(
											salesOrder.getStatus())) {
								SalesOrderVo salesOrdervo = salesOrderService.setSalesOrderVo(salesOrder, true);
								if(salesOrdervo!=null){
									salesOrderVoList.add(salesOrdervo);
								}
							}
							salesOrderResponse
							.setSalesOrderList(salesOrderVoList);
						}
					}
				}
			} else {
				salesOrderResponse
				.setStatus(SBMessageStatus.FAILURE.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			salesOrderResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			salesOrderResponse.setErrorString(CommonUtil.getErrorMessage(e));
			log.error(e.getMessage());
		}
		try {
			responseStr = CommonUtil.getObjectMapper(salesOrderResponse);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return responseStr;
	}



	public void customerSign(SalesOrder salesOrder, SalesOrderVo salesOrderVo)
			throws IOException, Exception {
		Merchant merchant = salesOrder.getMerchant();
		Store store = salesOrder.getStore();
		String imagePath = "";
		String defaultImagePath = "";
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		defaultImagePath = properties.getProperty("imagePath");
		imagePath = "merchant/" + merchant.getName() + "/" + store.getStoreId()
				+ "/customer_sign";
		String imageName = UUID.randomUUID().toString().replace("-", "");
		if (CommonUtil.uploadImage(salesOrderVo.getSign().getImage(),
				salesOrderVo.getSign().getType(), defaultImagePath + imagePath,
				imageName)) {
			salesOrderVo.getSign().setName(imageName);
			salesOrderVo.getSign().setUrl(
					imagePath + imageName + "."
							+ salesOrderVo.getSign().getType());
		}
	}

	@Path("/updateorderstatus")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String updateOrderStatusForEmp(SalesOrderVo salesOrderVo) {
		salesOrderResponse = new SalesOrderResponseVo();
		String responseStr = "";
		SocketServer socket = new SocketServer();
		SocketMessage message = new SocketMessage();
		try {
			SalesOrder salesOrder = salesOrderService
					.getSalesOrderById(salesOrderVo.getSalesOrderId());
			if (salesOrder != null) {

				if (salesOrderVo.getSalesOrderId() != null
						&& salesOrderVo.getStatus() != null) {

					boolean isValidStatus = OrderStatus.contains(salesOrderVo.getStatus());
					if (isValidStatus) {

						if (OrderStatus.Picked.toString().equalsIgnoreCase(
								salesOrderVo.getStatus())) {
							salesOrder.setPickupStartTime(new Date());
						}else if (OrderStatus.Packed.toString().equalsIgnoreCase(
								salesOrderVo.getStatus())) {
							salesOrder.setPackedTime(new Date());
						}else if (OrderStatus.Backer_Started.toString().equalsIgnoreCase(
								salesOrderVo.getStatus())) {
							salesOrder.setDeliveryStartTime(new Date());
						}else if (OrderStatus.Delivered.toString().equalsIgnoreCase(
								salesOrderVo.getStatus())) {
							if (salesOrderVo.getSign().getImage() != null) {
								customerSign(salesOrder, salesOrderVo);
								Image image = imageService.setImage(salesOrderVo
										.getSign(),null);
								salesOrder.setCustomerSign(image);
								salesOrder.setDeliveredTime(new Date());
							} else {
								salesOrderResponse
								.setStatus(SBMessageStatus.FAILURE
										.getValue());
								salesOrderResponse
								.setErrorCode(SBErrorMessage.UNKNOWN_CUSTOMER_SIGN
										.getCode());
								salesOrderResponse
								.setErrorString(SBErrorMessage.UNKNOWN_CUSTOMER_SIGN
										.getMessage());
								try {
									responseStr = CommonUtil
											.getObjectMapper(salesOrderResponse);
								} catch (Exception e) {
									e.printStackTrace();
									log.error(e.getMessage());
								}
								return responseStr;
							}
						}
						salesOrder.setStatus(salesOrderVo.getStatus());
						salesOrderService.updateSalesOrder(salesOrder);
						salesOrderResponse.setStatus(SBMessageStatus.SUCCESS
								.getValue());
						salesOrderVo = getSalesOrderService().setSalesOrderVo(
								salesOrder, false);
						message.setMessage("Update");
						message.setTag("SalesOrder");
						message.setSalesOrder(CommonUtil.getObjectMapper(salesOrderVo));
						message.setToUser(salesOrderVo.getMerchant().getMerchantId());
						socket.message(message, null);
						message.setToUser(salesOrderVo.getStore().getStoreId());
						socket.message(message, null);
					} else {
						salesOrderResponse.setStatus(SBMessageStatus.FAILURE
								.getValue());
						salesOrderResponse
						.setErrorString(SBErrorMessage.INVALID_SALES_ORDER_STATUS
								.getMessage());
						salesOrderResponse
						.setErrorCode(SBErrorMessage.INVALID_SALES_ORDER_STATUS
								.getCode());
					}
				} else {
					salesOrderResponse.setStatus(SBMessageStatus.FAILURE
							.getValue());
					salesOrderResponse
					.setErrorString(SBErrorMessage.INVALID_SALES_ORDER_ID
							.getMessage());
					salesOrderResponse
					.setErrorCode(SBErrorMessage.INVALID_SALES_ORDER_ID
							.getCode());
				}
			} else {
				salesOrderResponse
				.setStatus(SBMessageStatus.FAILURE.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			salesOrderResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			salesOrderResponse.setErrorString(CommonUtil.getErrorMessage(e));
			log.error(e.getMessage());
		}
		try {
			responseStr = CommonUtil.getObjectMapper(salesOrderResponse);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return responseStr;
	}

}