package com.mitosis.shopsbacker.admin.service;

import com.mitosis.shopsbacker.model.Role;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public interface RoleService<T> {
	public Role getRole(String name);
}
