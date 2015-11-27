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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.order.service.ShippingChargesService;
import com.mitosis.shopsbacker.responsevo.ShippingChargesResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.order.ShippingChargesVo;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.model.Merchant;
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
	
	@Autowired
	MerchantService<T> merchantService;
	
	public ShippingChargesService<T> getShippingChargeService() {
		return shippingChargeService;
	}

	public void setShippingChargeService(
			ShippingChargesService<T> shippingChargeService) {
		this.shippingChargeService = shippingChargeService;
	}
	
	
	public MerchantService<T> getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService<T> merchantService) {
		this.merchantService = merchantService;
	}


	ResponseModel response = null;
	ShippingChargesResponseVo shippingchargesResponse = null;

	@Path("/addshippingcharges")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel addShippingCharges(ShippingChargesVo shippingChargesVo) {
		response = new ResponseModel();
		try {
			ShippingCharges shippingCharges = getShippingChargeService().setShippingCharges(shippingChargesVo);
			shippingChargeService.saveShippingCharges(shippingCharges);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShippingChargesResponseVo getShippingCharges(MerchantVo merchantVo) {
		response = new ResponseModel();
		shippingchargesResponse = new ShippingChargesResponseVo();
		try {
 			Merchant merchant = merchantService.getMerchantById(merchantVo.getMerchantId());
			List<ShippingCharges> shippingcharges = shippingChargeService.getShippingCharges(merchant);
			for (ShippingCharges shippingcharge : shippingcharges) {
				ShippingChargesVo shippingchargeVo = getShippingChargeService().setShippingChargesVo(shippingcharge);
				shippingchargesResponse.getShippingChargesList().add(shippingchargeVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return shippingchargesResponse;
	}

	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
