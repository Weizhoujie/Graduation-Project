package com.admin.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.admin.dao.TeacherDao;
import com.system.entity.TeacherForImport;
import com.system.utils.DataSourceUtils;
import com.teacher.entity.Teacher;

public class TeacherDaoImpl implements TeacherDao{
	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

	public List<Teacher> QueryAllTeacher(int startIndex, int currentCount) {
		List<Teacher> list = null;
		String sql = "select * from teacher limit ?,?";
		try {
			list = runner.query(sql, new BeanListHandler<Teacher>(Teacher.class),startIndex,currentCount);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Teacher> queryTeacherByUsername(String s) {
		List<Teacher> list = null;
		String sql = "select * from teacher where username =?";
		try {
			list = runner.query(sql, new BeanListHandler<Teacher>(Teacher.class),s);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	public int batchDeleteTeachers(String s) {
		String sql = null;
		int result = 0;
		sql = "delete from teacher where id in ("+ s +")";
		try {
			result = runner.update(sql);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateTeacher(Teacher teacher) {
		String sql = "update teacher set username = ? , password = ? , realname = ? , "
				+ "sex = ? , faculty = ?, position = ?, email = ?, intro = ? where id = ?";
		
		try {
			 int result = runner.update(sql, teacher.getUsername(),teacher.getPassword(),teacher.getRealname()
					 ,teacher.getSex(),teacher.getFaculty(),teacher.getPosition(),teacher.getEmail(),teacher.getIntro(),teacher.getId());
			 if (result !=0 ) {
				 return true;
			}else {
				return false;
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int insertTeacher(Teacher teacher) {
		String sql = "insert into teacher(username,password,realname,sex,faculty,position,email,intro) "
				+ "values(?,?,?,?,?,?,?,?)";
		try {
			 int result = runner.update(sql, teacher.getUsername(),teacher.getPassword(),teacher.getRealname()
					 ,teacher.getSex(),teacher.getFaculty(),teacher.getPosition(),teacher.getEmail(),teacher.getIntro());
			return result;
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int BacthInsertTeacher(TeacherForImport teacherForImport) {
		String sql = "insert into teacher(username,password,realname,sex,faculty,position,email,intro) "
				+ "values(?,?,?,?,?,?,?,?)";
		try {
			 int result = runner.update(sql, teacherForImport.getUsername(),teacherForImport.getPassword(),teacherForImport.getRealname()
					 ,teacherForImport.getSex(),teacherForImport.getFaculty(),teacherForImport.getPosition(),teacherForImport.getEmail(),teacherForImport.getIntro());
			return result;
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int getTotalCount() {
		int totalCount=0;
		String sql = null;
		sql = "SELECT COUNT(*) from teacher";
			try {
				Long query = (Long)runner.query(sql, new ScalarHandler());
				totalCount = query.intValue();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return totalCount;
	}

}
