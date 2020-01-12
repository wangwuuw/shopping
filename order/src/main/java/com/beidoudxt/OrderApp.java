/**
 * 
 */
package com.beidoudxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月22日下午3:28:08
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.beidoudxt.dao")
public class OrderApp {

	/**
	 * @Description
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(OrderApp.class, args);

	}

}
