package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company findCompanyById(Integer companyId){
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company ID not found."));
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
        Company companyToBeUpdated = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company ID not found."));
        return companyRepository.save(updateCompanyInformation(companyToBeUpdated, companyUpdated));
    }

    private Company updateCompanyInformation(Company company, Company companyUpdated) {
        if(company.getCompanyName() != null){
            company.setCompanyName(companyUpdated.getCompanyName());
        }
        return company;
    }

    public void deleteCompanyRecord(Integer companyId) {
        companyRepository.deleteById(companyId);
    }
}
