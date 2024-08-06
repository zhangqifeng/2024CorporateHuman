package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Financial;
import com.example.service.FinancialService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 财务支出表前端操作接口
 **/
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

    @GetMapping("/getPie")
    public Result pie() {
        // 获取所有的财务信息
        List<Financial> list = financialService.selectAll(null);
        Map<String, Double> collect = list.stream().filter(x -> ObjectUtil.isNotEmpty(x.getDepartmentName()))
                .collect(Collectors.groupingBy(Financial::getDepartmentName, Collectors.reducing(0.0, Financial::getPrice, Double::sum)));
        List<Map<String, Object>> data = new ArrayList<>();
        for (String key : collect.keySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", key);
            map.put("value", collect.get(key));
            data.add(map);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("text", "财务支出饼图");
        result.put("subtext", "统计维度：部门");
        result.put("name", "财务支出");
        result.put("data", data);
        return Result.success(result);
    }

    @GetMapping("/getLine")
    public Result line() {
        // 获取所有的财务信息
        List<Financial> list = financialService.selectAll(null);
        Map<String, Double> collect = list.stream().filter(x -> ObjectUtil.isNotEmpty(x.getDepartmentName()))
                .collect(Collectors.groupingBy(Financial::getDepartmentName, Collectors.reducing(0.0, Financial::getPrice, Double::sum)));
        List<String> xAxis = new ArrayList<>();
        List<Double> data = new ArrayList<>();
        for (String key : collect.keySet()) {
            xAxis.add(key);
            data.add(collect.get(key));
        }
        Map<String, Object> result = new HashMap<>();
        result.put("text", "财务支出折线图");
        result.put("subtext", "统计维度：部门");
        result.put("xAxis", xAxis);
        result.put("yAxis", data);
        return Result.success(result);
    }

}