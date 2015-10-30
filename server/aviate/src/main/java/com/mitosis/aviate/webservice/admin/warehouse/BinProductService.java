package com.mitosis.aviate.webservice.admin.warehouse;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.BinProductDAO;
import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.daoimpl.BinProductDAOImpl;
import com.mitosis.aviate.dao.daoimpl.CommonDaoImpl;
import com.mitosis.aviate.model.BinProductModel;
import com.mitosis.aviate.model.service.BinProductListResponse;
import com.mitosis.aviate.model.service.ResponseModel;
import com.mitosis.aviate.util.CommonUtil;


@Path("binproduct")
public class BinProductService {
	final static Logger log = Logger
			.getLogger(BinProductService.class.getName());
	 
	BinProductDAO binProductDAO=new BinProductDAOImpl();
	CommonDao commonDao = new CommonDaoImpl();

	@Path("/getbinproductlist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BinProductListResponse getBinProductList(JSONObject requestObj){
		List<BinProductModel> binProductList= new ArrayList<BinProductModel>();
		BinProductListResponse binProductListResponse = new BinProductListResponse();
		try{
			if(commonDao.isValidProperty(requestObj, "storeId")){
				binProductList=binProductDAO.getBinProducts(requestObj.getLong("storeId"));
			}else if(commonDao.isValidProperty(requestObj, "merchantId")){
				binProductList=binProductDAO.getBinProducts(requestObj);
			}
			binProductListResponse.setBinProductList(binProductList);
		}catch(Exception e){
			log.error(e.getMessage());	
		}
		return binProductListResponse;		
	}
		
}
