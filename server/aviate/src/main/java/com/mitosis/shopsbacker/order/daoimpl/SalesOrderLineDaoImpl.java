package com.mitosis.shopsbacker.order.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.order.dao.SalesOrderLineDao;

@Repository
@Transactional
public class SalesOrderLineDaoImpl<T> extends CustomHibernateDaoSupport<T> 
	implements SalesOrderLineDao, Serializable{
	
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional
	public List<SalesOrderLine> getSalesOrderLineById(String id) {
		try {
			return (List<SalesOrderLine>) getSession().get(SalesOrderLine.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Override
	public List getSalesOrderLineBysalesOrder(SalesOrder salesOrder) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SalesOrderLine.class);
		criteria.add(Restrictions.eq("salesOrder", salesOrder));
		return ((List<SalesOrderLine>) findAll(criteria));
	}

}
