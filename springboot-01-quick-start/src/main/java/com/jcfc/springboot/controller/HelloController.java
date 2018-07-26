package com.jcfc.springboot.controller;

import com.jcfc.springboot.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*@Controller
@ResponseBody*/
@RestController
public class HelloController {

    @Autowired
    private Person person;

    @Autowired
    ApplicationContext ioc;

    @RequestMapping("hello")
    public String hello() {
        System.out.println(person);
        System.out.println(ioc.containsBean("helloService"));
        return "hello boot，quick start!";
    }

}
