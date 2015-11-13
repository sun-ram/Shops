package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.admin.dao.MovementDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;

@Repository
public class MovementDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
MovementDao<T>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void addMovement(Movement movement) {
		try {
			save((T) movement);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public void deleteMovement(Movement movement) {
		try {
			delete((T) movement);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public boolean uniqueNameChecking(Store store, Warehouse warehouse,
		String movementName, Merchant merchant) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Movement.class);
			criteria.add(Restrictions.eq("name", movementName));
			criteria.add(Restrictions.eq("store", store));
			criteria.add(Restrictions.eq("warehouse", warehouse));
			criteria.add(Restrictions.eq("merchant", merchant));
			List<Movement> movements =  (List<Movement>) findAll(criteria);
			boolean isUniqueName = false;
			if(movements.size()>0){
				isUniqueName = true;
			}
			return isUniqueName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public void updateMovement(Movement movement) {
		try {
			update((T) movement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public Movement getMovement(String movementId) {
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Movement.class);
			criteria.add(Restrictions.eq("movementId", movementId));
			return ((List<Movement>) findAll(criteria)).get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		}
	}

	@Override
	public List<Movement> getMovementListByStore(Store store) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Movement.class);
			criteria.add(Restrictions.eq("store", store));
			return ((List<Movement>) findAll(criteria));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		}
	}


}
