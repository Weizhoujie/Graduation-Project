package com.teacher.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.entity.StudentLeaveApplication;
import com.system.entity.PageBean;
import com.teacher.entity.Teacher;
import com.teacher.service.ProcessApplicationService;
import com.teacher.service.impl.ProcessApplicationServiceImpl;


@WebServlet("/ProcessingApplicationsServlet")
public class ProcessingApplicationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ProcessingApplicationsServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 ProcessApplicationService service = new ProcessApplicationServiceImpl();
		 Teacher teacher = (Teacher) session.getAttribute("teacher");
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
		 PageBean<StudentLeaveApplication> pageBean = null;
	 	 pageBean = service.findPageBean(currentPage, teacher.getId());
		 
		 List<StudentLeaveApplication> list = service.queryAllLeaveAppByTea(teacher.getId(),(pageBean.getCurrentPage()-1)*pageBean.getCurrentCount(),pageBean.getCurrentCount());
		 request.setAttribute("pageBean", pageBean);
		 request.setAttribute("allapplication", list);
		
		 request.getRequestDispatcher("/page/teacher/leaveapplist.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
