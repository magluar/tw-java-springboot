package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.controller.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeesServiceTest {
    @InjectMocks
    private EmployeesService employeeService;
    @Mock
    private EmployeesRepository employeeRepository;
    @Test
    public void should_return_all_employees_when_getAllEmployees_given_all_employees() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "alice", 20, "female", 2000));
        employees.add(new Employee(2, "bob", 21, "male", 1000));
        given(employeeRepository.getEmployees()).willReturn(employees);

        //when
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        //then
        assertIterableEquals(employees, actualEmployees);
    }

    @Test
    public void should_return_employee_by_id_when_findEmployeeById_given_employeeId(){
        //given
        List<Employee> employees = new ArrayList<>();
        Employee bob = new Employee(1, "alice", 20, "female", 2000);
        Employee alice = new Employee(2, "bob", 21, "male", 1000);
        employees.add(bob);
        employees.add(alice);
        given(employeeRepository.getEmployees()).willReturn(employees);

        //when
        Employee actualBobEmployee = employeeService.findEmployeeById(1);

        //then
        assertEquals(bob, actualBobEmployee);
    }

    @Test
    public void should_return_employees_by_page_and_size_when_getEmployeesPagination_given_page_and_page_size(){
        //given
        List<Employee> employees = new ArrayList<>();
        List<Employee> emptyEmployees = new ArrayList<>();
        Employee bob = new Employee(1, "alice", 20, "female", 2000);
        Employee alice = new Employee(2, "bob", 21, "male", 1000);
        employees.add(bob);
        employees.add(alice);
        given(employeeRepository.getEmployees()).willReturn(employees);

        //when
        List<Employee> actualEmployees = employeeService.getEmployeesByPagination(1, 2);
        List<Employee> actualEmptyEmployees = employeeService.getEmployeesByPagination(2, 5);

        //then
        assertIterableEquals(employees, actualEmployees);
        assertEquals(emptyEmployees, actualEmptyEmployees);
    }

    @Test
    public void should_return_employees_by_gender_when_getAllEmployeesByGender_given_employee_gender(){
        //given male
        List<Employee> maleEmployees = new ArrayList<>();
        maleEmployees.add(new Employee(1, "tom", 20, "male", 2000));
        maleEmployees.add(new Employee(2, "bob", 21, "male", 1000));

        //when & then male
        given(employeeRepository.getEmployees()).willReturn(maleEmployees);
        List<Employee> actualMaleEmployees = employeeService.getAllEmployeesByGender("male");
        assertIterableEquals(maleEmployees, actualMaleEmployees);

        //given female
        List<Employee> femaleEmployees = new ArrayList<>();
        femaleEmployees.add(new Employee(1, "alice", 20, "female", 2000));
        femaleEmployees.add(new Employee(2, "jeany", 21, "female", 1000));

        //when & then male
        given(employeeRepository.getEmployees()).willReturn(femaleEmployees);
        List<Employee> actualFemaleEmployees = employeeService.getAllEmployeesByGender("Female");
        assertIterableEquals(femaleEmployees, actualFemaleEmployees);
    }

    @Test
    public void should_return_added_employee_when_addEmployee_given_add_an_employee(){
        //given
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee(1, "tom", 20, "male", 2000);
        employees.add(employee);
        given(employeeRepository.getEmployees()).willReturn(employees);

        //when
        Employee newEmployee = new Employee(1, "alice", 21, "female", 2000);
        employeeService.addEmployee(newEmployee);
        List<Employee> addEmployee = employeeService.getAllEmployees();

        //then
        assertEquals(2, addEmployee.size());
    }

//    @Test
//    public void should_return_updated_employee_given_UpdateEmployeeInformation_given_update_request_body(){
//        //given
//        Employee employee = new Employee(1, "tom", 20, "male", 2000);
//        employeeService.updateEmployee(1, new Employee(null, null, 21, "female", null));
//        given(employeeService.updateEmployee(1, employee)).willReturn(employee);

        //when
//        Employee updatedEmployee = new Employee(1, "tom", 20, "male", 2000);
//        Employee updatedEmployee1 = employeeService.updateEmployee(1, new Employee(null, null, 21, "female", null));
//
//        //then
//        assertEquals(employee, updatedEmployee1);
//    }
}
