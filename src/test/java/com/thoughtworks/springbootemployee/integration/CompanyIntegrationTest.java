package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.controller.Company;
import com.thoughtworks.springbootemployee.controller.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;

    @Test
    public void should_return_employees_when_findEmployeesByCompanyId_api() throws Exception {
        //given
        List<Employee> googleEmployees = new ArrayList<>();
        List<Employee> amazonEmployees = new ArrayList<>();
        List<Company> companies = new ArrayList<>();
        final Employee tom = new Employee(1, "Tom", 20, "male", 9999, 1);
        final Employee bob = new Employee(2, "Bob", 20, "male", 9999, 1);
        final Employee alice = new Employee(3, "Alice", 20, "female", 9999, 2);
        googleEmployees.add(tom);
        googleEmployees.add(bob);
        amazonEmployees.add(alice);
        Company google = new Company(1, "Google", googleEmployees);
        Company amazon = new Company(2, "Amazon", amazonEmployees);
        companyRepository.saveAll(companies);

        //when

        //then
        Integer id = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/employees",
                companyService.findCompanyById(1).getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Tom"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(9999))
                .andExpect(jsonPath("$[0].companyId").value(1));
    }
}
