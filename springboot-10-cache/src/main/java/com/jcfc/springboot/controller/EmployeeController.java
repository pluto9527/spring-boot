package com.jcfc.springboot.controller;

import com.jcfc.springboot.entity.Employee;
import com.jcfc.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        Employee emp = employeeService.getEmp(id);
        return emp;
    }

    @GetMapping("emp")
    public Employee updateEmp(Employee employee){
        employeeService.updateEmp(employee);
        return employee;
    }

    @GetMapping("delemp")
    public String deleteEmp(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("emp/lastname/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName) {
        return employeeService.getEmpByLastName(lastName);
    }

}
