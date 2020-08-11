package com.teacher.service;

import java.util.List;

import com.system.entity.PageBean;
import com.teacher.entity.PendingCourse;

public interface ApproveCourseService {
	//查询待审批课程
	List<PendingCourse> queryAllPendingCourse(long tid,int startIndex, int currentCount);
	//通过选课申请
	int batchApproveCourse(String s);
	//驳回选课申请
	int rejectCourse(long id);
	public PageBean<PendingCourse> findPageBean(long tid,int currentPage);

}
