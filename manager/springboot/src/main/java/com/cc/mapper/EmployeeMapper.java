package com.cc.mapper;

import com.cc.entity.Employee;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper {
  int insert(Employee employee);

  int deleteById(Integer id);

  int updateById(Employee employee);

  Employee selectById(Integer id);

  List<Employee> selectAll(Employee employee);

  @Select("select * from employee where username = #{username}")
  Employee selectByUsername(String username);
@Select("select * from employee where level = '主管'")
  List<Employee> selectHeaders();
}
