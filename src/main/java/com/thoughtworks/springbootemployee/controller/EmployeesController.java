package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.controller.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeesController(){
        employees.add(new Employee(1, "alice", 20, "female", 2000));
        employees.add(new Employee(2, "bob", 21, "male", 1000));
        employees.add(new Employee(3, "tom", 5, "male", 5));
        employees.add(new Employee(4, "jerry", 4, "male", 5));
        employees.add(new Employee(5, "doraemon", 99, "male", 10));
        employees.add(new Employee(6, "nobita", 10, "male", 100));
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @GetMapping(path = "/{employeeId}")
    public Employee findEmployeeById(@PathVariable Integer employeeId){
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        return employees.stream()
                .skip((long) (pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @GetMapping (params="gender")
    public List<Employee> getAllEmployeesByGender(@RequestParam String gender){
        return employees.stream()
                .filter(employee -> gender.toLowerCase().equals(employee.getGender().toLowerCase()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee){
        Employee employeeToBeAdded = new Employee(employees.size() + 1, employee.getName(), employee.getAge(),
                employee.getGender(), employee.getSalary());
        employees.add(employeeToBeAdded);
    }

    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeUpdated){
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .map(employee -> updateEmployeeInformation(employee, employeeUpdated))
                .get();
    }

    private Employee updateEmployeeInformation(Employee employee, Employee employeeUpdated) {
        if(employeeUpdated.getAge() != null){
            employee.setAge(employeeUpdated.getAge());
        }
        if(employeeUpdated.getName() != null){
            employee.setName(employeeUpdated.getName());
        }
        if(employeeUpdated.getGender()!= null){
            employee.setGender(employeeUpdated.getGender());
        }
        if(employeeUpdated.getSalary() != null){
            employee.setSalary(employeeUpdated.getSalary());
        }

        return employee;
    }

}