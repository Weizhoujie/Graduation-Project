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
import com.student.entity.StudentChoseCourse;
import com.student.service.StudentCourseService;
import com.student.service.impl.StudentCourseServiceImpl;
import com.system.entity.PageBean;
import com.teacher.entity.TeacherChoseCourse;


@WebServlet("/AllCourseByStuServlet")
public class AllCourseByStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AllCourseByStuServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 Student student = (Student) session.getAttribute("student");
		 StudentCourseService service = new StudentCourseServiceImpl();
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
		 PageBean<TeacherChoseCourse> pageBean = null;
	 	 pageBean = service.findPageBean(currentPage);
		 List<TeacherChoseCourse> list1 = service.QueryAllCourse((pageBean.getCurrentPage()-1)*pageBean.getCurrentCount(),pageBean.getCurrentCount());
		 List<StudentChoseCourse> list2 = service.queryCoursesByStudent(student.getId());
		 
		 request.setAttribute("pageBean", pageBean);
		 request.setAttribute("allcourses", list1);
		 request.setAttribute("studentchosecourses", list2);
		
		 request.getRequestDispatcher("/page/student/allcoursesbystudent.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
