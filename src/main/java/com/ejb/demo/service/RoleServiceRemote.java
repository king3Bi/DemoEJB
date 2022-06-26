package com.ejb.demo.service;

import java.util.List;

import javax.ejb.Remote;

import com.ejb.demo.model.Role;

@Remote
public interface RoleServiceRemote {
	public List<Role> getAll();
	public Role getRoleById(int id);
	public Role insertRole(Role role);
	public Role updateRole(Role role);
	public boolean deleteRole(Role role);
}
