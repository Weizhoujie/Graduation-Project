package com.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.service.TeacherService;
import com.admin.service.impl.TeacherServiceImpl;
import com.system.entity.TeacherForImport;
import com.system.utils.ExcelUtil;


@WebServlet("/ImportTeacherServlet")
public class ImportTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ImportTeacherServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 String filename = (String) request.getAttribute("filename");
		 List<TeacherForImport> teacherInfos = new
				 ExcelUtil().parseFromExcel("C:\\Users\\ASUS\\eclipse-workspace\\ClassAttendance\\WebContent\\uploadfile\\"+filename, 1,
						 TeacherForImport.class);
		 TeacherService service = new TeacherServiceImpl();
		 int count = 0;
		 for (TeacherForImport teacherForImport : teacherInfos) {
			 
			 int result = service.BacthInsertTeacher(teacherForImport);
			 count = count + result;
		}
		 
		 if (count == 0) {
			request.setAttribute("error", "添加失败，账号可能重复");
			request.getRequestDispatcher("/page/admin/addTeacher.jsp").forward(request, response);
		 }else {
			 session.setAttribute("message", "成功添加"+count+"条数据");
			 request.getRequestDispatcher("/TeacherServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
