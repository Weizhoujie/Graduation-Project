package com.system.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.system.dao.LoginDao;
import com.admin.entity.Admin;
import com.student.entity.Student;
import com.teacher.entity.Teacher;
import com.system.utils.DataSourceUtils;

public class LoginDaoImpl implements LoginDao{
	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
	
	public Admin QueryAdmin(String username, String password) throws SQLException {
		String sql = "select * from admin where username =? and password = ?";
		Admin admin =  (Admin) runner.query(sql, new BeanHandler<Admin>(Admin.class), username,password);
		return admin;
	}

	
	public Teacher QueryTeacher(String username, String password) throws SQLException{
		String sql = "select * from teacher where username =? and password = ?";
		Teacher teacher = (Teacher) runner.query(sql, new BeanHandler<Teacher>(Teacher.class), username,password);
		return teacher;
	}

	
	public Student QueryStudent(String username, String password) throws SQLException{
		String sql = "select * from student where username =? and password = ?";
		Student student = (Student) runner.query(sql, new BeanHandler<Student>(Student.class), username,password);
		return student;
	}

}
