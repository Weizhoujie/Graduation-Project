package com.teacher.service;

import java.util.List;

import com.system.entity.PageBean;
import com.teacher.entity.AfterInsertAttendance;
import com.teacher.entity.AttendanceResult;
import com.teacher.entity.AttendanceTime;
import com.teacher.entity.Sclass;
import com.teacher.entity.StudentForAttendance;

public interface AttendanceByTeaService {
	List<Sclass> querySclass(long tcid);
	List<StudentForAttendance> queryStudentForAttendance(String sclass,long tcid);
	int insertAttendance(AttendanceResult attendanceResult);
	List<AfterInsertAttendance> queryAfterInsertAttendances(int count);
	//查询所有考勤记录
	List<AfterInsertAttendance> queryAllAttendances(int startIndex, int currentCount,long tid);
	int updateAttendance(long id,String status);
	int batchDelAttendance(String s);
	//根据课程名字查询考勤
	List<AfterInsertAttendance> queryAttendanceByCname(long tid,String s);
	//根据学生学号查询考勤
	List<AfterInsertAttendance> queryAttendanceByUsername(long tid,String s);
	//根据课程名字和学生学号查询考勤
	List<AfterInsertAttendance> queryAttendanceByCnameAndUsername(long tid,String cname,String username);
	public PageBean<AfterInsertAttendance> findPageBean(int currentPage,long tid);
	//查询考勤的次数
	List<AttendanceTime> queryAttendanceCount(long tid,long cid);
	//查询每次考勤
	List<AfterInsertAttendance> queryAttendanceByCount(long tid,long cid,String time);

}
