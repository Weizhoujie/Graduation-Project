package com.student.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.entity.Student;
import com.student.entity.StudentChoseCourse;
import com.student.service.StudentCourseService;
import com.student.service.impl.StudentCourseServiceImpl;


@WebServlet("/CourseAndTeaBeforeLeaveServlet")
public class CourseAndTeaBeforeLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CourseAndTeaBeforeLeaveServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 StudentCourseService service = new StudentCourseServiceImpl();
		 Student student = (Student) session.getAttribute("student");
		 List<StudentChoseCourse> list1 = service.queryCoursesByStudent(student.getId());
		 List<StudentChoseCourse> list2 = new ArrayList<StudentChoseCourse>();
		 for (StudentChoseCourse studentChoseCourse : list1) {
			if (studentChoseCourse.getFlag() == 1) {
				list2.add(studentChoseCourse);
			}
		}
		 request.setAttribute("chosecourses", list2);
		 request.getRequestDispatcher("/page/student/addleaveapplication.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
