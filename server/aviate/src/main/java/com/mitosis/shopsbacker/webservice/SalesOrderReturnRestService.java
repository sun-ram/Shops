package com.mitosis.shopsbacker.webservice;

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

import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.model.SalesOrderReturn;
import com.mitosis.shopsbacker.model.SalesOrderReturnLine;
import com.mitosis.shopsbacker.order.service.SalesOrderLineService;
import com.mitosis.shopsbacker.order.service.SalesOrderReturnLineService;
import com.mitosis.shopsbacker.order.service.SalesOrderReturnService;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.SalesOrderLineVo;

/**
 * @author Kartheeswaran
 *
 * @param <T>
 */

@Path("salesorderreturn")
@Controller("salesOrderReturnRestService")
public class SalesOrderReturnRestService<T> {
	
	final static Logger log = Logger.getLogger(SalesOrderReturnRestService.class.getName());
	
	@Autowired
	SalesOrderService<T> salesOrderService;
	
	@Autowired
	SalesOrderLineService<T> salesOrderLineService;
	
	@Autowired
	SalesOrderReturnService<T> salesOrderReturnService;
	
	@Autowired
	SalesOrderReturnLineService<T> salesOrderReturnLineService;
	
	@Path("/createsalesorderreturn")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel createSalesOrderReturn(SalesOrderLineVo salesOrderLineVo) {
		ResponseModel response = new ResponseModel();
		try {
			
			if(salesOrderLineVo!=null && salesOrderLineVo.getSalesOrderLineId()!=null && !salesOrderLineVo.getSalesOrderLineId().equals("")){
				SalesOrderLine salesOrderLine = salesOrderLineService.getSalesOrderLineById(salesOrderLineVo.getSalesOrderLineId());
				if(salesOrderLine!=null){
					SalesOrder salesOrder = salesOrderLine.getSalesOrder();
					SalesOrderReturn salesOrderReturn = (SalesOrderReturn) CommonUtil.setAuditColumnInfo(SalesOrderReturn.class.getName(), null);
					salesOrderReturn.setIsactive('Y');
					salesOrderReturn.setMerchant(salesOrder.getMerchant());
					salesOrderReturn.setReturnReason("Out of Stock");
					salesOrderReturn.setReturnStatus("OK");
					salesOrderReturn.setReturnTaxAmount(salesOrder.getTotalTaxAmount());
					salesOrderReturn.setReturnTotalAmount(salesOrderLine.getNetAmount());
					salesOrderReturn.setSalesOrderId(salesOrder.getSalesOrderId());
					salesOrderReturn.setShippingCharge(salesOrder.getShippingCharge());
					salesOrderReturn.setStore(salesOrder.getStore());
					salesOrderReturnService.createSalesOrderReturn(salesOrderReturn);
					
					SalesOrderReturnLine salesOrderReturnLine = (SalesOrderReturnLine) CommonUtil.setAuditColumnInfo(SalesOrderReturnLine.class.getName(), null);
					salesOrderReturnLine.setIsactive('Y');
					salesOrderReturnLine.setPrice(salesOrderLine.getPrice());
					salesOrderReturnLine.setProduct(salesOrderLine.getProduct());
					salesOrderReturnLine.setQuantity(salesOrderLineVo.getQty());
					salesOrderReturnLine.setReturnGrossAmount(salesOrderLine.getGrossAmount());
					salesOrderReturnLine.setReturnNetAmount(salesOrderLine.getNetAmount());
					salesOrderReturnLine.setSalesOrderLine(salesOrderLine);
					salesOrderReturnLine.setSalesOrderReturn(salesOrderReturn);
					salesOrderReturnLineService.createSalesOrderReturnLine(salesOrderReturnLine);
				}
			}
			
			
			
		} catch (Exception e) {
			response = CommonUtil.addStatusMessage(e, response);
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return response;
	}
	
}
