package com.cc.controller;

import com.cc.common.Result;
import com.cc.entity.Askapply;
import com.cc.service.AskapplyService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/askapply")
public class AskapplyController {

  @Resource
  private AskapplyService askapplyService;


  @PostMapping("/add")
  public Result add(@RequestBody Askapply askapply) {
    askapplyService.add(askapply);
    return Result.success();
  }


  @DeleteMapping("/delete/{id}")
  public Result deleteById(@PathVariable Integer id) {
    askapplyService.deleteById(id);
    return Result.success();
  }


  @DeleteMapping("/delete/batch")
  public Result deleteBatch(@RequestBody List<Integer> ids) {
    askapplyService.deleteBatch(ids);
    return Result.success();
  }


  @PutMapping("/update")
  public Result updateById(@RequestBody Askapply askapply) {
    askapplyService.updateById(askapply);
    return Result.success();
  }

  @GetMapping("/selectById/{id}")
  public Result selectById(@PathVariable Integer id) {
    Askapply askapply = askapplyService.selectById(id);
    return Result.success(askapply);
  }

  @GetMapping("/selectAll")
  public Result selectAll(Askapply askapply ) {
    List<Askapply> list = askapplyService.selectAll(askapply);
    return Result.success(list);
  }


  @GetMapping("/selectPage")
  public Result selectPage(Askapply askapply,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
    PageInfo<Askapply> page = askapplyService.selectPage(askapply, pageNum, pageSize);
    return Result.success(page);
  }

  @GetMapping("/selectPage2")
  public Result selectPage2(Askapply askapply,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {
    PageInfo<Askapply> page = askapplyService.selectPage2(askapply, pageNum, pageSize);
    return Result.success(page);
  }
}