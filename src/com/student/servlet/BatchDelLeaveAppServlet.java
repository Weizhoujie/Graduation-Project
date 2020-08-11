package com.student.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.service.StudentLeaveAppService;
import com.student.service.impl.StudentLeaveAppServiceImpl;


@WebServlet("/BatchDelLeaveAppServlet")
public class BatchDelLeaveAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BatchDelLeaveAppServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 StudentLeaveAppService service = new StudentLeaveAppServiceImpl();
		 String s = request.getParameter("delleaveappid");
		 int result = 0;
		 result = service.batchDelLeaveApplication(s);
		 if(result > 0) {
			 session.setAttribute("message", "删除成功！");
			 request.getRequestDispatcher("/AllLeaveAppByStuServlet").forward(request, response);
		 }else {
			 session.setAttribute("message", "删除失败！");
			 request.getRequestDispatcher("/AllLeaveAppByStuServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
