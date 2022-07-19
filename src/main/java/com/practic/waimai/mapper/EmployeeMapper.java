package com.practic.waimai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practic.waimai.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
