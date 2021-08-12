package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.controller.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeesRepository {
    private List<Employee> employees = new ArrayList<>();

    public EmployeesRepository(){
        employees.add(new Employee(1, "alice", 20, "female", 2000));
        employees.add(new Employee(2, "bob", 21, "male", 1000));
    }

    public EmployeesRepository(List<Employee> employees){
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
