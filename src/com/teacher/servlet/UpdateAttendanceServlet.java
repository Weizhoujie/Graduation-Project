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


@WebServlet("/UpdateAttendanceServlet")
public class UpdateAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateAttendanceServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/jsp");
		String id = request.getParameter("id");
		String page = request.getParameter("page-1");
		String attendance = request.getParameter("attendance");
		AttendanceByTeaService service = new AttendanceByTeaServiceImpl();
		int result = service.updateAttendance(Long.parseLong(id), attendance);
		if (result == 0) {
			session.setAttribute("message", "修改失败");
		} else {
			session.setAttribute("message", "修改成功");

		}
		request.setAttribute("page", page);
		request.getRequestDispatcher("/AllAttendanceByTeaServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
