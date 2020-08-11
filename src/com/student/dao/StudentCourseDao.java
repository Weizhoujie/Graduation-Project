package com.student.dao;

import java.util.List;

import com.student.entity.StudentChoseCourse;
import com.teacher.entity.TeacherChoseCourse;

public interface StudentCourseDao {
	//查询所有课程
	List<TeacherChoseCourse> QueryAllCourse(int startIndex, int currentCount);
	//根据课程名字查询课程
	List<TeacherChoseCourse> queryCourseByCname(String s);
	//根据教师名字查询课程
	List<TeacherChoseCourse> queryCourseByTeachername(String s);
	//根据课程名字和教师名字查询课程
	List<TeacherChoseCourse> queryCourseByCnameAndTname(String cname,String tname);
	//学生添加选课
	int chooseCourseByStudent(long sid,long tcid);
	//查询学生已选课程
	List<StudentChoseCourse> queryCoursesByStudent(long sid);
	//根据教师选课表id查询课程
	List<TeacherChoseCourse> queryCoursesByTcid(long tcid);
	//学生退课
	int giveupCourse(long scid);
	
	//查询课程的总条数
	public int getTotalCount();

}
