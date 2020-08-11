package com.student.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.entity.Student;
import com.student.entity.StudentAttendance;
import com.student.service.StudentAttendanceService;
import com.student.service.impl.StudentAttendanceServiceImpl;



@WebServlet("/QueryAttendanceByStuServlet")
public class QueryAttendanceByStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public QueryAttendanceByStuServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 StudentAttendanceService service = new StudentAttendanceServiceImpl();
		 Student student = (Student) session.getAttribute("student");
		 String cname = request.getParameter("cname");
		 List<StudentAttendance> list = null;
		 list = service.queryAttendancesByCname(student.getId(),cname);
		 if (list.size() != 0) {
			 request.setAttribute("allattendance", list);
			 request.getRequestDispatcher("/page/student/attendancelist.jsp").forward(request, response);
		 }else {
			 request.setAttribute("page", "1");
			 session.setAttribute("message", "不存在符合条件的考勤记录！");
			 request.getRequestDispatcher("/AllAttendancesByStuServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
