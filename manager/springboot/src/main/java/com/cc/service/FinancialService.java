package com.cc.service;

import com.cc.common.enums.LevelEnum;
import com.cc.common.enums.RoleEnum;
import com.cc.entity.Account;
import com.cc.entity.Employee;
import com.cc.entity.Financial;
import com.cc.mapper.EmployeeMapper;
import com.cc.mapper.FinancialMapper;
import com.cc.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FinancialService {

  @Resource
  private FinancialMapper financialMapper;
  @Resource
  private EmployeeMapper employeeMapper;

  public void add(Financial financial) {
    financialMapper.insert(financial);
  }

  public void deleteById(Integer id) {
    financialMapper.deleteById(id);
  }

  public void deleteBatch(List<Integer> ids) {
    for (Integer id : ids) {
      financialMapper.deleteById(id);
    }
  }

  public void updateById(Financial financial) {
    financialMapper.updateById(financial);
  }

  public Financial selectById(Integer id) {
    return financialMapper.selectById(id);
  }

  public List<Financial> selectAll(Financial financial) {
    return financialMapper.selectAll(financial);
  }


  public PageInfo<Financial> selectPage(Financial financial, Integer pageNum, Integer pageSize) {
    Account currentUser = TokenUtils.getCurrentUser();
    if (RoleEnum.USER.name().equals(currentUser.getRole())) {
      Employee employee = employeeMapper.selectById(currentUser.getId());
      if (LevelEnum.HEADER.level.equals(employee.getLevel())) {
        financial.setDepartmentId(employee.getDepartmentId());
      }
    }
    PageHelper.startPage(pageNum, pageSize);
    List<Financial> list = financialMapper.selectAll(financial);
    return PageInfo.of(list);
  }

  public List<Financial> selectAll2() {
    return financialMapper.selectAll2();
  }
}