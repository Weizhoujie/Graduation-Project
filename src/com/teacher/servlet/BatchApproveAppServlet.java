package com.teacher.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teacher.service.AttendanceByTeaService;
import com.teacher.service.ProcessApplicationService;
import com.teacher.service.impl.AttendanceByTeaServiceImpl;
import com.teacher.service.impl.ProcessApplicationServiceImpl;


@WebServlet("/BatchApproveAppServlet")
public class BatchApproveAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BatchApproveAppServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 ProcessApplicationService service = new ProcessApplicationServiceImpl();
		 String s = request.getParameter("processleaveappid");
		 int result = 0;
		 result = service.batchProcessApplication(s);
		 if(result > 0) {
			 session.setAttribute("message", "通过成功"+result+"条申请");
			 request.getRequestDispatcher("/ProcessingApplicationsServlet").forward(request, response);
		 }else {
			 session.setAttribute("message", "通过申请失败");
			 request.getRequestDispatcher("/ProcessingApplicationsServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
