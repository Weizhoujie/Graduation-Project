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


@WebServlet("/UpdateTeacherServlet")
public class UpdateTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateTeacherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 String page = request.getParameter("page-1");
		 
		 String id = request.getParameter("id");
		 String username = request.getParameter("username-1");
		 String password = request.getParameter("password");
		 String realname = request.getParameter("realname");
		 String sex = request.getParameter("sex");
		 String faculty = request.getParameter("faculty");
		 String position = request.getParameter("position");
		 String email = request.getParameter("email");
		 String intro = request.getParameter("intro");
		 Teacher teacher = new Teacher();
		 teacher.setId(Long.parseLong(id));
		 teacher.setUsername(username);
		 teacher.setPassword(password);
		 teacher.setRealname(realname);
		 teacher.setSex(sex);
		 teacher.setFaculty(faculty);
		 teacher.setPosition(position);
		 teacher.setEmail(email);
		 teacher.setIntro(intro);
		 
		 TeacherService teacherService = new TeacherServiceImpl();
		
		 boolean result = teacherService.updateTeacher(teacher);
		 if (result) {
			 request.setAttribute("page", page);
			 session.setAttribute("message","修改成功");
			 request.getRequestDispatcher("/TeacherServlet").forward(request, response);
		}else {
			request.setAttribute("page", page);
			session.setAttribute("message", "修改失败，账号可能重复");
			request.getRequestDispatcher("/TeacherServlet").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
