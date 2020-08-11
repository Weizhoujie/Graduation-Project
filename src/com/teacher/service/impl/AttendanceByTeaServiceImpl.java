package com.teacher.service.impl;

import java.util.List;

import com.system.entity.PageBean;
import com.teacher.dao.AttendanceByTeaDao;
import com.teacher.dao.impl.AttendanceByTeaDaoImpl;
import com.teacher.entity.AfterInsertAttendance;
import com.teacher.entity.AttendanceResult;
import com.teacher.entity.AttendanceTime;
import com.teacher.entity.Sclass;
import com.teacher.entity.StudentForAttendance;
import com.teacher.service.AttendanceByTeaService;

public class AttendanceByTeaServiceImpl implements AttendanceByTeaService{
	AttendanceByTeaDao dao = new AttendanceByTeaDaoImpl();

	@Override
	public List<Sclass> querySclass(long tcid) {
		List<Sclass> list = dao.querySclass(tcid);
		return list;
	}

	@Override
	public List<StudentForAttendance> queryStudentForAttendance(String sclass, long tcid) {
		List<StudentForAttendance> list = dao.queryStudentForAttendance(sclass, tcid);
		return list;
	}

	@Override
	public int insertAttendance(AttendanceResult attendanceResult) {
		int result = dao.insertAttendance(attendanceResult);
		return result;
	}

	@Override
	public List<AfterInsertAttendance> queryAfterInsertAttendances(int count) {
		List<AfterInsertAttendance> list = dao.queryAfterInsertAttendances(count);
		return list;
	}

	@Override
	public List<AfterInsertAttendance> queryAllAttendances(int startIndex, int currentCount, long tid) {
		List<AfterInsertAttendance> list = dao.queryAllAttendances(startIndex, currentCount, tid);
		return list;
	}

	@Override
	public PageBean<AfterInsertAttendance> findPageBean(int currentPage,long tid) {
		PageBean<AfterInsertAttendance> pageBean = new PageBean<AfterInsertAttendance>();
		//1.当前页数		---- 前台用户点击的页数
		pageBean.setCurrentPage(currentPage);
		
		//2.当前页面显示条数	---- 默认设定
		int currentCount = 20;
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
		List<AfterInsertAttendance> attendances = dao.queryAllAttendances(startIndex, currentCount, tid);
		pageBean.setAttendances(attendances);
		return pageBean;
	}

	@Override
	public int updateAttendance(long id, String status) {
		int result = dao.updateAttendance(id, status);
		return result;
	}

	@Override
	public int batchDelAttendance(String s) {
		int result = dao.batchDelAttendance(s);
		return result;
	}

	@Override
	public List<AfterInsertAttendance> queryAttendanceByCname(long tid, String s) {
		List<AfterInsertAttendance> list = dao.queryAttendanceByCname(tid, s);
		return list;
	}

	@Override
	public List<AfterInsertAttendance> queryAttendanceByUsername(long tid, String s) {
		List<AfterInsertAttendance> list = dao.queryAttendanceByUsername(tid, s);
		return list;
	}

	@Override
	public List<AfterInsertAttendance> queryAttendanceByCnameAndUsername(long tid, String cname, String username) {
		List<AfterInsertAttendance> list = dao.queryAttendanceByCnameAndUsername(tid, cname, username);
		return list;
	}

	@Override
	public List<AttendanceTime> queryAttendanceCount(long tid, long cid) {
		List<AttendanceTime> list = dao.queryAttendanceCount(tid, cid);
		return list;
	}

	@Override
	public List<AfterInsertAttendance> queryAttendanceByCount(long tid, long cid, String s) {
		List<AfterInsertAttendance> list = dao.queryAttendanceByCount(tid, cid, s); 
		return list;
	}

}
