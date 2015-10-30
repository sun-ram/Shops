package com.mitosis.aviate.webservice.admin.warehouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.PhysicalInventoryDAO;
import com.mitosis.aviate.dao.PhysicalInventoryLineDAO;
import com.mitosis.aviate.dao.daoimpl.PhysicalInventoryDAOImpl;
import com.mitosis.aviate.dao.daoimpl.PhysicalInventoryLineDAOImpl;
import com.mitosis.aviate.model.InventoryLineModel;
import com.mitosis.aviate.model.InventoryModel;
import com.mitosis.aviate.model.StorageBinModel;
import com.mitosis.aviate.model.WarehouseModel;
import com.mitosis.aviate.model.service.ResponseModel;
import com.mitosis.aviate.util.AVErrorMessage;
import com.mitosis.aviate.util.AVMessageStatus;
import com.mitosis.aviate.util.CommonUtil;

@Path("inventory")
public class PhysicalInventoryUpdateService {
	final static Logger log = Logger
			.getLogger(WarehouseUpdateServices.class.getName());
	PhysicalInventoryDAO physicalInvenotyDAO=new PhysicalInventoryDAOImpl();
	PhysicalInventoryLineDAO physicalInventoryLineDao=new PhysicalInventoryLineDAOImpl();
	JSONObject jsonResponse=new JSONObject();

	@Path("/addinventory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject addInventory(JSONObject requestObj){
		try{
			List<InventoryModel> uniqueInventoryList=null;
			String warehouseId=requestObj.getString("warehouseId");
			String storeId=requestObj.getString("storeId");
			String merchantId=requestObj.getString("merchantId");
			String inventoryName=requestObj.getString("inventoryName");
			if(warehouseId!=null&&storeId!=null&&inventoryName!=null){			
				uniqueInventoryList=physicalInvenotyDAO.uniqueNameChecking(storeId, warehouseId, inventoryName, merchantId);
			}else{
				jsonResponse.put("status", AVMessageStatus.FAILURE.getValue());
				jsonResponse.put("message", AVErrorMessage.STORE_NAME_EMPTY_ERROR.getMessage());
			}if(uniqueInventoryList!=null&&uniqueInventoryList.size()<1){
				jsonResponse=addInventoryDetails(requestObj, warehouseId, storeId, inventoryName, merchantId);
			}else{
				jsonResponse.put("status", AVMessageStatus.FAILURE.getValue());
				jsonResponse.put("message", AVErrorMessage.INVENTORY_ALREADY_EXIST_ERROR.getMessage());			
			}			
		}catch(Exception e){
			log.error(e.getMessage());	
			jsonResponse=CommonUtil.addStatusMessage(e);
		}
		return jsonResponse;		
	}
	
	@Path("/updateinventory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject updateInventory(JSONObject requestObj) throws JSONException{
		try{
			InventoryModel uniqueInventoryList=new InventoryModel();
		   uniqueInventoryList=physicalInvenotyDAO.getInventory(requestObj.getLong("inventoryId"));
		   uniqueInventoryList.setDescription(requestObj.getString("description"));
		   uniqueInventoryList.setStoreId(requestObj.getLong("storeId"));
		   uniqueInventoryList.setMerchantId(requestObj.getInt("merchantId"));
		   uniqueInventoryList.setWarehouseId(requestObj.getInt("warehouseId"));
		   uniqueInventoryList.setInventoryName(requestObj.getString("inventoryName"));
		   physicalInvenotyDAO.updateInventories(uniqueInventoryList);
		   jsonResponse.put("status", AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());	
			jsonResponse.put("status", AVMessageStatus.FAILURE.getValue());
			jsonResponse = CommonUtil.addStatusMessage(e);
		}
		return jsonResponse;		
	}

	


	private JSONObject addInventoryDetails(JSONObject requestObj, String warehouseId,
			String storeId, String inventoryName, String merchantId) throws JSONException {
		InventoryModel inventory=new InventoryModel();
		inventory.setCreated(new Date());
		inventory.setDescription(requestObj.getString("description"));
		inventory.setStoreId(Long.valueOf((storeId)));
		inventory.setIsactive(true);
		inventory.setWarehouseId(Integer.parseInt(warehouseId));
		inventory.setInventoryName(inventoryName);
		inventory.setMerchantId(Integer.parseInt(merchantId));
		InventoryModel	inventoryData =physicalInvenotyDAO.addInventory(inventory);
		if(inventoryData.getInventoryId() != 0){
			jsonResponse.put("inventoryId",inventoryData.getInventoryId());
			jsonResponse.put("status",AVMessageStatus.SUCCESS.getValue());
			return jsonResponse;
		}
		return jsonResponse=CommonUtil.statusMessage(false);
	}

