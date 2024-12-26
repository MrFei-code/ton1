package com.cssl.cpf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cssl.cpf.domain.Dept;
import com.cssl.cpf.repository.DeptRepository;
import com.cssl.cpf.service.DeptService;
import com.cssl.cpf.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 乌鸦
* @description 针对表【dept】的数据库操作Service实现
* @createDate 2024-12-03 12:02:44
*/
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept>
    implements DeptService{

    @Override
    public List<Dept> selectDept() {
        return super.baseMapper.selectDept();
    }
}




