package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.Date;
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
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;
import com.mitosis.shopsbacker.responsevo.MovementLineResponseVo;
import com.mitosis.shopsbacker.responsevo.MovementResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.MovementLineVo;
import com.mitosis.shopsbacker.vo.inventory.MovementVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;
import com.mitosis.shopsbacker.vo.inventory.StoragebinVo;
import com.mitosis.shopsbacker.vo.inventory.WarehouseVo;

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
			Movement movement = setMovement(movementVo, isUpdateProcess);

			if (!isUpdateProcess) {
				movementService.addMovement(movement);
			} else {
				movementService.updateMovement(movement);
			}
			MovementVo movementvo = setMovementVo(movement);
			response.setMovement(movementvo);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}

	/**
	 * @author Anbukkani Gajendran
	 * @param movement
	 * @return MovementVo
	 */
	private MovementVo setMovementVo(Movement movement) {
		MovementVo movementvo = new MovementVo();
		movementvo.setMovementId(movement.getMovementId());
		movementvo.setIsmoved(movement.getIsmoved());
		movementvo.setIsupdated(movement.getIsmoved());
		movementvo.setName(movement.getName());
		WarehouseVo warehouseVo = new WarehouseVo();
		Warehouse warehouse = movement.getWarehouse();
		warehouseVo.setName(warehouse.getName());
		warehouseVo.setWarehouseId(movement.getWarehouse().getWarehouseId());
		
		List<Storagebin> bins = new ArrayList<Storagebin>();
		List<StoragebinVo> binsVo = new ArrayList<StoragebinVo>();
		bins = warehouse.getStoragebins();
		for(Storagebin bin : bins){
			StoragebinVo binVo = new StoragebinVo();
			binVo.setStoragebinId(bin.getStoragebinId());
			binVo.setName(bin.getName());
			binVo.setLevel(bin.getLevel());
			binVo.setRow(bin.getRow());
			binVo.setStack(bin.getStack());
			binsVo.add(binVo);
		}
		warehouseVo.setStoragebins(binsVo);
		movementvo.setWarehouse(warehouseVo);
		List<MovementLine> movementLines = movement.getMovementLines();
		List<MovementLineVo> movementLineVos = new ArrayList<MovementLineVo>();
		for (MovementLine movementLine : movementLines) {
			MovementLineVo movementLineVo = setMovementLineVo(movementLine);
			movementLineVos.add(movementLineVo);
		}
		movementvo.setMovementLines(movementLineVos);
		return movementvo;
	}

	/**
	 * @author Anbukkani Gajendran
	 * @param movementLine
	 * @return MovementLineVo
	 */
	private MovementLineVo setMovementLineVo(MovementLine movementLine) {
		MovementLineVo movementLineVo = new MovementLineVo();
		movementLineVo.setMovementLineId(movementLine.getMovementLineId());
		movementLineVo.setQty(movementLine.getQty());
		ProductVo productVo = new ProductVo();
		productVo.setName(movementLine.getProduct().getName());
		productVo.setProductId(movementLine.getProduct().getProductId());
		movementLineVo.setProduct(productVo);
		StoragebinVo storagebinVo = new StoragebinVo();
		storagebinVo.setName(movementLine.getStoragebinByToBinId().getName());
		storagebinVo.setStoragebinId(movementLine.getStoragebinByToBinId()
				.getStoragebinId());
		storagebinVo.setLevel(movementLine.getStoragebinByToBinId().getLevel());
		storagebinVo.setStack(movementLine.getStoragebinByToBinId().getStack());
		storagebinVo.setRow(movementLine.getStoragebinByToBinId().getRow());
		movementLineVo.setToStoragebin(storagebinVo);
		return movementLineVo;
	}

	/**
	 * @author Anbukkai Gajendran
	 * @param movementVo
	 * @param isUpdateProcess
	 * @return Movement
	 * @throws Exception
	 */
	private Movement setMovement(MovementVo movementVo, boolean isUpdateProcess)
			throws Exception {
		Movement movement = null;
		if (!isUpdateProcess) {
			movement = (Movement) CommonUtil.setAuditColumnInfo(Movement.class
					.getName());
			movement.setIsactive('Y');
		} else {
			movement = movementService.getMovement(movementVo.getMovementId());
			movement.setUpdated(new Date());
			// TODO: Need to get user from session and set to updated by.
			movement.setUpdatedby("123");
		}
		Date date = new Date();
		movement.setName(CommonUtil.dateToString(date));
		movement.setIsmoved('N');
		movement.setIsupdated('N');
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
		for (MovementLineVo movementLineVo : movementVo.getMovementLines()) {

			boolean isUpdate = movementLineVo.getMovementLineId() != null ? true
					: false;
			MovementLine movementLine = setMovementLine(movementLineVo,
					isUpdate);
			movementLine.setMovement(movement);
			movementLines.add(movementLine);
		}
		movement.setMovementLines(movementLines);
		return movement;
	}

	/**
	 * @author Anbukkani Gajendran
	 * @param movementLineVo
	 * @param isUpdate
	 * @return MovementLine
	 * @throws Exception
	 */
	private MovementLine setMovementLine(MovementLineVo movementLineVo,
			boolean isUpdate) throws Exception {
		MovementLine movementLine = null;
		if (!isUpdate) {
			movementLine = (MovementLine) CommonUtil
					.setAuditColumnInfo(MovementLine.class.getName());
			movementLine.setIsactive('Y');
		} else {
			movementLine = movementLineService.getMovementLine(movementLineVo
					.getMovementLineId());
			movementLine.setCreated(new Date());
			// TODO: Need to get user from session and set to updated by.
			movementLine.setCreatedby("123");
		}

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
		return movementLine;
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
				MovementVo movementvo = setMovementVo(movement);
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

	@Path("/updatemovements")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel updateMovement(JSONObject inventory) {
		ResponseModel response = new ResponseModel();
		try {
			Movement movement = movementService.getMovement(inventory
					.getString("movementId"));
			movement.setIsupdated('Y');
			movementService.updateMovement(movement);
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
			MovementVo movementvo = setMovementVo(movement);
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
			MovementLine movementLine = setMovementLine(movementLineVo, isUpdateProcess);
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
			
			response.setMovementLine(setMovementLineVo(movementLine));
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
			MovementLine movementLine = movementLineService
					.getMovementLine(movementLineVo.getMovementLineId());
			movementLineService.removeMovementLine(movementLine);
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}

}
