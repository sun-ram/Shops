package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.RoleDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Role;

/**
 * @author prabakaran
 *
 * @param <T>
 */
@Repository
@Transactional
public class RoleDaoImpl<T> extends CustomHibernateDaoSupport<T> implements RoleDao<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Role getRole(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);
		criteria.add(Restrictions.eq("name", name.toString()));
		return (Role) findUnique(criteria);
	}

}
