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
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.customer.service.MyCartService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.OrderTax;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Tax;
import com.mitosis.shopsbacker.order.service.OrderNumberService;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.order.service.ShippingChargesService;
import com.mitosis.shopsbacker.responsevo.ConfirmOrderResponseVo;
import com.mitosis.shopsbacker.responsevo.SalesOrderResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;

/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
@Path("sales")
@Controller("salesOrderRestService")
public class SalesOrderRestService<T> {

	final static Logger log = Logger.getLogger(SalesOrder.class
			.getName());

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
	@Transactional(propagation=Propagation.REQUIRED)
	public SalesOrderResponseVo getSalesOrderByStore(SalesOrderVo salesOrderVo) {
		response = new ResponseModel();
		salesOrderResponse = new SalesOrderResponseVo();
		try {
			if(salesOrderVo.getStoreVo()!=null){
				Store store = getStoreService().getStoreById(salesOrderVo.getStoreVo().getStoreId());
				List<SalesOrder> salesOrderList = salesOrderService.getOrderList(store);
				for (SalesOrder salesOrder : salesOrderList) {
					salesOrderVo = getSalesOrderService().setSalesOrderVo(salesOrder);
					salesOrderResponse.getSalesOrderList().add(salesOrderVo);
				}
			}else if (salesOrderVo.getMerchant()!=null) {
				/*Merchant merchant = merchantService.getMerchantById(salesOrderVo.getMerchantId());
				List<Store> stores = getStoreService().getStoreByMerchant(merchant);
				for (Store store : stores) {
					StoreVo storeVo = storeService.setStoreVo(store);
					salesOrderVo.getStoreList().add(storeVo);
				}*/

				List<SalesOrder> salesOrderList = salesOrderService.getOrderList(merchantService.getMerchantById(salesOrderVo.getMerchant().getMerchantId()));
				for (SalesOrder salesOrder : salesOrderList) {
					salesOrderVo = getSalesOrderService().setSalesOrderVo(salesOrder);
					salesOrderResponse.getSalesOrderList().add(salesOrderVo);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return salesOrderResponse;
	}

	@Path("/getorderlistbydate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public SalesOrderResponseVo getSalesOrderByDate(SalesOrderVo salesOrderVo) {
		response = new ResponseModel();
		salesOrderResponse = new SalesOrderResponseVo();
		try {
			if(salesOrderVo.getStoreVo()!=null){
				Store store = getStoreService().getStoreById(salesOrderVo.getStoreVo().getStoreId());
				List<SalesOrder> salesOrderList = salesOrderService.salesOrderDetailList(salesOrderVo.getFromDate(),salesOrderVo.getDeliveryDate(),store);
				for (SalesOrder salesOrder : salesOrderList) {
					salesOrderVo = getSalesOrderService().setSalesOrderVo(salesOrder);
					salesOrderResponse.getSalesOrderList().add(salesOrderVo);
				}
			}else if(salesOrderVo.getMerchant()!=null) {
				List<SalesOrder> salesOrderList = salesOrderService.salesOrderDetailList(salesOrderVo.getFromDate(),salesOrderVo.getDeliveryDate(),merchantService.getMerchantById(salesOrderVo.getMerchant().getMerchantId()));
				for (SalesOrder salesOrder : salesOrderList) {
					salesOrderVo = getSalesOrderService().setSalesOrderVo(salesOrder);
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
	@Transactional(propagation=Propagation.REQUIRED)
	public ConfirmOrderResponseVo confirmOrder(SalesOrderVo salesOrderVo) {
		ConfirmOrderResponseVo response = new ConfirmOrderResponseVo();
		try {
			SalesOrder salesOrder = (SalesOrder) CommonUtil.setAuditColumnInfo(SalesOrder.class.getName());

			Customer customer = customerService.getCustomerInfoById(salesOrderVo.getCustomer().getCustomerId());
			Store store = storeService.getStoreById(salesOrderVo.getStoreVo().getStoreId());
			String orderNo = orderNumber.getSalesOrderNumber(storeService.getStoreById(salesOrderVo.getStoreVo().getStoreId()));
			Address address = addressService.getAddress(salesOrderVo.getAddress().getAddressId());
			
			salesOrder.setCustomer(customer);
			salesOrder.setAddress(address);
			salesOrder.setOrderNo(orderNo);
			salesOrder.setDeliveryDate(CommonUtil.stringToDate(salesOrderVo.getDeliveryDate()));
			//TODO: Delivery time slot need to set here
			salesOrder.setIspaid('N');
			salesOrder.setStore(store);
			
			Double totalTaxAmount = 0.0;
			Double orderGrossAmount = 0.0;
			
			List<MyCart> cartProduct = myCartService.getMyCartList(customer, store);
			if(cartProduct.isEmpty()){
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}
			
			List<SalesOrderLine> salesOrderLines = new ArrayList<SalesOrderLine>();
			for(MyCart myCart : cartProduct){
				SalesOrderLine sol = (SalesOrderLine) CommonUtil.setAuditColumnInfo(SalesOrderLine.class.getName());
				Double lineAmount = myCart.getProduct().getPrice().doubleValue() * myCart.getQty();
				BigDecimal lineGrossAmount = new BigDecimal(lineAmount);
				BigDecimal lineNetAmount = new BigDecimal(lineAmount);
				sol.setGrossAmount(lineGrossAmount);
				sol.setNetAmount(lineNetAmount);
				sol.setProduct(myCart.getProduct());
				sol.setQty(myCart.getQty());
				sol.setPrice(myCart.getProduct().getPrice());
				orderGrossAmount += lineAmount;
				
				salesOrderLines.add(sol);
			}
			salesOrder.setSalesOrderLines(salesOrderLines);
			
			List<Tax> taxs = taxService.getTax(store.getMerchant()); 
			List<OrderTax> orderTaxes = new ArrayList<OrderTax>();
			for(Tax tax : taxs){
				OrderTax orderTax = (OrderTax) CommonUtil.setAuditColumnInfo(OrderTax.class.getName());
				
				Double taxAmount = (orderGrossAmount * (tax.getTaxPercentage() / 100));
				orderTax.setTax(tax);
				orderTax.setTaxAmount(new BigDecimal(taxAmount));
				orderTax.setMerchant(store.getMerchant());
				totalTaxAmount += taxAmount;
				
				orderTaxes.add(orderTax);
			}
			salesOrder.setOrderTaxes(orderTaxes);
			salesOrder.setAmount(new BigDecimal(orderGrossAmount));
			salesOrder.setTotalTaxAmount(new BigDecimal(totalTaxAmount));
			Double ShippingCharge = shippingChargesService.getShippingCharge(orderGrossAmount,store.getMerchant());
		salesOrder.setShippingCharge(new BigDecimal(ShippingCharge));
			
			//TODO: Need to set Merchant
			//salesOrder.setMerchant(store.getMerchant());
			
			salesOrderService.saveSalesOrder(salesOrder);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}

}
