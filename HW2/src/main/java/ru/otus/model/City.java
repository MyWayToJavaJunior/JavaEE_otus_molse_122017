package ru.otus.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "city")
public class City extends BaseEntity{

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "city")
    private List<Employee> employees;

    public City() {
    }

    public City(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
