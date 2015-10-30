package com.mitosis.aviate.dao.daoimpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.CheckoutDao;
import com.mitosis.aviate.model.Address;
import com.mitosis.aviate.model.OrderTax;
import com.mitosis.aviate.model.SalesOrderModel;
import com.mitosis.aviate.model.StoreModel;

public class CheckoutDaoImpl extends BaseService<Object> implements CheckoutDao {
	@Override
	public boolean update(Address address) {
		boolean flag = false;
		try{
			begin();
			if(address.getAddressId() != null 
					&& !address.getAddressId().equals("")){
				merge(address);
			}else{
				persist(address);
			}
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
	public boolean removeAddress(Long addressId) {
		boolean flag = false;
		try{
			begin();
			Address address;
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Address> cq = qb.createQuery(Address.class);
			Root<Address> ga = cq.from(Address.class);
			cq.select(ga);
			cq.where(qb.equal(ga.get("addressId"),addressId));
			address = entityManager.createQuery(cq).getSingleResult();
			remove(address);
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
	public JSONObject getStoreNameMaxOrderId(Long storeId) {
		JSONObject storeOrder = new JSONObject();
		try{
			begin();
			/*get store name by id*/
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<String> cq = qb.createQuery(String.class);
			Root<StoreModel> getStoreName = cq.from(StoreModel.class);
			Path storeNamePath = getStoreName.get("storeName");
			cq.select(storeNamePath);
			cq.where(qb.equal(getStoreName.get("storeId"),storeId));
			String storeName = entityManager.createQuery(cq).getSingleResult();

			/*get max orderNo*/
			CriteriaBuilder cqb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> a = cqb.createQuery(Long.class);
			Root<SalesOrderModel> maxOrderNo = a.from(SalesOrderModel.class);
			Path orderPath = maxOrderNo.get("orderNo");
			a.select(cqb.max(orderPath));
			Long orderNo = entityManager.createQuery(a).getSingleResult();
			storeOrder.put("storeName", storeName);
			storeOrder.put("orderNo", orderNo);

		}catch(Exception e){
			e.printStackTrace();

		}finally{
			close();
		}
		return storeOrder;
	}

	@Override
	public boolean saveOrder(SalesOrderModel orderedItems) {
		boolean flag = false;
		try{
			begin();
			flush();
			clear();
			persist(orderedItems);
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
	public boolean saveOrderTax(OrderTax orderTax) {
		boolean flag = false;
		try{
			begin();
			flush();
			clear();
			persist(orderTax);
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
	public List<Address> getAddress(int customerId) {
		List<Address> address = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Address> cq = qb.createQuery(Address.class);
			Root<Address> getAddressList = cq.from(Address.class);
			cq.select(getAddressList);
			cq.where(qb.equal(getAddressList.get("customerId"),customerId));
			address = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return address;
	}

	@Override
	public SalesOrderModel conformPayment(String orderId, String transactionNo,
			String paymentMethod) {
		SalesOrderModel salesOrder = new SalesOrderModel();
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<SalesOrderModel> cq = qb.createQuery(SalesOrderModel.class);
			Root<SalesOrderModel> getAddressList = cq.from(SalesOrderModel.class);
			cq.select(getAddressList);
			cq.where(qb.equal(getAddressList.get("orderId"),orderId));
			salesOrder = entityManager.createQuery(cq).getSingleResult();
			salesOrder.setIsPaid("Paid");
			salesOrder.setOrderStatus("Initialized");
			salesOrder.setPaymentMethod(paymentMethod);
			salesOrder.setTransactionNo(transactionNo);
			merge(salesOrder);
			commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return salesOrder;
	}
}
