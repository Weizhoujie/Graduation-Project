package com.student.service;

import java.util.List;

import com.student.entity.StudentLeaveApplication;
import com.system.entity.PageBean;

public interface StudentLeaveAppService {
	//查询所有请假申请
	List<StudentLeaveApplication> queryAllLeaveAppByStu(long sid,int startIndex, int currentCount);
	int batchDelLeaveApplication(String s);
	List<StudentLeaveApplication> queryLeaveAppByCname(long sid,String cname);
	int insertLeaveApplication(long sid,long tid,long cid,String time,String location);
	public PageBean<StudentLeaveApplication> findPageBean(int currentPage,long sid);

}
