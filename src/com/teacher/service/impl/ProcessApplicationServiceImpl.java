package com.teacher.service.impl;

import java.util.List;

import com.student.entity.StudentLeaveApplication;
import com.system.entity.PageBean;
import com.teacher.dao.ProcessApplicationDao;
import com.teacher.dao.impl.ProcessApplicationDaoImpl;
import com.teacher.service.ProcessApplicationService;

public class ProcessApplicationServiceImpl implements ProcessApplicationService{
	ProcessApplicationDao dao = new ProcessApplicationDaoImpl();

	@Override
	public List<StudentLeaveApplication> queryAllLeaveAppByTea(long tid, int startIndex, int currentCount) {
		List<StudentLeaveApplication> list = dao.queryAllLeaveAppByTea(tid, startIndex, currentCount);
		return list;
	}

	@Override
	public PageBean<StudentLeaveApplication> findPageBean(int currentPage, long tid) {
		PageBean<StudentLeaveApplication> pageBean = new PageBean<StudentLeaveApplication>();
		//1.当前页数		---- 前台用户点击的页数
		pageBean.setCurrentPage(currentPage);
		
		//2.当前页面显示条数	---- 默认设定
		int currentCount = 10;
		pageBean.setCurrentCount(currentCount);
		
		//3.总条数			---- 向dao层传递请求,从数据库中获取
		int totalCount = dao.getTotalCount(tid);
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
		List<StudentLeaveApplication> leaveApplications = dao.queryAllLeaveAppByTea(tid, startIndex, currentCount);
		pageBean.setLeaveApp(leaveApplications);
		return pageBean;
	}

	@Override
	public int batchProcessApplication(String s) {
		int result = dao.batchProcessApplication(s);
		return result;
	}

	@Override
	public int rejectLeaveApplication(long id) {
		int result = dao.rejectLeaveApplication(id);
		return result;
	}

}
