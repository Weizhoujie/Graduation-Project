package com.teacher.service;

import java.util.List;

import com.admin.entity.Course;
import com.system.entity.PageBean;
import com.teacher.entity.TeacherChoseCourse;

public interface TeacherCourseService {
	//查询所有课程
	List<Course> QueryAllCourse(int startIndex, int currentCount);
	//根据课程名字查询课程
	List<Course> queryCourseByCname(String s);
	//教师添加选课
	int chooseCourseByTeacher(long tid, long cid);
	//查询教师已选课程
	List<TeacherChoseCourse> queryChoseCourseByTid(long tid);
	//教师退选课程
	boolean giveupCourseByTeacher(long id);
	public PageBean<Course> findPageBean(int currentPage);

}
