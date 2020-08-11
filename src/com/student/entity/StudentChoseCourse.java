package com.student.entity;

public class StudentChoseCourse {
	private long id;//学生选课表id
	private long sid;//学生id
	private long tcid;//教师选课表id
	private long tid;//教师id
	private String realname;//教师姓名
	private long cid;//课程id
	private String cname;//课程名字
	private int flag;//选课状态
	private String cintro;//课程介绍
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSid() {
		return sid;
	}
	public void setSid(long sid) {
		this.sid = sid;
	}
	public long getTcid() {
		return tcid;
	}
	public void setTcid(long tcid) {
		this.tcid = tcid;
	}
	public long getTid() {
		return tid;
	}
	public void setTid(long tid) {
		this.tid = tid;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getCintro() {
		return cintro;
	}
	public void setCintro(String cintro) {
		this.cintro = cintro;
	}
	

}
