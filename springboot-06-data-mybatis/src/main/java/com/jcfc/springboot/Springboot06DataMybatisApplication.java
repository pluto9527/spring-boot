package com.jcfc.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//使用MapperScan批量扫描所有的Mapper接口；代替@Mapper
@MapperScan("com.jcfc.**.mapper")
public class Springboot06DataMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot06DataMybatisApplication.class, args);
	}
}
