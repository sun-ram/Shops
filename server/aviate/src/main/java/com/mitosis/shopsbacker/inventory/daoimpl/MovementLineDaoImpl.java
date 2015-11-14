package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.MovementLineDao;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.MovementLine;

/**
 * @author RiyazKhan.M
 */
@Repository
public class MovementLineDaoImpl<T> extends CustomHibernateDaoSupport<T>
		implements MovementLineDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void addMovementLine(MovementLine movementLine) {
		try {
			save((T) movementLine);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeMovementLine(MovementLine movementLine) {
		try {
			delete((T) movementLine);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovementLine> getMovementLineList(Movement movement) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MovementLine.class);
		criteria.add(Restrictions.eq("movement", movement));
		criteria.add(Restrictions.eq("isactive", 'Y'));
		return ((List<MovementLine>) findAll(criteria));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeMovementLineByMovement(Movement movement) {

		DetachedCriteria criteria = DetachedCriteria
				.forClass(MovementLine.class);
		criteria.add(Restrictions.eq("movement", movement));
		criteria.add(Restrictions.eq("isactive", 'Y'));

		List<MovementLine> movementLines = (List<MovementLine>) findAll(criteria);

		for (MovementLine movementLine : movementLines) {
			delete((T) movementLine);
		}
	}

}
