package com.student.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.entity.Student;
import com.student.service.StudentCourseService;
import com.student.service.impl.StudentCourseServiceImpl;


@WebServlet("/StudentChooseCourseServlet")
public class StudentChooseCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StudentChooseCourseServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 String tcid = request.getParameter("tc_id");
		 Student student = (Student) session.getAttribute("student");
		 StudentCourseService service = new StudentCourseServiceImpl();
		 int result = service.chooseCourseByStudent(student.getId(), Long.parseLong(tcid));
		 if (result > 0) {
			session.setAttribute("message", "添加选课成功");
			request.getRequestDispatcher("/AllCourseByStuServlet").forward(request, response);
		 }else {
			session.setAttribute("message", "添加选课失败,已选其他同名课程");
			request.getRequestDispatcher("/AllCourseByStuServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
