package com.cc.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cc.common.Result;
import com.cc.common.enums.ResultCodeEnum;
import com.cc.common.enums.RoleEnum;
import com.cc.entity.Account;
import com.cc.service.AdminService;
import com.cc.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class WebController {

    @Resource
    private AdminService adminService;
    @Resource
    private EmployeeService employeeService;

    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }


    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            account = adminService.login(account);
        }
        if (RoleEnum.USER.name().equals(account.getRole())) {
            account = employeeService.login(account);
        }
        return Result.success(account);
    }


    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
        ) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        employeeService.register(account);
        return Result.success();
    }


    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.updatePassword(account);
        }
        if (RoleEnum.USER.name().equals(account.getRole())) {
            employeeService.updatePassword(account);
        }
        return Result.success();
    }

}
