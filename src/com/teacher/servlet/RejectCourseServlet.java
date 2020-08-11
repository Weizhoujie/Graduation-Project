package com.teacher.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teacher.service.ApproveCourseService;
import com.teacher.service.impl.ApproveCourseServiceImpl;


@WebServlet("/RejectCourseServlet")
public class RejectCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RejectCourseServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 ApproveCourseService service = new ApproveCourseServiceImpl();
		 String s = request.getParameter("rejectid");
		 int result = 0;
		 result = service.rejectCourse(Long.parseLong(s));
		 if(result > 0) {
			 session.setAttribute("message", "拒绝成功！");
			 request.getRequestDispatcher("/PendingCourseServlet").forward(request, response);
		 }else {
			 session.setAttribute("message", "拒绝失败！");
			 request.getRequestDispatcher("/PendingCourseServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
