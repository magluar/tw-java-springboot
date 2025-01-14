//package com.thoughtworks.springbootemployee.service;
//
//import com.thoughtworks.springbootemployee.model.Company;
//import com.thoughtworks.springbootemployee.model.Employee;
//import com.thoughtworks.springbootemployee.repository.CompanyRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertIterableEquals;
//import static org.mockito.BDDMockito.given;
//
//@ExtendWith(MockitoExtension.class)
//public class CompanyServiceTest {
//    @InjectMocks
//    private CompanyService companyService;
//    @Mock
//    private CompanyRepository companyRepository;
//
//    @Test
//    public void should_return_all_companies_when_getAllCompanies_given_company(){
//        //given
//        List<Employee> employees = new ArrayList<>();
//        List<Employee> emptyEmployees = new ArrayList<>();
//        List<Company> companies = new ArrayList<>();
//        Employee employee1 = new Employee(1, "alice", 20, "female", 2000, 1);
//        Employee employee2 = new Employee(2, "bob", 21, "male", 1000, 1);
//        employees.add(employee1);
//        employees.add(employee2);
//        Company google = new Company(companies.size() + 1, "Google", employees);
//        companies.add(google);
//        Company amazon = new Company(companies.size() + 1, "Amazon", emptyEmployees);
//        companies.add(amazon);
//        given(companyRepository.getCompanies()).willReturn(companies);
//
//        //when
//        List<Company> actualCompanies = companyService.getAllCompanies();
//
//        //then
//        assertIterableEquals(companies, actualCompanies);
//    }
//
//    @Test
//    public void should_return_companies_by_id_when_findCompanyById_given_company_id(){
//        //given
//        List<Employee> employees = new ArrayList<>();
//        List<Employee> emptyEmployees = new ArrayList<>();
//        List<Company> companies = new ArrayList<>();
//        Employee employee1 = new Employee(1, "alice", 20, "female", 2000, 1);
//        Employee employee2 = new Employee(2, "bob", 21, "male", 1000, 1);
//        employees.add(employee1);
//        employees.add(employee2);
//        Company google = new Company(companies.size() + 1, "Google", employees);
//        companies.add(google);
//        Company amazon = new Company(companies.size() + 1, "Amazon", emptyEmployees);
//        companies.add(amazon);
//        given(companyRepository.getCompanies()).willReturn(companies);
//
//        //when
//        Company actualGoogle = companyService.findCompanyById(1);
//
//        //then
//        assertEquals(google, actualGoogle);
//    }
//
//    @Test
//    public void should_return_companies_by_page_and_size_when_getCompanies_by_pagination_given_page_and_page_size(){
//        //given
//        List<Employee> employees = new ArrayList<>();
//        List<Employee> emptyEmployees = new ArrayList<>();
//        List<Company> companies = new ArrayList<>();
//        List<Company> emptyCompany = new ArrayList<>();
//        Employee employee1 = new Employee(1, "alice", 20, "female", 2000, 1);
//        Employee employee2 = new Employee(2, "bob", 21, "male", 1000, 1);
//        employees.add(employee1);
//        employees.add(employee2);
//        Company google = new Company(companies.size() + 1, "Google", employees);
//        companies.add(google);
//        Company amazon = new Company(companies.size() + 1, "Amazon", emptyEmployees);
//        companies.add(amazon);
//        given(companyRepository.getCompanies()).willReturn(companies);
//
//        //when
//        List<Company> actualCompanies = companyService.getCompaniesByPagination(1, 2);
//        List<Company> actualEmptyCompany = companyService.getCompaniesByPagination(2, 5);
//
//        //then
//        assertIterableEquals(companies, actualCompanies);
//        assertEquals(emptyCompany, actualEmptyCompany);
//    }
//
//    @Test
//    public void should_return_employees_when_findEmployeesByCompanyId_given_company_id(){
//        //given
//        List<Employee> employees = new ArrayList<>();
//        List<Employee> emptyEmployees = new ArrayList<>();
//        List<Company> companies = new ArrayList<>();
//        Employee employee1 = new Employee(1, "alice", 20, "female", 2000, 1);
//        Employee employee2 = new Employee(2, "bob", 21, "male", 1000, 1);
//        employees.add(employee1);
//        employees.add(employee2);
//        Company google = new Company(companies.size() + 1, "Google", employees);
//        companies.add(google);
//        Company amazon = new Company(companies.size() + 1, "Amazon", emptyEmployees);
//        companies.add(amazon);
//        given(companyRepository.getCompanies()).willReturn(companies);
//
//        //when
//        List<Employee> actualEmployees = companyService.findEmployeesByCompanyId(1);
//        List<Employee> actualEmptyEmployees = companyService.findEmployeesByCompanyId(2);
//
//        //then
//        assertIterableEquals(employees, actualEmployees);
//        assertEquals(emptyEmployees, actualEmptyEmployees);
//    }
//
//    @Test
//    public void should_return_added_company_when_addCompany_given_added_company(){
//        //given
//        List<Employee> employees = new ArrayList<>();
//        List<Employee> emptyEmployees = new ArrayList<>();
//        List<Company> companies = new ArrayList<>();
//        Employee employee1 = new Employee(1, "alice", 20, "female", 2000, 1);
//        Employee employee2 = new Employee(2, "bob", 21, "male", 1000, 1);
//        employees.add(employee1);
//        employees.add(employee2);
//        Company google = new Company(companies.size() + 1, "Google", employees);
//        companies.add(google);
//        Company amazon = new Company(companies.size() + 1, "Amazon", emptyEmployees);
//        companies.add(amazon);
//        given(companyRepository.getCompanies()).willReturn(companies);
//
//        //when
//        Company company = new Company(companies.size() + 1, "Netflix", emptyEmployees);
//        companyService.addCompany(company);
//        List<Company> companySize = companyService.getAllCompanies();
//
//        //then
//        assertEquals(3, companySize.size());
//    }
//
//    @Test
//    public void should_return_updated_company_given_updateCompanyRecord_given_update_request_body(){
//        //given
//        List<Employee> employees = new ArrayList<>();
//        List<Employee> emptyEmployees = new ArrayList<>();
//        List<Company> companies = new ArrayList<>();
//        Employee employee1 = new Employee(1, "alice", 20, "female", 2000, 1);
//        Employee employee2 = new Employee(2, "bob", 21, "male", 1000, 1);
//        employees.add(employee1);
//        employees.add(employee2);
//        Company google = new Company(companies.size() + 1, "Google", employees);
//        companies.add(google);
//        Company amazon = new Company(companies.size() + 1, "Amazon", emptyEmployees);
//        companies.add(amazon);
//        given(companyRepository.getCompanies()).willReturn(companies);
//
//        //when
//        Company updatedCompany = new Company(null, "Netflix", null);
//        Company updatedCompany1 = companyService.updateCompany(1, updatedCompany);
//
//        //then
//        assertEquals(companyService.findCompanyById(1).getCompanyName(), updatedCompany1.getCompanyName());
//    }
//
//    @Test
//    public void should_return_updated_company_list_when_deleteCompanyRecord_given_company_id(){
//        //given
//        List<Employee> employees = new ArrayList<>();
//        List<Employee> emptyEmployees = new ArrayList<>();
//        List<Company> companies = new ArrayList<>();
//        Employee employee1 = new Employee(1, "alice", 20, "female", 2000, 1);
//        Employee employee2 = new Employee(2, "bob", 21, "male", 1000, 1);
//        employees.add(employee1);
//        employees.add(employee2);
//        Company google = new Company(companies.size() + 1, "Google", employees);
//        companies.add(google);
//        Company amazon = new Company(companies.size() + 1, "Amazon", emptyEmployees);
//        companies.add(amazon);
//        given(companyRepository.getCompanies()).willReturn(companies);
//
//        //when
//        companyService.deleteCompanyRecord(1);
//        List<Company> updatedCompanyList = companyRepository.getCompanies();
//
//        //then
//        assertIterableEquals(companies, updatedCompanyList);
//    }
//}
