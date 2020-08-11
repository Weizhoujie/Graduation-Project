package com.teacher.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;

import com.teacher.entity.Teacher;
import com.teacher.service.TeacherInfoService;
import com.teacher.service.impl.TeacherInfoServiceImpl;


@WebServlet("/TeacherChangePwServlet")
public class TeacherChangePwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public TeacherChangePwServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 String password = request.getParameter("password");
		 String oldpassword = request.getParameter("oldpassword");
		 TeacherInfoService service = new TeacherInfoServiceImpl();
		 Teacher teacher = (Teacher) session.getAttribute("teacher");
		 
		 if (teacher.getPassword().equals(oldpassword)) {
			 boolean result = service.UpdatePassword(teacher.getId(), password);
			 if(result) {
				 Teacher newTeacher = service.queryTeacherById(teacher.getId());
				 LogFactory.getLog(getClass()).info(teacher.getRealname()+"修改了密码");
				 request.setAttribute("success", "修改成功");
				 session.setAttribute("teacher", newTeacher);
				 request.getRequestDispatcher("/TeacherInfoServlet").forward(request, response);
			 }
			 else {
				request.setAttribute("error", "修改密码失败");
				request.getRequestDispatcher("/page/teacher/changepassword.jsp").forward(request, response);
			}
		}
		else {
			 	request.setAttribute("error", "旧密码错误");
				request.getRequestDispatcher("/page/teacher/changepassword.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
