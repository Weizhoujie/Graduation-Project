package com.teacher.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.entity.PageBean;
import com.teacher.entity.PendingCourse;
import com.teacher.entity.Teacher;
import com.teacher.service.ApproveCourseService;
import com.teacher.service.impl.ApproveCourseServiceImpl;


@WebServlet("/PendingCourseServlet")
public class PendingCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PendingCourseServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/jsp");
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		ApproveCourseService service = new ApproveCourseServiceImpl();
		String page = request.getParameter("page");
		 if(page == null)
			 page = "1";
		 int currentPage = Integer.parseInt(page);
		 PageBean<PendingCourse> pageBean = null;
	 	 pageBean = service.findPageBean(teacher.getId(),currentPage);
		 List<PendingCourse> list = service.queryAllPendingCourse(teacher.getId(),(pageBean.getCurrentPage()-1)*pageBean.getCurrentCount(),pageBean.getCurrentCount());
		 request.setAttribute("pageBean", pageBean);
		 request.setAttribute("allcourses", list);
		 request.getRequestDispatcher("/page/teacher/approvecourse.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
