package com.mitosis.aviate.webservice.admin;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.mitosis.aviate.dao.ProductPriceDAO;
import com.mitosis.aviate.dao.daoimpl.ProductPriceDAOImpl;
import com.mitosis.aviate.model.PriceModel;
import com.mitosis.aviate.model.service.ResponseModel;
import com.mitosis.aviate.util.AVMessageStatus;
import com.mitosis.aviate.util.CommonUtil;

@Path("price")
public class ProductPriceService {
	final static Logger log = Logger
			.getLogger(ProductPriceService.class);
	ProductPriceDAO productPriceDAO = new ProductPriceDAOImpl();
	ResponseModel response = new ResponseModel();
	@Path("/addprice")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel savePrice(PriceModel price){
		try{
			log.info("Initializede product price add service");
				productPriceDAO.savePrice(price);
				response.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			e.printStackTrace();
			 log.error(e.getMessage());
			 response=CommonUtil.addStatusMessage(e,response);
		}
		log.info("End of product price add service");
		return response;
	}
	
	
	

}
