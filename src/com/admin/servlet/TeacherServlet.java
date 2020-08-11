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
import com.system.entity.PageBean;
import com.teacher.entity.Teacher;


@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public TeacherServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 TeacherService teacherService = new TeacherServiceImpl();
		 String pageByUpdate = (String) request.getAttribute("page");
		 int currentPage;
		 if (pageByUpdate == null) {
			//获取当前页数
			 String page = request.getParameter("page");
			 if(page == null)
				 page = "1";
			 currentPage = Integer.parseInt(page);
		}else {
			currentPage = Integer.parseInt(pageByUpdate);
		}	 
		 PageBean<Teacher> pageBean = null;
	 	 pageBean = teacherService.findPageBean(currentPage);
		 
		 List<Teacher> list = teacherService.queryAllTeacher((pageBean.getCurrentPage()-1)*pageBean.getCurrentCount(),pageBean.getCurrentCount());
		 request.setAttribute("pageBean", pageBean);
		 request.setAttribute("allteachers", list);
		
		 request.getRequestDispatcher("/page/admin/teacherlist.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
