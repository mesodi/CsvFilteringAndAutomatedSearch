package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import es.wacoco.csvfilteringandautomatedsearch.database.Database;
import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.Patent;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class FilterCsvProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        MultipartFile file = exchange.getIn().getBody(MultipartFile.class);
        Map<String, Company> companies = new HashMap<>();

        try (CSVParser parser = new CSVParser(new InputStreamReader(file.getInputStream()), CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord record : parser) {
                String applicant = record.get("Applicants");
                Company company = companies.computeIfAbsent(applicant, k -> new Company(applicant));

                Patent patent = new Patent();
                patent.setInventors(record.get("Inventors"));
                patent.setApplicationDate(record.get("Application Date"));
                patent.setApplicationNumber(record.get("Application Number"));
                patent.setTitle(record.get("Title"));
                patent.setAbstractText(record.get("Abstract"));

                company.addPatent(patent);
            }
        }
        List<Company> sortedCompanies = companies.values().stream()
                .sorted((c1, c2) -> Integer.compare(c2.getAppearances(), c1.getAppearances()))
                .collect(Collectors.toList());

        Database.companies.clear();
        Database.companies.addAll(sortedCompanies);

        log.info("Processed and stored {} companies.", companies.size());
        exchange.getIn().setBody(sortedCompanies);
    }
}