package com.alex.springDemo2.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@SuppressWarnings("unused")
public class Student {

	private String id;
	private String name;
	private String sex;
	private int age;
}
