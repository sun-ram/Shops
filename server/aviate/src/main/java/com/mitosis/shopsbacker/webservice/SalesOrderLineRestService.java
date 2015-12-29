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

import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.order.service.SalesOrderLineService;
import com.mitosis.shopsbacker.responsevo.SalesOrderLineResponseVo;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.SalesOrderLineVo;

/**
 * @author JAI BHARATHI
 * @param <T>
 *
 * @param <T>
 */
@Path("salesline")
@Controller("salesOrderLineRestService")
public class SalesOrderLineRestService<T> {

	final static Logger log = Logger.getLogger(SalesOrderLine.class
			.getName());

	@Autowired
	SalesOrderLineService<T> salesOrderLineService;

	public SalesOrderLineService<T> getSalesOrderService() {
		return salesOrderLineService;
	}

	public void setSalesOrderService(SalesOrderLineService<T> salesOrderLineService) {
		this.salesOrderLineService = salesOrderLineService;
	}

	ResponseModel response = null;
	SalesOrderLineResponseVo salesOrderLineResponse = null;
	
	@Path("/getsalesline")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SalesOrderLineResponseVo getSalesOrderLineByStore(SalesOrderLineVo salesOrderLineVo) {
		response = new ResponseModel();
		salesOrderLineResponse = new SalesOrderLineResponseVo();
		try {
			SalesOrderLine salesline = salesOrderLineService.getSalesOrderLineById(salesOrderLineVo.getSalesOrderId());
			//for (SalesOrderLine salesOrderLine : saleslineList) {
				//salesOrderLineVo = salesOrderLineService.setSalesOrderLineVo(salesOrderLine);
				salesOrderLineResponse.getSaleslistListVo().add(salesOrderLineVo);
			//}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return salesOrderLineResponse;
	}

}
