package com.teacher.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.admin.entity.Course;
import com.system.utils.DataSourceUtils;
import com.teacher.dao.TeacherCourseDao;
import com.teacher.entity.TeacherChoseCourse;

public class TeacherCourseDaoImpl implements TeacherCourseDao{
	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

	
	public List<Course> QueryAllCourse(int startIndex, int currentCount) {
		
		List<Course> list = null;
		String sql = "select * from course limit ?,?";
		try {
			list = runner.query(sql, new BeanListHandler<Course>(Course.class),startIndex,currentCount);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	
	public List<Course> queryCourseByCname(String s) {
		
		List<Course> list = null;
		String sql = "select * from course where cname LIKE CONCAT('%',?,'%')";
		try {
			list = runner.query(sql, new BeanListHandler<Course>(Course.class),s);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public int getTotalCount() {
		int totalCount=0;
		String sql = null;
		sql = "SELECT COUNT(*) from course";
			try {
				Long query = (Long)runner.query(sql, new ScalarHandler());
				totalCount = query.intValue();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return totalCount;
	}


	@Override
	public int chooseCourseByTeacher(long tid, long cid) {
		String sql = "insert into teacher_course(tid,cid) "
				+ "values(?,?)";
		try {
			 int result = runner.update(sql,tid,cid);
			return result;
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public List<TeacherChoseCourse> queryChoseCourseByTid(long tid) {
		List<TeacherChoseCourse> list = null;
		String sql = "select tc.id,t.id tid,t.realname,c.cid,c.cname,c.cintro from teacher_course as tc,teacher as t,course as c\r\n" + 
				"where tc.tid = t.id and tc.cid=c.cid and tc.tid=?";
		try {
			list = runner.query(sql, new BeanListHandler<TeacherChoseCourse>(TeacherChoseCourse.class),tid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public boolean giveupCourseByTeacher(long id) {
		String sql = null;
		int result = 0;
		sql = "delete from teacher_course where id in ("+ id +")";
		try {
			result = runner.update(sql);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		if (result != 0) {
			return true;
		}else {
			return false;
		}
	}

}
