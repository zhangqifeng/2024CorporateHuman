package com.cc.controller;

import cn.hutool.core.util.ObjectUtil;
import com.cc.common.Result;
import com.cc.entity.Financial;
import com.cc.service.FinancialService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/financial")
public class FinancialController {

  @Resource
  private FinancialService financialService;

  @PostMapping("/add")
  public Result add(@RequestBody Financial financial) {
    financialService.add(financial);
    return Result.success();
  }

  @DeleteMapping("/delete/{id}")
  public Result deleteById(@PathVariable Integer id) {
    financialService.deleteById(id);
    return Result.success();
  }

  @DeleteMapping("/delete/batch")
  public Result deleteBatch(@RequestBody List<Integer> ids) {
    financialService.deleteBatch(ids);
    return Result.success();
  }

  @PutMapping("/update")
  public Result updateById(@RequestBody Financial financial) {
    financialService.updateById(financial);
    return Result.success();
  }

  @GetMapping("/selectById/{id}")
  public Result selectById(@PathVariable Integer id) {
    Financial financial = financialService.selectById(id);
    return Result.success(financial);
  }

  @GetMapping("/selectAll")
  public Result selectAll(Financial financial ) {
    List<Financial> list = financialService.selectAll(financial);
    return Result.success(list);
  }

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
  @GetMapping("/getBar")
  public Result bar() {
    // 获取所有的财务信息
    List<Financial> list = financialService.selectAll2();
    Map<String, Double> collect = list.stream().filter(x -> ObjectUtil.isNotEmpty(x.getTime()))
            .collect(Collectors.groupingBy(Financial::getTime, Collectors.reducing(0.0, Financial::getPrice, Double::sum)));
    List<String> xAxis = new ArrayList<>();
    List<Double> data = new ArrayList<>();
    for (String key : collect.keySet()) {
      xAxis.add(key);
      data.add(collect.get(key));
    }
    Map<String, Object> result = new HashMap<>();
    result.put("text", "财务支出柱状");
    result.put("subtext", "统计维度：年月");
    result.put("xAxis", xAxis);
    result.put("yAxis", data);
    return Result.success(result);
  }
}