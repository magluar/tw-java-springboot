package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.controller.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeesRepository {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeesRepository(){
        employees.add(new Employee(1, "alice", 20, "female", 2000));
        employees.add(new Employee(2, "bob", 21, "male", 1000));
        employees.add(new Employee(3, "tom", 5, "male", 5));
        employees.add(new Employee(4, "jerry", 4, "male", 5));
        employees.add(new Employee(5, "doraemon", 99, "male", 10));
        employees.add(new Employee(6, "nobita", 10, "male", 100));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
