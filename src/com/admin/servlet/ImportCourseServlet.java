package com.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.service.CourseService;
import com.admin.service.impl.CourseServiceImpl;
import com.system.entity.CourseForImport;
import com.system.utils.ExcelUtil;


@WebServlet("/ImportCourseServlet")
public class ImportCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ImportCourseServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 String filename = (String) request.getAttribute("filename");
		 List<CourseForImport> courseInfos = new
				 ExcelUtil().parseFromExcel("C:\\Users\\ASUS\\eclipse-workspace\\ClassAttendance\\WebContent\\uploadfile\\"+filename, 1,
						 CourseForImport.class);
		 CourseService service = new CourseServiceImpl();
		 int count = 0;
		 for (CourseForImport courseForImport : courseInfos) {
			 
			 int result = service.BacthInsertCourse(courseForImport);
			 count = count + result;
		}
		 
		 if (count == 0) {
			request.setAttribute("error", "添加失败，账号可能重复");
			request.getRequestDispatcher("/page/admin/addCourse.jsp").forward(request, response);
		 }else {
			 session.setAttribute("message", "成功添加"+count+"条数据");
			 request.getRequestDispatcher("/CourseServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
