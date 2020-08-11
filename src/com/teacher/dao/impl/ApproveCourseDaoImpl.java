package com.teacher.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.system.utils.DataSourceUtils;
import com.teacher.dao.ApproveCourseDao;
import com.teacher.entity.PendingCourse;

public class ApproveCourseDaoImpl implements ApproveCourseDao{
	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

	@Override
	public List<PendingCourse> queryAllPendingCourse(long tid, int startIndex, int currentCount) {
		
		List<PendingCourse> list = null;
		String sql = "SELECT sc.id,sc.sid,sc.tc_id tcid,s.sclass,s.realname,c.cname,sc.flag,c.cintro \r\n" + 
				"FROM student_course as sc,teacher_course as tc,student as s,teacher as t,course as c\r\n" + 
				"where sc.sid=s.id and sc.tc_id=tc.id and tc.tid=t.id and tc.cid=c.cid and sc.flag=0 and t.id=? "
				+ "order by c.cname LIMIT ?,? ";
		try {
			list = runner.query(sql, new BeanListHandler<PendingCourse>(PendingCourse.class),tid,startIndex,currentCount);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getTotalCount(long tid) {
		int totalCount=0;
		String sql = null;
		sql = "SELECT COUNT(*) \r\n" + 
				"FROM student_course as sc,teacher_course as tc,student as s,teacher as t,course as c\r\n" + 
				"where sc.sid=s.id and sc.tc_id=tc.id and tc.tid=t.id and tc.cid=c.cid and sc.flag=0 and t.id=?";
			try {
				Long query = (Long)runner.query(sql, new ScalarHandler(),tid);
				totalCount = query.intValue();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return totalCount;
	}

	@Override
	public int batchApproveCourse(String s) {
		String sql = null;
		int result = 0;
		sql = "update student_course set flag=1 where id in ("+ s +")";
		try {
			result = runner.update(sql);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int rejectCourse(long id) {
		
		String sql = null;
		int result = 0;
		sql = "update student_course set flag=-1 where id=?";
		try {
			result = runner.update(sql,id);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}

}
