package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.Patent;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        MultipartFile file = exchange.getIn().getBody(MultipartFile.class);
        List<Company> companies = processCSV(file);
        exchange.getMessage().setBody(companies);
    }

    private List<Company> processCSV(MultipartFile file) {
        List<Company> evaluatedCompanies = new ArrayList<>();
        try {
            CSVParser csvParser = new CSVParser(new InputStreamReader(file.getInputStream()), CSVFormat.DEFAULT.withFirstRecordAsHeader()
                    .withIgnoreHeaderCase().withTrim());
            for (CSVRecord csvRecord : csvParser) {
                String applicant = csvRecord.get("applicant");
                String appearances = csvRecord.get("appearances");

                // Initialize a list to hold patents for the company
                List<Patent> patents = new ArrayList<>();
                for (int i = 0; i < Integer.parseInt(appearances); i++) {
                    // Create Patent objects and add them to the patents list
                    Patent patent = new Patent();
                    patent.setApplicationNumber(csvRecord.get("patent_" + i + "_applicationNumber"));
                    patent.setTitle(csvRecord.get("patent_" + i + "_title"));
                    // Add other patent properties as needed
                    patents.add(patent);
                }

                // Create a Company object and add it to the list
                Company company = new Company(applicant, appearances, patents);
                evaluatedCompanies.add(company);
            }
        } catch (Exception e) {
            // Handle exceptions
            throw new RuntimeException("Error processing CSV file: " + e.getMessage());
        }
        return evaluatedCompanies;
    }
}
