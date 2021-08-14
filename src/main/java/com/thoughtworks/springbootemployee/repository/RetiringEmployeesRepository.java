package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.controller.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RetiringEmployeesRepository {
    private List<Employee> employees = new ArrayList<>();

    public RetiringEmployeesRepository(){
        employees.add(new Employee(1, "alice", 20, "female", 2000, 1));
        employees.add(new Employee(2, "bob", 21, "male", 1000, 1));
    }

    public RetiringEmployeesRepository(List<Employee> employees){
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}