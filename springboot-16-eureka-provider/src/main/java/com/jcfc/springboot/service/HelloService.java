package com.jcfc.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hello() {
        return "HELLO !";
    }

}
