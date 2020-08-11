package com.student.service;

import java.util.List;

import com.student.entity.StudentAttendance;
import com.system.entity.PageBean;

public interface StudentAttendanceService {
	//查询所有考勤记录
	List<StudentAttendance> queryAllAttendancesByStu(int startIndex, int currentCount,long sid);
	List<StudentAttendance> queryAttendancesByCname(long sid,String cname);
	public PageBean<StudentAttendance> findPageBean(int currentPage,long sid);

}
