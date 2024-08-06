package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Employee;
import com.example.entity.Notice;
import com.example.entity.Salary;
import com.example.mapper.EmployeeMapper;
import com.example.mapper.NoticeMapper;
import com.example.mapper.SalaryMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 薪资表业务处理
 **/
@Service
public class SalaryService {

    @Resource
    private SalaryMapper salaryMapper;
    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 新增
     */
    public void add(Salary salary) {
        Integer employeeId = salary.getEmployeeId();
        Employee employee = employeeMapper.selectById(employeeId);
        salary.setDepartmentId(employee.getDepartmentId());
        salaryMapper.insert(salary);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        salaryMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            salaryMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Salary salary) {
        salaryMapper.updateById(salary);
    }

    /**
     * 根据ID查询
     */
    public Salary selectById(Integer id) {
        return salaryMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Salary> selectAll(Salary salary) {
        return salaryMapper.selectAll(salary);
    }

    /**
     * 分页查询
     */
    public PageInfo<Salary> selectPage(Salary salary, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            salary.setEmployeeId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Salary> list = salaryMapper.selectAll(salary);
        return PageInfo.of(list);
    }

}