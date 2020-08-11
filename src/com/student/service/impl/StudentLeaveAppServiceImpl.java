package com.student.service.impl;

import java.util.List;

import com.student.dao.StudentLeaveAppDao;
import com.student.dao.impl.StudentLeaveAppDaoImpl;
import com.student.entity.StudentLeaveApplication;
import com.student.service.StudentLeaveAppService;
import com.system.entity.PageBean;

public class StudentLeaveAppServiceImpl implements StudentLeaveAppService{
	StudentLeaveAppDao dao = new StudentLeaveAppDaoImpl();
	@Override
	public List<StudentLeaveApplication> queryAllLeaveAppByStu(long sid, int startIndex, int currentCount) {
		List<StudentLeaveApplication> list = dao.queryAllLeaveAppByStu(sid, startIndex, currentCount);
		return list;
	}

	@Override
	public PageBean<StudentLeaveApplication> findPageBean(int currentPage, long sid) {
		PageBean<StudentLeaveApplication> pageBean = new PageBean<StudentLeaveApplication>();
		//1.当前页数		---- 前台用户点击的页数
		pageBean.setCurrentPage(currentPage);
		
		//2.当前页面显示条数	---- 默认设定
		int currentCount = 10;
		pageBean.setCurrentCount(currentCount);
		
		//3.总条数			---- 向dao层传递请求,从数据库中获取
		int totalCount = dao.getTotalCount(sid);
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
		List<StudentLeaveApplication> leaveApplications = dao.queryAllLeaveAppByStu(sid, startIndex, currentCount);
		pageBean.setLeaveApp(leaveApplications);
		return pageBean;
	}

	@Override
	public int batchDelLeaveApplication(String s) {
		int result = dao.batchDelLeaveApplication(s);
		return result;
	}

	@Override
	public List<StudentLeaveApplication> queryLeaveAppByCname(long sid, String cname) {
		List<StudentLeaveApplication> list = dao.queryLeaveAppByCname(sid, cname);
		return list;
	}

	@Override
	public int insertLeaveApplication(long sid, long tid, long cid, String time, String location) {
		int result = dao.insertLeaveApplication(sid, tid, cid, time, location);
		return result;
	}

}
