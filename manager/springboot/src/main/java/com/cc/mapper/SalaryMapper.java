package com.cc.mapper;

import com.cc.entity.Salary;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SalaryMapper {


  int insert(Salary salary);

  int deleteById(Integer id);

  int updateById(Salary salary);

  Salary selectById(Integer id);

  List<Salary> selectAll(Salary salary);
@Select("select year from salary group by year")
  List<Salary> getMonth();
}
