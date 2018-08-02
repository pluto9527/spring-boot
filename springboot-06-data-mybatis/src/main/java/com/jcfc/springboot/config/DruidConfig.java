package com.jcfc.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    // 加载YML格式自定义配置文件
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//        yaml.setResources(new FileSystemResource("config.yml"));//File引入
		yaml.setResources(new ClassPathResource("application-druid.yml"));//class引入
        configurer.setProperties(yaml.getObject());
        return configurer;
    }

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    /**
     *  配置Druid的监控，两步：
     *    1、配置一个监控管理后台的Servlet
     *    2、配置一个Web监控的Filter，可以监控Web应用、URI监控、Session监控、spring监控等功能
     */

    //1、配置一个监控管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String, String> initPrams = new HashMap<>();
        //控制台管理用户
        initPrams.put("loginUsername", "root");
        initPrams.put("loginPassword", "root");
        //IP白名单,默认允许所有访问
        initPrams.put("allow", "");
        //IP黑名单(共同存在时，deny优先于allow)
        initPrams.put("deny", "192.168.1.1");
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
        initPrams.put("resetEnable", "false");

        //给当前注册的Servlet设置初始化参数
        bean.setInitParameters(initPrams);
        return bean;
    }

    //2、配置一个Web监控的Filter，可以监控Web应用、URI监控、Session监控、spring监控等功能
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        bean.setUrlPatterns(Arrays.asList("/*"));

        Map<String, String> initPrams = new HashMap<>();
        //这些静态数据等访问不统计监控
        initPrams.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        //给当前注册的Filter设置初始化参数
        bean.setInitParameters(initPrams);

        return bean;
    }

}
