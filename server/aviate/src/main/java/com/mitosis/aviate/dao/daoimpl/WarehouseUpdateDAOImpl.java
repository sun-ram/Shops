package com.mitosis.aviate.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.mitosis.aviate.dao.StorageBinDAO;
import com.mitosis.aviate.dao.WarehouseUpdateDAO;
import com.mitosis.aviate.model.StorageBinModel;
import com.mitosis.aviate.model.WarehouseModel;

public class WarehouseUpdateDAOImpl extends BaseService implements WarehouseUpdateDAO {
	Logger log=Logger.getLogger(WarehouseUpdateDAOImpl.class);
	@Override
	public boolean addWarehouse(WarehouseModel warehouse) {
		boolean flag=false;
		try{
			begin();
			EntityTransaction transaction = entityManager.getTransaction();
			entityManager.persist(warehouse);
			transaction.commit();
			flag=true;
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			if(entityManager!=null){
				entityManager.getTransaction().rollback();
			}
					
		}finally{
			close();
		}
		return flag;
	}

	@Override
	public boolean deleteWarehouse(String strWarehouseId) {
		boolean flag = false;
		try{
			begin();
			int warehouseId=Integer.parseInt(strWarehouseId);
			WarehouseModel warehouse;
			warehouse = entityManager.find(WarehouseModel.class,warehouseId);
			List<StorageBinModel> storgaBins = listOfStorgaBins(strWarehouseId);
			for(StorageBinModel storgaBin:storgaBins){
				remove(entityManager.getReference(StorageBinModel.class,storgaBin.getStorageBinId()));
				flush();
			}
			remove(entityManager.getReference(WarehouseModel.class,warehouseId));
			flush();
			clear();
			commit();
			flag = true;
		}catch(Exception e){
			rollback();
			e.printStackTrace();
			log.error(e.getMessage());
		}finally{
			close();
		}
		return flag;	
	}


	@Override
	public List<StorageBinModel> listOfStorgaBins(String warehouseId) {
		List<StorageBinModel> listOfStorageBins=new ArrayList<StorageBinModel>();
		try{
			begin();			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<StorageBinModel> criteriaQuery = criteriaBuilder.createQuery(StorageBinModel.class);
			Root<StorageBinModel> rootQuery = criteriaQuery.from(StorageBinModel.class);
			criteriaQuery.select(rootQuery);
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("warehouseId"),warehouseId));
			listOfStorageBins =  entityManager.createQuery(criteriaQuery).getResultList();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return listOfStorageBins;
	}


	@Override
	public List<WarehouseModel> checkUniqueName(String warehouseName,String storeId) {
		List<WarehouseModel> listOfWarehouse=new ArrayList<WarehouseModel>();
		try{
			begin();			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<WarehouseModel> criteriaQuery = criteriaBuilder.createQuery(WarehouseModel.class);
			Root<WarehouseModel> rootQuery = criteriaQuery.from(WarehouseModel.class);
			criteriaQuery.select(rootQuery);
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("warehouseName"),warehouseName)
					,criteriaBuilder.equal(rootQuery.get("storeId"),storeId));
			listOfWarehouse =  entityManager.createQuery(criteriaQuery).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return listOfWarehouse;
	}
	
	@Override
	public WarehouseModel getSingleWarehouse(int warehouseId) {
		WarehouseModel warehouse = new WarehouseModel();
		try{
			begin();			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<WarehouseModel> criteriaQuery = criteriaBuilder.createQuery(WarehouseModel.class);
			Root<WarehouseModel> rootQuery = criteriaQuery.from(WarehouseModel.class);
			criteriaQuery.select(rootQuery);
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("warehouseId"),warehouseId));
			warehouse =  entityManager.createQuery(criteriaQuery).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return warehouse;
	}
	public boolean updateWarehouse(WarehouseModel warehouseModel){
		boolean flag = false;
		try{
			begin();
			merge(warehouseModel);
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


}
