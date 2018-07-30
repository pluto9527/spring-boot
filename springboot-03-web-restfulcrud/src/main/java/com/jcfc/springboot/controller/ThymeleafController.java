package com.jcfc.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
public class ThymeleafController {

    @RequestMapping("/hello")
    public String hello(Map<String, Object> map) {
        map.put("hello", "<h2>你好！</h2>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
    }

}
