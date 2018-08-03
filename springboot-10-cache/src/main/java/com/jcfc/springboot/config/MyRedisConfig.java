package com.jcfc.springboot.config;

import com.jcfc.springboot.entity.Department;
import com.jcfc.springboot.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class MyRedisConfig {

    //保存的value是Employee对象
    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        //设置序列化方式，设置DefaultSerializer，会将key和value都使用此序列化方式
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(serializer);

        return template;
    }

    @Bean
    public RedisTemplate<Object, Department> deptRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        //设置序列化方式，设置DefaultSerializer，会将key和value都使用此序列化方式
        Jackson2JsonRedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(serializer);

        return template;
    }

    //自己定义CacheManager，那么spring配置的就不会生效
    //CacheManagerCustomizers可以定制一些缓存规则
    @Bean
    @Primary  //如果有多个，必须指定一个@Primary为默认的
    public RedisCacheManager empCacheManager(RedisTemplate<Object, Employee> empRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(empRedisTemplate);

        //使用前缀，默认会将cacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    @Bean
    public RedisCacheManager deptCacheManager(RedisTemplate<Object, Department> deptRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(deptRedisTemplate);

        //使用前缀，默认会将cacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

}
