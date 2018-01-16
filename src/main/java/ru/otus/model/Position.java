package ru.otus.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "position")
public class Position extends BaseEntity{
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "position")
    private List<Employee> employees;

    public Position() {}

    public Position(String position) {
        super(position);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
