package com.cssl.cpf.service;

import com.cssl.cpf.domain.Emp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 乌鸦
* @description 针对表【emp】的数据库操作Service
* @createDate 2024-12-03 12:02:44
*/
public interface EmpService extends IService<Emp> {
    List<Emp> selectEmp();
}
