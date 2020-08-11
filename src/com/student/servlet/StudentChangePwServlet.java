package com.student.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;

import com.student.entity.Student;
import com.student.service.StudentInfoService;
import com.student.service.impl.StudentInfoServiceImpl;


@WebServlet("/StudentChangePwServlet")
public class StudentChangePwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StudentChangePwServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 String password = request.getParameter("password");
		 String oldpassword = request.getParameter("oldpassword");
		 StudentInfoService service = new StudentInfoServiceImpl();
		 Student student = (Student) session.getAttribute("student");
		 
		 if (student.getPassword().equals(oldpassword)) {
			 boolean result = service.UpdatePassword(student.getId(), password);
			 if(result) {
				 Student newStudent = service.queryStudentById(student.getId());
				 LogFactory.getLog(getClass()).info(newStudent.getRealname()+"修改了密码");
				 request.setAttribute("success", "修改成功");
				 session.setAttribute("student", newStudent);
				 request.getRequestDispatcher("/StudentInfoServlet").forward(request, response);
			 }
			 else {
				request.setAttribute("error", "修改密码失败");
				request.getRequestDispatcher("/page/student/changepassword.jsp").forward(request, response);
			}
		}
		else {
			 	request.setAttribute("error", "旧密码错误");
				request.getRequestDispatcher("/page/student/changepassword.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
