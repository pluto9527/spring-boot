package com.jcfc.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  由于项目是以jar包的形式引入，所以引入此jar包的springboot项目无法扫描到该@Configuration配置类组件
 *  需要在META—INF下配置spring.factories，来加载此组件,并且会看引入该jar的项目配置文件中是否配置了属性，
 *  如果配置了HelloProperties会自动获取配置的属性值
 */
@Configuration
@ConditionalOnWebApplication    //web应用才有效
@EnableConfigurationProperties(HelloProperties.class)   //使HelloProperties上的@ConfigurationProperties生效
public class HelloServiceAutoConfiguration {

    @Autowired  //HelloProperties生效后就是ioc的组件，所以可以直接注入
    HelloProperties helloProperties;

    @Bean
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.setHelloProperties(helloProperties);
        return helloService;
    }

}
