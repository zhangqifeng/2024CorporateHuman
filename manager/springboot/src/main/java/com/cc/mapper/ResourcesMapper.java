package com.cc.mapper;

import com.cc.entity.Resources;

import java.util.List;

public interface ResourcesMapper {


  int insert(Resources resources);


  int deleteById(Integer id);


  int updateById(Resources resources);


  Resources selectById(Integer id);

  List<Resources> selectAll(Resources resources);

}