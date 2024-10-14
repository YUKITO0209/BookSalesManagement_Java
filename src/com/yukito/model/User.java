package com.yukito.model;

/**
 * 用户实体
 * @author wjw20
 *
 */
public class User {
	private int id; // 序号
	private String userName; // 用户名
	private String phoneNumber; // 手机号
	private String password; // 密码
	private int authority; // 权限
	
	public User() {
		super();
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public User(String userName, String phoneNumber, String password, int authority) {
		super();
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.authority = authority;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAuthority() {
		return authority;
	}
	
	public void setAuthority(int authority) {
		this.authority = authority;
	}
}
