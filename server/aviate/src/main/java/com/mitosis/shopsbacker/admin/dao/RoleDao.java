package com.mitosis.shopsbacker.admin.dao;

import com.mitosis.shopsbacker.model.Role;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public interface RoleDao<T> {
	
	public Role getRole(String name);

}
