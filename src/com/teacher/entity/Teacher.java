package com.teacher.entity;

public class Teacher {
	private long id;//老师id
	private String username;//登陆账号
	private String password;//密码
	private String realname;//老师真实姓名
	private String sex;//性别
	private String faculty;//学院
	private String position;//职位
	private String email;//老师邮箱
	private String intro;//教师介绍
	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", username=" + username + ", password=" + password + ", realname=" + realname
				+ ", email=" + email +", sex=" + sex + ", faculty=" + faculty +", position=" + position + ", intro=" + intro +  "]";
	}
	
	
	
	

	
}
