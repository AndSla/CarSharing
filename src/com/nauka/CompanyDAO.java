package com.nauka;

import java.util.List;

public interface CompanyDAO {

    void addCompany(Company company);

    List<Company> getAllCompanies();

    Company getCompanyById(int id);

}
