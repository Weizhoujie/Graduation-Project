package com.teacher.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teacher.entity.Sclass;
import com.teacher.service.AttendanceByTeaService;
import com.teacher.service.impl.AttendanceByTeaServiceImpl;


@WebServlet("/QuerySclassServlet")
public class QuerySclassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public QuerySclassServlet() {
        super();
       
    }

	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/jsp");
		
		String id = request.getParameter("id");
		//System.out.println(id);
		AttendanceByTeaService service = new AttendanceByTeaServiceImpl();
		List<Sclass> list1 = service.querySclass(Long.parseLong(id));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tcid", id);
		List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();
		list2.add(map);
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.add(list1);
		objects.add(list2);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(objects);
		//System.out.println("!!!!!!");
		//System.out.println(json);
		//System.out.println("aaaaaaaaaaaaa");
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
