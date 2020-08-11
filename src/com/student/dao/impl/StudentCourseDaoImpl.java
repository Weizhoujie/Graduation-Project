package com.student.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.student.dao.StudentCourseDao;
import com.student.entity.StudentChoseCourse;
import com.system.utils.DataSourceUtils;
import com.teacher.entity.TeacherChoseCourse;

public class StudentCourseDaoImpl implements StudentCourseDao{
	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

	@Override
	public List<TeacherChoseCourse> QueryAllCourse(int startIndex, int currentCount) {
		List<TeacherChoseCourse> list = null;
		String sql = "select tc.id,t.id tid,t.realname,c.cid,c.cname,c.cintro from teacher_course as tc,teacher as t,course as c "
				+ "where tc.tid = t.id and tc.cid=c.cid limit ?,?";
		try {
			list = runner.query(sql, new BeanListHandler<TeacherChoseCourse>(TeacherChoseCourse.class),startIndex,currentCount);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<TeacherChoseCourse> queryCourseByCname(String s) {
		List<TeacherChoseCourse> list = null;
		String sql = "select tc.id,t.id tid,t.realname,c.cid,c.cname,c.cintro from teacher_course as tc,teacher as t,course as c "
				+ "where tc.tid = t.id and tc.cid=c.cid and c.cname LIKE CONCAT('%',?,'%')";
		try {
			list = runner.query(sql, new BeanListHandler<TeacherChoseCourse>(TeacherChoseCourse.class),s);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int chooseCourseByStudent(long sid, long tcid) {
		
		String sql = "insert into student_course(sid,tc_id,flag) "
				+ "values(?,?,0)";
		try {
			 int result = runner.update(sql,sid,tcid);
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
		sql = "select COUNT(*) from teacher_course as tc,teacher as t,course as c \r\n" + 
				"where tc.tid = t.id and tc.cid=c.cid";
			try {
				Long query = (Long)runner.query(sql, new ScalarHandler());
				totalCount = query.intValue();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return totalCount;
	}

	@Override
	public List<TeacherChoseCourse> queryCourseByTeachername(String s) {
		List<TeacherChoseCourse> list = null;
		String sql = "select tc.id,t.id tid,t.realname,c.cid,c.cname,c.cintro from teacher_course as tc,teacher as t,course as c "
				+ "where tc.tid = t.id and tc.cid=c.cid and t.realname LIKE CONCAT('%',?,'%')";
		try {
			list = runner.query(sql, new BeanListHandler<TeacherChoseCourse>(TeacherChoseCourse.class),s);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<TeacherChoseCourse> queryCourseByCnameAndTname(String cname, String tname) {
		List<TeacherChoseCourse> list = null;
		String sql = "select tc.id,t.id tid,t.realname,c.cid,c.cname,c.cintro from teacher_course as tc,teacher as t,course as c "
				+ "where tc.tid = t.id and tc.cid=c.cid and c.cname LIKE CONCAT('%',?,'%') and t.realname LIKE CONCAT('%',?,'%')";
		try {
			list = runner.query(sql, new BeanListHandler<TeacherChoseCourse>(TeacherChoseCourse.class),cname,tname);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<StudentChoseCourse> queryCoursesByStudent(long sid) {
		List<StudentChoseCourse> list = null;
		String sql = "SELECT sc.id,sc.sid,sc.tc_id tcid,t.id tid,t.realname,c.cid,c.cname,sc.flag,c.cintro from student_course as sc,teacher_course as tc,teacher as t,course as c\r\n" + 
				"where sc.tc_id=tc.id and tc.tid=t.id and tc.cid=c.cid and sc.sid=?";
		try {
			list = runner.query(sql, new BeanListHandler<StudentChoseCourse>(StudentChoseCourse.class),sid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<TeacherChoseCourse> queryCoursesByTcid(long tcid) {
		List<TeacherChoseCourse> list = null;
		String sql = "select tc.id,t.id tid,t.realname,c.cid,c.cname,c.cintro from teacher_course as tc,teacher as t,course as c "
				+ "where tc.tid = t.id and tc.cid=c.cid and tc.id=?";
		try {
			list = runner.query(sql, new BeanListHandler<TeacherChoseCourse>(TeacherChoseCourse.class),tcid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int giveupCourse(long scid) {
		int result=0;
		String sql = "update student_course set flag=-1 where id=?";
		try {
			  result = runner.update(sql, scid);
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
