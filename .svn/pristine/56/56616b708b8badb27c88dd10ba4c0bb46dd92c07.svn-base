package com.cssl.cpf.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cssl.cpf.domain.Dept;
import com.cssl.cpf.domain.Emp;
import com.cssl.cpf.service.DeptService;
import com.cssl.cpf.service.EmpService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private EmpService empService;

    @Resource
    private MongoTemplate mongoTemplate;


    @GetMapping("/findDepts")
    public List<Dept> findDepts(){
        return (List<Dept>)mongoTemplate.insertAll(deptService.selectDept());
    }
    /**
     * 一对多：某个部门新来员工（给某个部门的员工集合添加对象）
     * @param emp
     * @return
     */
    @GetMapping("/addEmployee")
    public Dept saveEmp2(Emp emp){
        //mysql
        empService.save(emp);
        //mongodb
        Dept dept=mongoTemplate.findById(emp.getDeptno(),Dept.class);
        List<Emp> emps=dept.getEmps();
        if (emps==null){
            emps=new ArrayList<>();
        }
        emps.add(emp);
        mongoTemplate.save(dept);
        return dept;
    }
    /**
     * 一对多：修改某个部门的某个员工信息
     * @param emp
     * @return
     */
    @GetMapping("/updateDept")
    public Dept updateDept(Emp emp){
       empService.updateById(emp);
       Dept dept=mongoTemplate.findById(emp.getDeptno(),Dept.class);
       List<Emp> emps=dept.getEmps();
       emps.remove(emp);
       emps.add(emp);
       mongoTemplate.save(dept);
       return dept;
    }
    /**
     * 一对多：删除某个部门的某个员工信息
     * @param empno
     * @return
     */
    @GetMapping("/updateDept2")
    public Dept updateDept2(@RequestParam("empno") int empno){
        Emp emp = empService.getById(empno);
        empService.removeById(empno);
        Dept dept = mongoTemplate.findById(emp.getDeptno(),Dept.class);
        List<Emp> emps = dept.getEmps();
        emps.remove(emp);
        mongoTemplate.save(dept);
        return dept;
    }
    /**
     * 一对多删除：删除某个部门
     * @param deptno
     * @return
     */
    @GetMapping("/removeDept")
    public Dept removeDept2(@RequestParam("deptno") int deptno){
        empService.remove(new QueryWrapper<Emp>().eq("deptno",deptno));
        deptService.removeById(deptno);
        Dept dept = mongoTemplate.findById(deptno,Dept.class);
        mongoTemplate.remove(dept);
        return dept;
    }
}
