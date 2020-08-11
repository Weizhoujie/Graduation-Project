package com.teacher.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teacher.service.TeacherCourseService;
import com.teacher.service.impl.TeacherCourseServiceImpl;


@WebServlet("/TeacherGiveupCourseServlet")
public class TeacherGiveupCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public TeacherGiveupCourseServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/jsp");
		
		String id = request.getParameter("id");
		TeacherCourseService service = new TeacherCourseServiceImpl();
		boolean result = service.giveupCourseByTeacher(Long.parseLong(id));
		if (result) {
			session.setAttribute("message", "退选课程成功");
		}
		else {
			session.setAttribute("message", "退选课程失败");
		}
		request.getRequestDispatcher("/QueryChoseCourseServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
