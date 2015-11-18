/**
 * 
 */
package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mitosis.shopsbacker.inventory.service.UomService;
import com.mitosis.shopsbacker.model.Uom;
import com.mitosis.shopsbacker.responsevo.UomResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.UomVo;

/**
 * @author Anbukkani Gajendran
 *
 */
@Path("uom")
public class UomRestServices<T> {
	Logger log = Logger.getLogger(UomRestServices.class);

	@Autowired
	UomService<T> uomService;

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
	public ResponseModel addUnits(UomVo uomVo) {
		ResponseModel response = new ResponseModel();
		try {
			log.info("\n******************************************\n"
					+ "Initializing the add or update units service");

			/*Uom uom = getUomService().getUomByName(uomVo.getName());
			if (uom != null) {
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				response.setErrorString(SBErrorMessage.UOM_NAME_ALREADY_EXIST
						.getMessage());
				response.setErrorCode(SBErrorMessage.UOM_NAME_ALREADY_EXIST
						.getCode());
				return response;
			}*/
			setUom(uomVo);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
			response.setErrorCode("");
		}
		log.info("\n******************************************\n"
				+ "Response of the add or update units service");
		return response;
	}

	@Path("getuoms")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UomResponseVo getUnits() {
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
	private void setUom(UomVo uomVo) throws Exception {
		Uom productUnitOfMeasure = (Uom) CommonUtil
				.setAuditColumnInfo(Uom.class.getName());
		productUnitOfMeasure.setDescription(uomVo.getDescription());
		productUnitOfMeasure.setName(uomVo.getName());
		productUnitOfMeasure.setIsactive('Y');
		getUomService().addUOM(productUnitOfMeasure);
	}
}
