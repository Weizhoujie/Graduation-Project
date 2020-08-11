package com.student.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.student.dao.StudentLeaveAppDao;
import com.student.entity.StudentLeaveApplication;
import com.system.utils.DataSourceUtils;

public class StudentLeaveAppDaoImpl implements StudentLeaveAppDao{
	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

	@Override
	public List<StudentLeaveApplication> queryAllLeaveAppByStu(long sid, int startIndex, int currentCount) {
		List<StudentLeaveApplication> list = null;
		String sql = "SELECT la.id,la.sid,la.tid,la.cid,s.username,s.realname sname,t.realname tname,\r\n" + 
				"c.cname,la.`status`,la.time,la.location\r\n" + 
				"from leave_application as la,student as s,course as c,teacher as t\r\n" + 
				"where la.sid=s.id and la.cid = c.cid and t.id=la.tid and la.sid=? \r\n" + 
				"ORDER BY la.time DESC  LIMIT ?,?";
		try {
			list = runner.query(sql, new BeanListHandler<StudentLeaveApplication>(StudentLeaveApplication.class),sid,startIndex,currentCount);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getTotalCount(long sid) {
		int totalCount=0;
		String sql = null;
		sql = "SELECT COUNT(*) from leave_application\r\n" + 
				"where sid=?";
			try {
				Long query = (Long)runner.query(sql, new ScalarHandler(),sid);
				totalCount = query.intValue();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return totalCount;
	}

	@Override
	public int batchDelLeaveApplication(String s) {
		String sql = null;
		int result = 0;
		sql = "delete from leave_application where id in ("+ s +")";
		try {
			result = runner.update(sql);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<StudentLeaveApplication> queryLeaveAppByCname(long sid, String cname) {
		List<StudentLeaveApplication> list = null;
		String sql = "SELECT la.id,la.sid,la.tid,la.cid,s.username,s.realname sname,t.realname tname,\r\n" + 
				"c.cname,la.`status`,la.time,la.location\r\n" + 
				"from leave_application as la,student as s,course as c,teacher as t\r\n" + 
				"where la.sid=s.id and la.cid = c.cid and t.id=la.tid and la.sid=? and c.cname LIKE CONCAT('%',?,'%')\r\n" + 
				"ORDER BY la.time DESC  ";
		try {
			list = runner.query(sql, new BeanListHandler<StudentLeaveApplication>(StudentLeaveApplication.class),sid,cname);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertLeaveApplication(long sid, long tid, long cid, String time, String location) {
		String sql = "insert into leave_application(sid,tid,cid,time,status,location) "
				+ "values(?,?,?,?,0,?)";
		try {
			 int result = runner.update(sql, sid,tid,cid,time,location);
			return result;
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
