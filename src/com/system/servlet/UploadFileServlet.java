package com.system.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;



@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/jsp");
		System.out.println("***处理上传请求***");
		String fileType = request.getParameter("filetype");//区分上传的文件类型
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File("e:/"));//临时文件存储路径
		ServletFileUpload fileUpload = new ServletFileUpload(factory);//核心操作对象
		fileUpload.setHeaderEncoding("utf-8");//防乱码
		try {
			List<FileItem> list = fileUpload.parseRequest(request);//解析器解析request请求
			for (FileItem fileItem : list) {//多文件上传处理
				InputStream in = fileItem.getInputStream();
				String filename = fileItem.getName();//得到的是全路径文件名，需要处理
				if (filename!=null) {
					filename =UUID.randomUUID()+"_"+FilenameUtils.getName(filename);//filename = 时间戳_文件名.类型，解决文件重名被覆盖问题
					System.out.println(filename);
					int len = 0;
					byte[] array = new byte[1024];
					FileOutputStream fos = new FileOutputStream("C:\\Users\\ASUS\\eclipse-workspace\\ClassAttendance\\WebContent\\uploadfile\\"+filename);
					while((len = in.read(array))!=-1){//表示每次最多读1024个字节
						fos.write(array,0,len);
						fos.flush();
					}
					fos.close();
					in.close();
					fileItem.delete();
					if (fileType.equals("excelforstudent")) {
						request.setAttribute("filename", filename);
						request.getRequestDispatcher("/ImportStudentServlet").forward(request, response);
					}
					if (fileType.equals("excelforteacher")) {
						request.setAttribute("filename", filename);
						request.getRequestDispatcher("/ImportTeacherServlet").forward(request, response);
					}
					if (fileType.equals("excelforcourse")) {
						request.setAttribute("filename", filename);
						request.getRequestDispatcher("/ImportCourseServlet").forward(request, response);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}			


	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
