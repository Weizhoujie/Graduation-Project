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


@WebServlet("/InsertCourseServlet")
public class InsertCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertCourseServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 String cname = request.getParameter("cname");
		 String cintro = request.getParameter("cintro");
		 Course course = new Course();
		 course.setCid(0);
		 course.setCname(cname);
		 course.setCintro(cintro);
		 CourseService service = new CourseServiceImpl();
		 int result = service.insertCourse(course);
		 if (result == 0) {
			request.setAttribute("error", "添加失败，课程名可能重复");
			request.getRequestDispatcher("/page/admin/addCourse.jsp").forward(request, response);
		 }else {
			 session.setAttribute("message", "成功添加"+result+"条数据");
			 request.getRequestDispatcher("/CourseServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
