package com.student.service.impl;

import com.student.dao.StudentInfoDao;
import com.student.dao.impl.StudentInfoDaoImpl;
import com.student.entity.Student;
import com.student.service.StudentInfoService;

public class StudentInfoServiceImpl implements StudentInfoService{
	StudentInfoDao studentInfoDao = new StudentInfoDaoImpl();

	public Student queryStudentById(long id) {
		Student student = studentInfoDao.queryStudentById(id);
		return student;
	}

	@Override
	public boolean UpdatePassword(long id, String password) {
		boolean result = studentInfoDao.UpdatePassword(id, password);
		return result;
	}

}
