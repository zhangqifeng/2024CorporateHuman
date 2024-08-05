package com.example.mapper;

import com.example.entity.Askapply;

import java.util.List;

public interface AskapplyMapper {

    /**
     * 新增
     */
    int insert(Askapply askapply);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Askapply askapply);

    /**
     * 根据ID查询
     */
    Askapply selectById(Integer id);

    /**
     * 查询所有
     */
    List<Askapply> selectAll(Askapply askapply);

}