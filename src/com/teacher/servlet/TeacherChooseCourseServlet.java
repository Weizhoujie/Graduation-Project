package com.teacher.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teacher.entity.Teacher;
import com.teacher.service.TeacherCourseService;
import com.teacher.service.impl.TeacherCourseServiceImpl;


@WebServlet("/TeacherChooseCourseServlet")
public class TeacherChooseCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public TeacherChooseCourseServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 String cid = request.getParameter("cid");
		 Teacher teacher = (Teacher) session.getAttribute("teacher");
		 TeacherCourseService service = new TeacherCourseServiceImpl();
		 int result = service.chooseCourseByTeacher(teacher.getId(), Long.parseLong(cid));
		 if (result > 0) {
			session.setAttribute("message", "添加选课成功");
			request.getRequestDispatcher("/AllCourseByTeaServlet").forward(request, response);
		 }else {
			session.setAttribute("message", "添加选课失败");
			request.getRequestDispatcher("/AllCourseByTeaServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
