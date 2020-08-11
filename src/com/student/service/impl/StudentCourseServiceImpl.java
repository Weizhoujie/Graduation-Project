package com.student.service.impl;

import java.util.List;

import com.student.dao.StudentCourseDao;
import com.student.dao.impl.StudentCourseDaoImpl;
import com.student.entity.StudentChoseCourse;
import com.student.service.StudentCourseService;
import com.system.entity.PageBean;
import com.teacher.entity.TeacherChoseCourse;

public class StudentCourseServiceImpl implements StudentCourseService{
	StudentCourseDao dao = new StudentCourseDaoImpl();

	@Override
	public List<TeacherChoseCourse> QueryAllCourse(int startIndex, int currentCount) {
		List<TeacherChoseCourse> list = dao.QueryAllCourse(startIndex, currentCount);
		return list;
	}

	@Override
	public List<TeacherChoseCourse> queryCourseByCname(String s) {
		List<TeacherChoseCourse> list = dao.queryCourseByCname(s);
		return list;
	}

	@Override
	public int chooseCourseByStudent(long sid, long tcid) {
		List<TeacherChoseCourse> coursesByTcid = dao.queryCoursesByTcid(tcid);
		String cname = "";
		int result = 1;
		for (TeacherChoseCourse teacherChoseCourse : coursesByTcid) {
			cname = teacherChoseCourse.getCname();
		}
		List<StudentChoseCourse> coursesByStudent = dao.queryCoursesByStudent(sid);
		for (StudentChoseCourse studentChoseCourse : coursesByStudent) {
			if (studentChoseCourse.getCname().equals(cname) && studentChoseCourse.getFlag()!=-1) {
				result = 0;
				return result;
			}
		}
		result = dao.chooseCourseByStudent(sid, tcid);
		return result;
	}

	@Override
	public PageBean<TeacherChoseCourse> findPageBean(int currentPage) {
		PageBean<TeacherChoseCourse> pageBean = new PageBean<TeacherChoseCourse>();
		//1.当前页数		---- 前台用户点击的页数
		pageBean.setCurrentPage(currentPage);
		
		//2.当前页面显示条数	---- 默认设定
		int currentCount = 10;
		pageBean.setCurrentCount(currentCount);
		
		//3.总条数			---- 向dao层传递请求,从数据库中获取
		int totalCount = dao.getTotalCount();
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
		List<TeacherChoseCourse> courses = dao.QueryAllCourse(startIndex, currentCount);
		pageBean.setTeacherChoseCourses(courses);
		return pageBean;
	}

	@Override
	public List<TeacherChoseCourse> queryCourseByTeachername(String s) {
		List<TeacherChoseCourse> list = dao.queryCourseByTeachername(s);
		return list;
	}

	@Override
	public List<TeacherChoseCourse> queryCourseByCnameAndTname(String cname, String tname) {
		List<TeacherChoseCourse> list = dao.queryCourseByCnameAndTname(cname, tname);
		return list;
	}

	@Override
	public List<StudentChoseCourse> queryCoursesByStudent(long sid) {
		List<StudentChoseCourse> list = dao.queryCoursesByStudent(sid);
		return list;
	}

	@Override
	public int giveupCourse(long scid) {
		int result = dao.giveupCourse(scid);
		return result;
	}

}
