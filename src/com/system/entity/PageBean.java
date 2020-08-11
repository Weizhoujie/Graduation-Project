package com.system.entity;

import java.util.ArrayList;
import java.util.List;

import com.admin.entity.Course;
import com.student.entity.Student;
import com.student.entity.StudentAttendance;
import com.student.entity.StudentLeaveApplication;
import com.teacher.entity.AfterInsertAttendance;
import com.teacher.entity.PendingCourse;
import com.teacher.entity.Teacher;
import com.teacher.entity.TeacherChoseCourse;

//封装分页实体类PageBean
public class PageBean<T> {
	
	// 当前页数
	private int currentPage;
	
	// 当前页面显示条数
	private int currentCount;
	
	// 总页数
	private int totalPage;
	
	// 总条数
	private int totalCount;
	
	// 每页显示的数据
	private List<Student> student = new ArrayList<Student>();
	private List<Teacher> teacher = new ArrayList<Teacher>();
	private List<Course> course = new ArrayList<Course>();
	private List<TeacherChoseCourse> teacherChoseCourses = new ArrayList<TeacherChoseCourse>();
	private List<PendingCourse> pendingCourses = new ArrayList<PendingCourse>();
	private List<AfterInsertAttendance> attendances = new ArrayList<AfterInsertAttendance>();
	private List<StudentAttendance> studentAttendances = new ArrayList<StudentAttendance>();
	private List<StudentLeaveApplication> leaveApp = new ArrayList<StudentLeaveApplication>();

	

	public List<StudentLeaveApplication> getLeaveApp() {
		return leaveApp;
	}

	public void setLeaveApp(List<StudentLeaveApplication> leaveApp) {
		this.leaveApp = leaveApp;
	}

	public List<StudentAttendance> getStudentAttendances() {
		return studentAttendances;
	}

	public void setStudentAttendances(List<StudentAttendance> studentAttendances) {
		this.studentAttendances = studentAttendances;
	}

	public List<AfterInsertAttendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<AfterInsertAttendance> attendances) {
		this.attendances = attendances;
	}

	public List<PendingCourse> getPendingCourses() {
		return pendingCourses;
	}

	public void setPendingCourses(List<PendingCourse> pendingCourses) {
		this.pendingCourses = pendingCourses;
	}

	public List<TeacherChoseCourse> getTeacherChoseCourses() {
		return teacherChoseCourses;
	}

	public void setTeacherChoseCourses(List<TeacherChoseCourse> teacherChoseCourses) {
		this.teacherChoseCourses = teacherChoseCourses;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public List<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(List<Teacher> teacher) {
		this.teacher = teacher;
	}

	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	

	



	
	
}

