package com.admin.service;

import java.util.List;

import com.student.entity.Student;
import com.system.entity.PageBean;
import com.system.entity.StudentForImport;


public interface StudentService {
  	
	//查询所有学生
	List<Student> queryAllStudent(int startIndex, int currentCount);
	//根据账号搜索学生
	List<Student> queryStudentByUsername(String s);
	//更新学生
	boolean updateStudent(Student student);
	//插入学生
	int insertStudent(Student student);
	//批量删除学生
	int batchDeleteStudents(String s);
	//批量插入学生
	int BacthInsertStudent(StudentForImport studentForImport);
	public PageBean<Student> findPageBean(int currentPage);
}
