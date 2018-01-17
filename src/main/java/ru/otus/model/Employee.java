package ru.otus.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
@Entity
@NamedEntityGraphs(
        @NamedEntityGraph(
                name = "employee",
                attributeNodes = {
                        @NamedAttributeNode("roles"),
                        @NamedAttributeNode(value = "department"),
                        @NamedAttributeNode(value = "city"),
                        @NamedAttributeNode(value = "position")
                }
        ))
@Table(name = "employee")
public class Employee{
    @Id @GeneratedValue
    private long id;
    private String fio;
    private String email;
    private String login;
    private String password;
    private boolean enabled;
    private long salary;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Position position;

    @ManyToOne
    private City city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Role> roles;

    public Employee() {
    }

    @XmlAttribute
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @XmlAttribute
    public String getFio() {
        return fio;
    }
    public void setFio(String lastname) {
        this.fio = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public long getSalary() {
        return salary;
    }
    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
        for (Role role: roles) {
            role.setEmployee(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                enabled == employee.enabled &&
                salary == employee.salary &&
                Objects.equal(fio, employee.fio) &&
                Objects.equal(email, employee.email) &&
                Objects.equal(login, employee.login) &&
                Objects.equal(password, employee.password) &&
                Objects.equal(department, employee.department) &&
                Objects.equal(position, employee.position) &&
                Objects.equal(city, employee.city);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, fio, email, login, password, enabled, salary, department, position, city);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", salary=" + salary +
                ", department=" + department +
                ", position=" + position +
                ", city=" + city +
                ", roles=" + roles +
                '}';
    }


    public Employee fio(String fio) {
        this.fio = fio;
        return this;
    }

    public Employee email(String email) {
        this.email = email;
        return this;
    }

    public Employee login(String login) {
        this.login = login;
        return this;
    }

    public Employee password(String password) {
        this.password = password;
        return this;
    }

    public Employee enabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Employee salary(long salary) {
        this.salary = salary;
        return this;
    }

    public Employee department(Department department) {
        this.department = department;
        return this;
    }

    public Employee position(Position position) {
        this.position = position;
        return this;
    }

    public Employee city(City city) {
        this.city = city;
        return this;
    }

    public Employee roles(List<Role> roles) {
        this.roles = roles;
        setRoles(roles);
        return this;
    }
}
