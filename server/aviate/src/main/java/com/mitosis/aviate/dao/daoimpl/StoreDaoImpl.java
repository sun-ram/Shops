package com.mitosis.aviate.dao.daoimpl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.StoreDao;
import com.mitosis.aviate.model.CustomerDetailsModel;
import com.mitosis.aviate.model.CustomerModel;
import com.mitosis.aviate.model.ProductImages;
import com.mitosis.aviate.model.SalesOrderModel;
import com.mitosis.aviate.model.StoreModel;

public class StoreDaoImpl extends BaseService  implements StoreDao {

	@Override
	public boolean addUpdateStoreDetails(StoreModel storeModel) {
		boolean flag = false;
		try{
			begin();
			System.out.println(storeModel);
			if(storeModel.getStoreId()==null){
				persist(storeModel);
			}
			else{
			merge(storeModel);
			}
			flush();
			commit();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return flag;
	}

	@Override
	public boolean removeStore(long storeId) {

		boolean flag = false;
		try{
			begin();
			StoreModel storeModel;
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<StoreModel> cq = qb.createQuery(StoreModel.class);
			Root<StoreModel> storeobj = cq.from(StoreModel.class);
			cq.select(storeobj);
			cq.where(qb.equal(storeobj.get("storeId"),storeId));
			storeModel = entityManager.createQuery(cq).getSingleResult();
			remove(storeModel);
			flush();
			clear();
			commit();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return flag;
	}

	@Override
	public List<CustomerModel> getEmployeeList(String storeId , List<String> roles) {
		
		List<CustomerModel> employeeList = null;
		CommonDao commonDao = new CommonDaoImpl();
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CustomerModel> cq = qb.createQuery(CustomerModel.class);
			Root<CustomerModel> customerModel = cq.from(CustomerModel.class);
			Root<CustomerDetailsModel> customerDetailsModel = cq.from(CustomerDetailsModel.class);
			cq.select(customerModel);
			Expression<String> exp = customerModel.get("role");
			Predicate predicate = exp.in(roles);
			if(storeId.equals("ALL")){
				cq.where(predicate);
			}else{
				cq.where(qb.equal(customerDetailsModel.get("storeId"),storeId)
						,qb.equal(customerDetailsModel.get("customerId"),customerModel.get("customerId"))
						,predicate);
			}
			employeeList = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return employeeList;
	}

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
		CommonDao commonDao = new CommonDaoImpl();
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
				cq.where(qb.equal(storeobj.get("merchantId"),requestObj.getString("merchantId")),
						qb.equal(storeobj.get("storeId"),requestObj.getString("storeId")));
			}
			salesOrderModel = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return salesOrderModel;
	}

	@Override
	public boolean updateOrderStatus(SalesOrderModel salesOrderModel) {
		SalesOrderModel salesOrder = null;
		boolean flag = false;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<SalesOrderModel> cq = qb.createQuery(SalesOrderModel.class);
			Root<SalesOrderModel> storeobj = cq.from(SalesOrderModel.class);
			cq.select(storeobj);
			cq.where(qb.equal(storeobj.get("orderId"),salesOrderModel.getOrderId()));
			salesOrder = entityManager.createQuery(cq).getSingleResult();
			salesOrder.setOrderStatus(salesOrderModel.getOrderStatus());
			salesOrder.setUserSign(salesOrderModel.getUserSign());
			merge(salesOrder);
			flush();
			clear();
			commit();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return flag;
	}
	
	@Override
	public boolean removeEmployee(Long customerId) {
		CustomerModel customerModel = null;
		boolean flag = false;
		try{
			begin();
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CustomerModel> cq = cb.createQuery(CustomerModel.class);
			Root<CustomerModel> cm = cq.from(CustomerModel.class);
			cq.select(cm);
			cq.where(cb.equal(cm.get("customerId"), customerId));
			customerModel = entityManager.createQuery(cq).getSingleResult();
			remove(customerModel);
			commit();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return flag;
	}

	@Override
	public StoreModel getStoreLogoByImageName(String imageName) {

		StoreModel storeImage = null;
		try{
			begin();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<StoreModel> cq = qb.createQuery(StoreModel.class);
			Root<StoreModel> si = cq.from(StoreModel.class);
			cq.select(si);
			cq.where(qb.equal(si.get("imageName"),imageName));
			storeImage=entityManager.createQuery(cq).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return storeImage;
	}
	
	@Override
	public List<StoreModel> getStoreByMerchant(JSONObject merchantObj) {
		List<StoreModel> salesOrderModel = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<StoreModel> cq = qb.createQuery(StoreModel.class);
			Root<StoreModel> storeobj = cq.from(StoreModel.class);
			cq.select(storeobj);
			cq.where(qb.equal(storeobj.get("merchantId"),merchantObj.getString("merchantId")));
			salesOrderModel = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return salesOrderModel;
	}

	@Override
	public StoreModel getStoreDetailsById(long storeId) {
		try{
			begin();
			Query query = createQuery("select e from StoreModel e where e.storeId= :storeId", StoreModel.class);
			query.setParameter("storeId", storeId);
			StoreModel storeDetail = (StoreModel) query.getSingleResult();
			// TODO: handle exception
			return storeDetail;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			close();
		}
	}
}
