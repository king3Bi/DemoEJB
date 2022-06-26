package com.ejb.demo.service;

import java.util.List;

import javax.ejb.Local;

import com.ejb.demo.model.User;

@Local
public interface UserServiceLocal {
	public List<User> getAll();
	public User getUserById(int id);
	public User getUserByUsername(String username);
	public User insertUser(User user);
	public User updateUser(User user);
	public boolean deleteUser(User user);
	public User disableUser(User user);
	public User enableUser(User user);
}
