package com.jcfc.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 二、服务消费者引用服务
 * 		1、引入dubbo和zkclient的相关依赖
 * 		2、配置dubbo的注册中心地址
 * 		3、使用@Refenrence注解引用服务
 */
@SpringBootApplication
public class Springboot15DubboConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot15DubboConsumerApplication.class, args);
	}
}
