/**
 * 
 */
package com.mitosis.shopsbacker.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreHolidayService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.model.DeliveryTimeSlot;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.StoreHoliday;
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
	DeliveryTimeSlotService<T> deliveryTimeSlotService;

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	StoreService<T> storeService;

	@Autowired
	StoreHolidayService<T> storeHolidayService;

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
			String storeId = deliveryTimeSlotVo.getStoreId();
			Store store = storeService.getStoreById(storeId);
			Merchant merchant = merchantService.getMerchantById(merchantId);

			if (deliveryTimeSlotVo.getDeliveryTimeSlotId() != null) {
				DeliveryTimeSlot deliveryTimeSlot = deliveryTimeSlotService
						.get(deliveryTimeSlotVo.getDeliveryTimeSlotId());
				setDeliveryTimeSlot(deliveryTimeSlotVo, merchant, store,
						deliveryTimeSlot);
				deliveryTimeSlot.setUpdated(new Date());
				deliveryTimeSlot.setUpdatedby("123");
				deliveryTimeSlotService.update(deliveryTimeSlot);
			} else {
				DeliveryTimeSlot deliveryTimeSlot = (DeliveryTimeSlot) CommonUtil
						.setAuditColumnInfo(DeliveryTimeSlot.class.getName(), null);
				setDeliveryTimeSlot(deliveryTimeSlotVo, merchant, store,
						deliveryTimeSlot);
				deliveryTimeSlot.setIsactive('Y');
				deliveryTimeSlotService.save(deliveryTimeSlot);
			}

			// String storeId = deliveryTimeSlotVo.getStoreId();
			// Store store=storeService.getStoreById(storeId);
			List<StoreHoliday> storeHolidays = store.getStoreHolidays();
			if (!storeHolidays.isEmpty()) {
				StoreHoliday storeHoliday = storeHolidays.get(0);
				storeHoliday.setUpdated(new Date());
				storeHoliday.setUpdatedby("123");
				setStoreHoliday(deliveryTimeSlotVo, merchant, store,
						storeHoliday);
				storeHolidayService.update(storeHoliday);
			} else {
				StoreHoliday storeHoliday = (StoreHoliday) CommonUtil
						.setAuditColumnInfo(StoreHoliday.class.getName(), null);
				storeHoliday.setIsactive('Y');
				setStoreHoliday(deliveryTimeSlotVo, merchant, store,
						storeHoliday);
				storeHolidayService.add(storeHoliday);
			}
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(CommonUtil.getErrorMessage(e));
		}

		return response;

	}

	public void setStoreHoliday(DeliveryTimeSlotVo deliveryTimeSlotVo,
			Merchant merchant, Store store, StoreHoliday storeHoliday) {
		String holidayDate = StringUtils.join(
				deliveryTimeSlotVo.getHolidayDates(), ',');
		storeHoliday.setHolidayDate(holidayDate);
		storeHoliday.setReason(deliveryTimeSlotVo.getHolidayReasons());
		storeHoliday.setStore(store);
		if (merchant != null) {
			storeHoliday.setMerchant(merchant);
		} else {
			storeHoliday.setMerchant(store.getMerchant());
		}
	}

	@Path("/get")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getDeliveryTimeSlotRestService(
			DeliveryTimeSlotVo deliveryTimeSlotVo) {
		String responseString = "";
		DeliveryTimeSlotResponseVo response = new DeliveryTimeSlotResponseVo();
		try {
			Merchant merchant = new Merchant();
			Store store = null;
			if (deliveryTimeSlotVo.getStoreId() != null) {
				store = storeService.getStoreById(deliveryTimeSlotVo
						.getStoreId());
				merchant = store.getMerchant();
			} else if (deliveryTimeSlotVo.getMerchant().getMerchantId() != null) {
				String merchantId = deliveryTimeSlotVo.getMerchant()
						.getMerchantId();
				merchant = merchantService.getMerchantById(merchantId);
			} else {
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return CommonUtil.getObjectMapper(response);
			}
			List<DeliveryTimeSlot> deliveryTimeSlots = store
					.getDeliveryTimeSlots();
			List<DeliveryTimeSlotVo> deliveryTimeSlotVos = new ArrayList<DeliveryTimeSlotVo>();
			// for (DeliveryTimeSlot deliveryTimeSlot : deliveryTimeSlots) {
			if (!deliveryTimeSlots.isEmpty()) {
				DeliveryTimeSlot deliveryTimeSlot = deliveryTimeSlots.get(0);
				DeliveryTimeSlotVo deliveryTimeSlotvo = new DeliveryTimeSlotVo();
				deliveryTimeSlotvo.setDeliveryTimeSlotId(deliveryTimeSlot
						.getDeliveryTimeSlotId());
				deliveryTimeSlotvo.setFromTime(deliveryTimeSlot.getFromTime());
				deliveryTimeSlotvo.setToTime(deliveryTimeSlot.getToTime());

				MerchantVo merchantVo = new MerchantVo();
				merchantVo.setName(merchant.getName());
				merchantVo.setMerchantId(merchant.getMerchantId());
				deliveryTimeSlotvo.setMerchant(merchantVo);
				List<StoreHoliday> storeHolidays = store.getStoreHolidays();
				if (!storeHolidays.isEmpty()) {
					StoreHoliday storeHoliday = storeHolidays.get(0);
					String holidayDate = storeHoliday.getHolidayDate();
					List<Date> holidays = new ArrayList<Date>();
					if (!holidayDate.isEmpty()) {
						String[] holidayDates = holidayDate.split(",");
						for (String holiday : holidayDates) {
							SimpleDateFormat formatter = new SimpleDateFormat(
									"E MMM dd HH:mm:ss Z yyyy");
							Date date = formatter.parse(holiday);
							holidays.add(date);
						}
					}
					deliveryTimeSlotvo.setHolidayDates(holidays);
					deliveryTimeSlotvo.setHolidayReasons(storeHoliday
							.getReason());
				}

				deliveryTimeSlotVos.add(deliveryTimeSlotvo);
			}
			response.setDeliveryTimeSlot(deliveryTimeSlotVos);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(CommonUtil.getErrorMessage(e));
		}

		try {
			responseString = CommonUtil.getObjectMapper(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseString;

	}

	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteDeliveryTimeSlot(
			DeliveryTimeSlotVo deliveryTimeSlotVo) {
		ResponseModel response = new ResponseModel();
		try {
			log.info("\n******************************************\n"
					+ "Initializing the delete deliveryTimeSlot service");
			deliveryTimeSlotService.delete(deliveryTimeSlotVo
					.getDeliveryTimeSlotId());

			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
			response.setErrorCode("");
		}
		log.info("\n******************************************\n"
				+ "Response of the delete deliveryTimeSlot service");
		return response;
	}

	private void setDeliveryTimeSlot(DeliveryTimeSlotVo deliveryTimeSlotVo,
			Merchant merchant, Store store, DeliveryTimeSlot deliveryTimeSlot) {
		deliveryTimeSlot.setFromTime(deliveryTimeSlotVo.getFromTime());
		deliveryTimeSlot.setMerchant(merchant);
		deliveryTimeSlot.setStore(store);
		deliveryTimeSlot.setToTime(deliveryTimeSlotVo.getToTime());
	}
}
