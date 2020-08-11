package com.student.dao;

import java.util.List;

import com.student.entity.StudentAttendance;

public interface StudentAttendanceDao {
	//查询所有考勤记录
	List<StudentAttendance> queryAllAttendancesByStu(int startIndex, int currentCount,long sid);
	//根据课程名查找考勤记录
	List<StudentAttendance> queryAttendancesByCname(long sid,String cname);
	//查询考勤的总条数
	public int getTotalCount(long sid);

}
