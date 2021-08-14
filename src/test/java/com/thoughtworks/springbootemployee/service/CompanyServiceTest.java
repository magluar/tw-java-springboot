package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.controller.Company;
import com.thoughtworks.springbootemployee.controller.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;
    @Mock
    private CompanyRepository companyRepository;

    @Test
    public void should_return_all_companies_when_getAllCompanies_api(){
        //given
        List<Employee> employees = new ArrayList<>();
        List<Employee> emptyEmployees = new ArrayList<>();
        List<Company> companies = new ArrayList<>();
        Employee employee1 = new Employee(1, "alice", 20, "female", 2000, 1);
        Employee employee2 = new Employee(2, "bob", 21, "male", 1000, 1);
        employees.add(employee1);
        employees.add(employee2);
        Company google = new Company(companies.size() + 1, "Google", employees);
        companies.add(google);
        Company amazon = new Company(companies.size() + 1, "Amazon", emptyEmployees);
        companies.add(amazon);
        given(companyRepository.getCompanies()).willReturn(companies);

        //when
        List<Company> actualCompanies = companyService.getAllCompanies();

        //then
        assertIterableEquals(companies, actualCompanies);
    }


}
