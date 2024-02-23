package es.wacoco.csvfilteringandautomatedsearch;

import es.wacoco.csvfilteringandautomatedsearch.database.DatabaseService;
import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JobServiceTest {

    @Test
    void testAddAndRetrieveCompany() {
        // Create a company
        Company company = new Company("Test Company");

        // Add the company using the database service
        DatabaseService databaseService = new DatabaseService();
        databaseService.addCompany(company);

        // Retrieve the list of chosen companies using the database service
        List<Company> companies = databaseService.getChosenCompanies();

        // Assert that the list contains the added company
        assertEquals(1, companies.size());
        assertEquals(company, companies.get(0));
    }
}
