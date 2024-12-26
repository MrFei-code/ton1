package com.cssl.cpf.mapper;

import com.cssl.cpf.domain.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 乌鸦
* @description 针对表【dept】的数据库操作Mapper
* @createDate 2024-12-03 12:02:44
* @Entity generator.domain.Dept
*/
public interface DeptMapper extends BaseMapper<Dept> {
    List<Dept> selectDept();
}




