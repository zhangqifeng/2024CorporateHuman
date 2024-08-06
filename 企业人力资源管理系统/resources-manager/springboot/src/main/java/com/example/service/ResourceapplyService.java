package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.enums.ApplyEnum;
import com.example.common.enums.LevelEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Employee;
import com.example.entity.Resourceapply;
import com.example.entity.Resources;
import com.example.exception.CustomException;
import com.example.mapper.EmployeeMapper;
import com.example.mapper.ResourceapplyMapper;
import com.example.mapper.ResourcesMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资产申请审批表业务处理
 **/
@Service
public class ResourceapplyService {

    @Resource
    private ResourceapplyMapper resourceapplyMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private ResourcesMapper resourcesMapper;

    /**
     * 新增
     */
    public void add(Resourceapply resourceapply) {
        Integer num = resourceapply.getNum();
        Resources resources = resourcesMapper.selectById(resourceapply.getResourcesId());
        if (resources.getNum() < num) {
            throw new CustomException(ResultCodeEnum.RESOURCES_NUM_ERROR);
        }
        Employee employee = employeeMapper.selectById(resourceapply.getEmployeeId());
        resourceapply.setDepartmentId(employee.getDepartmentId());
        resourceapply.setTime(DateUtil.now());

        // 审批进度：主管审核中
        resourceapply.setProcess(ApplyEnum.PROCESS_HEADER_APPLYING.status);
        // 审批状态：待主管审核
        resourceapply.setStatus(ApplyEnum.STATUS_HEADER_APPLYING.status);
        resourceapplyMapper.insert(resourceapply);

        // 该资产数量减少对应的申请数量
        resources.setNum(resources.getNum() - num);
        resourcesMapper.updateById(resources);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        resourceapplyMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            resourceapplyMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Resourceapply resourceapply) {
        String status = resourceapply.getStatus();
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            // 如果是主管
            Employee employee = employeeMapper.selectById(currentUser.getId());
            if (LevelEnum.HEADER.level.equals(employee.getLevel())) {
                // 审批通过，要走到管理员那边
                if (ApplyEnum.APPLY_OK.status.equals(status)) {
                    resourceapply.setStatus(ApplyEnum.STATUS_ADMIN_APPLYING.status);
                    resourceapply.setProcess(ApplyEnum.PROCESS_ADMIN_APPLYING.status);
                }
                // 审批不通过，直接结束
                if (ApplyEnum.APPLY_NO.status.equals(status)) {
                    resourceapply.setStatus(ApplyEnum.STATUS_HEADER_APPLY_NO.status);
                    resourceapply.setProcess(ApplyEnum.APPLY_DONE.status);
                }
            }
        } else {
            if (ApplyEnum.APPLY_OK.status.equals(status)) {
                resourceapply.setStatus(ApplyEnum.APPLY_OK.status);
            }
            if (ApplyEnum.APPLY_NO.status.equals(status)) {
                resourceapply.setStatus(ApplyEnum.STATUS_ADMIN_APPLY_NO.status);
            }
            resourceapply.setProcess(ApplyEnum.APPLY_DONE.status);
        }
        resourceapplyMapper.updateById(resourceapply);
    }

    /**
     * 根据ID查询
     */
    public Resourceapply selectById(Integer id) {
        return resourceapplyMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Resourceapply> selectAll(Resourceapply resourceapply) {
        return resourceapplyMapper.selectAll(resourceapply);
    }

    /**
     * 请假记录分页查询
     */
    public PageInfo<Resourceapply> selectPage(Resourceapply resourceapply, Integer pageNum, Integer pageSize) {
        resourceapply.setStatus(ApplyEnum.APPLY_OK.status);
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            resourceapply.setEmployeeId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Resourceapply> list = resourceapplyMapper.selectAll(resourceapply);
        return PageInfo.of(list);
    }

    /**
     * 请假审批分页查询
     */
    public PageInfo<Resourceapply> selectPage2(Resourceapply resourceapply, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            Employee employee = employeeMapper.selectById(currentUser.getId());
            // 如果是主管
            if (LevelEnum.HEADER.level.equals(employee.getLevel())) {
                resourceapply.setDepartmentId(employee.getDepartmentId());
                resourceapply.setStatus(ApplyEnum.STATUS_HEADER_APPLYING.status);
            } else {
                // 如果是普通员工
                resourceapply.setEmployeeId(employee.getId());
            }
        } else {
            resourceapply.setStatus(ApplyEnum.STATUS_ADMIN_APPLYING.status);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Resourceapply> list = resourceapplyMapper.selectAll(resourceapply);
        return PageInfo.of(list);
    }

}