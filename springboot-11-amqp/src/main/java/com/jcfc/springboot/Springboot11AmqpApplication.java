package com.jcfc.springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置：
 　　1、RabbitAutoConfiguration
 　　2、自动配置了连接工厂 ConnectionFactory
 　　3、RabbitProperties封装了 RabbitMQ的配置
 　　4、RabbitTemplate:给RabbitMQ发送和接受消息的
 　　5、AmqpAdmin：RabbitMQ的系统管理功能组件（声明队列、交换机、绑定等，相当于信道）
 			AmqpAdmin：创建和删除 Queue，Exchange，Binding
 　　6、@EnableRabbit + @RabbitListener 监听消息队列内容
 */
@SpringBootApplication
@EnableRabbit	//开启基于注解的rabbitmq模式
public class Springboot11AmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot11AmqpApplication.class, args);
	}
}
