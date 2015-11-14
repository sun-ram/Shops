package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.admin.dao.RoleDao;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.model.Role;

/**
 * @author prabakaran
 *
 * @param <T>
 */
@Service("roleServiceImpl")
public class RoleServiceImpl<T> implements RoleService<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	RoleDao<T> roleDao;
	
	public RoleDao<T> getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao<T> roleDao) {
		this.roleDao = roleDao;
	}

	public Role getRole(String name) {
		return getRoleDao().getRole(name);
	}

}
