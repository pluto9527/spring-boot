package com.jcfc.springboot.service;

import com.jcfc.springboot.entity.Employee;
import com.jcfc.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/*
 *  原理：
 *      1、自动配置类：CacheAutoConfiguration (@Import(CacheConfigurationImportSelector.class))
 *      2、缓存的配置类：
 *          GenericCacheConfiguration
 *          JCacheCacheConfiguration
 *          EhCacheCacheConfiguration
 *          HazelcastCacheConfiguration
 *          InfinispanCacheConfiguration
 *          CouchbaseCacheConfiguration
 *          RedisCacheConfiguration
 *          CaffeineCacheConfiguration
 *          SimpleCacheConfiguration
 *          NoOpCacheConfiguration
 *      3、默认生效的配置类: SimpleCacheConfiguration
 *      4、SimpleCacheConfiguration会给容器中注册了一个CacheManager：ConcurrentMapCacheManager
 *      5、ConcurrentMapCacheManager可以获取和创建ConcurrentMapCache类型的缓存组件；它的作用将数据保存在ConcurrentMap中
 *
 *  运行流程：
 *  @Cacheable:
 *      1、方法运行之前，先查Cache(缓存组件），按照cacheName的指定名字获取；
 *          （CacheManager先获取相应的缓存），第一次获取缓存如果没有cache组件会自己创建
 *      2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数；
 *          key是按照某种策略生成的，默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key
 *              SimpleKeyGenerator生成key的默认策略:
 *                  1).没有参数: key=new SimpleKey()
 *                  2).如果有一个参数: key=参数值
 *                  3).如果多个参数: key=new SimpleKey(params);
 *      3、没有查到缓存就调用目标方法
 *      4、将目标方法返回的结果，放回缓存中
 *
 *   @Cacheable标注的方法执行之前，先来检查缓存中是否有这个数据，默认按照参数的值作为key去查询缓存，
 *   如果没有，就运行方法，将结果存入缓存；如果有数据，就取出map的值。
 */
@SuppressWarnings("ALL")
@CacheConfig(cacheNames = "emp", cacheManager = "empCacheManager")  //抽取当前类中缓存的公共配置
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     *  @Cacheable:
     *  将方法的运行结果进行缓存，以后要是再有相同的数据，直接从缓存中获取，不用调用方法
     *
     *  CacheManager管理多个Cache组件，对缓存的真正CRUD操作在Cache组件中，每个缓存组件都有自己的唯一名字；
     *
     *  属性：
     *      CacheName/value: 指定存储缓存组件的名字；讲方法的返回结果放入哪个缓存中，是数组的方式，可以指定多个缓存
     *      key: 缓存数据使用的key,可以使用它来指定。默认是使用方法参数的值，key(id)-value(方法的返回值)
     *              也可以编写Spel表达式指定key：#id 参数id的值， #a0/#p0 #root.args[0]
     *              key = "#root.methodName+'['+#id+']'"
     *      keyGenerator: key的生成器，自己可以指定key的生成器的组件id
     *              keyGenerator = "mykeyGenerator"
     *          key/keyGendertor二选一使用
     *
     *      cacheManager: 指定Cache管理器，
     *              或者使用cacheReslover: 指定获取解析器，二选一
     *      condition: 指定符合条件的情况下，才缓存；
     *              condition = "#id>0"：id>0才进行缓存
     *      unless：否定缓存，unless指定的条件为true，方法的返回值就不会被缓存，可以获取到结果进行判断
     *              unless = "#result == null"：结果==nulll，就不缓存
     *      sync: 是否使用异步模式，默认是方法执行完，以同步的方式将方法返回值放入缓存，异步模式不支持unless
     *
     */
    @Cacheable(cacheNames = "emp")
    public Employee getEmp(Integer id) {
        System.out.println("查询数据库："+id+"号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     *  @CachePut: 既调用方法，又更新缓存；同步更新缓存
     *      修改数据库的某个数据，同时更新缓存
     *  运行时机:
     *      1、先调用目标方法
     *      2、再将目标方法的结果缓存起来
     *
     *  注意：默认的key是参数
     *      所以这里默认的key是：传入的employee对象，  值：返回的employee对象
     *
     *      指定key：key = "#employee.id"，传入参数的员工id
     *      或者：key = "#result.id"，返回的员工id
     *
     */
    @CachePut(cacheNames = "emp",key = "#result.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     *  @CacheEvict：缓存清除
     *
     *  allEntries = true：不论清楚哪个key，都会清除这个缓存(cacheName)中的所有数据
     *  beforeInvocation=false：缓存的清除是否在方法之前执行
     *      默认代表缓存清除操作是在方法执行之后执行；如果出现异常缓存就不会清除
     *
     *  beforeInvocation=true：
     *      代表缓存清除操作是在方法执行之前执行，无论方法是否出现异常，缓存都清除
     *
     */
    @CacheEvict(value = "emp",key = "#id")
    public  void  deleteEmp(Integer id){
        System.out.println("delete的id"+id);
//        int i = 10/0;
    }

    //@Caching 定义复杂的缓存规则
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.gender")
            }
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }

}
