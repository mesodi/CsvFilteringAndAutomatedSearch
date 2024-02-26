package es.wacoco.csvfilteringandautomatedsearch.database;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DatabaseService {
    public void addCompany(Company company) {
        ChosenCompanies.addCompany(company);
    }
    public void removeCompany(Company company) {
        ChosenCompanies.removeCompany(company);
    }
    public List<Company> getChosenCompanies() {
        return ChosenCompanies.getChosenCompanies();
    }

}
