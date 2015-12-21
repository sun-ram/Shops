package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.inventory.service.MovementLineService;
import com.mitosis.shopsbacker.inventory.service.MovementService;
import com.mitosis.shopsbacker.inventory.service.ProductInventoryService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.inventory.service.StoragebinService;
import com.mitosis.shopsbacker.inventory.service.WarehouseService;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.responsevo.MovementLineResponseVo;
import com.mitosis.shopsbacker.responsevo.MovementResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.MovementLineVo;
import com.mitosis.shopsbacker.vo.inventory.MovementVo;

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
	StoragebinService<T> storeagebinService;
	
	@Autowired
	ProductInventoryService<T> productInventoryService;

	@Path("/save")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MovementResponseVo saveMovement(MovementVo movementVo) {
		MovementResponseVo response = new MovementResponseVo();
		try {
			boolean isUpdateProcess = movementVo.getMovementId() != null ? true
					: false;
			Movement movement = movementService.setMovement(movementVo, isUpdateProcess);
			movement.setIsMovement('Y');
			if (!isUpdateProcess) {
				movementService.addMovement(movement);
			} else {
				movementService.updateMovement(movement);
			}
			MovementVo movementvo = movementService.setMovementVo(movement);
			response.setMovement(movementvo);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}

	

	/*@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseModel updateMovement(MovementVo movementVo) {
		ResponseModel response = new ResponseModel();
		try {
			Movement movement = movementService.getMovement(movementVo
					.getMovementId());
			
			 * Warehouse warehouse = warehouseService.getWarehouse(movementVo
			 * .getWarehouse().getWarehouseId()); Store store =
			 * storeService.getStoreById(movementVo.getStore() .getStoreId());
			 
			
			 * movement.setMerchant(merchant); movement.setStore(store);
			 * movement.setWarehouse(warehouse);
			 

			movementService.updateMovement(movement);
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}*/

	@Path("/getmovement")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MovementResponseVo getMovements(MovementVo movementVo) {
		MovementResponseVo response = new MovementResponseVo();
		try {
			Store store =storeService.getStoreById(movementVo.getStore().getStoreId());
			List<Movement> movements = movementService.getMovementListByStore(store); 
			List<MovementVo> movementVos = new ArrayList<MovementVo>();
			for (Movement movement : movements) {
				MovementVo movementvo = movementService.setMovementVo(movement);
				movementVos.add(movementvo);
			}
			response.setMovements(movementVos);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteMovement(MovementVo movementVo) {
		ResponseModel response = new ResponseModel();
		try {
			movementService.deleteMovement(movementVo.getMovementId());
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
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
	public ResponseModel conformMovement(JSONObject inventoryId) {
		ResponseModel response = new ResponseModel();
		try {
			movementService.processMovement(inventoryId
					.getString("movementId"));
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}

	@Path("/movement/{movementId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getMovement(@PathParam("movementId") String movementId) {
		String responseStr = "";
		MovementResponseVo response = new MovementResponseVo();
		try {
			Movement movement = movementService.getMovement(movementId);
			MovementVo movementvo = movementService.setMovementVo(movement);
			response.setMovement(movementvo);
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		try {
			responseStr = CommonUtil.getObjectMapper(response);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return responseStr;
	}

	@Path("/save/line")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MovementLineResponseVo saveMovementLine(MovementLineVo movementLineVo) {
		MovementLineResponseVo response = new MovementLineResponseVo();
		try {
			
			
			boolean isUpdateProcess = movementLineVo.getMovementLineId() != null ? true
					: false;
			MovementLine movementLine = movementService.setMovementLine(movementLineVo, isUpdateProcess);
			Movement movement = movementService.getMovement(movementLineVo.getMovementId());
			movementLine.setMovement(movement);
			if (!isUpdateProcess) {
				movementLineService.addMovementLine(movementLine);
			} else {
				movementLineService.updateMovementLine(movementLine);
			}
			
			
			/*Storagebin storagebin = storeagebinService
					.getStoragebinById(movementLineVo.getToStoragebin()
							.getStoragebinId());
			movementLine.setStoragebinByToBinId(storagebin);
			movementLine.setProduct(productService.getProduct(movementLineVo
					.getProduct().getProductId()));
			movementLine.setQty(movementLineVo.getQty());
			*/
			
			response.setMovementLine(movementService.setMovementLineVo(movementLine));
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}

	/*@Path("/update/line")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseModel updateMovementLine(MovementLineVo movementLineVo) {
		ResponseModel response = new ResponseModel();
		try {
			MovementLine movementLine = movementLineService
					.getMovementLine(movementLineVo.getMovementLineId());
			movementLine.setUpdated(new Date());
			// TODO: need to set value from session
			movementLine.setUpdatedby("123456");
			Storagebin storagebin = storeagebinService
					.getStoragebinById(movementLineVo.getToStoragebin()
							.getStoragebinId());
			movementLine.setStoragebinByToBinId(storagebin);
			movementLine.setProduct(productService.getProduct(movementLineVo
					.getProduct().getProductId()));
			;
			movementLine.setQty(movementLineVo.getQty());
			movementLine.setStoragebinByToBinId(storeagebinService
					.getStoragebinById(movementLineVo.getToStoragebin()
							.getStoragebinId()));
			movementLineService.addMovementLine(movementLine);
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}*/

	@Path("/delete/line")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteMovementLine(MovementLineVo movementLineVo) {
		ResponseModel response = new ResponseModel();
		try {
			movementLineService.removeMovementLine(movementLineVo.getMovementLineId());
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}

}
