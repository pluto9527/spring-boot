package com.jcfc.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 一、服务提供者将服务注册到注册中心
 * 		1、引入dubbo和zkclient的相关依赖
 * 		2、配置dubbo的扫描包和注册中心地址
 * 		3、使用@Service注解发布服务
 */
@SpringBootApplication
public class Springboot15DubboProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot15DubboProviderApplication.class, args);
	}
}
