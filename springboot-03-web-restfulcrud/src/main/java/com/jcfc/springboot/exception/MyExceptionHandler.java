package com.jcfc.springboot.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//异常处理器，@ControllerAdvice 注解定义全局异常处理类
@ControllerAdvice
public class MyExceptionHandler {

    //1. 没有自适应效果，不会进入自己定制的错误页面，浏览器和客户端都是返回json格式
    @ResponseBody
    /*@ExceptionHandler(UserNotExistException.class)*/  //捕获处理UserNotExistException异常
    public Map<String, Object> handlerException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());
        return map;
    }

    //2. 转发到 /error, 让Springboot来自适应处理，但是不会进入自己定制的错误页面
    /*@ExceptionHandler(UserNotExistException.class)*/  //捕获处理UserNotExistException异常
    public String handlerException2(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());
        // 转发到 /error, 让Springboot来自适应处理
        return "forward:/error";
    }

    //3. 转发到 /error,并跳转到自定义错误页面携带自定义数据
    @ExceptionHandler(UserNotExistException.class)  //捕获处理UserNotExistException异常
    public String handlerException3(Exception e, HttpServletRequest request) {
        //传入我们自己的错误状态码  4xx 5xx，否则就不会进入定制错误页面的解析流程
        /**
         * Integer statusCode = (Integer) request
         .getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code",500);

        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());

        //把自己定制的错误信息放入request域中，后面ErrorAttributes才能获取
        request.setAttribute("ext", map);

        // 转发到 /error, 让Springboot来自适应处理
        return "forward:/error";
    }

}
