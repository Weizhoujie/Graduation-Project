package com.teacher.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teacher.entity.StudentForAttendance;
import com.teacher.service.AttendanceByTeaService;
import com.teacher.service.impl.AttendanceByTeaServiceImpl;


@WebServlet("/StudentListForAttendanceServlet")
public class StudentListForAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StudentListForAttendanceServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/jsp");
		
		String sclass = request.getParameter("sclass");
		String tcid = request.getParameter("tcid");
		//System.out.println(sclass);
		//System.out.println(tcid);
		AttendanceByTeaService service = new AttendanceByTeaServiceImpl();
		List<StudentForAttendance> list = service.queryStudentForAttendance(sclass, Long.parseLong(tcid));
		request.setAttribute("studentlist", list);
		request.getRequestDispatcher("/page/teacher/studentforattendance.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
