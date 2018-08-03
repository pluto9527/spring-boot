package com.jcfc.springboot.repository;

import com.jcfc.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Dao接口：
 *  第一个参数：操作的实体类
 *  第二个参数：主键类型
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
