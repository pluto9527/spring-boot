package com.jcfc.springboot.controller;

import com.jcfc.springboot.entity.Department;
import com.jcfc.springboot.entity.Employee;
import com.jcfc.springboot.mapper.DepartmentMapper;
import com.jcfc.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("ALL")
@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id) {
        return departmentMapper.getDeptById(id);
    }

    @GetMapping("/dept")
    public Department insertDept(Department department) {
        departmentMapper.insertDept(department);
        return department;
    }

    @GetMapping("emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        return employeeMapper.getEmpById(id);
    }

}
