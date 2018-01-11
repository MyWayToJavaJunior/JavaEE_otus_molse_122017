package ru.otus.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "EMP")
public class EmpEntity {
    @Id
    private long empno;
    private String ename;
    private String job;
    private Long mgr;
    private Time hiredate;
    private Long sal;
    private Long comm;

    public long getEmpno() {
        return empno;
    }

    public void setEmpno(long empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getMgr() {
        return mgr;
    }

    public void setMgr(Long mgr) {
        this.mgr = mgr;
    }

    public Time getHiredate() {
        return hiredate;
    }

    public void setHiredate(Time hiredate) {
        this.hiredate = hiredate;
    }

    public Long getSal() {
        return sal;
    }

    public void setSal(Long sal) {
        this.sal = sal;
    }

    public Long getComm() {
        return comm;
    }

    public void setComm(Long comm) {
        this.comm = comm;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpEntity empEntity = (EmpEntity) o;
        return empno == empEntity.empno &&
                Objects.equals(ename, empEntity.ename) &&
                Objects.equals(job, empEntity.job) &&
                Objects.equals(mgr, empEntity.mgr) &&
                Objects.equals(hiredate, empEntity.hiredate) &&
                Objects.equals(sal, empEntity.sal) &&
                Objects.equals(comm, empEntity.comm);
    }

    @Override
    public int hashCode() {

        return Objects.hash(empno, ename, job, mgr, hiredate, sal, comm);
    }

    @Override
    public String toString() {
        return "EmpEntity{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                '}';
    }
}
