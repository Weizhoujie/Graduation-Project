package com.system.dao;

import java.sql.SQLException;

import com.admin.entity.Admin;
import com.student.entity.Student;
import com.teacher.entity.Teacher;

public interface LoginDao {
	
	Admin QueryAdmin(String username,String password) throws SQLException;
	Teacher QueryTeacher(String username,String password) throws SQLException;
	Student QueryStudent(String username,String password) throws SQLException;

}
