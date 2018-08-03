package com.jcfc.springboot;

import com.jcfc.springboot.entity.Employee;
import com.jcfc.springboot.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot10CacheApplicationTests {

	@Autowired
	StringRedisTemplate stringRedisTemplate;  //（操作k，v都是字符串的）

	@Autowired
	RedisTemplate redisTemplate;  //（操作k，v都是对象的）

	@Autowired
	RedisTemplate<Object, Employee> empRedisTemplate;

	/**
	 * Redis的常用五大数据类型：
	 　　String【字符串】、List【列表】、Set【集合】、Hash【散列】、ZSet【有序集合】
	 　　根据不同的数据类型，大致的操作也分为这5种，以StringRedisTemplate为例

	 　　stringRedisTemplate.opsForValue() --String
	 　　stringRedisTemplate.opsForList() --List
	 　　stringRedisTemplate.opsForSet() --Set
	 　　stringRedisTemplate.opsForHash() --Hash
	 　　stringRedisTemplate.opsForZset() -Zset
	 */
	//保存字符串
	@Test
	public void test() {
		stringRedisTemplate.opsForValue().set("msg", "hello world!");
	}

	//保存对象
	@Test
	public void test2() {
		Employee emp = employeeMapper.getEmpById(1);
		//默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到Redis中
		redisTemplate.opsForValue().set("emp01", emp);

		/**
		 * 将数据以json的方式保存：
		 * 	 1).手动将对象转为json，在保存
		 * 	 2).redisTemplate默认的序列化规则：改变默认的序列化规则
		 */
		empRedisTemplate.opsForValue().set("emp02", emp);
	}

	@Autowired
	EmployeeMapper employeeMapper;

	@Test
	public void contextLoads() {
		Employee emp = employeeMapper.getEmpById(1);
		System.out.println(emp);
	}

}
