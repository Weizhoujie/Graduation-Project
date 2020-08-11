package com.teacher.dao;

import com.teacher.entity.Teacher;

public interface TeacherInfoDao {
	//通过教师ID查询教师信息
	Teacher queryTeacherById(long id);
	//修改教师密码
	boolean UpdatePassword(long id,String password);

}
