package com.admin.service.impl;

import java.util.List;

import com.admin.dao.CourseDao;
import com.admin.dao.impl.CourseDaoImpl;
import com.admin.entity.Course;
import com.admin.service.CourseService;
import com.system.entity.CourseForImport;
import com.system.entity.PageBean;

public class CourseServiceImpl implements CourseService{
	CourseDao courseDao = new CourseDaoImpl();

	@Override
	public List<Course> QueryAllCourse(int startIndex, int currentCount) {
		List<Course> list = courseDao.QueryAllCourse(startIndex, currentCount);
		return list;
	}

	@Override
	public List<Course> queryCourseByCname(String s) {
		List<Course> list = null;
		list = courseDao.queryCourseByCname(s);
		return list;
	}

	@Override
	public int batchDeleteCourses(String s) {
		int result = 0;
		result = courseDao.batchDeleteCourses(s);
		return result;
	}

	@Override
	public boolean updateCourse(Course course) {
		List<Course> list = null;
		list = courseDao.queryCourseByCname(course.getCname());
		//所更改的学号不存在，可以更改
		if (list.size() == 0) {
			boolean result = courseDao.updateCourse(course);
			if (result) {
				return true;
			}else {
				return false;
			}
		}else{
			long cid = 0;
			for (Course course2 : list) {
				cid = course2.getCid();
			}
			if (cid == course.getCid()) {
				boolean result = courseDao.updateCourse(course);
				if (result) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
	}

	@Override
	public int insertCourse(Course course) {
		List<Course> list = null;
		list = courseDao.queryCourseByCname(course.getCname());
		//所更改的学号不存在，可以插入
		if (list.size() == 0) {
			int result = courseDao.insertCourse(course);
			if (result == 0) {
				return 0;
			}else {
				return result;
			}
		}else {
			return 0;
		}
	}

	@Override
	public int BacthInsertCourse(CourseForImport courseForImport) {
		List<Course> list = null;
		list = courseDao.queryCourseByCname(courseForImport.getCname());
		//所更改的学号不存在，可以插入
		if (list.size() == 0) {
			int result = courseDao.BacthInsertCourse(courseForImport);
			if (result == 0) {
				return 0;
			}else {
				return result;
			}
		}else {
			return 0;
		}
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
		int totalCount = courseDao.getTotalCount();
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
		List<Course> courses = courseDao.QueryAllCourse(startIndex, currentCount);
		pageBean.setCourse(courses);
		return pageBean;
	}

	

}
