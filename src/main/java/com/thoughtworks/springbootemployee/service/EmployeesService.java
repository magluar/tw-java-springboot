package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeesService {
    @Autowired
    private EmployeesRepository employeesRepository;

    public List<Employee> getAllEmployees() {
        return employeesRepository.findAll();
    }

    public Employee findEmployeeById(Integer employeeID){
        return employeesRepository.findById(employeeID)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID not found."));
    }

    public List<Employee> getEmployeesByPagination(Integer pageIndex, Integer pageSize){
        return employeesRepository.findAll(PageRequest.of(pageIndex - 1, pageSize)).getContent();
    }

    public List<Employee> getAllEmployeesByGender(@RequestParam String gender){
        return employeesRepository.findAllByGender(gender);
    }

    public Employee addEmployee(Employee employee) {
        return employeesRepository.save(employee);
    }

    public Employee updateEmployee(Integer employeeId, Employee employeeUpdated){
        Employee updateEmployee = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID not found."));
        return employeesRepository.save(Objects.requireNonNull(updateEmployeeInformation(updateEmployee,
                employeeUpdated)));
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

    public void deleteEmployeeRecord(Integer employeeID){
        employeesRepository.deleteById(employeeID);
    }
}
