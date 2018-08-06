package com.jcfc.springboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcfc.springboot.entity.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BookService {

//    @RabbitListener(queues = "test_queue")
    public void recv(Book book) {
        System.out.println("监听消息："+book);
    }

    @RabbitListener(queues = "test_queue")
    public void recv(Message message) throws IOException {
        byte[] body = message.getBody();
        MessageProperties messageProperties = message.getMessageProperties();

        //反序列化
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = objectMapper.readValue(body, Book.class);

        System.out.println("book: "+book);
        System.out.println(messageProperties);
    }

}
