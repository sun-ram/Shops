package com.mitosis.aviate.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.BinProductDAO;
import com.mitosis.aviate.model.BinProductModel;
import com.mitosis.aviate.model.SalesOrderModel;
import com.mitosis.aviate.dao.CommonDao;

public class BinProductDAOImpl extends BaseService implements BinProductDAO{
	Logger log=Logger.getLogger(BinProductDAOImpl.class);
	@Override
	public List<BinProductModel> getBinProducts(Long storeId, Long productId) {
			List<BinProductModel> listOfBinProducts = new ArrayList<BinProductModel>();
			try{
				begin();
				entityManager.getEntityManagerFactory().getCache().evictAll();
				CriteriaBuilder qb = entityManager.getCriteriaBuilder();
				CriteriaQuery<BinProductModel> cq = qb.createQuery(BinProductModel.class);
				Root<BinProductModel> binProduct = cq.from(BinProductModel.class);
				cq.select(binProduct);
				cq.where(qb.equal(binProduct.get("storeId"),storeId)
						,qb.equal(binProduct.get("productId"),productId));
				listOfBinProducts = entityManager.createQuery(cq).getResultList();
			}catch(Exception e){
				e.printStackTrace();
				log.error(e.getMessage());
				throw e;
			}finally{
				close();
			}
			return listOfBinProducts;
	}

	@Override
	public List<BinProductModel> getBinProducts(Long storeId) {
		List<BinProductModel> listOfBinProducts = new ArrayList<BinProductModel>();
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<BinProductModel> cq = qb.createQuery(BinProductModel.class);
			Root<BinProductModel> binProduct = cq.from(BinProductModel.class);
			cq.select(binProduct);
			cq.where(qb.equal(binProduct.get("storeId"),storeId));
			cq.orderBy(qb.asc(binProduct.get("productId")));
			listOfBinProducts = entityManager.createQuery(cq).getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return listOfBinProducts;		
	}
	
	@Override
	public List<BinProductModel> getBinProducts(JSONObject merchant) {
		List<BinProductModel> listOfBinProducts = new ArrayList<BinProductModel>();
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<BinProductModel> cq = qb.createQuery(BinProductModel.class);
			Root<BinProductModel> binProduct = cq.from(BinProductModel.class);
			cq.select(binProduct);
			cq.where(qb.equal(binProduct.get("merchantId"),merchant.getLong("merchantId")));
			cq.orderBy(qb.asc(binProduct.get("productId")));
			listOfBinProducts = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}finally{
			close();
		}
		return listOfBinProducts;		
	}

}
