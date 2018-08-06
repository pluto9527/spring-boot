package com.jcfc.springboot;

import com.jcfc.springboot.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot11AmqpApplicationTests {

	private final String QUEUE_NAME = "test_queue";
	private final String EXCHANGE_NAME = "test_exchange";
	private final String ROUTING_KEY = "test_key";

	@Autowired
	AmqpAdmin amqpAdmin;  //用来声明队列、交换机、绑定等

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Test
	public void contextLoads() {
		Queue queue = new Queue(QUEUE_NAME, false, false, false);
		Exchange exchange = new DirectExchange(EXCHANGE_NAME, false, false);
		//声明交换机
		amqpAdmin.declareExchange(exchange);
		//声明队列
		amqpAdmin.declareQueue(queue);

		//绑定队列
		//方式一：
		Binding binding = BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).and(null);
		//方式二：
		//Binding binding2 = new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE_NAME, ROUTING_KEY, null);

		amqpAdmin.declareBinding(binding);
	}

	//单播（点对点）
	@Test
	public void Send() {
		//Message需要自己构建一个；定义消息体内容和消息头
		// rabbitTemplate.send(exchange, routingKey, message);

		//Object 默认当成消息体，只需要传入要发送的对象，自动化序列发送给rabbitmq；
		//rabbitTemplate.convertAndSend(exchange,routekey,object);

		Map<String, Object> map = new HashMap<>();
		map.put("msg", "第一个消息");
		map.put("data", Arrays.asList("hello", 123, true));
		map.put("book", new Book("书名", "作者"));
//		rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, map);
		rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, new Book("书名", "作者"));
	}

	@Test
	public void Recv() {
		Object o = rabbitTemplate.receiveAndConvert(QUEUE_NAME);
		System.out.println(o.getClass());
		System.out.println(o);
	}




}
