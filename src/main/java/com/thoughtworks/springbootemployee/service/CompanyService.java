package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.controller.Company;
import com.thoughtworks.springbootemployee.controller.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.getCompanies();
    }

    public Company findCompanyById(Integer companyId){
        return companyRepository.getCompanies()
                .stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .orElse(null);
    }

    public List<Company> getCompaniesByPagination(Integer pageIndex, Integer pageSize) {
        return companyRepository.getCompanies()
                .stream()
                .skip((long) (pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        return Objects.requireNonNull(companyRepository.getCompanies()
                .stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .orElse(null))
                .getEmployees();
    }

    public void addCompany(Company company) {
        Company companyToBeAdded = new Company(companyRepository.getCompanies().size() + 1,
                company.getCompanyName(),
                company.getEmployees());
        companyRepository.getCompanies().add(companyToBeAdded);
    }

    public Company updateEmployee(Integer companyId, Company companyUpdated) {
        return companyRepository.getCompanies()
                .stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .map(company -> updateCompanyInformation(company, companyUpdated))
                .orElse(null);
    }

    private Company updateCompanyInformation(Company company, Company companyUpdated) {
        if(company.getCompanyName() != null){
            company.setCompanyName(company.getCompanyName());
        }
        return company;
    }

    public void deleteCompanyRecord(Integer companyId) {
        Company companyToDelete = companyRepository.getCompanies()
                .stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .orElse(null);
        companyRepository.getCompanies().remove(companyToDelete);
    }
}
