package ru.otus.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "department")
public class Department extends BaseEntity{

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "department")
    private List<Employee> employees;

    public Department() {}

    public Department(String department) {
        super(department);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
