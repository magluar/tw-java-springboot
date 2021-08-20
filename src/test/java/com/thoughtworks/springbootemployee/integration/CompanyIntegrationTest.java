package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeesRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    @Autowired
    private EmployeesRepository employeesRepository;

    @BeforeEach
    public void deleteAll(){
        companyRepository.deleteAll();
    }

    @Test
    public void should_return_employees_when_findEmployeesByCompanyId_api() throws Exception {
        //given
        Company google = new Company("Google");
        Company amazon = new Company("Amazon");
        Company googleAdded = companyRepository.save(google);
        Company amazonAdded = companyRepository.save(amazon);
        final Employee tom = new Employee(1, "Tom", 20, "male", 9999, googleAdded.getId());
        final Employee bob = new Employee(2, "Bob", 20, "male", 9999, googleAdded.getId());
        final Employee alice = new Employee(3, "Alice", 20, "female", 9999, amazonAdded.getId());
        List<Employee> employees = Lists.newArrayList(tom, bob, alice);
        employeesRepository.saveAll(employees);

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/employees",
                googleAdded.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Tom"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(9999));
    }

    @Test
    public void should_return_companies_when_getAllCompanies_api() throws Exception {
        //given
        Company google = new Company("Google");
        Company amazon = new Company("Amazon");
        Company googleAdded = companyRepository.save(google);
        Company amazonAdded = companyRepository.save(amazon);

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyName").value("Google"))
                .andExpect(jsonPath("$[1].companyName").value("Amazon"));
    }

    @Test
    public void should_return_companies_when_getCompaniesByPagination_api() throws Exception {
        //given
        Company google = new Company("Google");
        Company amazon = new Company("Amazon");
        Company facebook = new Company("Facebook");
        List<Company> companies = Lists.newArrayList(google, amazon, facebook);
        companyRepository.saveAll(companies);

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies")
                .param("pageIndex", String.valueOf(1))
                .param("pageSize", String.valueOf(3)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyName").value("Google"))
                .andExpect(jsonPath("$[1].companyName").value("Amazon"))
                .andExpect(jsonPath("$[2].companyName").value("Facebook"));
    }

    @Test
    public void should_return_company_when_findCompanyById_api() throws Exception {
        //given
        Company google = new Company("Google");
        Company amazon = new Company("Amazon");
        Company facebook = new Company("Facebook");
        List<Company> companies = Lists.newArrayList(google, amazon, facebook);
        companyRepository.saveAll(companies);

        //when

        //then
        Integer id = amazon.getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}", id)
                .param("pageIndex", String.valueOf(1))
                .param("pageSize", String.valueOf(3)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Amazon"));
    }
}
