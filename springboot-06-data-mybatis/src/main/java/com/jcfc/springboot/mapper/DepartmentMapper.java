package com.jcfc.springboot.mapper;

import com.jcfc.springboot.entity.Department;
import org.apache.ibatis.annotations.*;

//@Mapper指定这是一个操作数据库的mapper，代替xxxmapper.xml文件
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id = #{id}")
    public int deleteDeptById(Integer id);

    //将自增的主键回写到插入的对象属性中去
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    public int updateDept(Department department);

}
