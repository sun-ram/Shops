package com.mitosis.shopsbacker.inventory.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.inventory.dao.UomDao;
import com.mitosis.shopsbacker.model.Uom;

/**
 * @author fayaz
 */

@Repository
@Transactional
public class UomDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
		UomDao<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void addUOM(Uom productUnitOfMeasure) {
		try {
			save((T) productUnitOfMeasure);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@Override
	public void updateUOM(Uom productUnitOfMeasure) {
		try {
			update((T) productUnitOfMeasure);
		} catch ( Exception  e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@Override
	public List<Uom> getAllUOM() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Uom.class);
			criteria.add(Restrictions.eq("isactive", 'Y'));
			return (List<Uom>) findAll(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public Uom getUOMById(String uomId) {
		try {
			return (Uom) getSession().get(Uom.class, uomId);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public void removeUOM(Uom productUnitOfMeasure) {
		try {
			delete((T) productUnitOfMeasure);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	/**
	 * @author Anbukkani Gajendran
	 * @param Uom Name
	 * @throws Exception
	 * @return Uom
	 */
	@Override
	public Uom getUomByName(String name) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Uom.class);
			criteria.add(Restrictions.eq("name",name));
			//criteria.add(Restrictions.eq("isactive", 'Y'));
			return (Uom) findUnique(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
	
	@Override
	public Uom getUom(String uomId,String name) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Uom.class);
			criteria.add(Restrictions.eq("name",name));
			criteria.add(Restrictions.ne("uomId",uomId));
			//criteria.add(Restrictions.eq("isactive", 'Y'));
			return (Uom) findUnique(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
}
