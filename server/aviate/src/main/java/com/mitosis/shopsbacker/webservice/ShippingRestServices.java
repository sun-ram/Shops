package com.mitosis.shopsbacker.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mitosis.shopsbacker.order.service.ShippingChargesService;
import com.mitosis.shopsbacker.responsevo.ShippingChargesResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.ShippingChargesVo;
import com.mitosis.shopsbacker.model.ShippingCharges;
/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
@Path("shippingcharges")
@Controller("shippingRestServices")
public class ShippingRestServices<T> {
	final static Logger log = Logger.getLogger(ShippingRestServices.class
			.getName());

	@Autowired
	ShippingChargesService<T> shippingChargeService;

	public ShippingChargesService<T> getShippingChargeService() {
		return shippingChargeService;
	}

	public void setShippingChargeService(
			ShippingChargesService<T> shippingChargeService) {
		this.shippingChargeService = shippingChargeService;
	}

	ResponseModel response = null;
	ShippingChargesResponseVo shippingchargesResponse = null;

	@Path("/addshippingcharges")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addShippingCharges(ShippingChargesVo shippingChargesVo) {
		response = new ResponseModel();
		try {
			ShippingCharges shippingCharges = setShippingCharges(shippingChargesVo);
			shippingChargeService.saveShippingCharges(shippingCharges);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	public ShippingCharges setShippingCharges(ShippingChargesVo shippingChargesVo) throws Exception {
		ShippingCharges shippingCharges = (ShippingCharges) CommonUtil
				.setAuditColumnInfo(ShippingCharges.class.getName());
		shippingCharges.setMerchant(shippingChargesVo.getMerchant());
		shippingCharges.setAmountRange(shippingChargesVo.getAmountRange());
		shippingCharges.setChargingAmount(shippingChargesVo.getChargingAmount());
		shippingCharges.setIsactive('Y');
		return shippingCharges;
	}

	@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel updateShippingCharges(ShippingChargesVo shippingChargesVo) {
		response = new ResponseModel();
		try {
			ShippingCharges shippingCharge = shippingChargeService.getShippingChargesById(shippingChargesVo.getShippingChargesId());
			shippingCharge.setChargingAmount(shippingChargesVo.getChargingAmount());
			shippingCharge.setAmountRange(shippingChargesVo.getAmountRange());
			shippingChargeService.updateShippingCharges(shippingCharge);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	@Path("/getshippingcharges")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ShippingChargesResponseVo getShippingCharges(ShippingChargesVo shippingChargesVo) {
		response = new ResponseModel();
		shippingchargesResponse = new ShippingChargesResponseVo();
		try {
			List<ShippingCharges> shippingcharges = shippingChargeService.getShippingCharges(shippingChargesVo.getMerchant());
			for (ShippingCharges shippingcharge : shippingcharges) {
				ShippingChargesVo shippingchargeVo = setShippingChargesVo(shippingcharge);
				shippingchargesResponse.getShippingChargesList().add(shippingchargeVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return shippingchargesResponse;
	}

	public ShippingChargesVo setShippingChargesVo (ShippingCharges shippingCharge) {
		ShippingChargesVo shippingChargeVo = new ShippingChargesVo();
		//shippingChargeVo.setMerchant(shippingCharge.getMerchant());
		shippingChargeVo.setShippingChargesId(shippingCharge.getShippingChargesId());
		shippingChargeVo.setAmountRange(shippingCharge.getAmountRange());
		shippingChargeVo.setChargingAmount(shippingCharge.getChargingAmount());
		return shippingChargeVo;
	}

	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel deleteShippingCharges(ShippingChargesVo shippingChargesVo) {
		response = new ResponseModel();
		try {
			shippingChargeService.deleteShippingCharges(shippingChargesVo.getShippingChargesId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

}
