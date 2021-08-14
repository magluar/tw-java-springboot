package com.thoughtworks.springbootemployee.controller;


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

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "/{companyId}")
    public Company findCompanyById(@PathVariable Integer companyId) {
        return companyService.findCompanyById(companyId);
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
    public Company updateCompany(@PathVariable Integer companyId, @RequestBody Company companyUpdated){
        return companyService.updateCompany(companyId, companyUpdated);
    }

    @DeleteMapping(path = "/{companyId}")
    public void deleteCompanyRecord(@PathVariable Integer companyId){
        companyService.deleteCompanyRecord(companyId);
    }
}
