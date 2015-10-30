package com.mitosis.aviate.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.mitosis.aviate.dao.PhysicalInventoryDAO;
import com.mitosis.aviate.model.InventoryLineModel;
import com.mitosis.aviate.model.InventoryModel;
import com.mitosis.aviate.model.WarehouseModel;

public class PhysicalInventoryDAOImpl extends BaseService implements
PhysicalInventoryDAO {
Logger log=Logger.getLogger(PhysicalInventoryDAOImpl.class);
	@Override
	public InventoryModel addInventory(InventoryModel inventory) {
		boolean flag=false;
		try{
			begin();
			EntityTransaction transaction = entityManager.getTransaction();
			entityManager.persist(inventory);
			transaction.commit();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
			if(entityManager!=null){
				entityManager.getTransaction().rollback();
			}	
			log.error(e.getMessage());
			throw e;

		}finally{
			close();
		}
		return inventory;
	}

	@Override
	public boolean removeInventory(String inventoryId) {

		boolean flag = false;
		try{
			begin();
			/*InventoryModel inventory;*/
			/*CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<InventoryModel> criteriaQuery = criteriaBuilder.createQuery(InventoryModel.class);
			Root<InventoryModel> rootQuery = criteriaQuery.from(InventoryModel.class);
			criteriaQuery.select(rootQuery);
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("inventoryId"),Integer.parseInt(inventoryId)));
			inventory = entityManager.createQuery(criteriaQuery).getSingleResult();
			remove(inventory);*/
			 Query query = entityManager.createQuery("DELETE FROM InventoryModel inventoryModel WHERE inventoryModel.inventoryId ="+inventoryId);
		    query.executeUpdate();
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
	public List<WarehouseModel> warehouseList(String storeId) {
		List<WarehouseModel> listOfWarehouse=new ArrayList<WarehouseModel>();
		try{
			begin();			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<WarehouseModel> criteriaQuery = criteriaBuilder.createQuery(WarehouseModel.class);
			Root<WarehouseModel> rootQuery = criteriaQuery.from(WarehouseModel.class);
			criteriaQuery.select(rootQuery);
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("storeId"),storeId));
			listOfWarehouse =  entityManager.createQuery(criteriaQuery).getResultList();
		}catch(Exception e){
			e.printStackTrace();	
			log.error(e.getMessage());
			throw e;
		}
		return listOfWarehouse;
	}

	@Override
	public List<InventoryModel> uniqueNameChecking(String storeId,
			String warehouseId, String inventoryName, String merchantId) {
		List<InventoryModel> uniqueInventoryList=new ArrayList<InventoryModel>();
		try{
			begin();			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<InventoryModel> criteriaQuery = criteriaBuilder.createQuery(InventoryModel.class);
			Root<InventoryModel> rootQuery = criteriaQuery.from(InventoryModel.class);
			criteriaQuery.select(rootQuery);
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("storeId"),storeId)
					,criteriaBuilder.equal(rootQuery.get("warehouseId"),warehouseId)
					,criteriaBuilder.equal(rootQuery.get("inventoryName"),inventoryName));
			uniqueInventoryList =  entityManager.createQuery(criteriaQuery).getResultList();
		}catch(Exception e){
			e.printStackTrace();	
			log.error(e.getMessage());
			throw e;
		}
		return uniqueInventoryList;
	}

	@Override
	public boolean updateInventroy(int inventoryId) {
		boolean flag = false;
		try{
			begin();
			/*InventoryModel inventoryModel = (InventoryModel)entityManager.find(InventoryModel.class, inventoryId);
			inventoryModel.setProcessed(true);
			entityManager.merge(inventoryModel);
			flush();
			commit();*/
			Query query = entityManager.createQuery("UPDATE InventoryModel im  SET im.isProcessed = :isProcessed  WHERE im.inventoryId = :inventoryId");
			query.setParameter("isProcessed", true);
			query.setParameter("inventoryId", inventoryId);
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
	public InventoryModel getInventory(long long1) {
		InventoryModel inventory;
		try{
			begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<InventoryModel> criteriaQuery = criteriaBuilder.createQuery(InventoryModel.class);
			Root<InventoryModel> rootQuery = criteriaQuery.from(InventoryModel.class);
			criteriaQuery.select(rootQuery);
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("inventoryId"),long1));
			inventory = entityManager.createQuery(criteriaQuery).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();	
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return inventory;
	}


	@Override
	public void updateInventories(InventoryModel uniqueInventoryList) {
		try{
			begin();
			merge(uniqueInventoryList);
			flush();
			clear();
			commit();
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		
	}
}
