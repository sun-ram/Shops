package com.mitosis.aviate.webservice.ecommerce;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.mitosis.aviate.dao.BinProductDAO;
import com.mitosis.aviate.dao.CheckoutDao;
import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.CustomerDao;
import com.mitosis.aviate.dao.PhysicalInventoryDAO;
import com.mitosis.aviate.dao.PhysicalInventoryLineDAO;
import com.mitosis.aviate.dao.ProductDao;
import com.mitosis.aviate.dao.daoimpl.BinProductDAOImpl;
import com.mitosis.aviate.dao.daoimpl.CheckoutDaoImpl;
import com.mitosis.aviate.dao.daoimpl.CommonDaoImpl;
import com.mitosis.aviate.dao.daoimpl.CustomerDaoImpl;
import com.mitosis.aviate.dao.daoimpl.PhysicalInventoryLineDAOImpl;
import com.mitosis.aviate.dao.daoimpl.PhysicalInventoryDAOImpl;
import com.mitosis.aviate.dao.daoimpl.ProductDaoImpl;
import com.mitosis.aviate.model.Address;
import com.mitosis.aviate.model.BinProductModel;
import com.mitosis.aviate.model.CustomerDetailsModel;
import com.mitosis.aviate.model.InventoryLineModel;
import com.mitosis.aviate.model.InventoryModel;
import com.mitosis.aviate.model.MyCartModel;
import com.mitosis.aviate.model.OrderTax;
import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.SalesOrderLineModel;
import com.mitosis.aviate.model.SalesOrderModel;
import com.mitosis.aviate.model.TaxModel;
import com.mitosis.aviate.model.WarehouseModel;
import com.mitosis.aviate.model.service.AddressResponseModel;
import com.mitosis.aviate.model.service.ResponseModel;

@Path("checkout")
public class CheckOutServices {
	final static Logger log = Logger
			.getLogger(CheckOutServices.class.getName());
	CommonDao commonDao = new CommonDaoImpl();
	CheckoutDao checkoutDao= new CheckoutDaoImpl();
	ResponseModel response = new ResponseModel();

