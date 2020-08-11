package com.admin.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.admin.dao.StudentDao;
import com.student.entity.Student;
import com.system.entity.StudentForImport;
import com.system.utils.DataSourceUtils;


public class StudenDaoImpl implements StudentDao{
	private static QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

	public List<Student> QueryAllStudent(int startIndex, int currentCount) {
		List<Student> list = null;
		String sql = "select * from student limit ?,?";
		try {
			list = runner.query(sql, new BeanListHandler<Student>(Student.class),startIndex,currentCount);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	public int getTotalCount() {
		int totalCount=0;
		String sql = null;
		sql = "SELECT COUNT(*) from student";
			try {
				Long query = (Long)runner.query(sql, new ScalarHandler());
				totalCount = query.intValue();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return totalCount;
	}

	
	public int batchDeleteStudents(String s) {
		String sql = null;
		int result = 0;
		sql = "delete from student where id in ("+ s +")";
		try {
			result = runner.update(sql);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}

	
	public List<Student> queryStudentByUsername(String s) {
		List<Student> list = null;
		String sql = "select * from student where username =?";
		try {
			list = runner.query(sql, new BeanListHandler<Student>(Student.class),s);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateStudent(Student student) {
		String sql = "update student set username = ? , password = ? , realname = ? , "
				+ "sclass = ? , sex = ? , admissionTime = ? where id = ?";
		
		try {
			 int result = runner.update(sql, student.getUsername(),student.getPassword(),student.getRealname()
					 ,student.getSclass(),student.getSex(),student.getAdmissionTime(),student.getId());
			 if (result !=0 ) {
				 return true;
			}else {
				return false;
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int insertStudent(Student student) {
		String sql = "insert into student(username,password,realname,sclass,sex,admissionTime) "
				+ "values(?,?,?,?,?,?)";
		try {
			 int result = runner.update(sql, student.getUsername(),student.getPassword(),student.getRealname()
					 ,student.getSclass(),student.getSex(),student.getAdmissionTime());
			return result;
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int BacthInsertStudent(StudentForImport studentForImport) {
		String sql = "insert into student(username,password,realname,sclass,sex,admissionTime) "
				+ "values(?,?,?,?,?,?)";
		try {
			 int result = runner.update(sql, studentForImport.getUsername(),studentForImport.getPassword(),studentForImport.getRealname()
					 ,studentForImport.getSclass(),studentForImport.getSex(),studentForImport.getAdmissionTime());
			return result;
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	

}
