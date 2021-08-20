package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class CompanyResponse {
    private Integer id;
    private String companyName;
    private List<Employee> employees;

    public CompanyResponse() {

    }

    public CompanyResponse(String companyName) {
        this.companyName = companyName;
    }

    public Integer getId() {
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
