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
import com.mitosis.shopsbacker.admin.service.TaxService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Tax;
import com.mitosis.shopsbacker.responsevo.TaxResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.TaxVo;

/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
@Path("tax")
@Controller("taxRestService")
public class TaxRestService<T> {
	final static Logger log = Logger.getLogger(Tax.class
			.getName());
	
	@Autowired
	TaxService<T> taxService;
	
	@Autowired
	MerchantService<T> merchantService;
	
	public TaxService<T> getTaxService() {
		return taxService;
	}

	public void setTaxService(TaxService<T> taxService) {
		this.taxService = taxService;
	}
	
	
	public MerchantService<T> getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService<T> merchantService) {
		this.merchantService = merchantService;
	}


	ResponseModel response = null;
	TaxResponseVo taxResponse = null;
	
	@Path("/addtax")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel addTax(TaxVo taxVo) {
		response = new ResponseModel();
		try {
			String texId = null;
			String name = taxVo.getName();
			Merchant merchat = merchantService.getMerchantById(taxVo.getMerchantVo().getMerchantId());
			List<Tax> taxList = taxService.getTaxListByName(texId,name,merchat);
			if(taxList.isEmpty()){
				Tax tax = getTaxService().setTax(taxVo);
				taxService.addTax(tax);
				return response;
			}else{
				response.setErrorCode(SBErrorMessage.TAX_NAME_ALREADY_EXIST
						.getCode());
				response.setErrorString(SBErrorMessage.TAX_NAME_ALREADY_EXIST
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

	@Path("/updatetax")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel updateTax(TaxVo taxVo) {
		response = new ResponseModel();
		try {
			String texId = taxVo.getTaxId();
			String name = taxVo.getName();
			Tax tax = taxService.getTaxById(taxVo.getTaxId());
			Merchant merchat = tax.getMerchant();
			List<Tax> taxList = taxService.getTaxListByName(texId,name,merchat);
			if(taxList.isEmpty()){
				tax.setTaxPercentage(taxVo.getTaxPercentage());
				tax.setName(taxVo.getName());
				taxService.updateTax(tax);
				return response;
			}else{
				response.setErrorCode(SBErrorMessage.TAX_NAME_ALREADY_EXIST
						.getCode());
				response.setErrorString(SBErrorMessage.TAX_NAME_ALREADY_EXIST
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}
	
	
	@Path("/gettax")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TaxResponseVo getTax(MerchantVo merchantVo) {
		taxResponse = new TaxResponseVo();
		response = new ResponseModel();
		try {
 			Merchant merchant = merchantService.getMerchantById(merchantVo.getMerchantId());
			List<Tax> taxList = taxService.getTax(merchant);
			for (Tax tax : taxList) {
				TaxVo taxVo = getTaxService().setTaxVo(tax);
				taxResponse.getTaxList().add(taxVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return taxResponse;
	}
	
	@Path("/deletetax")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteTax(TaxVo taxVo) {
		response = new ResponseModel();
		try {
			Tax tax = taxService.getTaxById(taxVo.getTaxId());
			taxService.removeTax(tax);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}
	
}
