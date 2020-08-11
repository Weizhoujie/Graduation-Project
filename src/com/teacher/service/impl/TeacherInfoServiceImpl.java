package com.teacher.service.impl;

import com.teacher.dao.TeacherInfoDao;
import com.teacher.dao.impl.TeacherInfoDaoImpl;
import com.teacher.entity.Teacher;
import com.teacher.service.TeacherInfoService;

public class TeacherInfoServiceImpl implements TeacherInfoService{
	TeacherInfoDao teacherInfoDao = new TeacherInfoDaoImpl();

	@Override
	public Teacher queryTeacherById(long id) {
		Teacher teacher = teacherInfoDao.queryTeacherById(id);
		return teacher;
	}

	@Override
	public boolean UpdatePassword(long id, String password) {
		boolean result = teacherInfoDao.UpdatePassword(id, password);
		return result;
	}

}
