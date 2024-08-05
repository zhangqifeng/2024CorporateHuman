package com.cc.service;

import com.cc.entity.Resources;
import com.cc.mapper.ResourcesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResourcesService {

  @Resource
  private ResourcesMapper resourcesMapper;

  public void add(Resources resources) {
    resourcesMapper.insert(resources);
  }


  public void deleteById(Integer id) {
    resourcesMapper.deleteById(id);
  }

  public void deleteBatch(List<Integer> ids) {
    for (Integer id : ids) {
      resourcesMapper.deleteById(id);
    }
  }


  public void updateById(Resources resources) {
    resourcesMapper.updateById(resources);
  }


  public Resources selectById(Integer id) {
    return resourcesMapper.selectById(id);
  }

  public List<Resources> selectAll(Resources resources) {
    return resourcesMapper.selectAll(resources);
  }


  public PageInfo<Resources> selectPage(Resources resources, Integer pageNum, Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<Resources> list = resourcesMapper.selectAll(resources);
    return PageInfo.of(list);
  }

}
