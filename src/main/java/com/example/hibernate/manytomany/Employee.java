package com.example.hibernate.manytomany;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
    private int id;
    private String firstName;
    private String secondName;
    private List<Company> companies = new ArrayList<>();

    public Employee() {

    }

    public Employee(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="JOIN_COMPANY_EMPLOYEE",
            joinColumns = {@JoinColumn (name="EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")},
            inverseJoinColumns = {@JoinColumn(name="COMPANY_ID", referencedColumnName = "COMPANY_ID")}
    )
    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="EMPLOYEE_ID", unique = true)
    public int getId() {
        return id;
    }

    @NotNull
    @Column(name="FIRSTNAME")
    public String getFirstName() {
        return firstName;
    }

    @NotNull
    @Column(name="SECONDNAME")
    public String getSecondName() {
        return secondName;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
