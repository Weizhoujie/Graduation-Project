package com.student.dao;

import java.util.List;

import com.student.entity.StudentLeaveApplication;

public interface StudentLeaveAppDao {
	//查询所有请假申请
	List<StudentLeaveApplication> queryAllLeaveAppByStu(long sid,int startIndex, int currentCount);
	//删除申请
	int batchDelLeaveApplication(String s);
	//根据课程查询申请记录
	List<StudentLeaveApplication> queryLeaveAppByCname(long sid,String cname);
	//添加请假申请
	int insertLeaveApplication(long sid,long tid,long cid,String time,String location);
	//查询请假申请的总条数
	public int getTotalCount(long sid);

}
