package com.mitosis.aviate.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.mitosis.aviate.dao.StorageBinDAO;
import com.mitosis.aviate.model.StorageBinModel;

public class StorageBinDAOImpl extends BaseService implements StorageBinDAO {
Logger log=Logger.getLogger(StorageBinDAOImpl.class);
	@Override
	public boolean addStorageBin(StorageBinModel storageBin) {
		boolean flag = false;
		try{
			begin();
			EntityTransaction transaction = entityManager.getTransaction();
			entityManager.persist(storageBin);
			transaction.commit();
			flag=true;
		}catch(Exception e){
			if(entityManager!=null){
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return flag;
	}

	@Override
	public boolean removeStorageBin(String storageBinId,boolean removalFlag) {
		boolean flag = false;
		try{
			begin();
			int storagebinId=Integer.parseInt(storageBinId);
//			StorageBinModel storageBin;
//			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//			CriteriaQuery<StorageBinModel> criteriaQuery = criteriaBuilder.createQuery(StorageBinModel.class);
//			Root<StorageBinModel> rootQuery = criteriaQuery.from(StorageBinModel.class);
//			criteriaQuery.select(rootQuery);
//			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("storageBinId"),storageBinId));
			StorageBinModel	storageBin = entityManager.find(StorageBinModel.class, storagebinId);
			if(storageBin!=null){
				remove(storageBin);
			}
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
	public List<StorageBinModel> uniqueNameChecking(String binName,String warehouseId) {
		List<StorageBinModel> listOfbins=new ArrayList<StorageBinModel>();
		try{
			begin();			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<StorageBinModel> criteriaQuery = criteriaBuilder.createQuery(StorageBinModel.class);
			Root<StorageBinModel> rootQuery = criteriaQuery.from(StorageBinModel.class);
			criteriaQuery.select(rootQuery);
			criteriaQuery.where(criteriaBuilder.equal(rootQuery.get("storageBinName"),binName)
					,criteriaBuilder.equal(rootQuery.get("warehouseId"),warehouseId));
			listOfbins =  entityManager.createQuery(criteriaQuery).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}
		return listOfbins;
	}
}
