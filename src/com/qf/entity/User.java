package com.qf.entity;

//User类中的属性往往与数据库中的字段保持一致
public class User {
	private Integer id;
	private String name;
	private String password;
	private String sex;
	private String phone;
	private String email;
	private String isAdmin;  //与数据库中的字段不一致
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public User(Integer id, String name, String password, String sex, String phone, String email, String isAdmin) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex + ", phone=" + phone
				+ ", email=" + email + ", isAdmin=" + isAdmin + "]";
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public User() {
	}
	
	
}
