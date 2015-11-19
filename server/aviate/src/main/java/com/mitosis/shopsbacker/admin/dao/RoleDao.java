package com.mitosis.shopsbacker.admin.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Role;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public interface RoleDao<T> {
	
	public Role getRole(String name);
	
	public List<Role> getRole(List<String> roles);

}
