package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyRequest;
import com.thoughtworks.springbootemployee.model.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final List<Company> companies = new ArrayList<>();
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyMapper companyMapper;

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyMapper.toResponse(companyService.getAllCompanies());
    }

    @GetMapping(path = "/{companyId}")
    public CompanyResponse findCompanyById(@PathVariable Integer companyId) {
        return companyMapper.toResponse(companyService.findCompanyById(companyId));
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Company> getCompaniesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return companyService.getCompaniesByPagination(pageIndex, pageSize);
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable Integer companyId){
        return companyService.findEmployeesByCompanyId(companyId);
    }

    @PostMapping
    public void addCompany(@RequestBody Company company){
        companyService.addCompany(company);
    }

    @PutMapping(path = "/{companyId}")
    public Company updateCompany(@PathVariable Integer companyId, @RequestBody CompanyRequest companyRequest){
        return companyService.updateCompany(companyId, companyMapper.toEntity(companyRequest));
    }

    @DeleteMapping(path = "/{companyId}")
    public void deleteCompanyRecord(@PathVariable Integer companyId){
        companyService.deleteCompanyRecord(companyId);
    }
}
