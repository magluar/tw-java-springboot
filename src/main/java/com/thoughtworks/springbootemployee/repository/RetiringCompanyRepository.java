package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RetiringCompanyRepository {
    private List<Company> companies = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();

    public RetiringCompanyRepository() {
        List<Employee> emptyEmployees = new ArrayList<>();
        Employee employee1 = new Employee(1, "alice", 20, "female", 2000, 1);
        Employee employee2 = new Employee(2, "bob", 21, "male", 1000, 1);
        employees.add(employee1);
        employees.add(employee2);
        companies.add(new Company(companies.size() + 1, "Google", employees));
        companies.add(new Company(companies.size() + 1, "Amazon", emptyEmployees));
    }

    public RetiringCompanyRepository(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public List<Employee> getEmployees(){
        return employees;
    }
}
