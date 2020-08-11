package com.admin.service.impl;

import java.util.List;


import com.admin.dao.StudentDao;
import com.admin.dao.impl.StudenDaoImpl;
import com.admin.service.StudentService;
import com.student.entity.Student;
import com.system.entity.PageBean;
import com.system.entity.StudentForImport;


public class StudentServiceImpl implements StudentService{

	StudentDao studentDao = new StudenDaoImpl();
	public List<Student> queryAllStudent(int startIndex, int currentCount) {
		List<Student> list = studentDao.QueryAllStudent(startIndex, currentCount);
		return list;
	}

	
	public PageBean<Student> findPageBean(int currentPage) {
		
		PageBean<Student> pageBean = new PageBean<Student>();
		//1.当前页数		---- 前台用户点击的页数
		pageBean.setCurrentPage(currentPage);
		
		//2.当前页面显示条数	---- 默认设定
		int currentCount = 10;
		pageBean.setCurrentCount(currentCount);
		
		//3.总条数			---- 向dao层传递请求,从数据库中获取
		int totalCount = studentDao.getTotalCount();
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
		List<Student> students = studentDao.QueryAllStudent(startIndex, currentCount);
		pageBean.setStudent(students);;
		return pageBean;
	}


	
	public int batchDeleteStudents(String s) {
		int result = 0;
		result = studentDao.batchDeleteStudents(s);
		return result;
	}


	public List<Student> queryStudentByUsername(String s) {
		List<Student> list = null;
		list = studentDao.queryStudentByUsername(s);
		return list;
	}


	
	public boolean updateStudent(Student student) {
		List<Student> list = null;
		list = studentDao.queryStudentByUsername(student.getUsername());
		//所更改的学号不存在，可以更改
		if (list.size() == 0) {
			boolean result = studentDao.updateStudent(student);
			if (result) {
				return true;
			}else {
				return false;
			}
		}else{
			long id = 0;
			for (Student student2 : list) {
				id = student2.getId();
			}
			if (id == student.getId()) {
				boolean result = studentDao.updateStudent(student);
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


	
	public int insertStudent(Student student) {
		List<Student> list = null;
		list = studentDao.queryStudentByUsername(student.getUsername());
		//所更改的学号不存在，可以插入
		if (list.size() == 0) {
			int result = studentDao.insertStudent(student);
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
	public int BacthInsertStudent(StudentForImport studentForImport) {
		List<Student> list = null;
		list = studentDao.queryStudentByUsername(studentForImport.getUsername());
		//所更改的学号不存在，可以插入
		if (list.size() == 0) {
			int result = studentDao.BacthInsertStudent(studentForImport);
			if (result == 0) {
				return 0;
			}else {
				return result;
			}
		}else {
			return 0;
		}
	}

}
