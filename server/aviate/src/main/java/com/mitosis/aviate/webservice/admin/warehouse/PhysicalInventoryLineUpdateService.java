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
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.PhysicalInventoryLineDAO;
import com.mitosis.aviate.dao.daoimpl.CommonDaoImpl;
import com.mitosis.aviate.dao.daoimpl.PhysicalInventoryLineDAOImpl;
import com.mitosis.aviate.model.InventoryLineModel;
import com.mitosis.aviate.model.InventoryModel;
import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.ProductUOM;
import com.mitosis.aviate.model.StorageBinModel;
import com.mitosis.aviate.model.service.InventoryLineResponseModel;
import com.mitosis.aviate.model.service.InventroyLineResponse;
import com.mitosis.aviate.model.service.ResponseModel;
import com.mitosis.aviate.util.AVErrorMessage;
import com.mitosis.aviate.util.AVMessageProperties;
import com.mitosis.aviate.util.AVMessageStatus;
import com.mitosis.aviate.util.CommonUtil;

@Path("inventoryline")
public class PhysicalInventoryLineUpdateService {
	final static Logger log = Logger
			.getLogger(WarehouseUpdateServices.class.getName());
	JSONObject jsonResponse=new JSONObject();
	PhysicalInventoryLineDAO physicalInventoryLineDAO=new PhysicalInventoryLineDAOImpl();
	
	
	@Path("/addinventoryline")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject addInventoryLine(JSONObject requestObj){
		try{
			InventoryLineModel inventoryLine=new InventoryLineModel();
			inventoryLine.setCreated(new Date());
			inventoryLine.setDescription(requestObj.getString("sbDesc"));
			inventoryLine.setQuantity(Integer.parseInt(requestObj.getString("totalQuantity")));
			inventoryLine.setInventoryId(Integer.parseInt(requestObj.getString("inventoryId")));
			inventoryLine.setProductId(Long.parseLong(requestObj.getString("productId")));
			inventoryLine.setStorageBinId(Integer.parseInt(requestObj.getString("storageBinId")));
			inventoryLine.setStoreId(Long.parseLong(requestObj.getString("storeId")));
			inventoryLine.setMerchantId(requestObj.getInt("merchantId"));
			/*inventoryLine.setAvailableQty(Integer.parseInt(requestObj.getString("availableQuantity")));
			inventoryLine.setBookedQty(Integer.parseInt(requestObj.getString("bookedQuantity")));*/
			inventoryLine.setUomId(Integer.parseInt(requestObj.getString("uomId")));
		boolean flag=physicalInventoryLineDAO.addInventoryLine(inventoryLine);			
		jsonResponse=CommonUtil.statusMessage(flag);
		}catch(Exception e){
			log.error(e.getMessage());		
			jsonResponse=CommonUtil.addStatusMessage(e);
		}
		return jsonResponse;		
	}
	
	@Path("/removeinventoryline")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject removeInventoryLine(JSONObject requestObj){
		try{
			String inventoryLineId=requestObj.getString("inventoryLineId");		
			boolean status=physicalInventoryLineDAO.removeInventory(inventoryLineId);
			jsonResponse=CommonUtil.statusMessage(status);
		}catch(Exception e){
			log.error(e.getMessage());	
			jsonResponse=CommonUtil.addStatusMessage(e);
		}
		return jsonResponse;		
	}
	