	@Path("/updateaddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel updateAddress(Address address) {
		try{
			log.info("\n******************************\n "
					+ "initialize the customer add new or update address service for customer id :"+address.getCustomerId());
			String full_address=address.getAddress()+","+address.getCity()+","+address.getState()+","+address.getCountry()+","+address.getPostalCode();
			JsonNode location = commonDao.getLatLong(full_address);
			JsonNode loc = location.findValue("lat".toString());
			address.setLatitude(loc.toString());
			loc = location.findValue("lng".toString());
			address.setLongitude(loc.toString());
			boolean flag = checkoutDao.update(address);
			if(flag){
				response.setStatus(com.mitosis.aviate.util.Constants.SUCCESS);
			}else{
				response.setStatus(com.mitosis.aviate.util.Constants.FAILURE);
				response.setErrorString(com.mitosis.aviate.util.Constants.E0006Error);
				response.setErrorCode(com.mitosis.aviate.util.Constants.E0006);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************\n "
				+ "Response of the customer add new or update address service for customer id :"+address.getCustomerId());
		return response;
	}

	@Path("/deleteaddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject deleteAddress(JSONObject addressId){
		JSONObject response = new JSONObject();
		try{
			log.info("\n******************************\n "
					+ "Initialized the customer address remove service for address id :"+addressId.getString("addressId"));
			boolean flag = checkoutDao.removeAddress(Long.parseLong(addressId.getString("addressId")));
			if(flag){
				response.put("status", com.mitosis.aviate.util.Constants.SUCCESS);
				response.put("errorString", "");
				response.put("errorCode", "");
			}else{
				response.put("status", com.mitosis.aviate.util.Constants.FAILURE);
				response.put("errorString", "");
				response.put("errorCode", "");
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************\n "
				+ "Response of the customer address remove service");
		return response;
	}


	@Path("/getlistofaddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AddressResponseModel getAddress(JSONObject reqObj){
		AddressResponseModel res = new AddressResponseModel();
		List<Address> addressList = new ArrayList<Address>();
		try{
			log.info("\n******************************\n "
					+ "Initialized the get address list service");
			addressList = checkoutDao.getAddress((int)Integer.parseInt(reqObj.getString("customerId")));
			if(!addressList.isEmpty()){
				res.setAddressList(addressList);
				res.setStatus("SUCCESS");
				res.setErrorCode("");
				res.setErrorString("");
			}else{
				res.setStatus("FAILURE");
				res.setErrorCode("");
				res.setErrorString("Address not found");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************\n "
				+ "Response of the get address list service");
		return res;
	}

	@Path("/conformorder")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject conformOrder(SalesOrderModel salesOrder){
		JSONObject responseObj = new JSONObject();
		ProductDao productDao = new ProductDaoImpl();
		try{
			log.info("\n******************************\n "
					+ "Initialized conform order service");
			if(salesOrder.getCustomerId() == null && salesOrder.getAddressId() == null &&
					salesOrder.getDeliveryDate() == null && salesOrder.getDeliveryTime() == null &&
					salesOrder.getStoreId() == null && salesOrder.getTotalAmount() == null){
				responseObj.put("status", "FAILURE");
				return responseObj;
			}else{
				JSONObject getOrderNo = checkoutDao.getStoreNameMaxOrderId(salesOrder.getStoreId());
				Long orderNo;
				if( commonDao.isValidProperty(getOrderNo, "storeName")){
					if(commonDao.isValidProperty(getOrderNo, "orderNo")){
						orderNo = Long.parseLong(getOrderNo.getString("orderNo"));
						orderNo++;
					}else{
						orderNo = (long) 1000;
					}
				}else{
					responseObj.put("status", "FAILURE");
					return responseObj;
				}
				String orderId = getOrderNo.getString("storeName").substring(0, 3).toUpperCase()+orderNo;
				salesOrder.setOrderId(orderId);
				salesOrder.setOrderNo(orderNo);

				List<MyCartModel> myCartList = new ArrayList<MyCartModel>();

				myCartList = productDao.getMyCartList(salesOrder.getCustomerId().intValue(),salesOrder.getStoreId());
				if(myCartList.isEmpty()){
					responseObj.put("status", "FAILURE");
					responseObj.put("errorString", "MyCart is empty");
					return responseObj;
				}
				List<SalesOrderLineModel> salesOrderLineModel = new ArrayList<SalesOrderLineModel>();
				for(MyCartModel list : myCartList){
					SalesOrderLineModel salesOrderObj = new SalesOrderLineModel();
					salesOrderObj.setPrice((long)list.getPrice());
					salesOrderObj.setQuantity((long)list.getQuantity());
					salesOrderObj.setProductId((long)list.getProductId());
					salesOrderLineModel.add(salesOrderObj);
				}
				salesOrder.setProduct(salesOrderLineModel);
				salesOrder.setIsPaid("Pending");
				Date date = new Date();
				salesOrder.setCreated(date);
				boolean flag = checkoutDao.saveOrder(salesOrder);	
				if(!flag){/*
					CustomerDao customerDao = new CustomerDaoImpl();
					JSONObject pickerSearch = new JSONObject();
					pickerSearch.put("storeId",salesOrder.getStoreId());
					pickerSearch.put("role","PICKER");
					List<CustomerDetailsModel> customerList =	customerDao.getPickerDeviceId(pickerSearch);
					for(CustomerDetailsModel customer : customerList ){
						if(customer.getDeviceType() != null 
								&& customer.getDeviceType().equals("ANDROID")){
							try{
								commonDao.androidPushNotification(orderId, customer.getDeviceId());
							}catch(Exception e){
							}
						}else if(customer.getDeviceType() != null 
								&& customer.getDeviceType().equals("IOS")){
							try{
								commonDao.applePushNotification(orderId, customer.getDeviceId());
							}catch(Exception e){
								e.printStackTrace();
							}
						}else{

						}
					}
				 */
					responseObj.put("status", "FAILURE");
					responseObj.put("errorString", "Order Not Generated");
					return responseObj;
				}else{
					JSONObject storeIdOb = new JSONObject();

					List<TaxModel> taxList = productDao.getShopTax(storeIdOb.put("storeId", salesOrder.getStoreId()));
					for(TaxModel tax: taxList){
						if(tax.isSummary())
							continue;
						OrderTax orderTax = new OrderTax();
						orderTax.setTaxId(tax.getTaxId());
						orderTax.setTaxBaseAmount(salesOrder.getTotalAmount());
						orderTax.setTaxAmount(salesOrder.getTotalAmount()*(tax.getRate()/100));
						orderTax.setMerchantId(tax.getMerchantId());
						orderTax.setActive(true);
						orderTax.setStoreId(salesOrder.getStoreId());
						orderTax.setOrderId(salesOrder.getSalesOrderId());
						orderTax.setCreated(new Date());
						orderTax.setCreatedBy(salesOrder.getCustomerId());
						orderTax.setUpdated(new Date());
						orderTax.setUpdatedBy(salesOrder.getCustomerId());
						checkoutDao.saveOrderTax(orderTax);
					}
				}
				responseObj.put("status", "SUCCESS");
				responseObj.put("orderId", orderId);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************\n "
				+ "Response of the conform order service");
		return responseObj;
	}

	public void conformPayment(String orderId,String transactionNo,String paymentMethod){
		try{
			SalesOrderModel salesOrder = checkoutDao.conformPayment(orderId, transactionNo, paymentMethod);
			JSONObject reqObj = new JSONObject();
			reqObj.put("storeId", salesOrder.getStoreId());
			reqObj.put("customerId", salesOrder.getCustomerId());
			ProductDao productDao = new ProductDaoImpl();
			boolean flag = productDao.deleteFromCart(reqObj);

			Set<WarehouseModel> warehouseModelSet=new HashSet<WarehouseModel>();
			Map<WarehouseModel,Map<BinProductModel,Long>> warehouseModelMap=new HashMap<WarehouseModel,Map<BinProductModel,Long>>();
			//Map<Long,Long> productQtyMap=new HashMap<Long, Long>();

			//Select Stocked product with warehouse based on sales order's store and product
			getStockedProductWithStorageBin(salesOrder, warehouseModelSet,
					warehouseModelMap);
			
			//Create Inventory and inventory lines based on available products 
			creatInventoryAndInventoryLines(salesOrder, warehouseModelSet,
					warehouseModelMap);
			CustomerDao customerDao = new CustomerDaoImpl();
			JSONObject pickerSearch = new JSONObject();
			pickerSearch.put("storeId",salesOrder.getStoreId());
			pickerSearch.put("role","PICKER");
			List<CustomerDetailsModel> customerList =	customerDao.getPickerDeviceId(pickerSearch);
			for(CustomerDetailsModel customer : customerList ){
				if(customer.getDeviceType() != null 
						&& customer.getDeviceType().equals("ANDROID")){
					try{
						commonDao.androidPushNotification(orderId, customer.getDeviceId());
					}catch(Exception e){
					}
				}else if(customer.getDeviceType() != null 
						&& customer.getDeviceType().equals("IOS")){
					try{
						commonDao.applePushNotification(orderId, customer.getDeviceId());
					}catch(Exception e){
						e.printStackTrace();
					}
				}else{

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void creatInventoryAndInventoryLines(SalesOrderModel salesOrder,
			Set<WarehouseModel> warehouseModelSet,
			Map<WarehouseModel, Map<BinProductModel, Long>> warehouseModelMap) {
		int count =1;

		for(WarehouseModel warehouse:warehouseModelSet){
			InventoryModel inventoryModel=new InventoryModel();
			inventoryModel.setWarehouseId(warehouse.getWarehouseId());
			inventoryModel.setInventoryName("Created From Sales Order"+salesOrder.getSalesOrderId()+"-"+count);
			inventoryModel.setIsactive(true);
			//inventoryModel.setMerchant(salesOrder.getStore().getMerchant());
			inventoryModel.setProcessed(false);
			inventoryModel.setStoreId(salesOrder.getStoreId());
			inventoryModel.setUpdated(new Date());
			inventoryModel.setCreated(new Date());
			inventoryModel.setCreatedBy(salesOrder.getCustomerId());
			inventoryModel.setUpdatedBy(salesOrder.getCustomerId());
			PhysicalInventoryDAO physicalInventoryDAO= new PhysicalInventoryDAOImpl();
			physicalInventoryDAO.addInventory(inventoryModel);

			Map<BinProductModel, Long> prdoctMap = warehouseModelMap.get(warehouse);
			Set<BinProductModel> keys = prdoctMap.keySet();
			for(BinProductModel key:keys){
				Long qty=prdoctMap.get(key);
				InventoryLineModel inventoryLineModel=new InventoryLineModel();
				inventoryLineModel.setQuantity(-(qty.intValue()));
				inventoryLineModel.setCreated(new Date());
				inventoryLineModel.setCreatedBy(salesOrder.getCustomerId());
				inventoryLineModel.setUpdated(new Date());
				inventoryLineModel.setUpdatedBy(salesOrder.getCustomerId());
				inventoryLineModel.setProductId(key.getProductId());
				inventoryLineModel.setInventoryId(inventoryModel.getInventoryId());
				inventoryLineModel.setMerchantId(key.getMerchantId());
				inventoryLineModel.setIsactive(true);
				inventoryLineModel.setStorageBinId(key.getStorageBinId());
				inventoryLineModel.setStoreId(salesOrder.getStoreId());
				PhysicalInventoryLineDAO physicalInventoryLineDAO=new PhysicalInventoryLineDAOImpl();
				physicalInventoryLineDAO.addInventoryLine(inventoryLineModel);
			}
			physicalInventoryDAO.updateInventroy(Integer.parseInt(""+inventoryModel.getInventoryId()));
			count++;
		}
	}

	private void getStockedProductWithStorageBin(SalesOrderModel salesOrder,
			Set<WarehouseModel> warehouseModelSet,
			Map<WarehouseModel, Map<BinProductModel, Long>> warehouseModelMap) {
		List<SalesOrderLineModel> salesOrderLineList=salesOrder.getProduct();
		for(SalesOrderLineModel salesOrderLine:salesOrderLineList){
			ProductDetails productDetails=salesOrderLine.getProductDetails();
			Long totalqty = salesOrderLine.getQuantity();
			Long balanceqty = totalqty;
			BinProductDAO dao=new BinProductDAOImpl();
			List<BinProductModel> ListOfBinPoduct = new ArrayList<BinProductModel>();
			ListOfBinPoduct = dao.getBinProducts(salesOrder.getStoreId(), productDetails.getProductId());
			boolean isProductAdded=false;
			for(BinProductModel binProductModel:ListOfBinPoduct){
				if(totalqty<binProductModel.getAvailableQuantity()&&!isProductAdded){
					Map<BinProductModel,Long> productQtyMap=null;
					if(warehouseModelMap.containsKey(binProductModel.getWarehouse())){
						productQtyMap = warehouseModelMap.get(binProductModel.getWarehouse());
						productQtyMap.put(binProductModel, totalqty);
						isProductAdded=true;
					}else{
						productQtyMap = new HashMap<BinProductModel, Long>();
						warehouseModelSet.add(binProductModel.getWarehouse());
						productQtyMap.put(binProductModel, totalqty);
						warehouseModelMap.put(binProductModel.getWarehouse(), productQtyMap);
						isProductAdded=true;
					}
				}
			}
			if(!isProductAdded){
				for(BinProductModel binProductModel:ListOfBinPoduct){
					Map<BinProductModel,Long> productQtyMap=null;					
					if(binProductModel.getAvailableQuantity()>0 && balanceqty!=0){
						if(warehouseModelMap.containsKey(binProductModel.getWarehouse())){
							productQtyMap = warehouseModelMap.get(binProductModel.getWarehouse());
							productQtyMap.put(binProductModel,binProductModel.getAvailableQuantity()>balanceqty?(balanceqty):(Long.valueOf(binProductModel.getAvailableQuantity())));
						//	isProductAdded=true;
							balanceqty=binProductModel.getAvailableQuantity()>balanceqty?0:((balanceqty)-(Long.valueOf(binProductModel.getAvailableQuantity())));
						}else {
							productQtyMap = new HashMap<BinProductModel, Long>();
							warehouseModelSet.add(binProductModel.getWarehouse());
							productQtyMap.put(binProductModel,binProductModel.getAvailableQuantity()>balanceqty?(balanceqty):(Long.valueOf(binProductModel.getAvailableQuantity())));
							//productQtyMap.put(binProductModel,binProductModel.getAvailableQuantity()>balanceqty?(Long.valueOf(binProductModel.getAvailableQuantity())-balanceqty):(Long.valueOf(binProductModel.getAvailableQuantity())));
							warehouseModelMap.put(binProductModel.getWarehouse(), productQtyMap);
							balanceqty=binProductModel.getAvailableQuantity()>balanceqty?0:((balanceqty)-(Long.valueOf(binProductModel.getAvailableQuantity())));
							//	isProductAdded=true;
						}
					}
				}	
			}
			
		}
	}	
}
