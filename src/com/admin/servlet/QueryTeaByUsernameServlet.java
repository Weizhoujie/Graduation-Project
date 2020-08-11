package com.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.service.TeacherService;
import com.admin.service.impl.TeacherServiceImpl;
import com.teacher.entity.Teacher;


@WebServlet("/QueryTeaByUsernameServlet")
public class QueryTeaByUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public QueryTeaByUsernameServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 TeacherService teacherService = new TeacherServiceImpl();
		
		 String username = request.getParameter("username");
		 List<Teacher> list = null;
		 list = teacherService.queryTeacherByUsername(username);
		 if (list.size() != 0) {
			 
			 request.setAttribute("allteachers", list);
			 request.getRequestDispatcher("/page/admin/teacherlist.jsp").forward(request, response);
		 }else {
			 request.setAttribute("page", "1");
			 session.setAttribute("message", "不存在该教师！");
			 request.getRequestDispatcher("/TeacherServlet").forward(request, response);
		}	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
