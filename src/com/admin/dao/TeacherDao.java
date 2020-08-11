package com.admin.dao;

import java.util.List;

import com.system.entity.TeacherForImport;
import com.teacher.entity.Teacher;

public interface TeacherDao {
		//查询所有教师
		List<Teacher> QueryAllTeacher(int startIndex, int currentCount);
		//根据账号查询教师
		List<Teacher> queryTeacherByUsername(String s);
		//批量删除教师
		int batchDeleteTeachers(String s);
		//更新教师
		boolean updateTeacher(Teacher teacher);
		//插入教师
		int insertTeacher(Teacher teacher);
		//批量插入教师
		int BacthInsertTeacher(TeacherForImport teacherForImport);
		//查询教师的总条数
		public int getTotalCount();

}
