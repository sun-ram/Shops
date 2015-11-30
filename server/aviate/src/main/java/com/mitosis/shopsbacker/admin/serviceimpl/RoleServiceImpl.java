package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.admin.dao.RoleDao;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.vo.customer.RoleVo;

/**
 * @author prabakaran
 *
 * @param <T>
 * 
 * Reviewed by Sundaram 28/11/2015
 */
@Service("roleServiceImpl")
public class RoleServiceImpl<T> implements RoleService<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	RoleDao<T> roleDao;
	
	RoleVo roleVo = null;	

	public Role getRole(String name) {
		return roleDao.getRole(name);
	}

	@Override
	public RoleVo setRoleVo(Role role) {
		roleVo = new RoleVo();
		roleVo.setName(role.getName());
		roleVo.setRoleId(role.getRoleId());
		roleVo.setDescription(role.getDescription());
		return roleVo;
	}

}
