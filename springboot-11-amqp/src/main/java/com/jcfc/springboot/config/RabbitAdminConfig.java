package com.jcfc.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitAdminConfig {

    private final String QUEUE_NAME = "test_queue";
    private final String EXCHANGE_NAME = "test_exchange";
    private final String ROUTING_KEY = "test_key";

    @Autowired
    AmqpAdmin amqpAdmin;

    //绑定队列
    @Bean
    public Binding binding() {
        Queue queue = declareQueue();
        Exchange exchange = declareExchange();
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).and(null);
        amqpAdmin.declareBinding(binding);
        return binding;
    }

    //声明队列
    public Queue declareQueue() {
        Queue queue = new Queue(QUEUE_NAME, false, false, false);
        amqpAdmin.declareQueue(queue);
        return queue;
    }

    //声明交换机
    public Exchange declareExchange() {
        Exchange exchange = new DirectExchange(EXCHANGE_NAME, false, false);
        amqpAdmin.declareExchange(exchange);
        return exchange;
    }

}
