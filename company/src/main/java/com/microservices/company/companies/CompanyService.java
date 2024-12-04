package com.microservices.company.companies;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getallcompanies();

    void create(Company company);

    boolean delete(Long id);

    Optional<Company> getCompanybyid(Long id);

    boolean updateCompany(Long id, Company company);
}
