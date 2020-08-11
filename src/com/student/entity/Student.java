package com.student.entity;

public class Student {
	private long id;//学生id
	private String username;//登陆账号
	private String password;//密码
	private String realname;//学生真实姓名
	private String sclass;//班级
	private String sex;//性别
	private String admissionTime;//入学时间
	
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAdmissionTime() {
		return admissionTime;
	}
	public void setAdmissionTime(String admissionTime) {
		this.admissionTime = admissionTime;
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
	@Override
	public String toString() {
		return "Student [id=" + id + ", username=" + username + ", password=" + password + ", realname=" + realname
				+ ", sclass=" + sclass + ", sex=" + sex + ", admissionTime=" + admissionTime + "]";
	}
	
	

	
}
