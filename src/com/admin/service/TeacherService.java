package com.admin.service;

import java.util.List;

import com.student.entity.Student;
import com.system.entity.PageBean;
import com.system.entity.StudentForImport;
import com.system.entity.TeacherForImport;
import com.teacher.entity.Teacher;

public interface TeacherService {
		//查询所有教师
		List<Teacher> queryAllTeacher(int startIndex, int currentCount);
		//根据账号搜索教师
		List<Teacher> queryTeacherByUsername(String s);
		//更新教师
		boolean updateTeacher(Teacher teacher);
		//插入教师
		int insertTeacher(Teacher teacher);
		//批量删除教师
		int batchDeleteTeachers(String s);
		//批量插入教师
		int BacthInsertTeacher(TeacherForImport teacherForImport);
		public PageBean<Teacher> findPageBean(int currentPage);

}
