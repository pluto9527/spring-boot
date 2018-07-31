package com.jcfc.springboot.controller;

import com.jcfc.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/hello2")
    @ResponseBody
    public String hello2(@RequestParam("user") String user) {
        if (user.equals("aaa")) {
            throw new UserNotExistException();
        }
        return "exception";
    }

}
