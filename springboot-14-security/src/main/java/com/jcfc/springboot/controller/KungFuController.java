package com.jcfc.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class KungFuController {

    private final String PREFIX = "pages/";

    //欢迎页
    @GetMapping("/")
    public String index() {
        return "welcome";
    }

    //登录
    @GetMapping("/userlogin")
    public String userlogin() {
        return PREFIX+"login";
    }

    //level1
    @GetMapping("/level1/{path}")
    public String level1(@PathVariable("path") String path) {
        return PREFIX+"level1/"+path;
    }

    //level2
    @GetMapping("/level2/{path}")
    public String level2(@PathVariable("path") String path) {
        return PREFIX+"level2/"+path;
    }

    //level3
    @GetMapping("/level3/{path}")
    public String level3(@PathVariable("path") String path) {
        return PREFIX+"level3/"+path;
    }

}
