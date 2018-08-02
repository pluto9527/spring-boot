package com.jcfc.springboot.mapper;

import com.jcfc.springboot.entity.Employee;

//不用@mapper或者@MapperScan,使用配置文件
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);

}
