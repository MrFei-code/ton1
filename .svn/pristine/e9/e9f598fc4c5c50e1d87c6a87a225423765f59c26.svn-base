package com.cssl.cpf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cssl.cpf.domain.Emp;
import com.cssl.cpf.service.EmpService;
import com.cssl.cpf.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

/**
* @author 乌鸦
* @description 针对表【emp】的数据库操作Service实现
* @createDate 2024-12-03 12:02:44
*/
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp>
    implements EmpService {


    @Override
    public List<Emp> selectEmp() {
        return super.baseMapper.selectEmp();
    }
}




