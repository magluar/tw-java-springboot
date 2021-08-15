package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.EmployeeResponse;
import com.thoughtworks.springbootemployee.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final List<Employee> employees = new ArrayList<>();
    @Autowired
    private final EmployeesService employeesService;
    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeesController(EmployeesService employeesService){
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeMapper.toResponse(employeesService.getAllEmployees());
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeResponse findEmployeeById(@PathVariable Integer employeeId){
        return employeeMapper.toRespose(employeesService.findEmployeeById(employeeId));
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        return employeeMapper.toResponse(employeesService.getEmployeesByPagination(pageIndex, pageSize));
    }

    @GetMapping (params="gender")
    public List<Employee> getAllEmployeesByGender(@RequestParam String gender){
        return employeesService.getAllEmployeesByGender(gender);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody EmployeeRequest employeeRequest){
        return employeesService.addEmployee(employeeMapper.toEntity(employeeRequest));
    }

    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeRequest){
        return employeesService.updateEmployee(employeeId, employeeMapper.toEntity(employeeRequest));
    }

    @DeleteMapping(path = "/{employeeId}")
    private void deleteEmployeeRecord(@PathVariable Integer employeeId){
        employeesService.deleteEmployeeRecord(employeeId);
    }
}