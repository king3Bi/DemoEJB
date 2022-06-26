package com.ejb.demo.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ejb.demo.model.User;
import com.ejb.demo.repository.UserRepository;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService implements UserServiceRemote, UserServiceLocal {

    public UserService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<User> getAll() {
		return UserRepository.getAll();
	}

	@Override
	public User getUserById(int id) {
		return UserRepository.getUserById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return UserRepository.getUserByUsername(username);
	}

	@Override
	public User insertUser(User user) {
		return UserRepository.insertUser(user);
	}

	@Override
	public User updateUser(User user) {
		return UserRepository.updateUser(user);
	}

	@Override
	public boolean deleteUser(User user) {
		return UserRepository.deleteUser(user);
	}

	@Override
	public User disableUser(User user) {
		user.setEnable(false);
		return UserRepository.updateUser(user);
	}

	@Override
	public User enableUser(User user) {
		user.setEnable(true);
		return UserRepository.updateUser(user);
	}

}
