package es.wacoco.csvfilteringandautomatedsearch.service.impl;

import es.wacoco.csvfilteringandautomatedsearch.database.Database;
import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private List<Company> companies = new ArrayList<>();
    public CompanyServiceImpl(List<Company> companies) {
        this.companies = companies;
    }


    @Override
    public List<Company> getAllCompanies() {
        return new ArrayList<>(Database.companies);
    }
}