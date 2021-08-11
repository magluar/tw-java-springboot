package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.repository.EmployeesRepository;
import com.thoughtworks.springbootemployee.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final List<Employee> employees = new ArrayList<>();
    @Autowired
    private EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService){
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeesService.getAllEmployees();
    }

    @GetMapping(path = "/{employeeId}")
    public Employee findEmployeeById(@PathVariable Integer employeeId){
        return employeesService.findEmployeeById(employeeId);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        return employeesService.getEmployeesByPagination(pageIndex, pageSize);
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

    @DeleteMapping(path = "/{employeeId}")
    private void deleteEmployeeRecord(@PathVariable Integer employeeId){
        employeesService.deleteEmployeeRecord(employeeId);
    }
}