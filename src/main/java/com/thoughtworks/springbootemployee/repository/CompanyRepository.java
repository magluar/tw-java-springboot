package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.controller.Company;
import com.thoughtworks.springbootemployee.controller.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    public CompanyRepository() {
        List<Employee> emptyEmployees = new ArrayList<>();
        Employee employee1 = new Employee(1, "alice", 20, "female", 2000, 1);
        Employee employee2 = new Employee(2, "bob", 21, "male", 1000, 1);
        employees.add(employee1);
        employees.add(employee2);
        companies.add(new Company(1, "Google", employees));
        companies.add(new Company(2, "Amazon", emptyEmployees));
    }

    public CompanyRepository(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }
}
