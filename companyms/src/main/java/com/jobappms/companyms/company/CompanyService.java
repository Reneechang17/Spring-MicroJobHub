package com.jobappms.companyms.company;

import com.jobappms.companyms.company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    void createCompany(Company company);
    boolean updateCompany(Company company, Long id);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);
    void updateCompanyRating(ReviewMessage reviewMessage);
}
