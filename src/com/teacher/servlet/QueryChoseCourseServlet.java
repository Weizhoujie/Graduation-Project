package com.teacher.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teacher.entity.Teacher;
import com.teacher.entity.TeacherChoseCourse;
import com.teacher.service.TeacherCourseService;
import com.teacher.service.impl.TeacherCourseServiceImpl;


@WebServlet("/QueryChoseCourseServlet")
public class QueryChoseCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public QueryChoseCourseServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 TeacherCourseService courseService = new TeacherCourseServiceImpl();
		 Teacher teacher = (Teacher) session.getAttribute("teacher");
		 List<TeacherChoseCourse> list = courseService.queryChoseCourseByTid(teacher.getId());
		 
		 request.setAttribute("chosecourses", list);
		 request.getRequestDispatcher("/page/teacher/chosecourses.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
