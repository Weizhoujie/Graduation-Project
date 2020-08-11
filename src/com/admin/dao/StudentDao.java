package com.admin.dao;

import java.util.List;

import com.student.entity.Student;
import com.system.entity.StudentForImport;



public interface StudentDao {
	
	//查询所有学生
	List<Student> QueryAllStudent(int startIndex, int currentCount);
	//根据账号查询学生
	List<Student> queryStudentByUsername(String s);
	//批量删除学生
	int batchDeleteStudents(String s);
	//更新学生
	boolean updateStudent(Student student);
	//插入学生
	int insertStudent(Student student);
	//批量插入学生
	int BacthInsertStudent(StudentForImport studentForImport);
	//查询学生的总条数
	public int getTotalCount();
}
