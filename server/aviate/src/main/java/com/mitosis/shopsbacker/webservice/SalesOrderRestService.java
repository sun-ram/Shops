package com.mitosis.shopsbacker.webservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.admin.service.TaxService;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.customer.service.MyCartService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.OrderTax;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Tax;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.order.service.OrderNumberService;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.order.service.ShippingChargesService;
import com.mitosis.shopsbacker.responsevo.ConfirmOrderResponseVo;
import com.mitosis.shopsbacker.responsevo.SalesOrderResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.OrderStatus;
import com.mitosis.shopsbacker.util.RoleName;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderLineVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;

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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SalesOrderResponseVo getSalesOrderByStore(SalesOrderVo salesOrderVo) {
		response = new ResponseModel();
		salesOrderResponse = new SalesOrderResponseVo();
		try {
			if (salesOrderVo.getStore() != null) {
				Store store = getStoreService().getStoreById(
						salesOrderVo.getStore().getStoreId());
				List<SalesOrder> salesOrderList = salesOrderService
						.getOrderList(store);
				for (SalesOrder salesOrder : salesOrderList) {
					salesOrderVo = getSalesOrderService().setSalesOrderVo(
							salesOrder);
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
							salesOrder);
					salesOrderResponse.getSalesOrderList().add(salesOrderVo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return salesOrderResponse;
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
				User user = userService.getUser(salesOrderVo.getUser()
						.getUserId());
				salesOrder.setStatus(OrderStatus.Shoper_Assigned.toString());
				salesOrder.setShopper(user);
				salesOrderService.updateSalesOrder(salesOrder);
				salesOrderResponseVo.setStatus(SBMessageStatus.SUCCESS
						.getValue());
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
				salesOrder.setBacker(user);
				salesOrderService.updateSalesOrder(salesOrder);
				salesOrderResponseVo.setStatus(SBMessageStatus.SUCCESS
						.getValue());
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
							salesOrder);
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
							salesOrder);
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
	public ConfirmOrderResponseVo confirmOrder(SalesOrderVo salesOrderVo) {
		ConfirmOrderResponseVo response = new ConfirmOrderResponseVo();
		try {
			SalesOrder salesOrder = (SalesOrder) CommonUtil
					.setAuditColumnInfo(SalesOrder.class.getName());
			salesOrder.setIsactive('Y');
			Customer customer = customerService
					.getCustomerInfoById(salesOrderVo.getCustomer()
							.getCustomerId());
			Store store = storeService.getStoreById(salesOrderVo.getStore()
					.getStoreId());
			String orderNo = orderNumber.getSalesOrderNumber(storeService
					.getStoreById(salesOrderVo.getStore().getStoreId()));
			Address address = addressService.getAddress(salesOrderVo
					.getAddress().getAddressId());

			salesOrder.setCustomer(customer);
			salesOrder.setAddress(address);
			salesOrder.setOrderNo(orderNo);
			salesOrder.setDeliveryDate(CommonUtil.stringToDate(salesOrderVo
					.getDeliveryDate()));
			salesOrder.setDeliveryTimeSlot(salesOrderVo.getDeliveryTimeSlot());
			salesOrder.setIspaid('N');
			salesOrder.setStore(store);
			salesOrder.setDiscountAmount(new BigDecimal(0));

			Double totalTaxAmount = 0.0;
			Double orderGrossAmount = 0.0;

			List<MyCart> cartProduct = myCartService.getMyCartList(customer,
					store);
			if (cartProduct.isEmpty()) {
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}

			List<SalesOrderLine> salesOrderLines = new ArrayList<SalesOrderLine>();
			for (MyCart myCart : cartProduct) {
				SalesOrderLine sol = (SalesOrderLine) CommonUtil
						.setAuditColumnInfo(SalesOrderLine.class.getName());
				Double lineAmount = myCart.getProduct().getPrice()
						.doubleValue()
						* myCart.getQty();
				BigDecimal lineGrossAmount = new BigDecimal(lineAmount);
				BigDecimal lineNetAmount = new BigDecimal(lineAmount);
				sol.setSalesOrder(salesOrder);
				sol.setIsactive('Y');
				sol.setGrossAmount(lineGrossAmount);
				sol.setNetAmount(lineNetAmount);
				sol.setDiscount(new BigDecimal(0.0));
				sol.setProduct(myCart.getProduct());
				sol.setQty(myCart.getQty());
				sol.setPrice(myCart.getProduct().getPrice());
				orderGrossAmount += lineAmount;

				salesOrderLines.add(sol);
			}
			salesOrder.setSalesOrderLines(salesOrderLines);

			List<Tax> taxs =store.getMerchant().getTaxes();
			List<OrderTax> orderTaxes = new ArrayList<OrderTax>();
			for (Tax tax : taxs) {
				OrderTax orderTax = (OrderTax) CommonUtil
						.setAuditColumnInfo(OrderTax.class.getName());

				Double taxAmount = (orderGrossAmount * (tax.getTaxPercentage() / 100));
				orderTax.setTax(tax);
				orderTax.setTaxAmount(new BigDecimal(taxAmount));
				orderTax.setMerchant(store.getMerchant());
				totalTaxAmount += taxAmount;

				orderTaxes.add(orderTax);
			}
			salesOrder.setOrderTaxes(orderTaxes);
			salesOrder.setAmount(new BigDecimal(orderGrossAmount));
			salesOrder.setNetAmount(new BigDecimal(orderGrossAmount));
			salesOrder.setTotalTaxAmount(new BigDecimal(totalTaxAmount));
			BigDecimal ShippingCharge = new BigDecimal(1000);
			salesOrder.setShippingCharge(ShippingCharge);
			salesOrder.setMerchant(store.getMerchant());

			salesOrderService.saveSalesOrder(salesOrder);
			response.setOrderNo(salesOrder.getOrderNo());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
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
					List<SalesOrder> salesOrderList = user.getSalesOrdersForShopperId();
					List<SalesOrderVo> salesOrderVoList = new ArrayList<SalesOrderVo>();
					Role role = user.getRole();
					if (RoleName.SHOPPER.toString().equalsIgnoreCase(role.getName())) {
						for (SalesOrder salesOrder : salesOrderList) {
							if (OrderStatus.Shoper_Assigned.toString().equalsIgnoreCase(salesOrder.getStatus())
									|| OrderStatus.Picked.toString().equalsIgnoreCase(salesOrder.getStatus())) {
								SalesOrderVo salesOrdervo = setSalesOrderVo(salesOrder);
								salesOrderVoList.add(salesOrdervo);
							}
						}
						salesOrderResponse.setSalesOrderList(salesOrderVoList);
					}
					salesOrderResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
				} else {
					salesOrderResponse.setStatus(SBMessageStatus.FAILURE.getValue());
					salesOrderResponse.setErrorString(SBErrorMessage.INVALID_SHOPPER_ID.getMessage());
					salesOrderResponse.setErrorCode(SBErrorMessage.INVALID_SHOPPER_ID.getCode());
				}
			} else if(salesOrderVo.getBackerId() != null){
				user = userService.getUser(salesOrderVo.getBackerId());
				if (user != null) {
					List<SalesOrder> salesOrderList = user.getSalesOrdersForBackerId();
					List<SalesOrderVo> salesOrderVoList = new ArrayList<SalesOrderVo>();
					Role role = user.getRole();
					if (RoleName.BACKER.toString().equalsIgnoreCase(role.getName())) {
						for (SalesOrder salesOrder : salesOrderList) {
							if (OrderStatus.Backer_Assigned.toString().equalsIgnoreCase(salesOrder.getStatus())
									|| OrderStatus.Backer_Started.toString().equalsIgnoreCase(salesOrder.getStatus())) {
								SalesOrderVo salesOrdervo = setSalesOrderVo(salesOrder);
								salesOrderVoList.add(salesOrdervo);
							}
						}
					}
				}
			}else{
				salesOrderResponse.setStatus(SBMessageStatus.FAILURE.getValue());
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
	
	public SalesOrderVo setSalesOrderVo(SalesOrder salesOrder) throws Exception{
		SalesOrderVo salesOrdervo = new SalesOrderVo();
		salesOrdervo.setSalesOrderId(salesOrder.getSalesOrderId());
		salesOrdervo.setOrderNo(salesOrder.getOrderNo());
		salesOrdervo.setStatus(salesOrder.getStatus());
		salesOrdervo.setAddress(addressService.setAddressVo(salesOrder.getAddress()));
		salesOrdervo.setCustomer(customerService.setCustomerVo(salesOrder.getCustomer()));
		List<SalesOrderLine> orderLines = salesOrder.getSalesOrderLines();
		List<SalesOrderLineVo> orderLineVos = new ArrayList<SalesOrderLineVo>();
		for (SalesOrderLine orderLine : orderLines) {
			SalesOrderLineVo salesOrderLineVo = new SalesOrderLineVo();
			ProductVo productVo = productService.setProductVo(orderLine.getProduct());
			salesOrderLineVo.setProductVo(productVo);
			salesOrderLineVo.setQty(orderLine.getQty());
			salesOrderLineVo.setSalesOrderLineId(orderLine.getSalesOrderLineId());
			orderLineVos.add(salesOrderLineVo);
		}
		salesOrdervo.setSalesOrderLineVo(orderLineVos);
		return salesOrdervo ;
	}
	
	@Path("/updateorderstatus")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String updateOrderStatusForEmp(SalesOrderVo salesOrderVo) {
		salesOrderResponse = new SalesOrderResponseVo();
		String responseStr = "";
		try {
			if (salesOrderVo.getSalesOrderId() != null && salesOrderVo.getStatus()!=null) {
			SalesOrder salesOrder = salesOrderService.getSalesOrderById(salesOrderVo.getSalesOrderId());
				if (salesOrder != null) {
					String status = salesOrderVo.getStatus();
					
					boolean isValidStatus = OrderStatus.contains(status);
							if (isValidStatus) {
								salesOrder.setStatus(status);
								salesOrderService.updateSalesOrder(salesOrder);
								salesOrderResponse.setStatus(SBMessageStatus.SUCCESS
										.getValue());
							}else{salesOrderResponse.setStatus(SBMessageStatus.FAILURE
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

		}
	 catch (Exception e) {
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
