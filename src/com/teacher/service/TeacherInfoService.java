package com.teacher.service;

import com.teacher.entity.Teacher;

public interface TeacherInfoService {
	//通过教师ID查询教师信息
	Teacher queryTeacherById(long id);
	//修改教师密码
	boolean UpdatePassword(long id,String password);

}
