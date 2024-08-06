package com.example.mapper;

import com.example.entity.Resources;

import java.util.List;

/**
 * 操作resources相关数据接口
*/
public interface ResourcesMapper {

    /**
      * 新增
    */
    int insert(Resources resources);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Resources resources);

    /**
      * 根据ID查询
    */
    Resources selectById(Integer id);

    /**
      * 查询所有
    */
    List<Resources> selectAll(Resources resources);

}