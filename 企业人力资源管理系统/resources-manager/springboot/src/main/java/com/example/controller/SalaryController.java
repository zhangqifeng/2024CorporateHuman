package com.example.controller;

import com.example.common.Result;
import com.example.entity.Salary;
import com.example.service.SalaryService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 薪资信息前端操作接口
 **/
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Resource
    private SalaryService salaryService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Salary salary) {
        salaryService.add(salary);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        salaryService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        salaryService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Salary salary) {
        salaryService.updateById(salary);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Salary salary = salaryService.selectById(id);
        return Result.success(salary);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Salary salary ) {
        List<Salary> list = salaryService.selectAll(salary);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Salary salary,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Salary> page = salaryService.selectPage(salary, pageNum, pageSize);
        return Result.success(page);
    }

}