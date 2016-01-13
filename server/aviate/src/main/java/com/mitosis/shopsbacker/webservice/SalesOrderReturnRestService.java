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
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.model.SalesOrderReturn;
import com.mitosis.shopsbacker.model.SalesOrderReturnLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.service.SalesOrderLineService;
import com.mitosis.shopsbacker.order.service.SalesOrderReturnLineService;
import com.mitosis.shopsbacker.order.service.SalesOrderReturnService;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.responsevo.SalesOrderReturnResponseVo;
import com.mitosis.shopsbacker.socket.SocketMessage;
import com.mitosis.shopsbacker.socket.SocketServer;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.SalesOrderLineVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderReturnLineVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderReturnVo;

/**
 * @author Kartheeswaran
 *
 * @param <T>
 */

@Path("salesorderreturn")
@Controller("salesOrderReturnRestService")
public class SalesOrderReturnRestService<T> {

	final static Logger log = Logger
			.getLogger(SalesOrderReturnRestService.class.getName());

	@Autowired
	SalesOrderService<T> salesOrderService;

	@Autowired
	SalesOrderLineService<T> salesOrderLineService;

	@Autowired
	SalesOrderReturnService<T> salesOrderReturnService;

	@Autowired
	SalesOrderReturnLineService<T> salesOrderReturnLineService;

	@Autowired
	StoreService<T> storeService;

	@Path("/createsalesorderreturn")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel createSalesOrderReturn(
			SalesOrderReturnLineVo salesOrderReturnLineVo) {
		ResponseModel response = new ResponseModel();
		try {
			BigDecimal totalGrossAmount = new BigDecimal(0);
			BigDecimal totalNetAmount = new BigDecimal(0);
			if (salesOrderReturnLineVo != null) {
				List<SalesOrderLineVo> salesOrderLineVos = salesOrderReturnLineVo
						.getSalesOrderLines();
				SalesOrder salesOrder = salesOrderService
						.getSalesOrderById(salesOrderReturnLineVo
								.getSalesOrderId());
				SalesOrderReturn salesOrderReturn = (SalesOrderReturn) CommonUtil
						.setAuditColumnInfo(SalesOrderReturn.class.getName(),
								null);
				salesOrderReturn.setSalesOrder(salesOrder);
				salesOrderReturn.setIsactive('Y');
				salesOrderReturn.setIspaid('N');
				salesOrderReturn.setMerchant(salesOrder.getMerchant());
				salesOrderReturn.setReturnReason("Out of Stock");
				salesOrderReturn.setReturnStatus("OK");
				salesOrderReturn.setReturnTaxAmount(salesOrder
						.getTotalTaxAmount());
				salesOrderReturn.setShippingCharge(salesOrder
						.getShippingCharge());
				salesOrderReturn.setStore(salesOrder.getStore());
				List<SalesOrderReturnLine> salesOrderReturnLineList = new ArrayList<SalesOrderReturnLine>();
				for (SalesOrderLineVo salesOrderLineVo : salesOrderLineVos) {
					if (salesOrderLineVo != null) {
						SalesOrderLine salesOrderLine = salesOrderLineService
								.getSalesOrderLineById(salesOrderLineVo
										.getSalesOrderLineId());
						if (salesOrderLine != null) {
							totalGrossAmount = totalGrossAmount
									.add(salesOrderLine.getGrossAmount());
							totalNetAmount = totalNetAmount.add(salesOrderLine
									.getNetAmount());
							SalesOrderReturnLine salesOrderReturnLine = (SalesOrderReturnLine) CommonUtil
									.setAuditColumnInfo(
											SalesOrderReturnLine.class
													.getName(), null);
							salesOrderReturnLine.setIsactive('Y');
							salesOrderReturnLine.setPrice(salesOrderLine
									.getPrice());
							salesOrderReturnLine.setProduct(salesOrderLine
									.getProduct());
							int returnQty = salesOrderLine.getQty() - salesOrderLineVo
									.getReturnQty();
							salesOrderReturnLine.setQuantity(returnQty);
							salesOrderReturnLine
									.setReturnGrossAmount(salesOrderLine
											.getGrossAmount());
							salesOrderReturnLine
									.setReturnNetAmount(salesOrderLine
											.getNetAmount());
							salesOrderReturnLine
									.setSalesOrderLine(salesOrderLine);
							salesOrderReturnLine
									.setSalesOrderReturn(salesOrderReturn);
							salesOrderReturnLineList.add(salesOrderReturnLine);
						}
					}
				}
				salesOrderReturn.setReturnTotalAmount(totalNetAmount);
				salesOrderReturn
						.setSalesOrderReturnLines(salesOrderReturnLineList);
				salesOrderReturnService
						.createSalesOrderReturn(salesOrderReturn);
				SalesOrderReturnVo salesOrderReturnvo = salesOrderReturnService
						.setSalesOrderToVo(salesOrderReturn);
				SocketMessage message = new SocketMessage();
				SocketServer socketServer = new SocketServer();
				message.setTag("SalesOrderReturn");
				message.setToUser(salesOrderReturnvo.getSalesOrder().getCustomer().getCustomerId());
				message.setSalesOrderReturn(CommonUtil.getObjectMapper(salesOrderReturnvo));
				socketServer.message(message, null);
			}
		} catch (Exception e) {
			response = CommonUtil.addStatusMessage(e, response);
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return response;
	}

	@Path("/getsalesorderreturn")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SalesOrderReturnResponseVo getsalesorderreturn(
			SalesOrderReturnVo salesOrderReturnVo) {
		SalesOrderReturnResponseVo salesOrderReturnResponseVo = new SalesOrderReturnResponseVo();
		List<SalesOrderReturnVo> salesOrderReturnVoList = new ArrayList<SalesOrderReturnVo>();
		Store store = storeService.getStoreById(salesOrderReturnVo.getStore()
				.getStoreId());
		if (store != null) {
			List<SalesOrderReturn> salesOrderReturnList = salesOrderReturnService
					.getSalesOrderByStore(store);
			for (SalesOrderReturn salesOrderReturn : salesOrderReturnList) {
				SalesOrderReturnVo salesOrderReturnvo = salesOrderReturnService
						.setSalesOrderToVo(salesOrderReturn);
				salesOrderReturnVoList.add(salesOrderReturnvo);
			}
		}
		salesOrderReturnResponseVo
				.setStatus(SBMessageStatus.SUCCESS.getValue());
		salesOrderReturnResponseVo
				.setSalesOrderReturnList(salesOrderReturnVoList);

		return salesOrderReturnResponseVo;

	}
	
	@Path("/refundrequest")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getsalesorderreturnrefundrequest(
			SalesOrderReturnVo salesOrderReturnVo) {
			
		JSONObject xmlJSONObj = new JSONObject();
		try{

		String xmlResponseStr = salesOrderReturnService.getSalesOrderReturnRefundRequest(salesOrderReturnVo);
		
		xmlJSONObj = XML.toJSONObject(xmlResponseStr);
		}catch(Exception e){
			
		}
		return xmlJSONObj.toString();

	}
}
