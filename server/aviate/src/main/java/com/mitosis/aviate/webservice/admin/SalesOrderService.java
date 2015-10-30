package com.mitosis.aviate.webservice.admin;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.SalesOrderDAO;
import com.mitosis.aviate.dao.daoimpl.SalesOrderDAOImpl;
import com.mitosis.aviate.model.SalesOrderModel;
import com.mitosis.aviate.model.service.ResponseModel;
import com.mitosis.aviate.model.service.SalesOrderResponseModel;
import com.mitosis.aviate.util.CommonUtil;

@Path("sales")
public class SalesOrderService {

	HttpServlet http;

	final static Logger log = Logger
			.getLogger(SalesOrderService.class.getName());
	ResponseModel response = new ResponseModel();
	SalesOrderDAO salesorderDao = new SalesOrderDAOImpl();
	Properties properties = new Properties();
	
	@Path("/getorderlist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SalesOrderResponseModel getOrderList(JSONObject requestObj){
		SalesOrderResponseModel res = new SalesOrderResponseModel();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the removce store service");
			if(requestObj.length()!=0){
			List<SalesOrderModel> salesOrderModels = salesorderDao.getOrderList(requestObj);
			if(salesOrderModels==null){
				res.setStatus("FAILURE");
				res.setErrorString("No Records Found");
			}else{
				res.setSalesOrderModel(salesOrderModels);
				res.setStatus("SUCCESS");
			}}else{
				res.setStatus("FAILURE");
				res.setErrorString("Invaild Request");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************************\n"
				+ "Response of the salesorder list service");
		return res;
	}
	
	@Path("/getorderlistbydate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SalesOrderResponseModel getOrderListByDate(JSONObject requestObj){
		SalesOrderResponseModel res = new SalesOrderResponseModel();
		try{
			log.info("\n******************************************\n"
					+ "Initializing the removce store service");
			List<SalesOrderModel> salesOrderModels = salesorderDao.salesOrderDetailList(requestObj);
			res.setSalesOrderModel(salesOrderModels);
			res.setStatus("SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("\n******************************************\n"
				+ "Response of the salesorder list service");
		return res;
	}
	
	
}
