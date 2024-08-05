package com.cc.service;

import com.cc.common.enums.RoleEnum;
import com.cc.entity.Account;
import com.cc.entity.Employee;
import com.cc.entity.Salary;
import com.cc.mapper.EmployeeMapper;
import com.cc.mapper.SalaryMapper;
import com.cc.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SalaryService {

  @Resource
  private SalaryMapper salaryMapper;
  @Resource
  private EmployeeMapper employeeMapper;


  public void add(Salary salary) {
    Integer employeeId = salary.getEmployeeId();
    Employee employee = employeeMapper.selectById(employeeId);
    salary.setDepartmentId(employee.getDepartmentId());
    salaryMapper.insert(salary);
  }

  public void deleteById(Integer id) {
    salaryMapper.deleteById(id);
  }

  public void deleteBatch(List<Integer> ids) {
    for (Integer id : ids) {
      salaryMapper.deleteById(id);
    }
  }

  public void updateById(Salary salary) {
    salaryMapper.updateById(salary);
  }

  public Salary selectById(Integer id) {
    return salaryMapper.selectById(id);
  }

  public List<Salary> selectAll(Salary salary) {
    return salaryMapper.selectAll(salary);
  }

  public PageInfo<Salary> selectPage(Salary salary, Integer pageNum, Integer pageSize) {
    Account currentUser = TokenUtils.getCurrentUser();
    if (RoleEnum.USER.name().equals(currentUser.getRole())) {
      salary.setEmployeeId(currentUser.getId());
    }
    PageHelper.startPage(pageNum, pageSize);
    List<Salary> list = salaryMapper.selectAll(salary);
    return PageInfo.of(list);
  }

  public List<Salary> getMonth() {
    return salaryMapper.getMonth();
  }
}