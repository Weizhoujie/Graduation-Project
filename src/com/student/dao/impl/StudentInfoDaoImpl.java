package com.student.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.student.dao.StudentInfoDao;
import com.student.entity.Student;
import com.system.utils.DataSourceUtils;

public class StudentInfoDaoImpl implements StudentInfoDao{
	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

	@Override
	public Student queryStudentById(long id) {
		Student student  = null;
		String sql = "select * from student as t where t.id = ?";
		try {
			student = runner.query(sql,new BeanHandler<Student>(Student.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public boolean UpdatePassword(long id, String password) {
		String sql = "update student set password=? where id=?";
		try {
			 runner.update(sql, password,id);
			 return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
