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
import com.teacher.entity.TeacherChoseCourse;


@WebServlet("/QueryCourseByStuServlet")
public class QueryCourseByStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public QueryCourseByStuServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 String coursename = request.getParameter("coursename").trim();
		 String teachername = request.getParameter("teachername").trim();
		 
		 StudentCourseService service = new StudentCourseServiceImpl();
		 List<TeacherChoseCourse> list = null;
		 if(coursename!=null && teachername.equals("")) {
			 list = service.queryCourseByCname(coursename);
		 }
		 else if (coursename.equals("") && teachername != null) {
			 list = service.queryCourseByTeachername(teachername);
		}else if(coursename!= null && teachername !=null){
			 list = service.queryCourseByCnameAndTname(coursename, teachername);
		}
		 if (list.size() != 0) {
			 Student student = (Student) session.getAttribute("student");
			 List<StudentChoseCourse> list2 = service.queryCoursesByStudent(student.getId());
			 request.setAttribute("allcourses", list);
			 request.setAttribute("studentchosecourses", list2);
			 request.getRequestDispatcher("/page/student/allcoursesbystudent.jsp").forward(request, response);
		}else {
			 request.setAttribute("page", "1");
			 session.setAttribute("message", "不存在该课程！");
			 request.getRequestDispatcher("/AllCourseByStuServlet").forward(request, response);
		}
		 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
