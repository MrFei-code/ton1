package com.cssl.cpf.controller;

import com.cssl.cpf.domain.Dept;
import com.cssl.cpf.domain.Emp;
import com.cssl.cpf.service.DeptService;
import com.cssl.cpf.service.EmpService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
public class EmpController {
    @Resource
    private MongoTemplate mongoTemplate;

    @Autowired
    private EmpService empService;

    @Autowired
    private DeptService deptService;

    @GetMapping("/findEmp")
    public List<Emp> findEmp(){
        List<Emp> list = empService.selectEmp();
        mongoTemplate.insertAll(list);
        return list;
    }
    /**
     * 多对一新增多：新增一个员工
     * @param emp
     * @return
     */
    @GetMapping("/saveEmp")
    public Emp saveEmp(Emp emp){
       empService.save(emp);
       Dept dept=deptService.getById(emp.getDeptno());
       emp.setDept(dept);
       mongoTemplate.save(dept);
       return emp;
    }

    /**
     * 多对一修改：更新某个员工的部门
     * @param emp
     * @return
     */
    @GetMapping("/updateEmp")
    public Emp updateEmp(Emp emp) {
        empService.updateById(emp);
        emp = empService.getById(emp.getEmpno());
        Dept dept = deptService.getById(emp.getDeptno());

        if (dept.getEmps() == null) {
            dept.setEmps(new ArrayList<>());
        }
        dept.getEmps().add(emp);

        mongoTemplate.save(dept);

        emp.setDept(new Dept(dept.getDeptno()));

        mongoTemplate.save(emp);

        return emp;
    }

    /**
     * 多对一：更新部门名称（更新所有员工的部门）
     * @return
     */
    @GetMapping("/updateEmp2")
    public Dept updateEmp2(Dept dept){
        deptService.updateById(dept);
        Query query = new Query(Criteria.where("deptno").is(dept.getDeptno()));
        List<Emp> list = mongoTemplate.find(query, Emp.class);
        list.forEach(e -> {
            e.setDept(dept);
            mongoTemplate.save(e);
        });
        return dept;
    }
    /**
     * 多对一删除：删除部门，同时删除部门下的所有员工
     * @param dept
     * @return
     */
    @GetMapping("/removeEmp")
    public List<Emp> removeEmp(Dept dept){
        empService.removeByMap(Map.of("deptno",dept.getDeptno()));
        deptService.removeById(dept);
        Query query=new Query(Criteria.where("deptno").is(dept.getDeptno()));
        List<Emp> list=mongoTemplate.find(query,Emp.class);
        mongoTemplate.remove(query,Emp.class);
        return list;
    }
    @CrossOrigin
    @GetMapping("/findByPage")
    public Map<String, Object> findByPage(
            @RequestParam("pageIndex") Integer pageIndex,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam(value = "ename", required = false) String ename) {
        Map<String, Object> map = new HashMap<>();
        Query query = new Query();
        if (ename != null&&ename.isEmpty()){
            Pattern pattern = Pattern.compile("^.*" + ename + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("ename").regex(pattern));
        }
        long count = mongoTemplate.count(query, Emp.class);
        System.out.println("count:" + count);
        map.put("count", count);
        query.with(Sort.by(Sort.Direction.DESC, "empno"));
        query.skip((pageIndex - 1) * pageSize).limit(pageSize);
        query.limit(pageSize);
        List<Emp> emps = mongoTemplate.find(query, Emp.class);
        map.put("list", emps);
        map.put("pages",(count% pageSize==0?count/pageSize:count/pageSize+1));
        return map;
    }
}
