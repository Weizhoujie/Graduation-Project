package com.admin.service;

import java.util.List;

import com.admin.entity.Course;
import com.system.entity.CourseForImport;
import com.system.entity.PageBean;

public interface CourseService {
	//查询所有课程
	List<Course> QueryAllCourse(int startIndex, int currentCount);
	//根据课程名字查询课程
	List<Course> queryCourseByCname(String s);
	//批量删除课程
	int batchDeleteCourses(String s);
	//更新课程
	boolean updateCourse(Course course);
	//插入课程
	int insertCourse(Course course);
	//批量插入课程
	int BacthInsertCourse(CourseForImport courseForImport);
	public PageBean<Course> findPageBean(int currentPage);


}
