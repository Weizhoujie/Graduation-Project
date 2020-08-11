package com.teacher.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teacher.entity.AfterInsertAttendance;
import com.teacher.entity.Teacher;
import com.teacher.service.AttendanceByTeaService;
import com.teacher.service.impl.AttendanceByTeaServiceImpl;


@WebServlet("/StatisticsServlet")
public class StatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StatisticsServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 Teacher teacher = (Teacher) session.getAttribute("teacher");
		 long tid = teacher.getId();
		 long cid = Long.parseLong(request.getParameter("cid"));
		 String time = request.getParameter("time"); 
		 AttendanceByTeaService service = new AttendanceByTeaServiceImpl();
		 List<AfterInsertAttendance> attendanceByCount = service.queryAttendanceByCount(tid, cid, time);
				 //System.out.println(attendanceByCount.size());
		 int daoqin = 0,queqin = 0,qingjia = 0,chidao = 0;
		 for (AfterInsertAttendance attendance : attendanceByCount) {
			switch (attendance.getStatus()) {
			case "到勤":
				daoqin++;
				break;
			case "迟到":
				chidao++;
				break;
			case "请假":
				qingjia++;
				break;
			case "缺勤":
				queqin++;
				break;
			default:
				break;
			}
		 }
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("到勤", daoqin);
		map.put("迟到", chidao);
		map.put("请假", qingjia);
		map.put("缺勤", queqin);
				 
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(map);
		System.out.println(jsonString);
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(jsonString);
		 
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
