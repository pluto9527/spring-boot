package com.jcfc.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//开启发现服务功能
@EnableDiscoveryClient
@SpringBootApplication
public class Springboot16EurekaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot16EurekaConsumerApplication.class, args);
	}

	@LoadBalanced	//使用负载均衡机制（默认轮询）
	@Bean	//eureka采用http方式远程调用
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
