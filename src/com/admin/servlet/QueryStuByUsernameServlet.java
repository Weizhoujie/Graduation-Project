package com.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.service.StudentService;
import com.admin.service.impl.StudentServiceImpl;
import com.student.entity.Student;


@WebServlet("/QueryStuByUsernameServlet")
public class QueryStuByUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public QueryStuByUsernameServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 StudentService studentService = new StudentServiceImpl();
		
		 String username = request.getParameter("username");
		 List<Student> list = null;
		 list = studentService.queryStudentByUsername(username);
		 if (list.size() != 0) {
			 
			 request.setAttribute("allstudents", list);
			 request.getRequestDispatcher("/page/admin/studentlist.jsp").forward(request, response);
		 }else {
			 request.setAttribute("page", "1");
			 session.setAttribute("message", "不存在该学生！");
			 request.getRequestDispatcher("/StudentServlet").forward(request, response);
		}	
		 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
