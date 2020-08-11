package com.teacher.dao;

import java.util.List;

import com.teacher.entity.AfterInsertAttendance;
import com.teacher.entity.AttendanceResult;
import com.teacher.entity.AttendanceTime;
import com.teacher.entity.Sclass;
import com.teacher.entity.StudentForAttendance;

public interface AttendanceByTeaDao {
	//查询选课学生的班级
	List<Sclass> querySclass(long tcid);
	//查询学生
	List<StudentForAttendance> queryStudentForAttendance(String sclass,long tcid);
	//插入考勤结果
	int insertAttendance(AttendanceResult attendanceResult);
	//查询插入的结果
	List<AfterInsertAttendance> queryAfterInsertAttendances(int count);
	//查询所有考勤记录
	List<AfterInsertAttendance> queryAllAttendances(int startIndex, int currentCount,long tid);
	//修改考勤记录
	int updateAttendance(long id,String status);
	//删除考勤记录
	int batchDelAttendance(String s);
	//根据课程名字查询考勤
	List<AfterInsertAttendance> queryAttendanceByCname(long tid,String s);
	//根据学生学号查询考勤
	List<AfterInsertAttendance> queryAttendanceByUsername(long tid,String s);
	//根据课程名字和学生学号查询考勤
	List<AfterInsertAttendance> queryAttendanceByCnameAndUsername(long tid,String cname,String username);
	//查询考勤的总条数
	public int getTotalCount(long tid);
	//查询考勤的次数
	List<AttendanceTime> queryAttendanceCount(long tid,long cid);
	//查询每次考勤
	List<AfterInsertAttendance> queryAttendanceByCount(long tid,long cid,String s);
	
}
