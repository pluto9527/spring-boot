package com.jcfc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("say")
    public String say(String name) {
        /**
         * 第一个参数：url，EUREKA-PROVIDER是应用名
         * 第二个参数是返回值类型
         */
        String s = restTemplate.getForObject("http://EUREKA-PROVIDER/hello", String.class);
        return name+":say "+s;
    }

}
