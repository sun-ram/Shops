package com.mitosis.aviate.webservice.admin.warehouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.StorageBinDAO;
import com.mitosis.aviate.dao.daoimpl.CommonDaoImpl;
import com.mitosis.aviate.dao.daoimpl.StorageBinDAOImpl;
import com.mitosis.aviate.model.StorageBinModel;
import com.mitosis.aviate.util.AVMessageProperties;
import com.mitosis.aviate.util.AVMessageStatus;
import com.mitosis.aviate.util.CommonUtil;

@Path("storagebin")
public class StorageBinUpdateService {
	final static Logger log = Logger
			.getLogger(WarehouseUpdateServices.class.getName());
	JSONObject jsonResponse=new JSONObject();
	StorageBinDAO storageBinDAO=new StorageBinDAOImpl();
	
	@Path("/addstoragebin")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject addStorageBin(JSONObject requestObj){
		try{
			List<StorageBinModel> uniqueChekcing=new ArrayList<StorageBinModel>();
			StorageBinModel storageBin=new StorageBinModel();
			storageBin.setCreated(new Date());
			//storageBin.setMerchantId(1);
			storageBin.setStoreId(Integer.parseInt(requestObj.getString("storeId")));
			storageBin.setStorageBinName(requestObj.getString("storageBinName"));
			storageBin.setIsactive(true);
			storageBin.setWarehouseId(Integer.parseInt(requestObj.getString("warehouseId")));
			storageBin.setxRow(requestObj.getString("xRow"));
			storageBin.setyRow(requestObj.getString("yRow"));
			storageBin.setzRow(requestObj.getString("zRow"));	
			uniqueChekcing=storageBinDAO.uniqueNameChecking(requestObj.getString("storageBinName"), requestObj.getString("warehouseId"));
			if(uniqueChekcing.isEmpty()){
				boolean status=storageBinDAO.addStorageBin(storageBin);
				return jsonResponse=CommonUtil.statusMessage(status);
			}
			else{
				return jsonResponse=CommonUtil.addStatusMessage("Storage bin name already exists");
			}
		}catch(Exception e){
			log.error(e.getMessage());	
			CommonUtil.addStatusMessage(e);
		}
		return jsonResponse;		
	}
	
	@Path("/removestoragebin")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject removeStorageBin(JSONObject requestObj){
		try{
			boolean fromBin=true;
			String storageBinId=requestObj.getString("storageBinId");
			boolean status=storageBinDAO.removeStorageBin(storageBinId,fromBin);
			jsonResponse=CommonUtil.statusMessage(status);
		}catch(Exception e){
			log.error(e.getMessage());	
			CommonUtil.addStatusMessage(e);
		}
		return jsonResponse;		
	}

}
