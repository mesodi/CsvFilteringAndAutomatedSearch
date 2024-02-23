package es.wacoco.csvfilteringandautomatedsearch.controller;

import es.wacoco.csvfilteringandautomatedsearch.database.DatabaseService;
import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final DatabaseService databaseService;

    @Autowired
    public CompanyController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @PostMapping("/add")
    public void addCompany(@RequestBody Company company) {
        databaseService.addCompany(company);
    }

    @DeleteMapping("/remove")
    public void removeCompany(@RequestBody Company company) {
        databaseService.removeCompany(company);
    }

    @GetMapping("/list")
    public List<Company> getChosenCompanies() {
        return databaseService.getChosenCompanies();
    }
}
