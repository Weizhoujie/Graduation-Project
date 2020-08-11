package com.system.servlet;

import java.io.IOException;
import java.sql.SQLException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;

import com.admin.entity.Admin;
import com.student.entity.Student;
import com.teacher.entity.Teacher;
import com.system.service.LoginService;
import com.system.service.impl.LoginServiceImpl;




@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 String checkcode = request.getParameter("checkcode");
		 String identity = request.getParameter("identity");
		 LoginService loginService = new LoginServiceImpl();
		 HttpSession session = request.getSession();
			
		//获取生成的图片验证码
			String checkCode_session = (String) request.getSession().getAttribute("checkcode_session");
			if (!checkcode.equals(checkCode_session)) {
				//验证码不正确
				request.setAttribute("loginInfo", "验证码不正确");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			if(identity.equals("管理员")){
				//用管理员身份进行登陆
				Admin admin = null;
				try {
					admin = loginService.QueryAdmin(username, password);
				} catch (SQLException e) {
		
					e.printStackTrace();
				}
				if (admin == null) {
					//用户名或密码错误
					request.setAttribute("loginInfo", "用户名或密码错误");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}else if(admin != null && admin.getPassword().equals(password)) {
					//登陆成功
					session.setAttribute("admin", admin);
					request.setAttribute("identitycheck", "管理员");
					request.getRequestDispatcher("/admin.jsp").forward(request, response);
				}
			}else if (identity.equals("教师")) {
				//用教师身份进行登陆
				Teacher teacher = null;
				try {
					teacher = loginService.QueryTeacher(username, password);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				if (teacher == null) {
					//用户名或密码错误
					request.setAttribute("loginInfo", "用户名或密码错误");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}else if(teacher != null && teacher.getPassword().equals(password)) {
					//登陆成功
					session.setAttribute("teacher", teacher);
					request.setAttribute("identitycheck", "教师");
					request.getRequestDispatcher("/admin.jsp").forward(request, response);
				}
			}else {
				//用学生身份进行登陆
				Student student = null;
				try {
					student = loginService.QueryStudent(username, password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (student == null) {
					//用户名或密码错误
					request.setAttribute("loginInfo", "用户名或密码错误");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}else if(student != null && student.getPassword().equals(password)) {
					//登陆成功
					session.setAttribute("student", student);
					LogFactory.getLog(getClass()).info(student.getRealname()+"登陆了系统");
 					request.setAttribute("identitycheck", "学生");
					request.getRequestDispatcher("/admin.jsp").forward(request, response);
				}
			}
		 
		 
		 
		 
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
