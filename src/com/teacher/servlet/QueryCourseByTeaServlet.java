package com.teacher.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.entity.Course;
import com.teacher.entity.Teacher;
import com.teacher.entity.TeacherChoseCourse;
import com.teacher.service.TeacherCourseService;
import com.teacher.service.impl.TeacherCourseServiceImpl;


@WebServlet("/QueryCourseByTeaServlet")
public class QueryCourseByTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public QueryCourseByTeaServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 TeacherCourseService service = new TeacherCourseServiceImpl();
		 String cname = request.getParameter("cname");
		 List<Course> list = null;
		 list = service.queryCourseByCname(cname);
		 if (list.size() != 0) {
			 Teacher teacher = (Teacher) session.getAttribute("teacher");
			 List<TeacherChoseCourse> list2 = service.queryChoseCourseByTid(teacher.getId());
			 request.setAttribute("allcourses", list);
			 request.setAttribute("teacherchosecourses", list2);
			 request.getRequestDispatcher("/page/teacher/allcoursesbyteacher.jsp").forward(request, response);
		 }else {
			 request.setAttribute("page", "1");
			 session.setAttribute("message", "不存在该课程！");
			 request.getRequestDispatcher("/AllCourseByTeaServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
