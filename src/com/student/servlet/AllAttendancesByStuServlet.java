package com.student.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.entity.Student;
import com.student.entity.StudentAttendance;
import com.student.service.StudentAttendanceService;
import com.student.service.impl.StudentAttendanceServiceImpl;
import com.system.entity.PageBean;



@WebServlet("/AllAttendancesByStuServlet")
public class AllAttendancesByStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AllAttendancesByStuServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/jsp");
		StudentAttendanceService service = new StudentAttendanceServiceImpl();
		Student student = (Student) session.getAttribute("student");
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
		 PageBean<StudentAttendance> pageBean = null;
	 	 pageBean = service.findPageBean(currentPage,student.getId());
		 
		 List<StudentAttendance> list = service.queryAllAttendancesByStu((pageBean.getCurrentPage()-1)*pageBean.getCurrentCount(),pageBean.getCurrentCount(),student.getId());
		 request.setAttribute("pageBean", pageBean);
		 request.setAttribute("allattendance", list);
		
		 request.getRequestDispatcher("/page/student/attendancelist.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
