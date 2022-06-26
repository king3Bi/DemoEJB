package com.ejb.demo.service;

import java.util.List;
import java.util.Optional;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ejb.demo.model.Role;
import com.ejb.demo.repository.RoleRepository;

/**
 * Session Bean implementation class RoleService
 */
@Stateless
@LocalBean
public class RoleService implements RoleServiceRemote, RoleServiceLocal {

    /**
     * Default constructor. 
     */
    public RoleService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Role> getAll() {
		return RoleRepository.getAll();
	}

	@Override
	public Role getRoleById(int id) {
		return RoleRepository.getRoleById(id);
	}

	@Override
	public Role insertRole(Role role) {
		return RoleRepository.insertRole(role);
	}

	@Override
	public Role updateRole(Role role) {
		return RoleRepository.updateRole(role);
	}

	@Override
	public boolean deleteRole(Role role) {
		return RoleRepository.deleteRole(role);
	}

}
