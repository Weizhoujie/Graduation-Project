package com.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.entity.Course;
import com.admin.service.CourseService;
import com.admin.service.impl.CourseServiceImpl;


@WebServlet("/UpdateCourseServlet")
public class UpdateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateCourseServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 String page = request.getParameter("page-1");
		 
		 String id = request.getParameter("id");
		 String cname = request.getParameter("cname-1");
		 String cintro = request.getParameter("cintro");
		 Course course = new Course();
		 course.setCid(Long.parseLong(id));
		 course.setCname(cname);
		 course.setCintro(cintro);
		 CourseService courseService = new CourseServiceImpl();
		
		 boolean result = courseService.updateCourse(course);
		 if (result) {
			 request.setAttribute("page", page);
			 session.setAttribute("message","修改成功");
			 request.getRequestDispatcher("/CourseServlet").forward(request, response);
		}else {
			request.setAttribute("page", page);
			session.setAttribute("message", "修改失败，课程名字可能重复");
			request.getRequestDispatcher("/CourseServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
