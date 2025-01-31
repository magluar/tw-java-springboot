package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class CompanyRequest {
    private String companyName;
    private List<Employee> employees;

    public CompanyRequest() {

    }

    public CompanyRequest(String companyName, List<Employee> employees) {
        this.companyName = companyName;
        this.employees = employees;
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
