package com.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.entity.Course;
import com.admin.service.CourseService;
import com.admin.service.impl.CourseServiceImpl;


@WebServlet("/QueryCourseByCnameServlet")
public class QueryCourseByCnameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public QueryCourseByCnameServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 CourseService courseService = new CourseServiceImpl();
		 String cname = request.getParameter("cname");
		 List<Course> list = null;
		 list = courseService.queryCourseByCname(cname);
		 if (list.size() != 0) {
			 
			 request.setAttribute("allcourses", list);
			 request.getRequestDispatcher("/page/admin/courselist.jsp").forward(request, response);
		 }else {
			 request.setAttribute("page", "1");
			 session.setAttribute("message", "不存在该课程！");
			 request.getRequestDispatcher("/CourseServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
