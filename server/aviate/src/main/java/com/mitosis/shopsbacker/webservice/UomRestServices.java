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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.inventory.service.UomService;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Uom;
import com.mitosis.shopsbacker.responsevo.UomResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.UomVo;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * @author Anbukkani Gajendran
 *
 */
@Controller("uomRestServices")
@Path("uom")
public class UomRestServices<T> {
	Logger log = Logger.getLogger(UomRestServices.class);

	@Autowired
	UomService<T> uomService;

	@Autowired
	ProductService<T> productService;

	public UomService<T> getUomService() {
		return uomService;
	}

	public void setUomService(UomService<T> uomService) {
		this.uomService = uomService;
	}

	@Path("/adduom")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel addUom(UomVo uomVo) {
		ResponseModel response = new ResponseModel();
		try {
			log.info("\n******************************************\n"
					+ "Initializing the add or update uom service");

			boolean isUpdateProcess = uomVo.getUomId()!=null?true:false;
			Uom uom =null;
			if(!isUpdateProcess){
			  uom = getUomService().getUomByName(uomVo.getName());
			}else{
				 uom = getUomService().getUom(uomVo.getUomId(), uomVo.getName());
			}
			if (uom != null) {
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				response.setErrorString(SBErrorMessage.UOM_NAME_ALREADY_EXIST
						.getMessage());
				response.setErrorCode(SBErrorMessage.UOM_NAME_ALREADY_EXIST
						.getCode());
				return response;
			}
			saveUom(uomVo);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
			
		}catch (Exception e) {
			String errormMessage = CommonUtil.getErrorMessage(e);
			log.error(errormMessage);
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(errormMessage);
			response.setErrorCode("");
		}
		log.info("\n******************************************\n"
				+ "Response of the add or update uom service");
		return response;
	}

	

	@Path("getuoms")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UomResponseVo getUoms() {
		UomResponseVo response=new UomResponseVo();
		try {
			log.info("\n******************************************\n"
					+ "Initializing the get units service");
			List<Uom> listOfUom = getUomService().getAllUOM();
			List<UomVo> uoms=new ArrayList<UomVo>();
			for (Uom uom : listOfUom) {
				UomVo uomVo = setUomVo(uom);
				uoms.add(uomVo);
			}
			response.setUom(uoms);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus( SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		log.info("\n******************************************\n"
				+ "Response of the get units service");
		return response;
	}
	
	@Path("/deleteuom")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteUom(UomVo uomVo) {
		ResponseModel response = new ResponseModel();
		try {
			log.info("\n******************************************\n"
					+ "Initializing the delete uom service");
			Uom uom = uomService.getUOMById(uomVo.getUomId());
			List<Product> products=productService.getProductByUom(uom);
			if(products.size()>0){
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				response.setErrorString(SBErrorMessage.UOM_ALREDY_ASSIGNED_IN_PRODUCT.getMessage());
				response.setErrorCode(SBErrorMessage.UOM_ALREDY_ASSIGNED_IN_PRODUCT.getCode());
				return response;
			}
			uomService.removeUOM(uom);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
			response.setErrorCode("");
		}
		log.info("\n******************************************\n"
				+ "Response of the delete uom service");
		return response;
	}

	/**
	 * @author Anbukkani Gajendran
	 * @param uom
	 * @return UomVo
	 */
	private UomVo setUomVo(Uom uom) {
		UomVo uomVo = new UomVo();
		uomVo.setUomId(uom.getUomId());
		uomVo.setName(uom.getName());
		uomVo.setDescription(uom.getDescription());
		return uomVo;
	}

	/**
	 * @author Anbukkani Gajendran
	 * @param uomVo
	 * @throws Exception
	 */
	private void saveUom(UomVo uomVo) throws Exception {
		Uom productUnitOfMeasure=null;
		if(uomVo.getUomId()==null){
		 productUnitOfMeasure = (Uom) CommonUtil
				.setAuditColumnInfo(Uom.class.getName(), null);
			productUnitOfMeasure.setIsactive('Y');
		}else{
			productUnitOfMeasure=uomService.getUOMById(uomVo.getUomId());
			productUnitOfMeasure.setUpdated(new Date());
			//TODO: Need to get user from session and set as Updatedby.
			productUnitOfMeasure.setUpdatedby("1223");
		}
		productUnitOfMeasure.setDescription(uomVo.getDescription());
		productUnitOfMeasure.setName(uomVo.getName());
		if(uomVo.getUomId()==null){
		getUomService().addUOM(productUnitOfMeasure);
		}else{
			getUomService().updateUOM(productUnitOfMeasure);
		}
	}
}
