/**
 * 
 */
package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.Date;
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
import com.mitosis.shopsbacker.model.DeliveryTimeSlot;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.order.service.DeliveryTimeSlotService;
import com.mitosis.shopsbacker.responsevo.DeliveryTimeSlotResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.order.DeliveryTimeSlotVo;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Controller("deliveryTimeSlotRestService")
@Path("deliverytimeslot")
public class DeliveryTimeSlotRestService {
	Logger log = Logger.getLogger(DeliveryTimeSlotRestService.class);

	@Autowired
	DeliveryTimeSlotService deliveryTimeSlotService;

	@Autowired
	MerchantService<T> merchantService;

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel addDeliveryTimeSlotRestService(
			DeliveryTimeSlotVo deliveryTimeSlotVo) {
		ResponseModel response = new ResponseModel();
		try {
			String merchantId = deliveryTimeSlotVo.getMerchant()
					.getMerchantId();
			Merchant merchant = merchantService.getMerchantById(merchantId);

			if (deliveryTimeSlotVo.getDeliveryTimeSlotId() != null) {
				DeliveryTimeSlot deliveryTimeSlot = deliveryTimeSlotService
						.get(deliveryTimeSlotVo.getDeliveryTimeSlotId());
				setDeliveryTimeSlot(deliveryTimeSlotVo, merchant,
						deliveryTimeSlot);
				deliveryTimeSlot.setUpdated(new Date());
				deliveryTimeSlot.setUpdatedby("123");
				deliveryTimeSlotService.update(deliveryTimeSlot);
			} else {
				DeliveryTimeSlot deliveryTimeSlot = (DeliveryTimeSlot) CommonUtil
						.setAuditColumnInfo(DeliveryTimeSlot.class.getName());
				setDeliveryTimeSlot(deliveryTimeSlotVo, merchant,
						deliveryTimeSlot);
				deliveryTimeSlot.setIsactive('Y');
				deliveryTimeSlotService.save(deliveryTimeSlot);
				response.setStatus(SBMessageStatus.SUCCESS.getValue());
			}
		} catch (Exception e) {
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(CommonUtil.getErrorMessage(e));
		}

		return response;

	}

	
	@Path("/get")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DeliveryTimeSlotResponseVo getDeliveryTimeSlotRestService(
			DeliveryTimeSlotVo deliveryTimeSlotVo) {
		DeliveryTimeSlotResponseVo response = new DeliveryTimeSlotResponseVo();
		try {
			String merchantId = deliveryTimeSlotVo.getMerchant()
					.getMerchantId();
			Merchant merchant = merchantService.getMerchantById(merchantId);
		List<DeliveryTimeSlot>	deliveryTimeSlots=merchant.getDeliveryTimeSlots();
		List<DeliveryTimeSlotVo>	deliveryTimeSlotVos= new ArrayList<DeliveryTimeSlotVo>();
		for(DeliveryTimeSlot deliveryTimeSlot:deliveryTimeSlots){
			DeliveryTimeSlotVo	deliveryTimeSlotvo= new DeliveryTimeSlotVo();
			deliveryTimeSlotvo.setDeliveryTimeSlotId(deliveryTimeSlot.getDeliveryTimeSlotId());
			deliveryTimeSlotvo.setFromTime(deliveryTimeSlot.getFromTime());
			deliveryTimeSlotvo.setToTime(deliveryTimeSlot.getToTime());
			
			MerchantVo merchantVo=new MerchantVo();
			merchantVo.setName(merchant.getName());
			merchantVo.setMerchantId(merchant.getMerchantId());
			deliveryTimeSlotvo.setMerchant(merchantVo);
			deliveryTimeSlotVos.add(deliveryTimeSlotvo);
		}
		response.setDeliveryTimeSlot(deliveryTimeSlotVos);
		response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(CommonUtil.getErrorMessage(e));
		}

		return response;

	}
	
	private void setDeliveryTimeSlot(DeliveryTimeSlotVo deliveryTimeSlotVo,
			Merchant merchant, DeliveryTimeSlot deliveryTimeSlot) {
		deliveryTimeSlot.setFromTime(deliveryTimeSlotVo.getFromTime());
		deliveryTimeSlot.setMerchant(merchant);
		deliveryTimeSlot.setToTime(deliveryTimeSlotVo.getToTime());
	}
}
