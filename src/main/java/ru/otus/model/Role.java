package ru.otus.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role extends BaseEntity{

    public Employee getEmployee() {
        return employee;
    }

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
