package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.controller.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesService {
    @Autowired
    private EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeesRepository.getEmployees();
    }

    public void deleteEmployeeRecord(Integer employeeID){
        Employee employeeToDelete = employeesRepository.getEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(employeeID))
                .findFirst()
                .orElse(null);
        employeesRepository.getEmployees().remove(employeeToDelete);
    }

    public Employee findEmployeeById(Integer employeeID){
        return employeesRepository.getEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(employeeID))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployeesByPagination(Integer pageIndex, Integer pageSize){
        return employeesRepository.getEmployees()
                .stream()
                .skip((long) (pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
