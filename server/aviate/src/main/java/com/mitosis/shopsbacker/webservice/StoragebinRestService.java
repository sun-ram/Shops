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
import com.mitosis.shopsbacker.inventory.service.StoragebinService;
import com.mitosis.shopsbacker.inventory.service.WarehouseService;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;
import com.mitosis.shopsbacker.responsevo.StoragebinResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.inventory.StoragebinVo;
import com.mitosis.shopsbacker.vo.inventory.WarehouseVo;

/**
 * @author Anbukkani Gajendran
 *
 */
@Controller("storagebinRestService")
@Path("storagebin")
public class StoragebinRestService {
	Logger log = Logger.getLogger(StoragebinRestService.class);
	@Autowired
	StoragebinService<T> storagebinService;

	@Autowired
	StoreService<T> storeService;

	@Autowired
	WarehouseService<T> warehouseService;

	@Path("/addstoragebin")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public StoragebinResponseVo addStorageBin(StoragebinVo storagebinVo) {
		StoragebinResponseVo response = new StoragebinResponseVo();
		try {
			boolean isUpdate=storagebinVo.getStoragebinId()!=null?true:false;
			
			Warehouse warehouse = warehouseService.getWarehouse(storagebinVo
					.getWarehouse().getWarehouseId());
			List<Storagebin> storagebins =null;
			if(!isUpdate){
				 storagebins = storagebinService.getStoragebin(storagebinVo.getName(),warehouse, storagebinVo.getStack(), storagebinVo.getRow(), storagebinVo.getLevel());
			}else{
				storagebins = storagebinService.getStoragebin(storagebinVo.getStoragebinId(),storagebinVo.getName(),warehouse, storagebinVo.getStack(), storagebinVo.getRow(), storagebinVo.getLevel());
			}
			if(storagebins.size()>0){
				response.setStatus(SBMessageStatus.FAILURE.getValue()); 
				//TODO:need to remove hard code.
				String str=storagebinVo.getName()+" , "+ storagebinVo.getStack()+"-"+storagebinVo.getRow()+"-"+ storagebinVo.getLevel();
				response.setErrorString(SBErrorMessage.STORAGEBIN_NAME_ALREADY_EXITS.getMessage().replaceAll("1",str));	
			return response;
			}
			
			Storagebin storagebin = setStoragebin(storagebinVo,warehouse,isUpdate);
			if(isUpdate){
				storagebinService.updateStorageBin(storagebin);
			}else{
				storagebinService.addStorageBin(storagebin);
			}
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue()); 
			response.setErrorString(e.getMessage());
		}
		return response;
	}

	

	@Path("/removestoragebin")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public StoragebinResponseVo deletedStorageBin(StoragebinVo storagebinVo) {
		StoragebinResponseVo response = new StoragebinResponseVo();
		try {
			Storagebin storagebin = storagebinService.getStoragebinById(storagebinVo.getStoragebinId());
			storagebinService.removeStorageBin(storagebin);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue()); 
			response.setErrorString(e.getMessage());
		}
		return response;
	}

	@Path("/getstoragebins")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public StoragebinResponseVo getStorageBins(StoragebinVo storagebinVo) {
		StoragebinResponseVo response = new StoragebinResponseVo();
		try {
			String storeId = storagebinVo.getStore().getStoreId();	
			Store store = storeService.getStoreById(storeId);
			List<Warehouse> listOfWarehouses = store.getWarehouses();
			List<WarehouseVo> listOFwarehouseVo= new ArrayList<WarehouseVo>();
			List<StoragebinVo>  listOfstoragebinVo = new ArrayList<StoragebinVo>();
			for(Warehouse warehouse:listOfWarehouses){
				WarehouseVo warehouseVoObj = new WarehouseVo();
				warehouseVoObj.setWarehouseId(warehouse.getWarehouseId());
				warehouseVoObj.setName(warehouse.getName());
				  List<Storagebin> storagebins = warehouse.getStoragebins();
				for(Storagebin storagebin:storagebins){
					StoragebinVo storagebinvo = storeService.setStoragebinVO(storagebin);
					storagebinvo.setWarehouse(warehouseVoObj);
					listOfstoragebinVo.add(storagebinvo);
				}
				listOFwarehouseVo.add(warehouseVoObj);
			}
			response.setStoragebins(listOfstoragebinVo);
			response.setWarehouses(listOFwarehouseVo);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue()); 
			response.setErrorString(e.getMessage());
		}
		return response;
	}

	
	/**
	 * @author Anbukkani Gajendran
	 * @param storagebinVo,isUpdate
	 * @return
	 * @throws Exception
	 */
	private Storagebin setStoragebin(StoragebinVo storagebinVo,Warehouse warehouse,boolean isUpdate)
			throws Exception {
		Storagebin storagebin = null;
		if (!isUpdate) {
			storagebin = (Storagebin) CommonUtil
					.setAuditColumnInfo(Storagebin.class.getName(), null);
			storagebin.setIsactive('Y');
		} else {
			storagebin = storagebinService.getStoragebinById(storagebinVo.getStoragebinId());
		}
	
		Store store = storeService.getStoreById(storagebinVo.getStore()
				.getStoreId());
		storagebin.setStore(store);
		storagebin.setName(storagebinVo.getName());
		
		storagebin.setWarehouse(warehouse);
		storagebin.setLevel(storagebinVo.getLevel());
		storagebin.setRow(storagebinVo.getRow());
		storagebin.setStack(storagebinVo.getStack());
		storagebin.setDescription(storagebinVo.getDescription());
		storagebin.setMerchant(store.getMerchant());
		return storagebin;
	}

	
}
