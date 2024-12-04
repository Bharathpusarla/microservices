package com.microservices.company.companies.Impl;


import com.microservices.company.companies.Company;
import com.microservices.company.companies.CompanyRepository;
import com.microservices.company.companies.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniesServiveImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompaniesServiveImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getallcompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void create(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean delete(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company != null) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Company> getCompanybyid(Long id) {
       return companyRepository.findById(id);
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> optionalCompany= companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company company1 = optionalCompany.get();
            company1.setName(company.getName());
            company1.setDescriptions(company.getDescriptions());
            companyRepository.save(company1);

            return true;
        }
        return false;
    }
}