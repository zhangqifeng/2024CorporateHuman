package com.example.controller;

import com.example.common.Result;
import com.example.entity.Askapply;
import com.example.service.AskapplyService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请假信息前端操作接口
 **/
@RestController
@RequestMapping("/askapply")
public class AskapplyController {

    @Resource
    private AskapplyService askapplyService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Askapply askapply) {
        askapplyService.add(askapply);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        askapplyService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        askapplyService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Askapply askapply) {
        askapplyService.updateById(askapply);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Askapply askapply = askapplyService.selectById(id);
        return Result.success(askapply);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Askapply askapply ) {
        List<Askapply> list = askapplyService.selectAll(askapply);
        return Result.success(list);
    }

    /**
     * 请假记录分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Askapply askapply,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Askapply> page = askapplyService.selectPage(askapply, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 请假审批分页查询
     */
    @GetMapping("/selectPage2")
    public Result selectPage2(Askapply askapply,
                              @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Askapply> page = askapplyService.selectPage2(askapply, pageNum, pageSize);
        return Result.success(page);
    }
}