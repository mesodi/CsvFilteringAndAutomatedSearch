package es.wacoco.csvfilteringandautomatedsearch;

import es.wacoco.csvfilteringandautomatedsearch.controller.CompanyController;
import es.wacoco.csvfilteringandautomatedsearch.database.DatabaseService;
import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CompanyControllerTest {

    @Mock
    private DatabaseService databaseService;

    @InjectMocks
    private CompanyController companyController;

    @Test
    void testGetChosenCompanies() {
        // Create a list of companies to be returned by the mock database service
        List<Company> expectedCompanies = new ArrayList<>();
        expectedCompanies.add(new Company("Company 1"));
        expectedCompanies.add(new Company("Company 2"));

        // Mock the behavior of the database service
        when(databaseService.getChosenCompanies()).thenReturn(expectedCompanies);

        // Call the controller method
        List<Company> actualCompanies = companyController.getChosenCompanies();

        // Assert that the returned list matches the expected list
        assertEquals(expectedCompanies.size(), actualCompanies.size());
        assertEquals(expectedCompanies.get(0).getApplicant(), actualCompanies.get(0).getApplicant());
        assertEquals(expectedCompanies.get(1).getApplicant(), actualCompanies.get(1).getApplicant());

    }
}
