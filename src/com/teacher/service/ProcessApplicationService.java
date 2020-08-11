package com.teacher.service;

import java.util.List;

import com.student.entity.StudentLeaveApplication;
import com.system.entity.PageBean;

public interface ProcessApplicationService {
	List<StudentLeaveApplication> queryAllLeaveAppByTea(long tid,int startIndex, int currentCount);
	public PageBean<StudentLeaveApplication> findPageBean(int currentPage,long tid);
	int batchProcessApplication(String s);
	int rejectLeaveApplication(long id);

}
