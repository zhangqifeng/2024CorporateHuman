package com.cc.controller;

import com.cc.common.Result;
import com.cc.entity.Resources;
import com.cc.service.ResourcesService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourcesController {

  @Resource
  private ResourcesService resourcesService;


  @PostMapping("/add")
  public Result add(@RequestBody Resources resources) {
    resourcesService.add(resources);
    return Result.success();
  }


  @DeleteMapping("/delete/{id}")
  public Result deleteById(@PathVariable Integer id) {
    resourcesService.deleteById(id);
    return Result.success();
  }

  @DeleteMapping("/delete/batch")
  public Result deleteBatch(@RequestBody List<Integer> ids) {
    resourcesService.deleteBatch(ids);
    return Result.success();
  }

  @PutMapping("/update")
  public Result updateById(@RequestBody Resources resources) {
    resourcesService.updateById(resources);
    return Result.success();
  }


  @GetMapping("/selectById/{id}")
  public Result selectById(@PathVariable Integer id) {
    Resources resources = resourcesService.selectById(id);
    return Result.success(resources);
  }

  @GetMapping("/selectAll")
  public Result selectAll(Resources resources ) {
    List<Resources> list = resourcesService.selectAll(resources);
    return Result.success(list);
  }

  @GetMapping("/selectPage")
  public Result selectPage(Resources resources,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
    PageInfo<Resources> page = resourcesService.selectPage(resources, pageNum, pageSize);
    return Result.success(page);
  }

}