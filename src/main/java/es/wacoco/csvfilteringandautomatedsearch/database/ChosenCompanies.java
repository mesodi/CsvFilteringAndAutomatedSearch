package es.wacoco.csvfilteringandautomatedsearch.database;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;

import java.util.ArrayList;
import java.util.List;

public class ChosenCompanies {
    private static List<Company> chosenCompanies = new ArrayList<>();

    public static List<Company> getChosenCompanies() {
        return chosenCompanies;
    }
    public static void setChosenCompanies(List<Company> companies) {
        chosenCompanies = companies;
    }
    public static void addCompany(Company company) {
        chosenCompanies.add(company);
    }
    public static void removeCompany(Company company) {
        chosenCompanies.remove(company);
    }
}
