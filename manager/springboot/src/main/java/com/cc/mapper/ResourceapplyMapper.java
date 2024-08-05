package com.cc.mapper;

import com.cc.entity.Resourceapply;

import java.util.List;

public interface ResourceapplyMapper {

  int insert(Resourceapply resourceapply);

  int deleteById(Integer id);

  int updateById(Resourceapply resourceapply);

  Resourceapply selectById(Integer id);

  List<Resourceapply> selectAll(Resourceapply resourceapply);

}