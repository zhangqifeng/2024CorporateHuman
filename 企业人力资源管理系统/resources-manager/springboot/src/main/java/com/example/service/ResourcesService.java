package com.example.service;

import com.example.entity.Resources;
import com.example.mapper.ResourcesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资产信息表业务处理
 **/
@Service
public class ResourcesService {

    @Resource
    private ResourcesMapper resourcesMapper;

    /**
     * 新增
     */
    public void add(Resources resources) {
        resourcesMapper.insert(resources);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        resourcesMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            resourcesMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Resources resources) {
        resourcesMapper.updateById(resources);
    }

    /**
     * 根据ID查询
     */
    public Resources selectById(Integer id) {
        return resourcesMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Resources> selectAll(Resources resources) {
        return resourcesMapper.selectAll(resources);
    }

    /**
     * 分页查询
     */
    public PageInfo<Resources> selectPage(Resources resources, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Resources> list = resourcesMapper.selectAll(resources);
        return PageInfo.of(list);
    }

}