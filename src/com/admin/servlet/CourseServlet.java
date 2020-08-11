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
import com.system.entity.PageBean;


@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CourseServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 CourseService courseService = new CourseServiceImpl();
		 String pageByUpdate = (String) request.getAttribute("page");
		 int currentPage;
		 if (pageByUpdate == null) {
			//获取当前页数
			 String page = request.getParameter("page");
			 if(page == null) {
				 page = "1";
			 }
			 currentPage = Integer.parseInt(page);
		}else {
			currentPage = Integer.parseInt(pageByUpdate);
		}	 
		 PageBean<Course> pageBean = null;
	 	 pageBean = courseService.findPageBean(currentPage);
		 
		 List<Course> list = courseService.QueryAllCourse((pageBean.getCurrentPage()-1)*pageBean.getCurrentCount(),pageBean.getCurrentCount());
		 request.setAttribute("pageBean", pageBean);
		 request.setAttribute("allcourses", list);
		
		 request.getRequestDispatcher("/page/admin/courselist.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
