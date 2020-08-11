package com.teacher.service.impl;

import java.util.List;

import com.admin.entity.Course;
import com.system.entity.PageBean;
import com.teacher.dao.TeacherCourseDao;
import com.teacher.dao.impl.TeacherCourseDaoImpl;
import com.teacher.entity.TeacherChoseCourse;
import com.teacher.service.TeacherCourseService;

public class TeacherCourseServiceImpl implements TeacherCourseService{
	TeacherCourseDao teacherCourseDao = new TeacherCourseDaoImpl();

	public List<Course> QueryAllCourse(int startIndex, int currentCount) {
		List<Course> list = teacherCourseDao.QueryAllCourse(startIndex, currentCount);
		return list;
	}

	@Override
	public List<Course> queryCourseByCname(String s) {
		List<Course> list = teacherCourseDao.queryCourseByCname(s);
		return list;
	}

	@Override
	public PageBean<Course> findPageBean(int currentPage) {
		PageBean<Course> pageBean = new PageBean<Course>();
		//1.当前页数		---- 前台用户点击的页数
		pageBean.setCurrentPage(currentPage);
		
		//2.当前页面显示条数	---- 默认设定
		int currentCount = 10;
		pageBean.setCurrentCount(currentCount);
		
		//3.总条数			---- 向dao层传递请求,从数据库中获取
		int totalCount = teacherCourseDao.getTotalCount();
		pageBean.setTotalCount(totalCount);
		
		//4.总页数			---- 向上取整,double(总条数/当前页面显示条数).ceil
		/*
		 * 		总条数   	当前页面显示条数		总页数
		 * 		 10			4			 3
		 *  	 11			4			 3
		 *   	 12			4			 3
		 *   	 13			4			 4		
		 */
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		
		//5.每页显示的数据		---- 起始索引==(当前页面-1)* 每页显示条数;
	
		/*
		 * 	      当前页数   		起始索引		 每页显示条数
		 * 		1		  0				4           --- 0,1,2,3
		 * 		2		  4				4			--- 4,5,6,7
		 * 		3		  8				4			--- 8,9,10,11
		 * 		4		  12 			4			--- 12,13,14,15
		 */
		int startIndex = (currentPage-1)*currentCount;
		List<Course> courses = teacherCourseDao.QueryAllCourse(startIndex, currentCount);
		pageBean.setCourse(courses);
		return pageBean;
	}

	@Override
	public int chooseCourseByTeacher(long tid, long cid) {
		int result = teacherCourseDao.chooseCourseByTeacher(tid, cid);
		return result;
	}

	@Override
	public List<TeacherChoseCourse> queryChoseCourseByTid(long tid) {
		List<TeacherChoseCourse> list = teacherCourseDao.queryChoseCourseByTid(tid);
		return list;
	}

	@Override
	public boolean giveupCourseByTeacher(long id) {
		boolean result = teacherCourseDao.giveupCourseByTeacher(id);
		return result;
	}

}
