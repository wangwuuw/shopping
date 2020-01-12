/**
 * 
 */
package com.beidoudxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月15日下午8:16:25
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.beidoudxt.dao")
public class ProductApp {

	/**
	 * @Description
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProductApp.class, args);

	}

}
