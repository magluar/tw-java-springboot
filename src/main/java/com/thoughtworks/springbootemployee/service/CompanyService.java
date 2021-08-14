package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.controller.Company;
import com.thoughtworks.springbootemployee.controller.Employee;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private final RetiringCompanyRepository retiringCompanyRepository;

    public CompanyService(RetiringCompanyRepository retiringCompanyRepository) {
        this.retiringCompanyRepository = retiringCompanyRepository;
    }

    public List<Company> getAllCompanies() {
        return retiringCompanyRepository.getCompanies();
    }

    public Company findCompanyById(Integer companyId){
        return retiringCompanyRepository.getCompanies()
                .stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .orElse(null);
    }

    public List<Company> getCompaniesByPagination(Integer pageIndex, Integer pageSize) {
        return retiringCompanyRepository.getCompanies()
                .stream()
                .skip((long) (pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<Employee> findEmployeesByCompanyId(Integer companyId) {
        return Objects.requireNonNull(retiringCompanyRepository.getCompanies()
                .stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .orElse(null))
                .getEmployees();
    }

    public void addCompany(Company company) {
        Company companyToBeAdded = new Company(retiringCompanyRepository.getCompanies().size() + 1,
                company.getCompanyName(),
                company.getEmployees());
        retiringCompanyRepository.getCompanies().add(companyToBeAdded);
    }

    public Company updateCompany(Integer companyId, Company companyUpdated) {
        return retiringCompanyRepository.getCompanies()
                .stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .map(company -> updateCompanyInformation(company, companyUpdated))
                .orElse(null);
    }

    private Company updateCompanyInformation(Company company, Company companyUpdated) {
        if(company.getCompanyName() != null){
            company.setCompanyName(companyUpdated.getCompanyName());
        }
        return company;
    }

    public void deleteCompanyRecord(Integer companyId) {
        Company companyToDelete = retiringCompanyRepository.getCompanies()
                .stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .orElse(null);
        retiringCompanyRepository.getCompanies().remove(companyToDelete);
    }
}
