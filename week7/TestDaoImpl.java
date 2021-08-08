package com.alex.springDemo2.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
    
//	@Autowired
//	private JdbcTemplate jdbcTemplate1;
//	

	@Resource(name = "masterDataSource")
    private DataSource masterDataSource;
    
	@Resource(name = "slaveDataSource")
    private DataSource slaveDataSource;
    
    //返回auto configuration 生成的student bean
	public Student queryStudentById(String id) {
		 Student s = applicationContext.getBean("student", Student.class);
		return s;
	}
	
	//注解注入jdbcTemplate 
	public Student queryStudentById2(String id) {
		JdbcTemplate jdbcTemplate1 = new JdbcTemplate(slaveDataSource);
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

	//注解注入jdbcTemplate 
	@Override
	public void batchInsertData(long idStart) {
		long start = System.currentTimeMillis();
		JdbcTemplate jdbcTemplate1 = new JdbcTemplate(masterDataSource);
		jdbcTemplate1.batchUpdate("insert into student(id,name,sex,age)values(?,?,?,?);", new BatchPreparedStatementSetter() {
		    long count = idStart;
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, count++);
				ps.setString(2, "小明");
				ps.setString(3,"男" );
				ps.setString(4, "20");
			}
			@Override
			public int getBatchSize() {
				return 100;
			}
		});
		
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("耗时：" + time);
	}
	
	
	
}
