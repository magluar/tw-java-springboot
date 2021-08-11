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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
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
        Employee actualBobEmployee = employeeService.findEmployeeById(2);

        //then
        assertEquals(alice, actualBobEmployee);
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
}
