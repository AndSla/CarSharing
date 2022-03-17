package com.nauka;

import java.util.Comparator;
import java.util.List;

public class CompanyListMenu extends Menu {

    private List<Company> companies;

    @Override
    void showMenu() {
        Comparator<Company> byId = Comparator.comparingInt(Company::getId);

        if (!companies.isEmpty()) {
            System.out.println("Choose a company:");
            companies.stream().sorted(byId).forEach(System.out::println);
            System.out.print("> ");
        } else {
            System.out.println("The company list is empty!");
            System.out.println();
        }
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

}
