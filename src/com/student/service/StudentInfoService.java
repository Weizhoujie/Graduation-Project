package com.student.service;

import com.student.entity.Student;

public interface StudentInfoService {
	//通过学生ID查询学生信息
	Student queryStudentById(long id);
	//修改学生密码
	boolean UpdatePassword(long id,String password);

}
