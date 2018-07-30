package com.jcfc.springboot.controller;

import com.jcfc.springboot.dao.EmployeeDao;
import com.jcfc.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    //查询员工列表
    @GetMapping("emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中
        model.addAttribute("emps", employees);

        //thymeleaf默认就会拼接字符串 classpath:/templates/xxx.html
        return "emp/list";
    }

}
