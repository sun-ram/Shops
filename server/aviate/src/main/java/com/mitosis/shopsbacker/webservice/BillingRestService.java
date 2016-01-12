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
import com.mitosis.shopsbacker.model.Billing;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.service.BillingService;
import com.mitosis.shopsbacker.responsevo.BillingResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.order.BillingVo;
import com.mitosis.shopsbacker.vo.order.TransactionDetailVo;

/**
 * @author kathir 
 *
 */
@Path("billing")
@Controller("billingRestServices")
public class BillingRestService {
	final static Logger log = Logger.getLogger(BannerRestServices.class
			.getName());
	
	@Autowired
	BillingService<T> billingService;
	
	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	StoreService<T> storeService;

	Merchant merchant = null;

	Store store = null;
	
	ResponseModel response = new ResponseModel();
	
	@Path("/getBillsByMerchant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BillingResponseVo getBillsByMerchant(Billing billing){
		
		BillingResponseVo billingResponse = new BillingResponseVo();
		try {
			List<Billing> billsList = null;
			String merchantId = billing.getMerchant().getMerchantId();
			if (billing.getMerchant() != null) {
				merchant = merchantService.getMerchantById(merchantId);
				billsList = billingService.getBillsByMerchant(merchant, billing.getIsPaid());
			} 
			for (Billing bills : billsList) {
				BillingVo billingVo = billingService.setBillingVo(bills);
				billingResponse.getBillList().add(billingVo);
			}
			billingResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			billingResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			billingResponse.setErrorString(CommonUtil.getErrorMessage(e));
		}
		return billingResponse;
	}
	
	@Path("/makePayment")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TransactionDetailVo makePayment(List<Billing> selected){
		TransactionDetailVo transactionDetail = new TransactionDetailVo();
		try{
			transactionDetail = billingService.setTransactionDetails(selected);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}		
		return transactionDetail;
  }
	
}
