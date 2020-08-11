package com.teacher.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teacher.entity.AfterInsertAttendance;
import com.teacher.entity.Teacher;
import com.teacher.service.AttendanceByTeaService;
import com.teacher.service.impl.AttendanceByTeaServiceImpl;


@WebServlet("/QueryAttendanceByTeaServlet")
public class QueryAttendanceByTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public QueryAttendanceByTeaServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 String username = request.getParameter("username").trim();
		 String cname = request.getParameter("cname").trim();
		 Teacher teacher = (Teacher) session.getAttribute("teacher");
		 AttendanceByTeaService service = new AttendanceByTeaServiceImpl();
		 List<AfterInsertAttendance> list = null;
		 if(username!=null && cname.equals("")) {
			 list = service.queryAttendanceByUsername(teacher.getId(), username);
		 }
		 else if (username.equals("") && cname != null) {
			 list = service.queryAttendanceByCname(teacher.getId(), cname);
		}else if(username!= null && cname !=null){
			 list = service.queryAttendanceByCnameAndUsername(teacher.getId(), cname, username);
		}
		 if (list.size() != 0) {
			 
			 request.setAttribute("allattendance", list);
			 request.getRequestDispatcher("/page/teacher/attendancelist.jsp").forward(request, response);
		}else {
			 request.setAttribute("page", "1");
			 session.setAttribute("message", "不存在符合条件的考勤！");
			 request.getRequestDispatcher("/AllAttendanceByTeaServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
