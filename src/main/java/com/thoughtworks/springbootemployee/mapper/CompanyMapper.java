package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    private EmployeeMapper employeeMapper;

    public Company toEntity(CompanyRequest companyRequest){
        Company company = new Company();
        BeanUtils.copyProperties(companyRequest, company);
        return company;
    }

    public CompanyResponse toResponse(Company company){
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);
        return companyResponse;
    }

    public List<CompanyResponse> toResponse(List<Company> companies) {
        return companies.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
