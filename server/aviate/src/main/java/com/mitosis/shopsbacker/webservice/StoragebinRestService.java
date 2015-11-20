/**
 * 
 */
package com.mitosis.shopsbacker.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.inventory.service.StoragebinService;
import com.mitosis.shopsbacker.inventory.service.WarehouseService;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;
import com.mitosis.shopsbacker.responsevo.StoragebinResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.inventory.StoragebinVo;

/**
 * @author Anbukkani Gajendran
 *
 */
@Controller("storagebinRestService")
@Path("Storagebin")
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
	public StoragebinResponseVo addStorageBin(StoragebinVo storagebinVo) {
		StoragebinResponseVo response = new StoragebinResponseVo();
		try {
			boolean isUpdate=storagebinVo.getStoragebinId()!=null?true:false;
			Storagebin storagebin = setStoragebin(storagebinVo,isUpdate);
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

	/**
	 * @author Anbukkani Gajendran
	 * @param storagebinVo,isUpdate
	 * @return
	 * @throws Exception
	 */
	private Storagebin setStoragebin(StoragebinVo storagebinVo,boolean isUpdate)
			throws Exception {
		Storagebin storagebin = null;
		if (isUpdate) {
			storagebin = (Storagebin) CommonUtil
					.setAuditColumnInfo(Storagebin.class.getName());
		} else {
			storagebin = storagebinService.getStoragebinById(storagebinVo.getStoragebinId());
		}
		storagebin.setIsactive('Y');
		Store store = storeService.getStoreById(storagebinVo.getStore()
				.getStoreId());
		storagebin.setStore(store);
		storagebin.setName(storagebinVo.getName());
		Warehouse warehouse = warehouseService.getWarehouse(storagebinVo
				.getWarehouse().getWarehouseId());
		storagebin.setWarehouse(warehouse);
		storagebin.setLevel(storagebinVo.getLevel());
		storagebin.setRow(storagebinVo.getRow());
		storagebin.setStack(storagebinVo.getStack());
		storagebin.setDescription(storagebinVo.getDescription());
		storagebin.setMerchant(store.getMerchant());
		return storagebin;
	}

}
