package com.cc.service;

import cn.hutool.core.util.ObjectUtil;
import com.cc.common.Constants;
import com.cc.common.enums.LevelEnum;
import com.cc.common.enums.ResultCodeEnum;
import com.cc.entity.Account;
import com.cc.entity.Employee;
import com.cc.exception.CustomException;
import com.cc.mapper.EmployeeMapper;
import com.cc.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.cc.common.enums.RoleEnum;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeService {

  @Resource
  private EmployeeMapper employeeMapper;

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
    if (ObjectUtil.isEmpty(employee.getLevel())) {
      employee.setLevel(LevelEnum.EMPLOYEE.level);
    }
    // 初始化一个角色
    employee.setRole(RoleEnum.USER.name());
    employeeMapper.insert(employee);
  }

  public void deleteById(Integer id) {
    employeeMapper.deleteById(id);
  }

  public void deleteBatch(List<Integer> ids) {
    for (Integer id : ids) {
      employeeMapper.deleteById(id);
    }
  }


  public void updateById(Employee employee) {
    employeeMapper.updateById(employee);
  }


  public Employee selectById(Integer id) {
    return employeeMapper.selectById(id);
  }


  public List<Employee> selectAll(Employee employee) {
    return employeeMapper.selectAll(employee);
  }


  public PageInfo<Employee> selectPage(Employee employee, Integer pageNum, Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<Employee> list = employeeMapper.selectAll(employee);
    return PageInfo.of(list);
  }


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

  public void register(Account account) {
    Employee employee = new Employee();
    BeanUtils.copyProperties(account, employee);
    add(employee);
  }

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