package com.student.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.student.dao.StudentAttendanceDao;
import com.student.entity.StudentAttendance;
import com.system.utils.DataSourceUtils;

public class StudentAttendanceDaoImpl implements StudentAttendanceDao{
	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

	@Override
	public List<StudentAttendance> queryAllAttendancesByStu(int startIndex, int currentCount, long sid) {
		List<StudentAttendance> list = null;
		String sql = "SELECT a.id,s.username,s.realname sname,c.cname,t.realname tname,s.sclass,s.sex,a.time,a.status \r\n" + 
				"from attence as a,student as s,course as c,teacher as t " + 
				"where a.sid=s.id and a.cid = c.cid and t.id=a.tid and a.sid=? " + 
				"ORDER BY a.time DESC,c.cname asc  LIMIT ?,?";
		try {
			list = runner.query(sql, new BeanListHandler<StudentAttendance>(StudentAttendance.class),sid,startIndex,currentCount);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getTotalCount(long sid) {
		int totalCount=0;
		String sql = null;
		sql = "SELECT COUNT(*) from attence\r\n" + 
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
	public List<StudentAttendance> queryAttendancesByCname(long sid, String cname) {
		List<StudentAttendance> list = null;
		String sql = "SELECT a.id,s.username,s.realname sname,c.cname,t.realname tname,s.sclass,s.sex,a.time,a.status \r\n" + 
				"from attence as a,student as s,course as c,teacher as t " + 
				"where a.sid=s.id and a.cid = c.cid and t.id=a.tid and a.sid=? and c.cname LIKE CONCAT('%',?,'%')" + 
				"ORDER BY a.time DESC,c.cname asc  ";
		try {
			list = runner.query(sql, new BeanListHandler<StudentAttendance>(StudentAttendance.class),sid,cname);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

}
