package com.teacher.dao.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.system.utils.DataSourceUtils;
import com.teacher.dao.AttendanceByTeaDao;
import com.teacher.entity.AfterInsertAttendance;
import com.teacher.entity.AttendanceResult;
import com.teacher.entity.AttendanceTime;
import com.teacher.entity.Sclass;
import com.teacher.entity.StudentForAttendance;

public class AttendanceByTeaDaoImpl implements AttendanceByTeaDao{
	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

	@Override
	public List<Sclass> querySclass(long tcid) {
		
		List<Sclass> list = null;
		String sql = "SELECT DISTINCT s.sclass  \r\n" + 
				"FROM student as s,student_course as sc\r\n" + 
				"WHERE sc.sid = s.id and sc.tc_id=? and sc.flag=1";
		try {
			list = runner.query(sql, new BeanListHandler<Sclass>(Sclass.class),tcid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<StudentForAttendance> queryStudentForAttendance(String sclass, long tcid) {
		List<StudentForAttendance> list = null;
		String sql = "SELECT sc.sid,tc.tid,c.cid,s.username,s.realname sname,s.sclass,s.sex\r\n" + 
				"FROM student as s,student_course as sc,teacher_course as tc,course as c\r\n" + 
				"WHERE sc.sid = s.id and sc.tc_id=tc.id  and sc.flag=1 and tc.cid=c.cid\r\n" + 
				"AND sc.tc_id=? AND s.sclass=? "
				+ "order by s.username";
		try {
			list = runner.query(sql, new BeanListHandler<StudentForAttendance>(StudentForAttendance.class),tcid,sclass);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertAttendance(AttendanceResult attendanceResult) {
		String sql = "INSERT INTO attence(tid,cid,sid,time,status)\r\n" + 
				"VALUES(?,?,?,?,?)";
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			 int result = runner.update(sql,attendanceResult.getTid(),attendanceResult.getCid(),attendanceResult.getSid(),
					 date.format(formatter),attendanceResult.getAttendance());
			return result;
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<AfterInsertAttendance> queryAfterInsertAttendances(int count) {
		List<AfterInsertAttendance> list = null;
		String sql = "SELECT a.id,s.username,s.realname sname,c.cname,s.sclass,s.sex,a.time,a.status\r\n" + 
				"from attence as a,student as s,course as c\r\n" + 
				"where a.sid=s.id and a.cid = c.cid\r\n" + 
				"ORDER BY a.id DESC  LIMIT 0,?\r\n" + 
				"";
		try {
			list = runner.query(sql, new BeanListHandler<AfterInsertAttendance>(AfterInsertAttendance.class),count);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<AfterInsertAttendance> queryAllAttendances(int startIndex, int currentCount, long tid) {
		List<AfterInsertAttendance> list = null;
		String sql = "SELECT a.id,s.username,s.realname sname,c.cname,s.sclass,s.sex,a.time,a.status \r\n" + 
				"from attence as a,student as s,course as c " + 
				"where a.sid=s.id and a.cid = c.cid and a.tid=? " + 
				"ORDER BY a.time DESC,c.cname asc,s.username asc  LIMIT ?,?";
		try {
			list = runner.query(sql, new BeanListHandler<AfterInsertAttendance>(AfterInsertAttendance.class),tid,startIndex,currentCount);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getTotalCount(long tid) {
		int totalCount=0;
		String sql = null;
		sql = "SELECT COUNT(*) from attence\r\n" + 
				"where tid=?";
			try {
				Long query = (Long)runner.query(sql, new ScalarHandler(),tid);
				totalCount = query.intValue();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return totalCount;
	}

	@Override
	public int updateAttendance(long id,String status) {
		int result = 0;
		String sql = "update attence set status=? where id=?";
		try {
			result = runner.update(sql,status,id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int batchDelAttendance(String s) {
		String sql = null;
		int result = 0;
		sql = "delete from attence where id in ("+ s +")";
		try {
			result = runner.update(sql);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<AfterInsertAttendance> queryAttendanceByCname(long tid,String s) {
		List<AfterInsertAttendance> list = null;
		String sql = "SELECT a.id,s.username,s.realname sname,c.cname,s.sclass,s.sex,a.time,a.status \r\n" + 
				"from attence as a,student as s,course as c " + 
				"where a.sid=s.id and a.cid = c.cid and a.tid=? and c.cname LIKE CONCAT('%',?,'%') " + 
				"ORDER BY a.time DESC,c.cname asc,s.username asc ";
		try {
			list = runner.query(sql, new BeanListHandler<AfterInsertAttendance>(AfterInsertAttendance.class),tid,s);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<AfterInsertAttendance> queryAttendanceByUsername(long tid,String s) {
		List<AfterInsertAttendance> list = null;
		String sql = "SELECT a.id,s.username,s.realname sname,c.cname,s.sclass,s.sex,a.time,a.status \r\n" + 
				"from attence as a,student as s,course as c " + 
				"where a.sid=s.id and a.cid = c.cid and a.tid=? and s.username LIKE CONCAT('%',?,'%') " + 
				"ORDER BY a.time DESC,c.cname asc,s.username asc ";
		try {
			list = runner.query(sql, new BeanListHandler<AfterInsertAttendance>(AfterInsertAttendance.class),tid,s);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<AfterInsertAttendance> queryAttendanceByCnameAndUsername(long tid,String cname, String username) {
		List<AfterInsertAttendance> list = null;
		String sql = "SELECT a.id,s.username,s.realname sname,c.cname,s.sclass,s.sex,a.time,a.status \r\n" + 
				"from attence as a,student as s,course as c " + 
				"where a.sid=s.id and a.cid = c.cid and a.tid=? and c.cname LIKE CONCAT('%',?,'%') and s.username LIKE CONCAT('%',?,'%') " + 
				"ORDER BY a.time DESC,c.cname asc,s.username asc ";
		try {
			list = runner.query(sql, new BeanListHandler<AfterInsertAttendance>(AfterInsertAttendance.class),tid,cname,username);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<AttendanceTime> queryAttendanceCount(long tid, long cid) {
		List<AttendanceTime> list = null;
		String sql = "SELECT DISTINCT time from attence\r\n" + 
				"WHERE tid = ? and cid = ?";
		try {
			list = runner.query(sql, new BeanListHandler<AttendanceTime>(AttendanceTime.class),tid,cid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<AfterInsertAttendance> queryAttendanceByCount(long tid, long cid, String s) {
		List<AfterInsertAttendance> list = null;
		String sql = "SELECT a.id,s.username,s.realname sname,c.cname,s.sclass,s.sex,a.time,a.status \r\n" + 
				"from attence as a,student as s,course as c " + 
				"where a.sid=s.id and a.cid = c.cid and a.tid=? and a.cid=? and a.time=? " + 
				"ORDER BY a.time DESC,c.cname asc,s.username asc ";
		try {
			list = runner.query(sql, new BeanListHandler<AfterInsertAttendance>(AfterInsertAttendance.class),tid,cid,s);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

}
