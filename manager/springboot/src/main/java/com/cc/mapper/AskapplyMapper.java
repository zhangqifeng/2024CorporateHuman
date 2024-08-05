package com.cc.mapper;

import com.cc.entity.Askapply;

import java.util.List;

public interface AskapplyMapper {

  int insert(Askapply askapply);

  int deleteById(Integer id);

  int updateById(Askapply askapply);

  Askapply selectById(Integer id);

  List<Askapply> selectAll(Askapply askapply);

}
