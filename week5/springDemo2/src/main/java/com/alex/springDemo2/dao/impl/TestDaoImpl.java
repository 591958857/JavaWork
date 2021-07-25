package com.alex.springDemo2.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;	
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.alex.springDemo2.dao.TestDao;
import com.alex.springDemo2.model.Student;

@Repository
public class TestDaoImpl implements TestDao, ApplicationContextAware{
	ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
    @Autowired
	private JdbcTemplate jdbcTemplate1;
	
    //返回auto configuration 生成的student bean
	public Student queryStudentById(String id) {
		 Student s = applicationContext.getBean("student", Student.class);
		return s;
	}
	
	//注解注入jdbcTemplate 
	public Student queryStudentById2(String id) {
		RowMapper<Student> rowMapper = new BeanPropertyRowMapper(Student.class);
		List<Student> stuList = jdbcTemplate1.query("select * from student where id = ?", rowMapper,id);
		if(CollectionUtils.isEmpty(stuList)) {
			return null;	
		}
		return stuList.get(0);
	}
	
	//通过xml文件获取dataSource bean，然后再构造jdbcTemplate
	public Student queryStudentById3(String id) {
		  ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext*.xml");
		  DataSource dataSource = applicationContext.getBean("driverManagerDataSource", DataSource.class);
		  JdbcTemplate  jdbcTemplate2 = 	  new JdbcTemplate(dataSource);
		  
		  //原生JDBC操作
//		  try {
//			  dataSource.getConnection().createStatement().execute(sql);
//			  dataSource.getConnection().createStatement().addBatch("");
//			  dataSource.getConnection().commit();
//			  dataSource.getConnection().close();
//			  
//			  dataSource.getConnection().prepareStatement(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		  
		  RowMapper<Student> rowMapper = new BeanPropertyRowMapper(Student.class);
		  List<Student> stuList = jdbcTemplate2.query("select * from student where id = ?", rowMapper,id);
		  if(CollectionUtils.isEmpty(stuList)) {
				return null;	
		  }
		  return stuList.get(0);
		
	}


	
	
}
