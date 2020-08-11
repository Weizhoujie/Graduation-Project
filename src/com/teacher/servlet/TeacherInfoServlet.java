package com.teacher.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teacher.entity.Teacher;


@WebServlet("/TeacherInfoServlet")
public class TeacherInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public TeacherInfoServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");

		 Teacher teacher = (Teacher) session.getAttribute("teacher");
		 
		 request.setAttribute("teacherinfo", teacher);
		 request.getRequestDispatcher("/page/teacher/teacherInfo.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
