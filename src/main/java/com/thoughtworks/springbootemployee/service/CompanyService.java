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
        Company company = companyRepository.findById(companyId).orElse(null);
        return company.getEmployees();
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public Company updateCompany(Integer companyId, Company companyUpdated) {
        Company companyToBeUpdated = companyRepository.findById(companyId).orElse(null);
        return companyRepository.save(updateCompanyInformation(companyToBeUpdated, companyUpdated));
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
