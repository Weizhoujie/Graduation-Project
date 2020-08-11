package com.admin.service.impl;

import java.util.List;

import com.admin.dao.TeacherDao;
import com.admin.dao.impl.TeacherDaoImpl;
import com.admin.service.TeacherService;
import com.system.entity.PageBean;
import com.system.entity.TeacherForImport;
import com.teacher.entity.Teacher;

public class TeacherServiceImpl implements TeacherService{
	
	TeacherDao teacherDao = new TeacherDaoImpl();
	@Override
	public List<Teacher> queryAllTeacher(int startIndex, int currentCount) {
		List<Teacher> list = teacherDao.QueryAllTeacher(startIndex, currentCount);
		return list;
	}

	@Override
	public List<Teacher> queryTeacherByUsername(String s) {
		List<Teacher> list = null;
		list = teacherDao.queryTeacherByUsername(s);
		return list;
	}

	@Override
	public boolean updateTeacher(Teacher teacher) {
		List<Teacher> list = null;
		list = teacherDao.queryTeacherByUsername(teacher.getUsername());
		//所更改的学号不存在，可以更改
		if (list.size() == 0) {
			boolean result = teacherDao.updateTeacher(teacher);
			if (result) {
				return true;
			}else {
				return false;
			}
		}else{
			long id = 0;
			for (Teacher Teacher2 : list) {
				id = Teacher2.getId();
			}
			if (id == teacher.getId()) {
				boolean result = teacherDao.updateTeacher(teacher);
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
	public int insertTeacher(Teacher teacher) {
		List<Teacher> list = null;
		list = teacherDao.queryTeacherByUsername(teacher.getUsername());
		//所更改的学号不存在，可以插入
		if (list.size() == 0) {
			int result = teacherDao.insertTeacher(teacher);
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
	public int batchDeleteTeachers(String s) {
		int result = 0;
		result = teacherDao.batchDeleteTeachers(s);
		return result;
	}

	@Override
	public int BacthInsertTeacher(TeacherForImport teacherForImport) {
		List<Teacher> list = null;
		list = teacherDao.queryTeacherByUsername(teacherForImport.getUsername());
		//所更改的学号不存在，可以插入
		if (list.size() == 0) {
			int result = teacherDao.BacthInsertTeacher(teacherForImport);
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
	public PageBean<Teacher> findPageBean(int currentPage) {
		PageBean<Teacher> pageBean = new PageBean<Teacher>();
		//1.当前页数		---- 前台用户点击的页数
		pageBean.setCurrentPage(currentPage);
		
		//2.当前页面显示条数	---- 默认设定
		int currentCount = 10;
		pageBean.setCurrentCount(currentCount);
		
		//3.总条数			---- 向dao层传递请求,从数据库中获取
		int totalCount = teacherDao.getTotalCount();
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
		List<Teacher> teachers = teacherDao.QueryAllTeacher(startIndex, currentCount);
		pageBean.setTeacher(teachers);
		return pageBean;
	}

}
