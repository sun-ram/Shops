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
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.inventory.service.MovementLineService;
import com.mitosis.shopsbacker.inventory.service.MovementService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.inventory.service.WarehouseService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.MovementLineVo;
import com.mitosis.shopsbacker.vo.inventory.MovementVo;
import com.mitosis.shopsbacker.vo.inventory.StoragebinVo;

/**
 * @author prabakaran
 *
 */
@Path("movement")
@Controller("movementRestService")
public class MovementRestService<T> {

	final static Logger log = Logger.getLogger(MovementRestService.class
			.getName());

	@Autowired
	MovementService<T> movementService;

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	WarehouseService<T> warehouseService;

	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	ProductService<T> productService;
	
	@Autowired
	MovementLineService<T> movementLineService;
	
	@Autowired
	com.mitosis.shopsbacker.inventory.service.StoragebinService<T> storeagebinService;

	

	@Path("/save")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel saveMovement(MovementVo movementVo) {
		ResponseModel response = new ResponseModel();
		try {
			Movement movement = (Movement) CommonUtil
					.setAuditColumnInfo(Movement.class.getName());
			Date date = new Date();
			movement.setName(CommonUtil.dateToString(date));
			movement.setIsmoved('N');
			movement.setIsupdated('N');
			movement.setIsactive('Y');
			Merchant merchant = merchantService.getMerchantById(movementVo
					.getMerchant().getMerchantId());
			Warehouse warehouse = warehouseService.getWarehouse(movementVo
					.getWarehouse().getWarehouseId());
			Store store = storeService.getStoreById(movementVo.getStore()
					.getStoreId());
			movement.setMerchant(merchant);
			movement.setStore(store);
			movement.setWarehouse(warehouse);

			List<MovementLine> movementLines = new ArrayList<MovementLine>();
			for (MovementLineVo movementLineVo : movementVo.getMovementLines()){
				MovementLine movementLine = (MovementLine) CommonUtil
						.setAuditColumnInfo(MovementLine.class.getName());
				movementLine.setIsactive('Y');
				Storagebin storagebin = storeagebinService.getStoragebinById(movementLineVo.getToStoragebin().getStoragebinId());
				movementLine.setStoragebinByToBinId(storagebin);
				movementLine.setProduct(productService.getProduct(movementLineVo.getProduct().getProductId()));;
				movementLine.setQty(movementLineVo.getQty());
				movementLine.setStoragebinByToBinId(storeagebinService.getStoragebinById(movementLineVo.getToStoragebin().getStoragebinId()));
				
				movementLines.add(movementLine);
			}
				movement.setMovementLines(movementLines);
				movementService.addMovement(movement);
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}

	@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel updateMovement(MovementVo movementVo) {
		ResponseModel response = new ResponseModel();
		try {
			Movement movement = movementService.getMovement(movementVo
					.getMovementId());
			/*
			 * Warehouse warehouse = warehouseService.getWarehouse(movementVo
			 * .getWarehouse().getWarehouseId()); Store store =
			 * storeService.getStoreById(movementVo.getStore() .getStoreId());
			 */
			/*
			 * movement.setMerchant(merchant); movement.setStore(store);
			 * movement.setWarehouse(warehouse);
			 */

			movementService.updateMovement(movement);
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}

	@Path("/getmovement")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel movementMovement(MovementVo movementVo) {
		ResponseModel response = new ResponseModel();
		try {

			List<Movement> movement = movementService
					.getMovementListByStore(storeService
							.getStoreById(movementVo.getStore().getStoreId()));
			/*
			 * Warehouse warehouse = warehouseService.getWarehouse(movementVo
			 * .getWarehouse().getWarehouseId()); Store store =
			 * storeService.getStoreById(movementVo.getStore() .getStoreId());
			 */
			/*
			 * movement.setMerchant(merchant); movement.setStore(store);
			 * movement.setWarehouse(warehouse);
			 */

			// movementService.updateMovement(movement);
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}

	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel deleteMovement(MovementVo movementVo) {
		ResponseModel response = new ResponseModel();
		try {
			if (false) {
				// TODO : need to check the movement in processed
				return response;
			}

			movementService.deleteMovement(movementVo.getMovementId());
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}
	
	@Path("/process")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel conformMovement(JSONObject inventoryId){
		ResponseModel response = new ResponseModel();
		try{
			Movement movement = movementService.getMovement(inventoryId.getString("movementId"));
			movement.setIsmoved('Y');
			movementService.updateMovement(movement);
		}catch(Exception e){
			log.error(e.getMessage());	
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;		
	}
	
	@Path("/updatemovements")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel updateMovement(JSONObject inventory){
		ResponseModel response = new ResponseModel();
		try{
			Movement movement = movementService.getMovement(inventory.getString("movementId"));
			movement.setIsmoved('Y');
			movementService.updateMovement(movement);
		}catch(Exception e){
			log.error(e.getMessage());	
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;		
	}
	
	@Path("/save/line")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel saveMovementLine(MovementLineVo movementLineVo) {
		ResponseModel response = new ResponseModel();
		try {
				MovementLine movementLine = (MovementLine) CommonUtil
						.setAuditColumnInfo(MovementLine.class.getName());
				movementLine.setIsactive('Y');
				Storagebin storagebin = storeagebinService.getStoragebinById(movementLineVo.getFromStoragebin().getStoragebinId());
				movementLine.setStoragebinByToBinId(storagebin);
				movementLine.setProduct(productService.getProduct(movementLineVo.getProduct().getProductId()));;
				movementLine.setQty(movementLineVo.getQty());
				movementLine.setStoragebinByToBinId(storeagebinService.getStoragebinById(movementLineVo.getToStoragebin().getStoragebinId()));
				movementLineService.addMovementLine(movementLine);
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}
	
	@Path("/update/line")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel updateMovementLine(MovementLineVo movementLineVo) {
		ResponseModel response = new ResponseModel();
		try {
				MovementLine movementLine = movementLineService.getMovementLine(movementLineVo.getMovementLineId());
				movementLine.setUpdated(new Date());
				//TODO: need to set value from session
				movementLine.setUpdatedby("123456");
				Storagebin storagebin = storeagebinService.getStoragebinById(movementLineVo.getFromStoragebin().getStoragebinId());
				movementLine.setStoragebinByToBinId(storagebin);
				movementLine.setProduct(productService.getProduct(movementLineVo.getProduct().getProductId()));;
				movementLine.setQty(movementLineVo.getQty());
				movementLine.setStoragebinByToBinId(storeagebinService.getStoragebinById(movementLineVo.getToStoragebin().getStoragebinId()));
				movementLineService.addMovementLine(movementLine);
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}
	
	@Path("/delete/line")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel deleteMovementLine(MovementLineVo movementLineVo) {
		ResponseModel response = new ResponseModel();
		try {
				MovementLine movementLine = movementLineService.getMovementLine(movementLineVo.getMovementLineId());
				movementLineService.removeMovementLine(movementLine);
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}
	

}
