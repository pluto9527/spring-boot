package com.jcfc.springboot.exception;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

//给容器中加入我们自己定义的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    //返回值的map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        map.put("company", "jcfc");

        //从request域中获取我们的异常处理器携带的数据
        //第二个参数scope，0表示request域
        Map<String, Object> ext = (Map<String, Object>) requestAttributes.getAttribute("ext", 0);
        //将自定义的异常数据放入getErrorAttributes的map中
        map.put("ext", ext);

        return map;
    }
}
