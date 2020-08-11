package com.system.service.impl;

import java.sql.SQLException;

import com.system.dao.LoginDao;
import com.system.dao.impl.LoginDaoImpl;
import com.admin.entity.Admin;
import com.student.entity.Student;
import com.teacher.entity.Teacher;
import com.system.service.LoginService;

public class LoginServiceImpl implements LoginService{
	LoginDao loginDao = new LoginDaoImpl();
	
	public Admin QueryAdmin(String username, String password) throws SQLException {
		Admin admin = loginDao.QueryAdmin(username, password);
		return admin;
	}

	
	public Teacher QueryTeacher(String username, String password) throws SQLException {
		Teacher teacher = loginDao.QueryTeacher(username, password);
		return teacher;
	}

	
	public Student QueryStudent(String username, String password) throws SQLException {
		Student student = loginDao.QueryStudent(username, password);
		return student;
	}

}
