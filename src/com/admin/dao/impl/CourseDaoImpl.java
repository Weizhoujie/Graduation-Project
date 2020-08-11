package com.admin.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.admin.dao.CourseDao;
import com.admin.entity.Course;
import com.system.entity.CourseForImport;
import com.system.utils.DataSourceUtils;

public class CourseDaoImpl implements CourseDao{
	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

	@Override
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

	@Override
	public List<Course> queryCourseByCname(String s) {
		List<Course> list = null;
		String sql = "select * from course where cname =?";
		try {
			list = runner.query(sql, new BeanListHandler<Course>(Course.class),s);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int batchDeleteCourses(String s) {
		String sql = null;
		int result = 0;
		sql = "delete from course where cid in ("+ s +")";
		try {
			result = runner.update(sql);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateCourse(Course course) {
		String sql = "update course set cname = ? , cintro = ?   where cid = ?";
		
		try {
			 int result = runner.update(sql, course.getCname(),course.getCintro(),course.getCid());
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
	public int insertCourse(Course course) {
		String sql = "insert into course(cname,cintro) "
				+ "values(?,?)";
		try {
			 int result = runner.update(sql,course.getCname(),course.getCintro());
			return result;
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int BacthInsertCourse(CourseForImport courseForImport) {
		String sql = "insert into course(cname,cintro)"
				+ "values(?,?)";
		try {
			 int result = runner.update(sql,courseForImport.getCname(),courseForImport.getCintro());
			return result;
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
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

	

}
