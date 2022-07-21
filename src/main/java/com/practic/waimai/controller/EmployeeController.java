package com.practic.waimai.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.practic.waimai.common.Result;
import com.practic.waimai.entity.Employee;
import com.practic.waimai.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        //@RequestBody Employee 将request中的数据映射为Employee

        String pass = employee.getPassword();
//        pass = DigestUtils.md5DigestAsHex(pass.getBytes());

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        // 方法静态引用::
        queryWrapper.eq(Employee::getUsername, employee.getUsername()); // 类似Sql语句       Employee::getUsername查找column
        Employee emp = employeeService.getOne(queryWrapper); // 执行SQL语句

        if(emp == null) {
            return Result.error("用户不存在");
        }

        if(!emp.getPassword().equals(pass)) {
            return Result.error("密码错误");
        }

        if(emp.getStatus() != 1) {
            return Result.error("账号被禁用");
        }

        request.getSession().setAttribute("empId", emp.getId());
        return Result.success(emp);
    };

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
      request.getSession().removeAttribute("empId");
      return Result.success("退出成功");
    };


    @PostMapping("/add")
    public Result add(HttpServletRequest request, @RequestBody Employee emp) {
        emp.setPassword("123456");

        employeeService.save(emp);
        return Result.success("添加员工成功");
    };

    @PostMapping("/update")
    public Result update(HttpServletRequest request, @RequestBody Employee emp) {
        employeeService.updateById(emp);
        return Result.success("修改信息成功");
    };

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        Employee emp = employeeService.getById(id);
        return Result.success(emp);
    };

    @GetMapping("/page")
    public Result getPage(int page, int pageSize, String name) {
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper();
        wrapper.like(!StringUtils.isEmpty(name), Employee::getName, name);
        wrapper.orderByDesc(Employee::getUpdateTime);

        employeeService.page(pageInfo, wrapper);
        return Result.success(pageInfo);
    };
}
