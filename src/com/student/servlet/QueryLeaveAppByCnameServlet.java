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
import com.student.entity.StudentLeaveApplication;
import com.student.service.StudentLeaveAppService;
import com.student.service.impl.StudentLeaveAppServiceImpl;


@WebServlet("/QueryLeaveAppByCnameServlet")
public class QueryLeaveAppByCnameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public QueryLeaveAppByCnameServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 StudentLeaveAppService service = new StudentLeaveAppServiceImpl();
		 Student student = (Student) session.getAttribute("student");
		 String cname = request.getParameter("cname");
		 List<StudentLeaveApplication> list = null;
		 list = service.queryLeaveAppByCname(student.getId(),cname);
		 if (list.size() != 0) {
			 request.setAttribute("allapplication", list);
			 request.getRequestDispatcher("/page/student/leaveapplist.jsp").forward(request, response);
		 }else {
			 request.setAttribute("page", "1");
			 session.setAttribute("message", "不存在符合条件的考勤记录！");
			 request.getRequestDispatcher("/AllLeaveAppByStuServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
