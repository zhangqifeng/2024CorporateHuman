package com.cc.mapper;

import com.cc.entity.Financial;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FinancialMapper {

  int insert(Financial financial);

  int deleteById(Integer id);

  int updateById(Financial financial);

  Financial selectById(Integer id);

  List<Financial> selectAll(Financial financial);
@Select("select left(time,7) as time, price from financial")
  List<Financial> selectAll2();
}