package com.alex.springDemo2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "student")
public class StudentProperties {
	private String id = "123";
	private String sex = "未知";
	private int age = 0;
    
}
