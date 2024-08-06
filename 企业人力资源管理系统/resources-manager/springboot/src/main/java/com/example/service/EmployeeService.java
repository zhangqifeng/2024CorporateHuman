package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.LevelEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Employee;
import com.example.exception.CustomException;
import com.example.mapper.EmployeeMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员业务处理
 **/
@Service
public class EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 新增
     */
    public void add(Employee employee) {
        Employee dbEmployee = employeeMapper.selectByUsername(employee.getUsername());
        if (ObjectUtil.isNotNull(dbEmployee)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(employee.getPassword())) {
            employee.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(employee.getName())) {
            employee.setName(employee.getUsername());
        }
        if(ObjectUtil.isEmpty(employee.getLevel())){
            employee.setLevel(LevelEnum.EMPLOYEE.level);
        }

        //初始化一个角色
        employee.setRole(RoleEnum.USER.name());
        employeeMapper.insert(employee);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        employeeMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            employeeMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Employee employee) {
        employeeMapper.updateById(employee);
    }

    /**
     * 根据ID查询
     */
    public Employee selectById(Integer id) {
        return employeeMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Employee> selectAll(Employee employee) {
        return employeeMapper.selectAll(employee);
    }

    /**
     * 分页查询
     */
    public PageInfo<Employee> selectPage(Employee employee, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = employeeMapper.selectAll(employee);
        return PageInfo.of(list);
    }

    /**
     * 登录
     */
    public Account login(Account account) {
        Account dbEmployee = employeeMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbEmployee)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbEmployee.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbEmployee.getId() + "-" + RoleEnum.USER.name();
        String token = TokenUtils.createToken(tokenData, dbEmployee.getPassword());
        dbEmployee.setToken(token);
        return dbEmployee;
    }

    /**
     * 注册
     */
    public void register(Account account) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(account, employee);
        add(employee);
    }

    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        Employee dbEmployee = employeeMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbEmployee)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbEmployee.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbEmployee.setPassword(account.getNewPassword());
        employeeMapper.updateById(dbEmployee);
    }

    public List<Employee> selectHeaders() {
        return employeeMapper.selectHeaders();
    }
}