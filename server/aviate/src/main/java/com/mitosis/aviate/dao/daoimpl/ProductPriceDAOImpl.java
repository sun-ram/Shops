package com.mitosis.aviate.dao.daoimpl;

import org.apache.log4j.Logger;

import com.mitosis.aviate.dao.ProductPriceDAO;
import com.mitosis.aviate.model.PriceModel;
import com.mitosis.aviate.webservice.admin.ProductPriceService;

public class ProductPriceDAOImpl extends BaseService implements ProductPriceDAO{

	final static Logger log = Logger.getLogger(ProductPriceService.class);
	@Override
	public boolean savePrice(PriceModel price) {
		boolean flag = false;
		try{
			begin();
			merge(price);
			commit();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return flag;
	}

}
