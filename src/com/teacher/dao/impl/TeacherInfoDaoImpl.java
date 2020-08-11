package com.teacher.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.system.utils.DataSourceUtils;
import com.teacher.dao.TeacherInfoDao;
import com.teacher.entity.Teacher;

public class TeacherInfoDaoImpl implements TeacherInfoDao{

	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
	public Teacher queryTeacherById(long id) {
		Teacher teacher  = null;
		String sql = "select * from teacher as t where t.id = ?";
		try {
			teacher = runner.query(sql,new BeanHandler<Teacher>(Teacher.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacher;
	}

	@Override
	public boolean UpdatePassword(long id, String password) {
		String sql = "update teacher set password=? where id=?";
		try {
			 runner.update(sql, password,id);
			 return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
