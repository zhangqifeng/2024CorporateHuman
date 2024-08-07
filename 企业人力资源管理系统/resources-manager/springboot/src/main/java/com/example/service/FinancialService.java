package com.example.service;

import com.example.common.enums.LevelEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Employee;
import com.example.entity.Financial;
import com.example.mapper.EmployeeMapper;
import com.example.mapper.FinancialMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 财务支出表业务处理
 **/
@Service
public class FinancialService {

    @Resource
    private FinancialMapper financialMapper;
    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 新增
     */
    public void add(Financial financial) {
        financialMapper.insert(financial);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        financialMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            financialMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Financial financial) {
        financialMapper.updateById(financial);
    }

    /**
     * 根据ID查询
     */
    public Financial selectById(Integer id) {
        return financialMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Financial> selectAll(Financial financial) {
        return financialMapper.selectAll(financial);
    }

    /**
     * 分页查询
     */
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