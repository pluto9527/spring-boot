package com.jcfc.springboot.config;

import com.jcfc.springboot.component.LoginHandlerIntercepter;
import com.jcfc.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {

    //方式二：直接返回WebMvcConfigurationSupport到ioc容器中
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter support = new WebMvcConfigurerAdapter() {
            //注册自定义的视图解析器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("Dashboard");
            }

            //注册自定义的拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //springboot已经做好了静态资源的映射 *.css *.js
                registry.addInterceptor(new LoginHandlerIntercepter()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login");
            }
        };
        return support;
    }

    //方式一:继承WebMvcConfigurationSupport,复写方法
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
//        浏览器发送 /hello2 请求来到 success
        registry.addViewController("/hello2").setViewName("success");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

}
