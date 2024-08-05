package com.cc.controller;

import com.cc.common.Result;
import com.cc.entity.Employee;
import com.cc.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

  @Resource
  private EmployeeService employeeService;


  @PostMapping("/add")
  public Result add(@RequestBody Employee employee) {
    employeeService.add(employee);
    return Result.success();
  }

  @DeleteMapping("/delete/{id}")
  public Result deleteById(@PathVariable Integer id) {
    employeeService.deleteById(id);
    return Result.success();
  }

  @DeleteMapping("/delete/batch")
  public Result deleteBatch(@RequestBody List<Integer> ids) {
    employeeService.deleteBatch(ids);
    return Result.success();
  }


  @PutMapping("/update")
  public Result updateById(@RequestBody Employee employee) {
    employeeService.updateById(employee);
    return Result.success();
  }


  @GetMapping("/selectById/{id}")
  public Result selectById(@PathVariable Integer id) {
    Employee employee = employeeService.selectById(id);
    return Result.success(employee);
  }

  @GetMapping("/selectAll")
  public Result selectAll(Employee employee ) {
    List<Employee> list = employeeService.selectAll(employee);
    return Result.success(list);
  }

  @GetMapping("/selectHeaders")
  public Result selectHeaders() {
    List<Employee> list = employeeService.selectHeaders();
    return Result.success(list);
  }

  @GetMapping("/selectPage")
  public Result selectPage(Employee employee,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
    PageInfo<Employee> page = employeeService.selectPage(employee, pageNum, pageSize);
    return Result.success(page);
  }

}
