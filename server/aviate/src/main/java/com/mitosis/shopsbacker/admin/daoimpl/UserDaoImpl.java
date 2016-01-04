package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.UserDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.util.OrderStatus;

/**
 * @author prabakaran
 *
 * @param <T>
 * 
 * Reviewed by Sundaram 28/11/2015
 */

@Repository
public class UserDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
		UserDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void saveUser(User user) {
		try {
			save((T) user);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateUser(User user) {
		try {
			update((T) user);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteUser(User user) {
		try {
			delete((T) user);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User getUser(String id) {
		return (User) getSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByStore(Store store) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("store", store));
		criteria.add(Restrictions.eq("isactive",'Y'));
		return (List<User>) findAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByMerchant(Merchant merchant) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("merchant", merchant));
		criteria.add(Restrictions.eq("isactive",'Y'));
		return (List<User>) findAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByStoreAndRole(Store store, Role role) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("store", store));
		criteria.add(Restrictions.eq("role", role));
		criteria.add(Restrictions.eq("isactive",'Y'));
		return (List<User>) findAll(criteria);
	}

	@Override
	public User getUserByName(String userName, boolean isFromLogin) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
			criteria.add(Restrictions.eq("userName", userName));
			if(isFromLogin){
				criteria.add(Restrictions.eq("isactive", 'Y'));
			}
			return (User) findUnique(criteria);
		} catch (Throwable e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(List<Role> role, Store store) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("store", store));
		criteria.add(Restrictions.in("role", role));
		criteria.add(Restrictions.eq("isactive",'Y'));
		criteria.addOrder(Order.desc("created"));
		return (List<User>) findAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(List<Role> role, Merchant merchant) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("merchant", merchant));
		criteria.add(Restrictions.in("role", role));
		criteria.add(Restrictions.eq("isactive",'Y'));
		criteria.addOrder(Order.desc("created"));
		return (List<User>) findAll(criteria);
	}

	public int inActiveUsers(Merchant merchant) {
		Query updateQuery = getSession().createQuery(
				"update User set isactive='N' where merchant = :merchant");
		updateQuery.setParameter("merchant", merchant);
		return updateQuery.executeUpdate();
	}

	@Override
	public List<User> filterAssignedShoppers(Store store) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SalesOrder.class);
		criteria.add(Restrictions.eq("store", store));
		List<String> statuses = new ArrayList<String>();
		statuses.add(OrderStatus.Shoper_Assigned.toString());
		statuses.add(OrderStatus.Picked.toString());
		criteria.add(Restrictions.in("status",statuses));
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("shopper"));
		criteria.setProjection(proList);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User>  userList=	(List<User>) findAll(criteria);
        return userList;
	}
	
	public List<User> getUsers(Merchant merchant,Store store,String roleName){
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(merchant!=null){
			criteria.add(Restrictions.eq("merchant", merchant));
		}
		if(store!=null){
			criteria.add(Restrictions.eq("store", store));
		}
		if(roleName!=null){
			criteria.createAlias("role", "rl");
			criteria.add(Restrictions.eq("rl.name", roleName).ignoreCase());
		}
		criteria.add(Restrictions.eq("isactive",'Y'));
		criteria.addOrder(Order.desc("created"));
		return (List<User>) findAll(criteria);
	}
}
