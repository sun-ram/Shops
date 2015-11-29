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

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ShippingCharges;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.service.ShippingChargesService;
import com.mitosis.shopsbacker.responsevo.ShippingChargesResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.order.ShippingChargesVo;

/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
@Path("shippingcharges")
@Controller("shippingRestServices")
public class ShippingChargesRestServices<T> {
	final static Logger log = Logger
			.getLogger(ShippingChargesRestServices.class.getName());

	@Autowired
	ShippingChargesService<T> shippingChargeService;

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	StoreService<T> storeService;

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
			String id = null;
			Merchant merchat = merchantService
					.getMerchantById(shippingChargesVo.getMerchantVo()
							.getMerchantId());
			List<ShippingCharges> shippingChargeList = shippingChargeService
					.getShippingChargesList(id,
							shippingChargesVo.getAmountRange(), merchat);
			if (shippingChargeList.isEmpty()) {
				ShippingCharges shippingCharges = getShippingChargeService()
						.setShippingCharges(shippingChargesVo);
				shippingChargeService.saveShippingCharges(shippingCharges);
				return response;
			} else {
				response.setErrorCode(SBErrorMessage.AMOUNT_RANGE_ALREADY_EXIST
						.getCode());
				response.setErrorString(SBErrorMessage.AMOUNT_RANGE_ALREADY_EXIST
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}

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
	public ResponseModel updateShippingCharges(
			ShippingChargesVo shippingChargesVo) {
		response = new ResponseModel();
		try {

			String id = shippingChargesVo.getShippingChargesId();
			ShippingCharges shippingCharge = shippingChargeService
					.getShippingChargesById(shippingChargesVo
							.getShippingChargesId());
			Merchant merchat = shippingCharge.getMerchant();
			List<ShippingCharges> shippingChargeList = shippingChargeService
					.getShippingChargesList(id,
							shippingChargesVo.getAmountRange(), merchat);
			if (shippingChargeList.isEmpty()) {
				shippingCharge.setChargingAmount(shippingChargesVo
						.getChargingAmount());
				shippingCharge.setAmountRange(shippingChargesVo
						.getAmountRange());
				shippingChargeService.updateShippingCharges(shippingCharge);
				return response;
			} else {
				response.setErrorCode(SBErrorMessage.AMOUNT_RANGE_ALREADY_EXIST
						.getCode());
				response.setErrorString(SBErrorMessage.AMOUNT_RANGE_ALREADY_EXIST
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}

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
	public String getShippingCharges(MerchantVo merchantVo) {
		shippingchargesResponse = new ShippingChargesResponseVo();
		String responseStr = "";
		try {
			Merchant merchant = new Merchant();
			if (merchantVo.getStoreId() != null) {
				Store store = storeService
						.getStoreById(merchantVo.getStoreId());
				merchant = store.getMerchant();
			} else {
				merchant = merchantService.getMerchantById(merchantVo
						.getMerchantId());
			}
			List<ShippingCharges> shippingcharges = shippingChargeService
					.getShippingCharges(merchant);
			for (ShippingCharges shippingcharge : shippingcharges) {
				ShippingChargesVo shippingchargeVo = getShippingChargeService()
						.setShippingChargesVo(shippingcharge);
				shippingchargesResponse.getShippingChargesList().add(
						shippingchargeVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		try {
			responseStr = CommonUtil.getObjectMapper(shippingchargesResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseStr;
	}

	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteShippingCharges(
			ShippingChargesVo shippingChargesVo) {
		response = new ResponseModel();
		try {
			shippingChargeService.deleteShippingCharges(shippingChargesVo
					.getShippingChargesId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

}