	@Path("/inventorylinelist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public InventroyLineResponse inventoryLineList(JSONObject inventoryId){
		InventroyLineResponse inventroyLineResponse = new InventroyLineResponse();
		List<InventoryLineModel> listOfInventoryLine = new ArrayList<InventoryLineModel>();
		try{
			listOfInventoryLine = physicalInventoryLineDAO.getInventoryLineList(Integer.parseInt(inventoryId.getString("inventoryId")));
			inventroyLineResponse.setListOfInventroyLine(listOfInventoryLine);;
			inventroyLineResponse.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());	
			jsonResponse=CommonUtil.addStatusMessage(e);
		}
		return inventroyLineResponse;		
	}
	
	@Path("/inventoryList")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getInventories(JSONObject requestObj){
		List<InventoryModel> listOfInventor=new ArrayList<InventoryModel>();
		JSONObject response=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		
		try{
			String storeId=requestObj.getString("storeId");		
			listOfInventor=physicalInventoryLineDAO.listOfInventoryies(storeId);
			JSONObject list = new JSONObject();
			if(!listOfInventor.isEmpty()){				
				for(InventoryModel inventory:listOfInventor){	
					list = new JSONObject();
					list.put("inventoryId", inventory.getInventoryId());
					list.put("inventoryName", inventory.getInventoryName());
					list.put("description", inventory.getDescription());
					list.put("isprocessed", inventory.isProcessed());
					if(inventory.getStore()!=null){
						list.put("storeName", inventory.getStore().getStoreName());
					}
					if(inventory.getWarehouse()!=null){
						list.put("warehouseName", inventory.getWarehouse().getWarehouseName());
						list.put("warehouseId", inventory.getWarehouse().getWarehouseId());
					}
					list.put(AVMessageProperties.STATUS.getValue(),AVMessageStatus.SUCCESS.getValue());
					jsonArray.put(list);
				}
				response.put("inventoryList", jsonArray);
			}else{
				list.put("status",AVMessageStatus.FAILURE.getValue());
			}
			
		}catch(Exception e){
			log.error(e.getMessage());	
			jsonResponse=CommonUtil.addStatusMessage(e);
		}
		return response;	
	}
	
	@Path("/productlist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject productList(JSONObject requestObj){
		InventoryLineResponseModel inventoryLineResponseModel=new InventoryLineResponseModel();
		List<ProductDetails> listOfProducts=new ArrayList<ProductDetails>();
		JSONObject response=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		try{
			String merchantId=requestObj.getString("merchantId");		
			listOfProducts=physicalInventoryLineDAO.productList(merchantId);
			if(!listOfProducts.isEmpty()){				
				for(ProductDetails product:listOfProducts){
					JSONObject list = new JSONObject();
					list.put("productId", product.getProductId());
					list.put("productName", product.getProductName());
					jsonArray.put(list);
				}
				inventoryLineResponseModel.setProductList(listOfProducts);
				inventoryLineResponseModel.setStatus(AVMessageStatus.SUCCESS.getValue());			
			}else{
				inventoryLineResponseModel.setStatus(AVMessageStatus.FAILURE.getValue());
				inventoryLineResponseModel.setErrorCode(AVErrorMessage.PRODUCT_NOT_AVAILABLE.getCode());
				inventoryLineResponseModel.setErrorString(AVErrorMessage.PRODUCT_NOT_AVAILABLE.getMessage());
			}
			response.put("productList", jsonArray);
			response.put("reposponseModel", inventoryLineResponseModel);
		}catch(Exception e){
			log.error(e.getMessage());	
			jsonResponse=CommonUtil.addStatusMessage(e);
		}
		return response;	
	}
	
	@Path("/getUomList")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getUomList(JSONObject requestObj){
		List<ProductUOM> listOfProductsUom=new ArrayList<ProductUOM>();
		JSONObject response=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		JSONObject list = new JSONObject();
		try{
			String productId=requestObj.getString("productId");		
			listOfProductsUom=physicalInventoryLineDAO.unitOfMeasureList(productId);
			if(!listOfProductsUom.isEmpty()){				
				for(ProductUOM productUom:listOfProductsUom){					
					list.put("status", AVMessageStatus.SUCCESS.getValue());
					list.put("uomId", productUom.getUnitOfMeasureId());
					list.put("uomName",productUom.getAbbreviation());
					list.put("productId",productUom.getProductId());
					jsonArray.put(list);
				}
			}else{
				list.put("status", AVMessageStatus.FAILURE.getValue());
			}
			response.put("productUomList", jsonArray);
		}catch(Exception e){
			log.error(e.getMessage());	
			jsonResponse=CommonUtil.addStatusMessage(e);
		}
		return response;	
	}
	
	@Path("/storagebinlist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject storageBinList(JSONObject requestObj){
		InventoryLineResponseModel inventoryLineResponseModel=new InventoryLineResponseModel();
		List<StorageBinModel> listOfStorageBins=new ArrayList<StorageBinModel>();
		JSONArray jsonArray=new JSONArray();
		JSONObject response=new JSONObject();
		try{
			String warehouseId=requestObj.getString("warehouseId");		
			listOfStorageBins=physicalInventoryLineDAO.storageBinList(warehouseId);
			if(!listOfStorageBins.isEmpty()){
				for(StorageBinModel storageBin:listOfStorageBins){
					JSONObject list=new JSONObject();
					list.put("binId", storageBin.getStorageBinId());
					list.put("binName", storageBin.getStorageBinName());
					list.put("warehouseId", storageBin.getWarehouseId());
					jsonArray.put(list);
					
				}
				inventoryLineResponseModel.setStorageBinList(listOfStorageBins);
				inventoryLineResponseModel.setStatus(AVMessageStatus.SUCCESS.getValue());			
			}else{
				inventoryLineResponseModel.setStatus(AVMessageStatus.FAILURE.getValue());
				inventoryLineResponseModel.setErrorCode(AVErrorMessage.STORAGEBIN_NOT_FOUND.getCode());
				inventoryLineResponseModel.setErrorString(AVErrorMessage.STORAGEBIN_NOT_FOUND.getMessage());
			}
			response.put("binList", jsonArray);
			response.put("binModel", inventoryLineResponseModel);
		}catch(Exception e){
			log.error(e.getMessage());		
			jsonResponse=CommonUtil.addStatusMessage(e);
		}
		return response;		
	}

}
