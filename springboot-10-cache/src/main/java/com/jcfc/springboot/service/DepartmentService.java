package com.jcfc.springboot.service;

import com.jcfc.springboot.entity.Department;
import com.jcfc.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Qualifier("deptCacheManager")
    @Autowired
    RedisCacheManager deptCacheManager;

    /**
     *  缓存的数据能存入redis，但第二次查询不能反序列化回来
     *  因为存的是dept的json数据；CacheManager默认使用RedisTemplate<Object, Employee>操作Redis
     */
    @Cacheable(cacheNames = "dept", cacheManager = "deptCacheManager")
    public Department getDeptById(Integer id) {
        System.out.println("查询部门："+id);
        Department dept = departmentMapper.getDeptById(id);
        return dept;
    }

    /**
     *  代码方式操作缓存
     */
    public Department getDept(Integer id) {
        System.out.println("查询部门："+id);
        Department dept = departmentMapper.getDeptById(id);

        //从CacheManager获取某个缓存Cache，通过Cache操作缓存数据
        Cache cache = deptCacheManager.getCache("dept");
        cache.put("dept:1", dept);

        return dept;
    }

}
