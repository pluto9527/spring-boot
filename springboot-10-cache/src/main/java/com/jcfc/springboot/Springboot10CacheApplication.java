package com.jcfc.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 	一、搭建基本环境（web+cache+mysql+mybatis）
 * 	1. 导入数据库文件，建表
 * 	2. 创建javaBean封装数据
 * 	3. 整合mybatis操作数据库
 * 		1). 配置数据源
 * 		2). 使用注解版mybatis(@MapperScan或者@Mapper扫描mapper接口)
 *
 * 	二、快速使用缓存
 * 	  步骤：
 * 		1. 开启基于注解的缓存 	@EnableCaching
 * 		2. 标注缓存注解即可（@Cacheable，@CacheEvict，@CachePut）
 */
@SpringBootApplication
@MapperScan("com.jcfc.**.mapper")
@EnableCaching
public class Springboot10CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot10CacheApplication.class, args);
	}
}
