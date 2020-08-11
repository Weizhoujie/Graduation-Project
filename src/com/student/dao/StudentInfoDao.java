package com.student.dao;

import com.student.entity.Student;

public interface StudentInfoDao {
	//通过学生ID查询学生信息
	Student queryStudentById(long id);
	//修改学生密码
	boolean UpdatePassword(long id,String password);

}
