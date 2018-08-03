package com.jcfc.springboot.mapper;

import com.jcfc.springboot.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//不用@mapper或者@MapperScan,使用配置文件
public interface EmployeeMapper {

    @Select("SELECT * FROM employee WHERE id = #{id}")
    public Employee getEmpById(Integer id);

    @Select("SELECT * FROM employee WHERE last_name = #{lastName}")
    public Employee getEmpByLastName(String lastName);

    @Insert("INSERT INTO employee(lastName,gender,email,d_id) VALUES(#{lastName},#{gender},#{email},#{dId})")
    public void insertEmp(Employee employee);

    @Update("UPDATE employee SET lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    public void updateEmp(Employee employee);

    @Delete("DELETE FROM employee WHERE id=#{id}")
    public void deleteEmpById(Integer id);

}
