package com.mitosis.shopsbacker.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.inventory.service.MovementLineService;
import com.mitosis.shopsbacker.inventory.service.MovementService;
import com.mitosis.shopsbacker.inventory.service.PhysicalInventoryService;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;
import com.mitosis.shopsbacker.responsevo.MovementLineResponseVo;
import com.mitosis.shopsbacker.responsevo.MovementResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.MovementLineVo;
import com.mitosis.shopsbacker.vo.inventory.MovementVo;

@Path("inventory")
@Controller("physicalInventoryRestService")
public class PhysicalInventoryRestService {
	
	Logger log = Logger.getLogger(PhysicalInventoryRestService.class);
	
	@Autowired
	PhysicalInventoryService<T> physicalInventoryService;
	
	@Autowired 
	MovementService<T> movementService;
	
	@Autowired
	MovementLineService<T> movementLineService;
	
	@Path("/save")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MovementResponseVo saveInventory(MovementVo movementVo) {
		MovementResponseVo response = new MovementResponseVo();
		try {
			boolean isUpdateProcess = movementVo.getMovementId() != null ? true
					: false;
			Movement movement = movementService.setMovement(movementVo, isUpdateProcess);
			movement.setIsMovement('N');
			movementService.addMovement(movement);
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
	
	@Path("/save/Inventoryline")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MovementLineResponseVo saveMovementLine(MovementLineVo movementLineVo) {
		MovementLineResponseVo response = new MovementLineResponseVo();
		try {
			String userId=movementLineVo.getUserId();
			boolean isUpdateProcess = movementLineVo.getMovementLineId() != null ? true
					: false;
			MovementLine movementLine = movementService.setMovementLine(movementLineVo, isUpdateProcess, userId);
			Movement movement = movementService.getMovement(movementLineVo.getMovementId());
			movementLine.setMovement(movement);
			if (!isUpdateProcess) {
				movementLineService.addMovementLine(movementLine);
			} else {
				movementLineService.updateMovementLine(movementLine);
			}
			response.setMovementLine(movementService.setMovementLineVo(movementLine));
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}
	
	@Path("/delete/{movementId}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteInventory(@PathParam("movementId") String movementId) {
		ResponseModel response = new ResponseModel();
		try {
			movementService.deleteMovement(movementId);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}
	
	@Path("/delete/inventoryline/{movementLineId}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteMovementLine(@PathParam("movementLineId") String movementLineId) {
		ResponseModel response = new ResponseModel();
		try {
			movementLineService.removeMovementLine(movementLineId);
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}
	
	@Path("/getinventories/{storeId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getPoductStock(@PathParam("storeId") String storeId) {
		String responseStr = "";
		MovementResponseVo response = new MovementResponseVo();
		try {
			response = physicalInventoryService.getInventory(storeId);
		} catch (Exception e) {
			e.printStackTrace();
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
	
	@Path("/inventory/{movementId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getInventory(@PathParam("movementId") String movementId) {
		String responseStr = "";
		MovementResponseVo response = new MovementResponseVo();
		try {
			response = physicalInventoryService.getInventoryById(movementId);
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
	
	@Path("/isupdate/{movementId}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel updatePhysicalInventory(@PathParam("movementId") String movementId) {
		ResponseModel response = new ResponseModel();
		try {
			physicalInventoryService.updatePhysicalInventory(movementId);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}
	

}
