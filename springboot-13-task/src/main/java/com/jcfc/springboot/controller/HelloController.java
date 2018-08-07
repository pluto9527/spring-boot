package com.jcfc.springboot.controller;

import com.jcfc.springboot.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("hello")
    public String hello() {
        asyncService.hello();
        return "success";
    }

}
