package com.cssl.cpf.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @TableName emp
 */
@EqualsAndHashCode(of = "empno")
public class Emp implements Serializable {

    /**
     * MongoDB 的 _id 字段
     */
    @MongoId
    @Indexed
    @TableId
    private String empno;

    private String ename;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hiredate;

    private BigDecimal sal;

    private Integer deptno;
    @TableField(exist = false)
    private Dept dept;

    // Getter 和 Setter 方法

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public BigDecimal getSal() {
        return sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno='" + empno + '\'' +
                ", ename='" + ename + '\'' +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", deptno=" + deptno +
                '}';
    }
}
