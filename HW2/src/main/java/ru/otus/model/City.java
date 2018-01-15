package ru.otus.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class City {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    public City() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
