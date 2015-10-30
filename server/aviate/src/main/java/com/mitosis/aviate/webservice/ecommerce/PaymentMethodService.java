package com.mitosis.aviate.webservice.ecommerce;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.firstdata.integration.FDGGWSAxisClient;
import com.mitosis.aviate.model.service.ResponseModel;
import com.mitosis.aviate.util.CommonUtil;

@Path("payment")
public class PaymentMethodService {
	final static Logger log = Logger
			.getLogger(PaymentMethodService.class.getName());	
	ResponseModel response = new ResponseModel();
	@Path("/creditcard")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject payment(JSONObject reqObj){
		log.info("********* payment request object *************** /n"+reqObj);
		JSONObject response = new JSONObject();
		try{
			
		FDGGWSAxisClient obj = new FDGGWSAxisClient();
		response = obj.creditCard(reqObj);
		log.info("********* payment request object *************** /n"+response);
		}catch(Exception e){
			log.error(e.getMessage());
			response=CommonUtil.addStatusMessage(e);
			throw e;
		}
		
		return response;
	}
}
