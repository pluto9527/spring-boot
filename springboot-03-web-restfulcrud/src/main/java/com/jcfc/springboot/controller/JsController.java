package com.jcfc.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsController {

    @RequestMapping("/asserts/js/Chart.min.js")
    public String test1() {
        return "Chart.min.js";
    }

}
