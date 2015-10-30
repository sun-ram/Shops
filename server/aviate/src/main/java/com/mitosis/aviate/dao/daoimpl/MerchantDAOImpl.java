package com.mitosis.aviate.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.mitosis.aviate.dao.MerchantDAO;
import com.mitosis.aviate.model.CustomerModel;
import com.mitosis.aviate.model.MerchantDetails;
import com.mitosis.aviate.model.StoreModel;
import com.mitosis.aviate.model.WarehouseModel;

public class MerchantDAOImpl extends BaseService implements MerchantDAO {
	final static Logger log =Logger.getLogger(MerchantDAOImpl.class); 
	@Override
	public boolean addMerchant(MerchantDetails merchantDetails) {
		boolean flag = false;
		try{
			begin();
		    if(merchantDetails.getMerchantId()==0){
		    		persist(merchantDetails);
		    		flag=true;
		    		flush();
					commit();
		    }
		    else{
			merge(merchantDetails);
			flag = true;
			flush();
			commit();
		  }
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
	public List<MerchantDetails> getMerchantList() {
		List<MerchantDetails> merchantList = new ArrayList<MerchantDetails>();
		try{
			begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<MerchantDetails> criteriaQuery = criteriaBuilder.createQuery(MerchantDetails.class);
			Root<MerchantDetails> rootQuery = criteriaQuery.from(MerchantDetails.class);
			criteriaQuery.select(rootQuery);
			merchantList = entityManager.createQuery(criteriaQuery).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return merchantList;
	}
	
	public boolean removeMerchant(long merchantId)
	{
		boolean flag = false;
		try{
			begin();
			MerchantDetails merchantDetails;
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<MerchantDetails> cq = qb.createQuery(MerchantDetails.class);
			Root<MerchantDetails> merchantobj = cq.from(MerchantDetails.class);
			cq.select(merchantobj);
			cq.where(qb.equal(merchantobj.get("merchantId"),merchantId));
			merchantDetails = entityManager.createQuery(cq).getSingleResult();
			remove(merchantDetails);
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
	public List<MerchantDetails> getMerchantListByQuery(String param) {
		// TODO Auto-generated method stub
		begin();
		Query query = createQuery("select e from MerchantDetails e where e.userName= :userName", MerchantDetails.class);
		query.setParameter("userName",param);
		List<MerchantDetails> merchant = query.getResultList();
		return merchant;
	}

	@Override
	public MerchantDetails getMerchantById(long merchantId) {
		begin();
		MerchantDetails merchantDetails;
		try{
			
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<MerchantDetails> cq = qb.createQuery(MerchantDetails.class);
			Root<MerchantDetails> merchantobj = cq.from(MerchantDetails.class);
			cq.select(merchantobj);
			cq.where(qb.equal(merchantobj.get("merchantId"),merchantId));
			merchantDetails = entityManager.createQuery(cq).getSingleResult();
		}catch(Exception e){
			merchantDetails=null;
			e.printStackTrace();
			log.error(e.getMessage());
			return merchantDetails;
		}finally{
			close();
		}
		return merchantDetails;
	}

}
