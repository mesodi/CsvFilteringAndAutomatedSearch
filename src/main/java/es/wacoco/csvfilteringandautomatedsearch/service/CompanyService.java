package es.wacoco.csvfilteringandautomatedsearch.service;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    private List<Company> companies = new ArrayList<>();
    public CompanyService(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getAllCompanies() {
        return companies;
    }
}
