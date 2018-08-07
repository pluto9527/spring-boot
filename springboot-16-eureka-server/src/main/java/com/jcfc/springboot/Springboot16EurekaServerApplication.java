package com.jcfc.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 * 	1、配置Eureka信息
 * 	2、@EnableEurekaServer开启注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class Springboot16EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot16EurekaServerApplication.class, args);
	}
}
