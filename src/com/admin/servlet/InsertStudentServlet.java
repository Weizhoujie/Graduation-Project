package com.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.service.StudentService;
import com.admin.service.impl.StudentServiceImpl;
import com.student.entity.Student;


@WebServlet("/InsertStudentServlet")
public class InsertStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertStudentServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 String realname = request.getParameter("realname");
		 String sex = request.getParameter("sex");
		 String sclass = request.getParameter("sclass");
		 String date = request.getParameter("date");
		 Student student = new Student();
		 student.setId(0);
		 student.setUsername(username);
		 student.setPassword(password);
		 student.setRealname(realname);
		 student.setSex(sex);
		 student.setSclass(sclass);
		 student.setAdmissionTime(date);		
		 StudentService service = new StudentServiceImpl();
		 int result = service.insertStudent(student);
		 if (result == 0) {
			request.setAttribute("error", "添加失败，学号可能重复");
			request.getRequestDispatcher("/page/admin/addStudent.jsp").forward(request, response);
		 }else {
			 session.setAttribute("message", "成功添加"+result+"条数据");
			 request.getRequestDispatcher("/StudentServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
