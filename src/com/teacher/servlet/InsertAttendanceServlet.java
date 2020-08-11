package com.teacher.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teacher.entity.AfterInsertAttendance;
import com.teacher.entity.AttendanceResult;
import com.teacher.service.AttendanceByTeaService;
import com.teacher.service.impl.AttendanceByTeaServiceImpl;


@WebServlet("/InsertAttendanceServlet")
public class InsertAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertAttendanceServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/jsp");
		String attendancelist = request.getParameter("attendancelist");
		System.out.println(attendancelist);
		
		AttendanceByTeaService service = new AttendanceByTeaServiceImpl();
		int count=0;
		final ObjectMapper mapper = new ObjectMapper();
		
		List<AttendanceResult> list = mapper.readValue(attendancelist, new TypeReference<List<AttendanceResult>>(){});
		
		for (AttendanceResult attendanceResult : list) {
			int result = service.insertAttendance(attendanceResult);
			count = count + result;
		}
		
		List<AfterInsertAttendance> afterInsertAttendances = service.queryAfterInsertAttendances(count);
		session.setAttribute("message", "成功添加"+count+"条考勤数据");
		request.setAttribute("afterInsertAttendances", afterInsertAttendances);
		request.getRequestDispatcher("/page/teacher/afterinsertattendance.jsp").forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
