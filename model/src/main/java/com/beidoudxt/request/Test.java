package com.beidoudxt.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Test {
	private String name;
	private Integer age;
public static void main(String[] args) {
	Test test = new Test();
	test.getAge();
	test.setAge(1);
	
}
}
