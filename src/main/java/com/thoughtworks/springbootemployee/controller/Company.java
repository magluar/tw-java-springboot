package com.thoughtworks.springbootemployee.controller;

import java.util.List;

public class Company {
    private Integer id;
    private String companyName;
    private List<Employee> employees;

    public Company() {

    }

    public Company(Integer id, String name, List<Employee> employees) {
        this.id = id;
        this.companyName = name;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees(){
        return employees;
    }

}
