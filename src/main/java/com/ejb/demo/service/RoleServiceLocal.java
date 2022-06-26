package com.ejb.demo.service;

import java.util.List;

import javax.ejb.Local;

import com.ejb.demo.model.Role;

@Local
public interface RoleServiceLocal {
	public List<Role> getAll();
	public Role getRoleById(int id);
	public Role insertRole(Role role);
	public Role updateRole(Role role);
	public boolean deleteRole(Role role);
}
