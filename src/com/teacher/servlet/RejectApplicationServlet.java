package com.teacher.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teacher.service.ProcessApplicationService;
import com.teacher.service.impl.ProcessApplicationServiceImpl;


@WebServlet("/RejectApplicationServlet")
public class RejectApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RejectApplicationServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 ProcessApplicationService service = new ProcessApplicationServiceImpl();
		 String s = request.getParameter("rejectid");
		 int result = 0;
		 result = service.rejectLeaveApplication(Long.parseLong(s));
		 if(result > 0) {
			 session.setAttribute("message", "拒绝成功！");
			 request.getRequestDispatcher("/ProcessingApplicationsServlet").forward(request, response);
		 }else {
			 session.setAttribute("message", "拒绝失败！");
			 request.getRequestDispatcher("/ProcessingApplicationsServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
