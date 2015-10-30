package com.mitosis.aviate.webservice.admin.warehouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.StorageBinDAO;
import com.mitosis.aviate.dao.WarehouseUpdateDAO;
import com.mitosis.aviate.dao.daoimpl.CommonDaoImpl;
import com.mitosis.aviate.dao.daoimpl.StorageBinDAOImpl;
import com.mitosis.aviate.dao.daoimpl.WarehouseUpdateDAOImpl;
import com.mitosis.aviate.model.StorageBinModel;
import com.mitosis.aviate.model.WarehouseModel;
import com.mitosis.aviate.model.service.ResponseModel;
import com.mitosis.aviate.util.CommonUtil;


@Path("warehouse")
public class WarehouseUpdateServices  {
	final static Logger log = Logger
			.getLogger(WarehouseUpdateServices.class.getName());
	WarehouseUpdateDAO warehouseDao=new WarehouseUpdateDAOImpl();
	StorageBinDAO storageBinDAO=new StorageBinDAOImpl();
	JSONObject jsonResponse=new JSONObject();
	
	@Path("/addwarehouse")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject addWarehouse(JSONObject requestObj){		
		try{
			List<WarehouseModel> checkUniqueName=null;
			String warehouseName=requestObj.getString("warehouseName");
			String storeId=requestObj.getString("storeId");
			if(warehouseName!=null){
				checkUniqueName =warehouseDao.checkUniqueName(warehouseName,storeId);
			}else{
				jsonResponse.put("status", "Failure");
				jsonResponse.put("message", "Warehouse Name should not empty");
			}
			if(checkUniqueName!=null&&checkUniqueName.size()<1){
				jsonResponse=addWarehouseDetails(requestObj);
			}else{
				jsonResponse.put("status", "Failure");
				jsonResponse.put("message", "Warehouse Already available");
			}			
		}catch(Exception e){
			log.error(e.getMessage());			
		}
		return jsonResponse;		
	}
	
	@Path("/updatewarehouse")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject updateWarehouse(JSONObject requestObj){		
		try{
			int retunbin;
			WarehouseModel warehouse=warehouseDao.getSingleWarehouse(requestObj.getInt("warehouseId"));
			if(requestObj.getString("warehouseName")!=null){
				warehouse.setUpdated(new Date());
				warehouse.setStoreId(Integer.parseInt(requestObj.getString("storeId")));
				warehouse.setIsactive(true);
				warehouse.setWarehouseName(requestObj.getString("warehouseName"));
				setWarehouseAddress(requestObj,warehouse);
				warehouse.setDescription(requestObj.getString("whDescription"));
				warehouse.setMarchantId(requestObj.getInt("merchantId"));
				try{
					retunbin=Integer.parseInt(requestObj.getString("returnBinId"));
				}catch(NumberFormatException e){
					retunbin =0;
				}
				warehouse.setStorageBin(retunbin==0?0:retunbin);
				boolean flag=warehouseDao.updateWarehouse(warehouse);
				return jsonResponse=CommonUtil.statusMessage(flag);
			}else{
				jsonResponse.put("status", "Failure");
				jsonResponse.put("message", "Duplicate Name Should not be empty");
			}			
		}catch(Exception e){
			log.error(e.getMessage());			
		}
		return jsonResponse;		
	}

	private JSONObject addWarehouseDetails(JSONObject requestObj)
			throws JSONException {
		int retunbin;
		WarehouseModel warehouse=new WarehouseModel();			
		warehouse.setCreated(new Date());
		warehouse.setStoreId(Integer.parseInt(requestObj.getString("storeId")));
		warehouse.setIsactive(true);
		warehouse.setWarehouseName(requestObj.getString("warehouseName"));
		setWarehouseAddress(requestObj,warehouse);
		warehouse.setDescription(requestObj.getString("whDescription"));
		warehouse.setMarchantId(requestObj.getInt("merchantId"));
		try{
			retunbin=Integer.parseInt(requestObj.getString("returnBinId"));
		}catch(NumberFormatException e){
			retunbin =0;
		}
		warehouse.setStorageBin(retunbin==0?0:retunbin);
		boolean flag=warehouseDao.addWarehouse(warehouse);
		return jsonResponse=CommonUtil.statusMessage(flag);
	}
	
	private void setWarehouseAddress(JSONObject requestObj,WarehouseModel warehouse){
		try{			
			warehouse.setAddress1(requestObj.getString("address"));
			warehouse.setAddress2(requestObj.getString("address1"));			
			warehouse.setCity(requestObj.getString("city"));
			warehouse.setState(requestObj.getString("state"));
			warehouse.setCountry(requestObj.getString("country"));
			warehouse.setPostelcode(requestObj.getString("postelcode"));
		}catch(Exception e){
			log.error(e.getMessage());
		}		
	}
	
	@Path("/removewarehouse")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject removeWarehouse(JSONObject requestObj){
		try{
			boolean status=false;
			boolean fromWarehouse=true;
			String warehouseId=requestObj.getString("warehouseId");
			 //Before deleting the warehouse ,Need to delete the storage bin 
			// which associated with this warehouse 
//			List<StorageBinModel> storageBinList = warehouseDao.listOfStorgaBins(warehouseId);
//			for (StorageBinModel storageBin:storageBinList){
//				storageBinDAO.removeStorageBin(""+storageBin.getStorageBinId(),fromWarehouse);
//			}
			status=warehouseDao.deleteWarehouse(warehouseId);
			jsonResponse=CommonUtil.statusMessage(status);
			if(!status){
				jsonResponse.put("errorString", "The warehouse can't delete.this warehouse have some products");
			}
		}catch(Exception e){
			log.error(e.getMessage());	
			jsonResponse=CommonUtil.addStatusMessage(e);
			
		}
		return jsonResponse;		
	}
	
		
	@Path("/storagebinlist")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public WarehouseResponseModel storageBinList(JSONObject requestObj){
		WarehouseResponseModel warehouseResponseModel=new WarehouseResponseModel();		
		List<StorageBinModel> listOfStorageBins=new ArrayList<StorageBinModel>();
		try{
			String warehouseId=requestObj.getString("warehouseid");		
			listOfStorageBins=warehouseDao.listOfStorgaBins(warehouseId);
			if(!listOfStorageBins.isEmpty()){
				warehouseResponseModel.setStorageBinList(listOfStorageBins);
				warehouseResponseModel.setStatus("SUCCESS");
			}else{
				warehouseResponseModel.setStatus("FAILURE");
				warehouseResponseModel.setErrorCode("");
				warehouseResponseModel.setErrorString("Storage Bins are not available");
			}
		}catch(Exception e){
			log.error(e.getMessage());			
		}
		return warehouseResponseModel;		
	}
	

}
