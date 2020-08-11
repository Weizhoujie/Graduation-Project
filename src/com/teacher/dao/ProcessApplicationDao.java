package com.teacher.dao;

import java.util.List;

import com.student.entity.StudentLeaveApplication;

public interface ProcessApplicationDao {
	//查询所有请假申请
	List<StudentLeaveApplication> queryAllLeaveAppByTea(long tid,int startIndex, int currentCount);
	//通过申请
	int batchProcessApplication(String s);
	//拒绝申请
	int rejectLeaveApplication(long id);
	//查询请假申请的总条数
	public int getTotalCount(long tid);

}
