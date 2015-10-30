package com.mitosis.aviate.dao.daoimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.SalesOrderDAO;
import com.mitosis.aviate.model.SalesOrderModel;
import com.mitosis.aviate.util.CommonUtil;

public class SalesOrderDAOImpl extends BaseService implements SalesOrderDAO {
	CommonUtil util = new CommonUtil();
	CommonDao commonDao = new CommonDaoImpl();

	@Override
	public List<SalesOrderModel> getUserDetails(JSONObject requestObj) {
		List<SalesOrderModel> salesOrderModel = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<SalesOrderModel> cq = qb.createQuery(SalesOrderModel.class);
			Root<SalesOrderModel> storeobj = cq.from(SalesOrderModel.class);
			cq.select(storeobj);
			cq.where(qb.equal(storeobj.get("storeId"),requestObj.getString("storeId"))
					,qb.equal(storeobj.get("isPaid"),"Paid"));
			salesOrderModel = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return salesOrderModel;
	}

	@Override
	public List<SalesOrderModel> getOrderList(JSONObject requestObj) {
		List<SalesOrderModel> salesOrderModel = null;
		List<String> orderStat = Arrays.asList("Initialized", "Inprogress", "Readytoship","Shipping");
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<SalesOrderModel> cq = qb.createQuery(SalesOrderModel.class);
			Root<SalesOrderModel> storeobj = cq.from(SalesOrderModel.class);
			cq.select(storeobj);
			if(commonDao.isValidProperty(requestObj, "orderId")){
				cq.where(qb.equal(storeobj.get("orderId"),requestObj.getString("orderId")),
						qb.equal(storeobj.get("storeId"),requestObj.getString("storeId")));
			}else if(commonDao.isValidProperty(requestObj, "orderStatus")
					&& requestObj.getString("orderStatus").equals("ALL")){
				Expression<String> exp = storeobj.get("orderStatus");
				Predicate predicate = exp.in(orderStat);
				cq.where(qb.equal(storeobj.get("storeId"),requestObj.getString("storeId"))
						,predicate);
			}else if(commonDao.isValidProperty(requestObj, "orderStatus")){
				cq.where(qb.equal(storeobj.get("orderStatus"),requestObj.getString("orderStatus")),
						qb.equal(storeobj.get("storeId"),requestObj.getString("storeId")));
			}else if(commonDao.isValidProperty(requestObj, "merchantId")){
				cq.where(qb.equal(storeobj.get("merchantId"),requestObj.getString("merchantId")));
			}else if(commonDao.isValidProperty(requestObj, "storeId")){
				cq.where(qb.equal(storeobj.get("storeId"),requestObj.getString("storeId")));
			}
			salesOrderModel = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return salesOrderModel;
	}
	
	public List<SalesOrderModel> salesOrderDetailList(JSONObject requestObj) {
		// TODO Auto-generated method stub
		
		List<SalesOrderModel> leaveDetailList = new ArrayList<SalesOrderModel>();
		
		try {
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<SalesOrderModel> cq = qb.createQuery(SalesOrderModel.class);
			Root<SalesOrderModel> root = cq.from(SalesOrderModel.class);
			Path<Date> fromdate = root.get("created");
			Path<Date> todate = root.get("deliveryDate");
			Predicate condition1 = qb.greaterThanOrEqualTo(fromdate,util.stringToDate(requestObj.getString("fromDate")));
			Predicate condition2 = qb.lessThanOrEqualTo(todate,util.stringToDate(requestObj.getString("toDate")));
			Predicate conditions = qb.and(condition1,condition2);
			if(commonDao.isValidProperty(requestObj, "storeId")){
				cq.where(conditions,qb.equal(root.get("storeId"),requestObj.getInt("storeId")));
			}else if(commonDao.isValidProperty(requestObj, "merchantId")){
				cq.where(conditions,qb.equal(root.get("merchantId"),requestObj.getInt("merchantId")));
			}
			cq.select(root);
			cq.orderBy(qb.asc(root.get("created")), qb.asc(root.get("deliveryDate")));
			leaveDetailList = entityManager.createQuery(cq).getResultList();
			commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return leaveDetailList;
	}

}
