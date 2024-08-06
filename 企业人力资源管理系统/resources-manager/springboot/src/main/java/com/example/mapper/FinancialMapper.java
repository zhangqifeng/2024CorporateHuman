package com.example.mapper;

import com.example.entity.Financial;

import java.util.List;

/**
 * 操作financial相关数据接口
*/
public interface FinancialMapper {

    /**
      * 新增
    */
    int insert(Financial financial);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Financial financial);

    /**
      * 根据ID查询
    */
    Financial selectById(Integer id);

    /**
      * 查询所有
    */
    List<Financial> selectAll(Financial financial);

}