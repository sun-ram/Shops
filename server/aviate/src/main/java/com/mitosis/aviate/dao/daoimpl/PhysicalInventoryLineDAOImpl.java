package com.mitosis.aviate.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.mitosis.aviate.dao.PhysicalInventoryLineDAO;
import com.mitosis.aviate.model.InventoryLineModel;
import com.mitosis.aviate.model.InventoryModel;
import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.ProductUOM;
import com.mitosis.aviate.model.StorageBinModel;

public class PhysicalInventoryLineDAOImpl extends BaseService implements
		PhysicalInventoryLineDAO {
Logger log=Logger.getLogger(PhysicalInventoryLineDAOImpl.class);
	@Override
	public boolean addInventoryLine(InventoryLineModel inventoryLine) {
		boolean flag=false;
		try{
			begin();
			EntityTransaction transaction = entityManager.getTransaction();
			entityManager.persist(inventoryLine);
			transaction.commit();
			flag=true;
		}catch(Exception e){
			if(entityManager!=null){
				entityManager.getTransaction().rollback();
			}	
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return flag;
	}

	@Override
	public boolean removeInventory(String inventoryLineId) {
		boolean flag = false;
		try{
			begin();
			Query query = entityManager.createQuery("DELETE FROM InventoryLineModel ilm  WHERE ilm.inventoryLineId = :inventoryLineId");
			query.setParameter("inventoryLineId", Integer.parseInt(inventoryLineId));
			query.executeUpdate();
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
	public boolean removeInventoryById(int inventoryLineId) {
		boolean flag = false;
		try{
			begin();
			Query query = entityManager.createQuery("DELETE FROM InventoryLineModel ilm  WHERE ilm.inventoryLineId = :inventoryLineId");
			query.setParameter("inventoryLineId",inventoryLineId) ;
			query.executeUpdate();
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
	public List<ProductDetails> productList(String merchantId) {
		List<ProductDetails> listOfProducts=new ArrayList<ProductDetails>();
		try{
			begin();			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductDetails> criteriaQuery = criteriaBuilder.createQuery(ProductDetails.class);
			Root<ProductDetails> rootQuery = criteriaQuery.from(ProductDetails.class);
			criteriaQuery.select(rootQuery);
			//TODO : Need to add store id column to product details table  
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("merchantId"),merchantId));
			listOfProducts =  entityManager.createQuery(criteriaQuery).getResultList();		
		}catch(Exception e){
			e.printStackTrace();	
			log.error(e.getMessage());
			throw e;
		}
		return listOfProducts;
	}

	@Override
	public List<StorageBinModel> storageBinList(String warehouseId) {
		List<StorageBinModel> listOfBins=new ArrayList<StorageBinModel>();
		try{
			begin();			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<StorageBinModel> criteriaQuery = criteriaBuilder.createQuery(StorageBinModel.class);
			Root<StorageBinModel> rootQuery = criteriaQuery.from(StorageBinModel.class);
			criteriaQuery.select(rootQuery);			
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("warehouseId"),warehouseId));
			listOfBins =  entityManager.createQuery(criteriaQuery).getResultList();		
		}catch(Exception e){
			e.printStackTrace();	
			log.error(e.getMessage());
			throw e;
		}
		return listOfBins;
	}

	@Override
	public List<ProductUOM> unitOfMeasureList(String productId) {
		List<ProductUOM> listOfUoms=new ArrayList<ProductUOM>();
		try{
			begin();			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductUOM> criteriaQuery = criteriaBuilder.createQuery(ProductUOM.class);
			Root<ProductUOM> rootQuery = criteriaQuery.from(ProductUOM.class);
			criteriaQuery.select(rootQuery);
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("productId"),productId));
			listOfUoms =  entityManager.createQuery(criteriaQuery).getResultList();		
		}catch(Exception e){
			e.printStackTrace();	
			log.error(e.getMessage());
			throw e;
		}
		return listOfUoms;
	}

	@Override
	public List<InventoryModel> listOfInventoryies(String storeId) {
		List<InventoryModel> listOfUoms=new ArrayList<InventoryModel>();
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<InventoryModel> criteriaQuery = criteriaBuilder.createQuery(InventoryModel.class);
			Root<InventoryModel> rootQuery = criteriaQuery.from(InventoryModel.class);
			criteriaQuery.select(rootQuery);
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("storeId"),storeId));
			listOfUoms =  entityManager.createQuery(criteriaQuery).getResultList();		
		}catch(Exception e){
			e.printStackTrace();	
			log.error(e.getMessage());
			throw e;
		}
		return listOfUoms;
	}

	@Override
	public List<InventoryLineModel> getInventoryLineList(int inventoryId) {
		List<InventoryLineModel> listOfInventoryLine=new ArrayList<InventoryLineModel>();
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<InventoryLineModel> criteriaQuery = criteriaBuilder.createQuery(InventoryLineModel.class);
			Root<InventoryLineModel> rootQuery = criteriaQuery.from(InventoryLineModel.class);
			criteriaQuery.select(rootQuery);
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("inventoryId"),inventoryId));
			listOfInventoryLine =  entityManager.createQuery(criteriaQuery).getResultList();		
		}catch(Exception e){
			e.printStackTrace();	
			log.error(e.getMessage());
			throw e;
		}
		return listOfInventoryLine;
	}

	
}
