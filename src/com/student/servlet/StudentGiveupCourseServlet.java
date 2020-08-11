package com.student.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.service.StudentCourseService;
import com.student.service.impl.StudentCourseServiceImpl;


@WebServlet("/StudentGiveupCourseServlet")
public class StudentGiveupCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StudentGiveupCourseServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/jsp");
		
		String id = request.getParameter("id");
		StudentCourseService service = new StudentCourseServiceImpl();
		int result = service.giveupCourse(Long.parseLong(id));
		if (result > 0) {
			session.setAttribute("message", "退选课程成功");
			
		}else {
			session.setAttribute("message", "退选课程失败");
		}
		request.getRequestDispatcher("/StudentChoseCourseServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
