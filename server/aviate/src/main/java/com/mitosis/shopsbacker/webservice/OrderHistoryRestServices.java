package com.mitosis.shopsbacker.webservice;

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

import com.mitosis.shopsbacker.customer.service.OrderHistoryService;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.responsevo.SalesOrderResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;

/**
 * @author prabakaran
 *
 * @param <T>
 */
@Path("orderhistory")
@Controller("orderHistoryRestServices")
public class OrderHistoryRestServices<T> {
	final static Logger log = Logger.getLogger(OrderHistoryRestServices.class);

	@Autowired
	OrderHistoryService<T> orderHistoryService;

	@Autowired
	SalesOrderService<T> salesOrderService;

	@Path("/customerorder")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String customerOrder(SalesOrderVo salesOrderVo) throws Exception {
		String response= "";
		SalesOrderResponseVo salesOrderResponse = new SalesOrderResponseVo();
		try {
			List<SalesOrder> orders = orderHistoryService.getOrderList(salesOrderVo.getStoreId(), salesOrderVo.getCustomerId());
			for (SalesOrder salesOrder : orders) {
				salesOrderVo = salesOrderService.setSalesOrderVo(salesOrder);
				salesOrderResponse.getSalesOrderList().add(salesOrderVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			salesOrderResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			salesOrderResponse.setErrorString(CommonUtil.getErrorMessage(e));
		} finally{
			response = CommonUtil.getObjectMapperWithSerializationFeature(salesOrderResponse);
		}
		return response;
	}

}
