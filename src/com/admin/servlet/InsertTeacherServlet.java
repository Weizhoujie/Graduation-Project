package com.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.service.TeacherService;
import com.admin.service.impl.TeacherServiceImpl;
import com.teacher.entity.Teacher;


@WebServlet("/InsertTeacherServlet")
public class InsertTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertTeacherServlet() {
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
		 String faculty = request.getParameter("faculty");
		 String position = request.getParameter("position");
		 String email = request.getParameter("email");
		 String intro = request.getParameter("intro");
		 Teacher teacher = new Teacher();
		 teacher.setId(0);
		 teacher.setUsername(username);
		 teacher.setPassword(password);
		 teacher.setRealname(realname);
		 teacher.setSex(sex);
		 teacher.setFaculty(faculty);
		 teacher.setPosition(position);
		 teacher.setEmail(email);
		 teacher.setIntro(intro);
		 TeacherService service = new TeacherServiceImpl();
		 int result = service.insertTeacher(teacher);
		 if (result == 0) {
			request.setAttribute("error", "添加失败，账号可能重复");
			request.getRequestDispatcher("/page/admin/addTeacher.jsp").forward(request, response);
		 }else {
			 session.setAttribute("message", "成功添加"+result+"条数据");
			 request.getRequestDispatcher("/TeacherServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
