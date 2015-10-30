package com.mitosis.aviate.dao.daoimpl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.CustomerDao;
import com.mitosis.aviate.model.CustomerDetailsModel;
import com.mitosis.aviate.model.CustomerModel;
import com.mitosis.aviate.model.CustomerProductList;
import com.mitosis.aviate.util.AVMessageStatus;

public class CustomerDaoImpl extends BaseService implements CustomerDao {

	final static Logger log = Logger
			.getLogger(CustomerDaoImpl.class);
	CommonDao commonDao = new CommonDaoImpl(); 

	@Override
	public void save(CustomerModel customerModel) {
		try{
			begin();
			Query query = createQuery("select e from CustomerModel e where e.emailId= :emailId", CustomerModel.class);
			query.setParameter("emailId", customerModel.getEmailId());
			try
			{
				List<CustomerModel> customer = query.getResultList();

				if(!customer.isEmpty())
				{
					customerModel.setStatus(com.mitosis.aviate.util.Constants.FAILURE);
				}else{
					persist(customerModel);
					flush();
					clear();
					commit();
					customerModel.setStatus(com.mitosis.aviate.util.Constants.SUCCESS);
				}
			}
			catch(NoResultException e)
			{
				e.printStackTrace();
				log.error(e.getMessage());
				throw e;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}

	@Override
	public List<CustomerDetailsModel> getListByStoreId(Long store_id) {
		try{
			begin();
			Query query = createQuery("select e from CustomerDetailsModel e where e.storeId= :storeId", CustomerDetailsModel.class);
			query.setParameter("storeId", store_id);
			List<CustomerDetailsModel> customerDetails = query.getResultList();
			// TODO: handle exception
			return customerDetails;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}

	@Override
	public CustomerDetailsModel getListBycustomerId(Long customerId) {
		try{
			begin();
			CustomerDetailsModel cm=new CustomerDetailsModel();
			Query query = createQuery("select e from CustomerDetailsModel e where e.customerId= :customerId", CustomerDetailsModel.class);
			query.setParameter("customerId", customerId);
			cm=(CustomerDetailsModel) query.getSingleResult();
			return cm;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}

	@Override
	public boolean remove(long id) {
		boolean flag = false;
		try{
			begin();
			CustomerModel customerModel;
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CustomerModel> cq = qb.createQuery(CustomerModel.class);
			Root<CustomerModel> storeobj = cq.from(CustomerModel.class);
			cq.select(storeobj);
			cq.where(qb.equal(storeobj.get("customerId"),id));
			customerModel = entityManager.createQuery(cq).getSingleResult();
			remove(customerModel);
			flush();
			clear();
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

	@Override
	public void saveOtherRole(CustomerModel customerModel) {
		try{
			begin();
			persist(customerModel);
			flush();
			clear();
			commit();
			customerModel.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}



	@Override
	public List<CustomerModel> getListByQuery(String parm,String role) {
		try{
			begin();
			Query query = createQuery("select e from CustomerModel e where e.emailId= :emailId and e.role= :role", CustomerModel.class);
			query.setParameter("emailId", parm);
			query.setParameter("role", role);
			List<CustomerModel> customer = query.getResultList();
			return customer;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}

	@Override
	public List<CustomerModel> getListByQuery2(String parm, String role) {
		try{
			begin();
			Query query = createQuery("select e from CustomerModel e where e.emailId= :emailId and e.role <> :role", CustomerModel.class);
			query.setParameter("emailId", parm);
			query.setParameter("role", role);
			List<CustomerModel> customer = query.getResultList();
			return customer;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}

	}

	public void updateCustomerDetails(CustomerDetailsModel credencial){
		try{
			begin();
			merge(credencial);
			flush();
			clear();
			commit();

		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}

	@Override
	public CustomerModel getCustomerPass(String emailId) {
		CustomerModel customerModel = null;
		try
		{	
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CustomerModel> cq = qb.createQuery(CustomerModel.class);
			Root<CustomerModel> cm = cq.from(CustomerModel.class);
			cq.select(cm);
			cq.where(qb.equal(cm.get("emailId"),emailId));
			customerModel = entityManager.createQuery(cq).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}
		finally{
			close();
		}
		return customerModel;
	}


	public CustomerModel checkLogin(String emailId, String password){
		CustomerModel customerModel = null;
		try
		{	
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CustomerModel> cq = qb.createQuery(CustomerModel.class);
			Root<CustomerModel> cm = cq.from(CustomerModel.class);
			cq.select(cm);
			cq.where(qb.equal(cm.get("emailId"),emailId),qb.equal(cm.get("password"),password));
			customerModel = entityManager.createQuery(cq).getSingleResult();
			if(!customerModel.equals(null) && customerModel.getPassword().equals(password)){
				customerModel.setStatus("SUCCESS");
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}
		finally{
			close();
		}
		return customerModel;
	}

	@Override
	public boolean updateDeviceDetails(CustomerDetailsModel customerDetailsModel) {
		boolean flag = false;
		CustomerDetailsModel customerDetails = null;
		try
		{	
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CustomerDetailsModel> cq = qb.createQuery(CustomerDetailsModel.class);
			Root<CustomerDetailsModel> cm = cq.from(CustomerDetailsModel.class);
			cq.select(cm);
			cq.where(qb.equal(cm.get("customerId"),customerDetailsModel.getCustomerId()));
			customerDetails = entityManager.createQuery(cq).getSingleResult();
			customerDetails.setDeviceType(customerDetailsModel.getDeviceType());
			customerDetails.setDeviceId(customerDetailsModel.getDeviceId());
			merge(customerDetails);
			commit();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}
		finally{
			close();
		}
		return flag;
	}

	@Override
	public CustomerDetailsModel getCusotmerByEmail(JSONObject credentials) throws Exception {
		CustomerDetailsModel customer = null;
		try
		{
			String emailId = credentials.getString("emailId");
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CustomerDetailsModel> cq = qb.createQuery(CustomerDetailsModel.class);
			Root<CustomerDetailsModel> cm = cq.from(CustomerDetailsModel.class);
			cq.select(cm);
			cq.where(qb.equal(cm.get("emailId"),emailId));
			customer = entityManager.createQuery(cq).getSingleResult();
			if(commonDao.isValidProperty(credentials, "deviceType") 
					&& commonDao.isValidProperty(credentials, "deviceId")){
				customer.setDeviceId(credentials.getString("deviceId"));
				customer.setDeviceType(credentials.getString("deviceType"));
				merge(customer);
			}
			flush();
			clear();
			commit();
		}catch(Exception e){
			log.info(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally{
			close();
		}

		return customer;
	}

	@Override
	public List<CustomerDetailsModel> getPickerDeviceId(JSONObject storeIdRole) throws Exception {

		List<CustomerDetailsModel> customer = null;
		try
		{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CustomerDetailsModel> cq = qb.createQuery(CustomerDetailsModel.class);
			Root<CustomerDetailsModel> cdm = cq.from(CustomerDetailsModel.class);
			Root<CustomerModel> cm = cq.from(CustomerModel.class);
			cq.select(cdm);
			cq.where(qb.equal(cdm.get("customerId"),cm.get("customerId")),
					qb.equal(cdm.get("storeId"),storeIdRole.getString("storeId")),
					qb.equal(cm.get("role"),storeIdRole.getString("role")));
			customer = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			log.info(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return customer;
	}

	@Override
	public void addMyList(CustomerProductList customerProduct) {
		try
		{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CustomerProductList> cq = qb.createQuery(CustomerProductList.class);
			Root<CustomerProductList> cml = cq.from(CustomerProductList.class);
			cq.select(cml);
			cq.where(qb.equal(cml.get("customerId"),customerProduct.getCustomerId()),
					qb.equal(cml.get("productId"),customerProduct.getProductId()),
					qb.equal(cml.get("storeId"),customerProduct.getStoreId()));
			List<CustomerProductList> cpl = entityManager.createQuery(cq).getResultList();
			if(cpl.isEmpty()){
				customerProduct.setActive(true);
				flush();
				persist(customerProduct);
				commit();
			}else{
				for(CustomerProductList customerProducts : cpl){
					customerProducts.setActive(true);
					merge(customerProducts);
					commit();
				}
			}
			customerProduct.setStatus(com.mitosis.aviate.util.Constants.SUCCESS);
		}catch(Exception e){
			log.info(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}

	public boolean removeMyList(CustomerProductList customerProduct) {
		CustomerProductList customerProducts = null;
		boolean flag = false;
		try{
			begin();
			/*Query query = entityManager.createQuery("DELETE FROM CustomerProductList c WHERE c.myListId =:myListId");
			query.setParameter("myListId",customerProduct.getMyListId());
			query.executeUpdate();*/
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CustomerProductList> cq = qb.createQuery(CustomerProductList.class);
			Root<CustomerProductList> getMyList = cq.from(CustomerProductList.class);
			cq.select(getMyList);
			if(customerProduct.getMyListId() != null)
				cq.where(qb.equal(getMyList.get("myListId"),customerProduct.getMyListId()));
			else if(customerProduct.getProductId() != null
					&& customerProduct.getCustomerId() != null){
				cq.where(qb.equal(getMyList.get("productId"),customerProduct.getProductId())
						,qb.equal(getMyList.get("customerId"),customerProduct.getCustomerId())
						,qb.equal(getMyList.get("storeId"),customerProduct.getStoreId()));
			}
			customerProducts = entityManager.createQuery(cq).getSingleResult();
			customerProducts.setActive(false);
			merge(customerProducts);
			flush();
			commit();
			flag = true;
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return flag;
	}

	@Override
	public List<CustomerProductList> getMyList(Long customerId, Long storeId) {
		List<CustomerProductList> myList = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CustomerProductList> cq = qb.createQuery(CustomerProductList.class);
			Root<CustomerProductList> getMyList = cq.from(CustomerProductList.class);
			cq.select(getMyList);
			cq.where(qb.equal(getMyList.get("customerId"),customerId),
					qb.equal(getMyList.get("active"),true),
					qb.equal(getMyList.get("storeId"),storeId));
			myList = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return myList;
	}

	@Override
	public void edit(CustomerModel customerModel) {
		try{
			begin();
			merge(customerModel);
			flush();
			clear();
			commit();
			customerModel.setStatus(AVMessageStatus.SUCCESS.getValue());
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}
	}






}
