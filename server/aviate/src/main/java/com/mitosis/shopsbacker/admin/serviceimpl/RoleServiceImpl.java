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
 */
@Service("roleServiceImpl")
public class RoleServiceImpl<T> implements RoleService<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	RoleDao<T> roleDao;

	public Role getRole(String name) {
		return roleDao.getRole(name);
	}

	@Override
	public RoleVo setRoleVo(Role role) {
		RoleVo roleVo = new RoleVo();
		roleVo.setName(role.getName());
		roleVo.setRoleId(role.getRoleId());
		roleVo.setDescription(role.getDescription());
		return roleVo;
	}

}
