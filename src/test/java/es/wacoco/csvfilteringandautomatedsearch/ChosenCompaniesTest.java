package es.wacoco.csvfilteringandautomatedsearch;

import es.wacoco.csvfilteringandautomatedsearch.database.ChosenCompanies;
import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChosenCompaniesTest {

    @Test
    void testAddAndRetrieveCompany() {
        // Create a company
        Company company = new Company("Test Company");

        // Add the company to the chosen companies list
        ChosenCompanies.addCompany(company);

        // Retrieve the list of chosen companies
        List<Company> companies = ChosenCompanies.getChosenCompanies();

        // Assert that the list contains the added company
        assertEquals(1, companies.size());
        assertEquals(company, companies.get(0));
    }
}
