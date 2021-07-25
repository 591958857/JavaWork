package com.alex.springDemo2.dao;

import com.alex.springDemo2.model.Student;

public interface TestDao{

	public Student queryStudentById(String id);
	public Student queryStudentById2(String id);
	public Student queryStudentById3(String id) ;
}
