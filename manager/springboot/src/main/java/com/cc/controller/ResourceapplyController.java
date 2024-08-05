package com.cc.controller;

import com.cc.common.Result;
import com.cc.entity.Resourceapply;
import com.cc.service.ResourceapplyService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/resourceapply")
public class ResourceapplyController {

  @Resource
  private ResourceapplyService resourceapplyService;

  @PostMapping("/add")
  public Result add(@RequestBody Resourceapply resourceapply) {
    resourceapplyService.add(resourceapply);
    return Result.success();
  }

  @DeleteMapping("/delete/{id}")
  public Result deleteById(@PathVariable Integer id) {
    resourceapplyService.deleteById(id);
    return Result.success();
  }

  @DeleteMapping("/delete/batch")
  public Result deleteBatch(@RequestBody List<Integer> ids) {
    resourceapplyService.deleteBatch(ids);
    return Result.success();
  }

  @PutMapping("/update")
  public Result updateById(@RequestBody Resourceapply resourceapply) {
    resourceapplyService.updateById(resourceapply);
    return Result.success();
  }

  @GetMapping("/selectById/{id}")
  public Result selectById(@PathVariable Integer id) {
    Resourceapply resourceapply = resourceapplyService.selectById(id);
    return Result.success(resourceapply);
  }

  @GetMapping("/selectAll")
  public Result selectAll(Resourceapply resourceapply ) {
    List<Resourceapply> list = resourceapplyService.selectAll(resourceapply);
    return Result.success(list);
  }

  @GetMapping("/selectPage")
  public Result selectPage(Resourceapply resourceapply,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
    PageInfo<Resourceapply> page = resourceapplyService.selectPage(resourceapply, pageNum, pageSize);
    return Result.success(page);
  }

  @GetMapping("/selectPage2")
  public Result selectPage2(Resourceapply resourceapply,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {
    PageInfo<Resourceapply> page = resourceapplyService.selectPage2(resourceapply, pageNum, pageSize);
    return Result.success(page);
  }
}