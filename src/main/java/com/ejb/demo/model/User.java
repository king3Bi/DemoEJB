package com.ejb.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private int id;
	
	@Column(name = "full_name")
	@Expose
	private String fullName;
	
	@Column(name = "username")
	@Expose
	private String username;
	
	@Column(name = "password")
	@Expose
	private String password;
	
	@Column(name = "enable")
	@Expose
	private boolean enable;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	@Expose
	private Role role;
	
	public User() {}
	
	public User(int id, String fullName, String username, String password, boolean enable, Role role) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.role = role;
	}
	
	public User(String fullName, String username, String password, boolean enable, Role role) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", username=" + username + ", password=" + password
				+ ", enable=" + enable + "]";
	}
}
