package com.example.controller;

import com.example.common.Result;
import com.example.entity.Financial;
import com.example.service.FinancialService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/financial")
public class FinancialController {

    @Resource
    private FinancialService financialService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Financial financial) {
        financialService.add(financial);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        financialService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        financialService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Financial financial) {
        financialService.updateById(financial);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Financial financial = financialService.selectById(id);
        return Result.success(financial);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Financial financial ) {
        List<Financial> list = financialService.selectAll(financial);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Financial financial,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Financial> page = financialService.selectPage(financial, pageNum, pageSize);
        return Result.success(page);
    }

}