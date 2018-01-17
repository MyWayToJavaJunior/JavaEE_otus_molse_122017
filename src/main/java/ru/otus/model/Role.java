package ru.otus.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "role")
public class Role extends BaseEntity{

    public Employee getEmployee() {
        return employee;
    }

    @XmlTransient
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @ManyToOne
    private Employee employee;

    public Role() {}

    public Role(String role) {
        super(role);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
