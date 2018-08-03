package com.revature;

public class User {
	private int id = -1;
	private String email;
	private String username;
	private String password;
	private boolean is_host = false;
	
	public boolean isIs_host() {
		return is_host;
	}
	public void setIs_host(boolean is_host) {
		this.is_host = is_host;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public User(int id, String email, String username, String password) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public User(int id, String email, String username, String password, int is_host) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.is_host = is_host == 1 ? true:false;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password + "is_host= " + is_host + "]";
	}
	
	public User (String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
