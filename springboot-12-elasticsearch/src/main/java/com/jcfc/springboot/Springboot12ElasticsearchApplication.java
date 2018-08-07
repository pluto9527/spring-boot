package com.jcfc.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springBoot默认支持两种技术和ES进行交互
 　　1、Jest【默认不生效，需要导入jest的工具包（io.searchbox.client.JestClient）使用】，通过http方式通信操作ElasticSearch
 ​ 　　　　利用JestClient和服务器的9200端口进行http通信
 ​ 　　2、SpringData ElasticSearch【默认】
 ​ 　　　　1）、Client 节点信息: clusterNodes: clusterName
 ​ 　　　　2）、ElasticsearchTemplate 操作ElasticSearch
 ​ 　　　　3）、编写一个ElasticsearchRepository的子接口来操作ElasticSearch，类似于（JPA）
 */
@SpringBootApplication
public class Springboot12ElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot12ElasticsearchApplication.class, args);
	}
}
