package com.jcfc.springboot.controller;

import com.jcfc.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("hello")
    public String hello() {
        return userService.hello();
    }

}
