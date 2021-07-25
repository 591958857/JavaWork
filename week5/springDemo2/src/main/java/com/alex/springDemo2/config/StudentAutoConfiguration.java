package com.alex.springDemo2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alex.springDemo2.model.Student;

@Configuration
@Import(StudentConfiguration.class)
@EnableConfigurationProperties(StudentProperties.class)
public class StudentAutoConfiguration {
	
	 @Autowired
	 StudentProperties properties;
	 
	 @Autowired
	 StudentConfiguration configuration;

	  @Bean(name = "student")
	  public Student createStudent() {
		  Student s =new Student();
		  s.setName(configuration.name);
		  s.setId(properties.getId());
		  s.setSex(properties.getSex());
		  s.setAge(properties.getAge());
		  return s;
	  }
	
}
