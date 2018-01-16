package ru.otus.model.dto;

public class ExternalDataDTO {
    private String fio;
    private String email;
    private boolean enabled;
    private String login;
    private String password;
    private long salary;
    private String city;
    private String department;
    private String position;
    private String roles;

    public String getFio() {
        return fio;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public long getSalary() {
        return salary;
    }

    public String getCity() {
        return city;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getRoles() {
        return roles;
    }

    public ExternalDataDTO fio(String fio) {
        this.fio = fio;
        return this;
    }

    public ExternalDataDTO email(String email) {
        this.email = email;
        return this;
    }

    public ExternalDataDTO enabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public ExternalDataDTO login(String login) {
        this.login = login;
        return this;
    }

    public ExternalDataDTO password(String password) {
        this.password = password;
        return this;
    }

    public ExternalDataDTO salary(long salary) {
        this.salary = salary;
        return this;
    }

    public ExternalDataDTO city(String city) {
        this.city = city;
        return this;
    }

    public ExternalDataDTO department(String department) {
        this.department = department;
        return this;
    }

    public ExternalDataDTO position(String position) {
        this.position = position;
        return this;
    }

    public ExternalDataDTO roles(String roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public String toString() {
        return "ExternalDataDTO{" +
                "fio='" + fio + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", salary=" + salary +
                ", city='" + city + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
