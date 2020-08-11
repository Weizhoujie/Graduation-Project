package com.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.service.CourseService;
import com.admin.service.impl.CourseServiceImpl;


@WebServlet("/BatchDelCouServlet")
public class BatchDelCouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BatchDelCouServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 CourseService courseService = new CourseServiceImpl();
		 String s = request.getParameter("delcoursesid");
		 int result = 0;
		 result = courseService.batchDeleteCourses(s);
		 if(result > 0) {
			 session.setAttribute("message", "删除成功！");
			 request.getRequestDispatcher("/CourseServlet").forward(request, response);
		 }else {
			 session.setAttribute("message", "删除失败！");
			 request.getRequestDispatcher("/CourseServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
