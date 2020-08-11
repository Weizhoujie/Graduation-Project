package com.teacher.dao;

import java.util.List;

import com.teacher.entity.PendingCourse;

public interface ApproveCourseDao {
	//查询待审批课程
	List<PendingCourse> queryAllPendingCourse(long tid,int startIndex, int currentCount);
	//通过选课申请
	int batchApproveCourse(String s);
	//驳回选课申请
	int rejectCourse(long id);
	//查询课程的总条数
	public int getTotalCount(long tid);

}
