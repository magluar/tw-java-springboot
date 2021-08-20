package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.*;
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
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyMapper.toResponse(companyService.getAllCompanies());
    }

    @GetMapping(path = "/{companyId}")
    public CompanyResponse findCompanyById(@PathVariable Integer companyId) {
        return companyMapper.toResponse(companyService.findCompanyById(companyId));
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<CompanyResponse> getCompaniesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return companyMapper.toResponse(companyService.getCompaniesByPagination(pageIndex, pageSize));
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<EmployeeResponse> getEmployeesByCompanyId(@PathVariable Integer companyId){
        return employeeMapper.toResponse(companyService.findEmployeesByCompanyId(companyId));
    }

    @PostMapping
    public CompanyResponse addCompany(@RequestBody Company company){
        return companyMapper.toResponse(companyService.addCompany(company));
    }

    @PutMapping(path = "/{companyId}")
    public CompanyResponse updateCompany(@PathVariable Integer companyId, @RequestBody CompanyRequest companyRequest){
        return companyMapper.toResponse(companyService.updateCompany(companyId, companyMapper.toEntity(companyRequest)));
    }

    @DeleteMapping(path = "/{companyId}")
    public void deleteCompanyRecord(@PathVariable Integer companyId){
        companyService.deleteCompanyRecord(companyId);
    }
}
