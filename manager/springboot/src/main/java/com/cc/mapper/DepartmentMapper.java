package com.cc.mapper;

import com.cc.entity.Department;

import java.util.List;

public interface DepartmentMapper {


  int insert(Department department);


  int deleteById(Integer id);

  int updateById(Department department);

  Department selectById(Integer id);

  List<Department> selectAll(Department department);

}