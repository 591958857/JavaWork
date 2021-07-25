package com.alex.springDemo2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.springDemo2.dao.TestDao;
import com.alex.springDemo2.model.Student;
import com.alex.springDemo2.service.TestService;


@Service
public class TestServiceImpl implements TestService{
	@Autowired
	private TestDao testDao;
	
	@Override
	public Student queryStudentById(String id) {
//		return testDao.queryStudentById(id);
		return testDao.queryStudentById2(id);
//		return testDao.queryStudentById3(id);
	}
}
