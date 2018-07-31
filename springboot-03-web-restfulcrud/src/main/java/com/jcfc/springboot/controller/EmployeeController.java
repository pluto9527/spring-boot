package com.jcfc.springboot.controller;

import com.jcfc.springboot.dao.DepartmentDao;
import com.jcfc.springboot.dao.EmployeeDao;
import com.jcfc.springboot.entities.Department;
import com.jcfc.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询员工列表
    @GetMapping("emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中
        model.addAttribute("emps", employees);

        //thymeleaf默认就会拼接字符串 classpath:/templates/xxx.html
        return "emp/list";
    }

    //跳转员工添加页面
    @GetMapping("emp")
    public String toAddPage(Model model) {
        //来到添加页面，查出所有部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();

        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //员工添加
    /* SpringMVC自动将请求参数和入参对象的属性进行一一绑定；
     * 要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    */
    @PostMapping("emp")
    public String addEmp(Employee employee) {
        System.out.println(employee);

        //保存员工
        employeeDao.save(employee);

        //添加成功，跳转员工列表页面
        /*  redirect：表示重定向到一个地址     /代表当前项目路径
         *  forward：表示转发到一个地址
         */
        return "redirect:/emps";
    }

    //来到修改页面
    @GetMapping("emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.getEmpById(id);
        model.addAttribute("emp", employee);

        //来到添加页面，查出所有部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        //回到修改页面
        return "emp/edit";
    }

    //员工修改：需要提交员工id
    @PutMapping("emp")
    public String edit(Employee employee) {
        System.out.println(employee);

        //员工修改
        employeeDao.save(employee);

        //重定向到员工列表
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("emp/{id}")
    public String delete(@PathVariable("id") Integer id) {
        employeeDao.deleteEmpById(id);
        return "redirect:/emps";
    }

}