	@Path("/removeinventory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject removeInventory(JSONObject requestObj){
		List<InventoryLineModel> inventoryLineModel=new ArrayList<InventoryLineModel>();
		try{
			String inventoryId=requestObj.getString("inventoryId");		
			inventoryLineModel=physicalInventoryLineDao.getInventoryLineList(Integer.parseInt(inventoryId));
			for(int i=0;i<inventoryLineModel.size();i++){
				physicalInventoryLineDao.removeInventoryById(inventoryLineModel.get(i).getInventoryLineId());
			}
			boolean flag=physicalInvenotyDAO.removeInventory(inventoryId);
			jsonResponse=CommonUtil.statusMessage(flag);
		}catch(Exception e){
			log.error(e.getMessage());		
			jsonResponse= CommonUtil.addStatusMessage(e);		
		}
		return jsonResponse;	
	}

	@Path("/warehouselist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject warehouseList(JSONObject requestObj){
		WarehouseResponseModel warehouseResponseModel=new WarehouseResponseModel();
		List<WarehouseModel> listOfWarehouses=new ArrayList<WarehouseModel>();
		JSONArray jsonArray=new JSONArray();
		JSONObject response=new JSONObject();
		try{
			String storeId=requestObj.getString("storeId");		
			listOfWarehouses=physicalInvenotyDAO.warehouseList(storeId);
			if(!listOfWarehouses.isEmpty()){
				warehouseResponseModel.setWarehouseList(listOfWarehouses);
				warehouseResponseModel.setStatus(AVMessageStatus.SUCCESS.getValue());
				for(WarehouseModel warehouseModel:listOfWarehouses){
					JSONObject list = new JSONObject();
					list.put("warehouseId", warehouseModel.getWarehouseId());
					list.put("warehouseName", warehouseModel.getWarehouseName());
					list.put("warehouseId", warehouseModel.getWarehouseId());
					list.put("address", warehouseModel.getAddress1());
					list.put("address1", warehouseModel.getAddress2());
					list.put("city", warehouseModel.getCity());
					list.put("state", warehouseModel.getState());
					list.put("country", warehouseModel.getCountry());
					list.put("postelcode", warehouseModel.getPostel_code());
					list.put("whDescription", warehouseModel.getDescription());
					if(warehouseModel.getStorageBins()!=null&&warehouseModel.getStorageBins().size()>0&&
							warehouseModel.getStorageBins().get(0).getStorageBinName()!=null){
						JSONArray arr = new JSONArray();
						for(StorageBinModel sbm : warehouseModel.getStorageBins()){
							JSONObject list2 = new JSONObject();
							list2.put("storagebinId",sbm.getStorageBinId());
							list2.put("storageBinName", sbm.getStorageBinName());
							list2.put("xRow", sbm.getxRow());
							list2.put("yRow", sbm.getyRow());
							list2.put("zRow", sbm.getzRow());
							arr.put(list2);
						}
						
							list.put("returnBin", arr);
					}

					jsonArray.put(list);
				}				
			}else{
				warehouseResponseModel.setStatus(AVMessageStatus.SUCCESS.getValue());
				warehouseResponseModel.setErrorCode(AVErrorMessage.WAREHOUSE_NOT_AVAILABLE.getCode());
				warehouseResponseModel.setErrorString(AVErrorMessage.WAREHOUSE_NOT_AVAILABLE.getMessage());
			}
			response.put("warehouseList", jsonArray);
			response.put("warehouseresponsemodel", warehouseResponseModel);
		}catch(Exception e){
			log.error(e.getMessage());	
			jsonResponse=CommonUtil.addStatusMessage(e);			
		}
		return response;	
	}
	
	@Path("/conforminventroylinedata")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel conformInventroyLineData(JSONObject inventoryId){
		ResponseModel response = new ResponseModel();
		try{
			boolean flag = physicalInvenotyDAO.updateInventroy(Integer.parseInt(inventoryId.getString("inventoryId")));
			if(flag){
				response.setStatus(AVMessageStatus.SUCCESS.getValue());	
			}else{
				response.setStatus(AVMessageStatus.FAILURE.getValue());	
			}
		}catch(Exception e){
			log.error(e.getMessage());		
			jsonResponse=CommonUtil.addStatusMessage(e);		
		}
		return response;		
	}
	

}
