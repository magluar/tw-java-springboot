package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.controller.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeesController(){
        employees.add(new Employee(1, "alice", 20, "female", 2000));
        employees.add(new Employee(2, "bob", 21, "male", 1000));
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }
}