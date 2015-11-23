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
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.inventory.service.WarehouseService;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;
import com.mitosis.shopsbacker.responsevo.WarehouseResponseVo;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.common.AddressVo;
import com.mitosis.shopsbacker.vo.inventory.StoragebinVo;
import com.mitosis.shopsbacker.vo.inventory.WarehouseVo;

/**
 * @author Anbukkani Gajendran
 *
 */
@Controller("warehouseRestService")
@Path("warehouse")
public class WarehouseRestService {
	Logger log = Logger.getLogger(WarehouseRestService.class);
	@Autowired
	WarehouseService<T> warehouseService;

	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	AddressService<T> addressService;

	@Path("/addwarehouse")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public WarehouseResponseVo addWarehouse(WarehouseVo warehouseVo) {
		WarehouseResponseVo warehouseResponseVo = new WarehouseResponseVo();
		try {
			Store store = storeService.getStoreById(warehouseVo.getStore()
					.getStoreId());
			List<Warehouse> warehouseList = null;
			boolean isUpdateProcess=warehouseVo.getWarehouseId() != null?true:false;
			if(!isUpdateProcess){
			 warehouseList = warehouseService.getWarehouse(
					warehouseVo.getName(), store);
			}else{
				 warehouseList = warehouseService.getWarehouse(warehouseVo.getWarehouseId(),
							warehouseVo.getName(), store);
			}
			
			if (warehouseList.size() > 0) {
				warehouseResponseVo.setStatus(SBMessageStatus.FAILURE
						.getValue());
				warehouseResponseVo.setErrorCode("");
				warehouseResponseVo
						.setErrorString("Warehouse Already available in this name");
				return warehouseResponseVo;
			}
			
			Warehouse warehouse = warehouseService.setWarehouse(warehouseVo, store,
					isUpdateProcess);
			if (isUpdateProcess) {
				warehouseService.updateWarehouse(warehouse);
			}else{
				warehouseService.addWarehouse(warehouse);
			}
			warehouseResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			warehouseResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
			warehouseResponseVo.setErrorString(e.getMessage());
		}
		return warehouseResponseVo;
	}
	
	@Path("/warehouselist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public WarehouseResponseVo warehouseList(WarehouseVo warehouseVo){
		WarehouseResponseVo response=new WarehouseResponseVo();
		//List<WarehouseVo> listOfWarehouses=new ArrayList<WarehouseVo>();
		try{
			String storeId=warehouseVo.getStore().getStoreId();	
			Store store=storeService.getStoreById(storeId);
			List<Warehouse> listOfWarehouses = store.getWarehouses();
			List<WarehouseVo> listOFwarehouseVo= new ArrayList<WarehouseVo>();
		//	List<Warehouse> listOfWarehouses = warehouseService.getWarehouse(store);
			for(Warehouse warehouse:listOfWarehouses){
				WarehouseVo warehouseVoObj = setWarehouseVO(warehouse);
				  List<Storagebin> storagebins = warehouse.getStoragebins();
				List<StoragebinVo>  listOfstoragebinVo = new ArrayList<StoragebinVo>();
				for(Storagebin storagebin:storagebins){
					StoragebinVo storagebinVo = storeService.setStoragebinVO(storagebin);
					listOfstoragebinVo.add(storagebinVo);
				}
				warehouseVoObj.setStoragebins(listOfstoragebinVo);
				listOFwarehouseVo.add(warehouseVoObj);
			}
			response.setWarehouses(listOFwarehouseVo);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
			
		}catch(Exception e){
			log.error(e.getMessage());	
			response.setStatus(SBMessageStatus.FAILURE.getValue());	
			response.setErrorString(e.getMessage());
		}
		return response;	
	}

	@Path("/removewarehouse")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED)
	public WarehouseResponseVo deleteWarehouse(WarehouseVo warehouseVo) {
		WarehouseResponseVo warehouseResponseVo = new WarehouseResponseVo();
		try {
			warehouseService.deleteWarehouse(warehouseVo.getWarehouseId());
			warehouseResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			warehouseResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
			warehouseResponseVo.setErrorString(e.getMessage());
		}
		return warehouseResponseVo;
	}
	
	
	/**
	 * @author Anbukkani Gajendran
	 * @param warehouse
	 * @return WarehouseVo
	 */
	
	public WarehouseVo setWarehouseVO(Warehouse warehouse) {
		WarehouseVo warehouseVoObj=new WarehouseVo();
		warehouseVoObj.setName(warehouse.getName());
		warehouseVoObj.setDescription(warehouse.getDescription());
		warehouseVoObj.setWarehouseId(warehouse.getWarehouseId());
		AddressVo addressVo = addressService.setAddressVo(warehouse.getAddress());
		warehouseVoObj.setAddress(addressVo);
		return warehouseVoObj;
	}
}
