package com.mitosis.shopsbacker.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mitosis.shopsbacker.admin.service.TaxService;
import com.mitosis.shopsbacker.model.Tax;
import com.mitosis.shopsbacker.responsevo.TaxResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.TaxVo;

/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
@Path("tax")
public class TaxRestService<T> {
	final static Logger log = Logger.getLogger(Tax.class
			.getName());
	
	@Autowired
	TaxService<T> taxService;

	public TaxService<T> getTaxService() {
		return taxService;
	}

	public void setTaxService(TaxService<T> taxService) {
		this.taxService = taxService;
	}
	
	ResponseModel response = new ResponseModel();
	
	@Path("/addtax")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addTax(TaxVo taxVo) {
		try {
			Tax tax = setTax(taxVo);
			taxService.addTax(tax);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	public Tax setTax(TaxVo taxVo) throws Exception {
		Tax tax = (Tax) CommonUtil
				.setAuditColumnInfo(Tax.class.getName());
		tax.setName(taxVo.getName());
		tax.setMerchant(taxVo.getMerchant());
		tax.setTaxPercentage(taxVo.getTaxPercentage());
		tax.setIsactive('Y');
		return tax;
	}
	
	@Path("/updatetax")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel updateTax(TaxVo taxVo) {
		try {
			Tax tax = taxService.getTaxById(taxVo.getTaxId());
			tax.setTaxPercentage(taxVo.getTaxPercentage());
			tax.setName(taxVo.getName());
			taxService.updateTax(tax);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}
	
	public TaxVo setTaxVo (Tax tax) {
		TaxVo taxVo = new TaxVo();
		taxVo.setTaxId(tax.getTaxId());
		taxVo.setName(tax.getName());
		taxVo.setTaxPercentage(tax.getTaxPercentage());
		return taxVo;
	}
	
	@Path("/gettax")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TaxResponseVo getTax(TaxVo taxsVo) {
		TaxResponseVo taxResponse = new TaxResponseVo();
		try {
			List<Tax> taxList = taxService.getTax(taxsVo.getMerchant());
			for (Tax tax : taxList) {
				TaxVo taxVo = setTaxVo(tax);
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
	public ResponseModel deleteTax(TaxVo taxVo) {
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
