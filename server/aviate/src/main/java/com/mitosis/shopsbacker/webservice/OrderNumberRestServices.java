package com.mitosis.shopsbacker.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.model.OrderNumber;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.service.OrderNumberService;
import com.mitosis.shopsbacker.responsevo.OrderNumberResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.order.OrderNumberVo;

@Path("ordernumber")
@Controller("orderNumberRestServices")
public class OrderNumberRestServices {
	
	final static Logger log = Logger.getLogger(OrderNumber.class
			.getName());
	
	@Autowired
	OrderNumberService<T> orderNumberService;
	
	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	StoreService<T> storeService;
	
	public OrderNumberService<T> getOrderNumberService() {
		return orderNumberService;
	}

	public void setOrderNumberService(OrderNumberService<T> orderNumberService) {
		this.orderNumberService = orderNumberService;
	}

	ResponseModel response = new ResponseModel();
	
	@Path("/addordernumber")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public ResponseModel addOrderNumber(OrderNumberVo orderNumberVo) {
		try {
			OrderNumber orderNumber = orderNumberService.setOrderNumber(orderNumberVo);
			orderNumberService.saveOrderNumber(orderNumber);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}
	
	@Path("/getordernumber")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public OrderNumberResponseVo getOrderNumber(StoreVo storeVo) {
		OrderNumberResponseVo orderNumberResponse = new OrderNumberResponseVo();
		OrderNumber orderNo = null; 
		try {
			Store store = storeService.getStoreById(storeVo.getStoreId());
			List<OrderNumber> orderNumbers = store.getOrderNumbers();
			if(!orderNumbers.isEmpty()){
			//one store has only one order number so we used here get(0).
			orderNo=orderNumbers.get(0);
			OrderNumberVo orderNumber = orderNumberService.setOrderNumberVo(orderNo);
			orderNumberResponse.setOrderNumber(orderNumber);
			}else{
			 orderNumberResponse.orderNumber = null;
			}
		orderNumberResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			orderNumberResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			orderNumberResponse.setErrorString(CommonUtil.getErrorMessage(e));
			
		}
		return orderNumberResponse;
	}
	
	@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public ResponseModel updateOrderNumber(OrderNumberVo orderNumberVo) {
		try {
			OrderNumber orderNumber = orderNumberService.setOrderNumber(orderNumberVo);
			orderNumberService.saveOrderNumber(orderNumber);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}
	
	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public ResponseModel deleteOrderNumber(OrderNumberVo OrderNumberVo) {
		try {
			orderNumberService.deleteOrderNumber(OrderNumberVo.getOrderNumberId());
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

}
