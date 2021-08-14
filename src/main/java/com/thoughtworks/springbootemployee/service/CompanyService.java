package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.controller.Company;
import com.thoughtworks.springbootemployee.controller.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private final RetiringCompanyRepository retiringCompanyRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyService(RetiringCompanyRepository retiringCompanyRepository) {
        this.retiringCompanyRepository = retiringCompanyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company findCompanyById(Integer companyId){
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Company> getCompaniesByPagination(Integer pageIndex, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(pageIndex - 1, pageSize)).getContent();
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
