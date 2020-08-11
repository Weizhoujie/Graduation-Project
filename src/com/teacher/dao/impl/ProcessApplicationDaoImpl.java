package com.teacher.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.student.entity.StudentLeaveApplication;
import com.system.utils.DataSourceUtils;
import com.teacher.dao.ProcessApplicationDao;

public class ProcessApplicationDaoImpl implements ProcessApplicationDao{
	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

	@Override
	public List<StudentLeaveApplication> queryAllLeaveAppByTea(long tid, int startIndex, int currentCount) {
		List<StudentLeaveApplication> list = null;
		String sql = "SELECT la.id,la.sid,la.tid,la.cid,s.username,s.realname sname,t.realname tname,\r\n" + 
				"c.cname,la.`status`,la.time,la.location\r\n" + 
				"from leave_application as la,student as s,course as c,teacher as t\r\n" + 
				"where la.sid=s.id and la.cid = c.cid and t.id=la.tid and la.tid=? and la.status=0 \r\n" + 
				"ORDER BY la.time DESC  LIMIT ?,?";
		try {
			list = runner.query(sql, new BeanListHandler<StudentLeaveApplication>(StudentLeaveApplication.class),tid,startIndex,currentCount);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getTotalCount(long tid) {
		int totalCount=0;
		String sql = null;
		sql = "SELECT COUNT(*) from leave_application\r\n" + 
				"where tid=? and status=0";
			try {
				Long query = (Long)runner.query(sql, new ScalarHandler(),tid);
				totalCount = query.intValue();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return totalCount;
	}

	@Override
	public int batchProcessApplication(String s) {
		String sql = null;
		int result = 0;
		sql = "update leave_application set status=1 where id in ("+ s +")";
		try {
			result = runner.update(sql);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int rejectLeaveApplication(long id) {
		String sql = null;
		int result = 0;
		sql = "update leave_application set status=-1 where id =?";
		try {
			result = runner.update(sql,id);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}

}
