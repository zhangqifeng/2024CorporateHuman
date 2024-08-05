package com.cc.service;

import cn.hutool.core.util.ObjectUtil;
import com.cc.common.enums.ApplyEnum;
import com.cc.common.enums.LevelEnum;
import com.cc.common.enums.ResultCodeEnum;
import com.cc.common.enums.RoleEnum;
import com.cc.entity.Account;
import com.cc.entity.Askapply;
import com.cc.entity.Employee;
import com.cc.exception.CustomException;
import com.cc.mapper.AskapplyMapper;
import com.cc.mapper.EmployeeMapper;
import com.cc.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AskapplyService {

  @Resource
  private AskapplyMapper askapplyMapper;
  @Resource
  private EmployeeMapper employeeMapper;

  public void add(Askapply askapply) {
    Account currentUser = TokenUtils.getCurrentUser();
    Employee employee = employeeMapper.selectById(currentUser.getId());
    askapply.setEmployeeId(employee.getId());
    askapply.setDepartmentId(employee.getDepartmentId());

    // 默认进入主管审批的进度
    // 状态：待主管审批
    askapply.setStatus(ApplyEnum.STATUS_HEADER_APPLYING.status);
    // 进度：主管审批中
    askapply.setProcess(ApplyEnum.PROCESS_HEADER_APPLYING.status);

    askapplyMapper.insert(askapply);
  }

  public void deleteById(Integer id) {
    askapplyMapper.deleteById(id);
  }


  public void deleteBatch(List<Integer> ids) {
    for (Integer id : ids) {
      askapplyMapper.deleteById(id);
    }
  }

  public void updateById(Askapply askapply) {
    String status = askapply.getStatus();
    if (ObjectUtil.isEmpty(status)) {
      throw new CustomException(ResultCodeEnum.PARAM_STATUS_ERROR);
    }
    Account currentUser = TokenUtils.getCurrentUser();
    if (RoleEnum.USER.name().equals(currentUser.getRole())) {
      Employee employee = employeeMapper.selectById(currentUser.getId());
      // 如果是主管审批
      if (LevelEnum.HEADER.level.equals(employee.getLevel())) {
        if (ApplyEnum.APPLY_OK.status.equals(status)) {
          askapply.setStatus(ApplyEnum.STATUS_ADMIN_APPLYING.status);
          askapply.setProcess(ApplyEnum.PROCESS_ADMIN_APPLYING.status);
        } else {
          askapply.setStatus(ApplyEnum.STATUS_HEADER_APPLY_NO.status);
          askapply.setProcess(ApplyEnum.APPLY_DONE.status);
        }
      }
    }
    // 管理员审批的时候
    if (RoleEnum.ADMIN.name().equals(currentUser.getRole())) {
      if (ApplyEnum.APPLY_OK.status.equals(status)) {
        askapply.setStatus(ApplyEnum.STATUS_ADMIN_APPLY_OK.status);
      } else {
        askapply.setStatus(ApplyEnum.STATUS_ADMIN_APPLY_NO.status);
      }
      askapply.setProcess(ApplyEnum.APPLY_DONE.status);
    }
    askapplyMapper.updateById(askapply);
  }

  public Askapply selectById(Integer id) {
    return askapplyMapper.selectById(id);
  }

  public List<Askapply> selectAll(Askapply askapply) {
    return askapplyMapper.selectAll(askapply);
  }


  public PageInfo<Askapply> selectPage(Askapply askapply, Integer pageNum, Integer pageSize) {
    Account currentUser = TokenUtils.getCurrentUser();
    if (RoleEnum.USER.name().equals(currentUser.getRole())) {
      askapply.setEmployeeId(currentUser.getId());
    }
    PageHelper.startPage(pageNum, pageSize);
    List<Askapply> list = askapplyMapper.selectAll(askapply);
    return PageInfo.of(list);
  }

  public PageInfo<Askapply> selectPage2(Askapply askapply, Integer pageNum, Integer pageSize) {
    Account currentUser = TokenUtils.getCurrentUser();
    // 如果是员工登录
    if (RoleEnum.USER.name().equals(currentUser.getRole())) {
      Employee employee = employeeMapper.selectById(currentUser.getId());
      // 又是主管
      if (LevelEnum.HEADER.level.equals(employee.getLevel())) {
        askapply.setDepartmentId(employee.getDepartmentId());
        askapply.setStatus(ApplyEnum.STATUS_HEADER_APPLYING.status);
      }
    }
    // 如果是管理员登录
    if (RoleEnum.ADMIN.name().equals(currentUser.getRole())) {
      askapply.setStatus(ApplyEnum.STATUS_ADMIN_APPLYING.status);
    }
    PageHelper.startPage(pageNum, pageSize);
    List<Askapply> list = askapplyMapper.selectAll(askapply);
    return PageInfo.of(list);
  }

}
