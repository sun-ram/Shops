package com.mitosis.shopsbacker.admin.service;

import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.vo.customer.RoleVo;

/**
 * @author prabakaran
 *
 * @param <T>
 * Reviewed by Sundaram  27/11/2015
 */
public interface RoleService<T> {
	public Role getRole(String name);
	
	public RoleVo setRoleVo(Role role);
	
}
