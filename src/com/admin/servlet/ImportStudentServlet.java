package com.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.service.StudentService;
import com.admin.service.impl.StudentServiceImpl;
import com.system.entity.StudentForImport;
import com.system.utils.ExcelUtil;


@WebServlet("/ImportStudentServlet")
public class ImportStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ImportStudentServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/jsp");
		 
		 String filename = (String) request.getAttribute("filename");
		 List<StudentForImport> studentInfos = new
				 ExcelUtil().parseFromExcel("C:\\Users\\ASUS\\eclipse-workspace\\ClassAttendance\\WebContent\\uploadfile\\"+filename, 1,
						 StudentForImport.class);
		 StudentService service = new StudentServiceImpl();
		 int count = 0;
		 for (StudentForImport studentForImport : studentInfos) {
			 
			 int result = service.BacthInsertStudent(studentForImport);
			 count = count + result;
		}
		 
		 if (count == 0) {
			request.setAttribute("error", "添加失败，学号可能重复");
			request.getRequestDispatcher("/page/admin/addStudent.jsp").forward(request, response);
		 }else {
			 session.setAttribute("message", "成功添加"+count+"条数据");
			 request.getRequestDispatcher("/StudentServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
