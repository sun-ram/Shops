package com.mitosis.shopsbacker.webservice;

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
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.responsevo.SalesOrderResponseVo;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
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

				List<SalesOrder> salesOrderList = salesOrderService.getOrderList(salesOrderVo.getMerchant().getMerchantId());
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
				List<SalesOrder> salesOrderList = salesOrderService.salesOrderDetailList(salesOrderVo.getFromDate(),salesOrderVo.getDeliveryDate(),salesOrderVo.getMerchant().getMerchantId());
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



}
