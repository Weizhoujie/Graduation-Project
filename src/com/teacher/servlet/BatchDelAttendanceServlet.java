package com.teacher.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teacher.service.AttendanceByTeaService;
import com.teacher.service.impl.AttendanceByTeaServiceImpl;


@WebServlet("/BatchDelAttendanceServlet")
public class BatchDelAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BatchDelAttendanceServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 AttendanceByTeaService service = new AttendanceByTeaServiceImpl();
		 String s = request.getParameter("delattendanceid");
		 int result = 0;
		 result = service.batchDelAttendance(s);
		 if(result > 0) {
			 session.setAttribute("message", "删除成功！");
			 request.getRequestDispatcher("/AllAttendanceByTeaServlet").forward(request, response);
		 }else {
			 session.setAttribute("message", "删除失败！");
			 request.getRequestDispatcher("/AllAttendanceByTeaServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
