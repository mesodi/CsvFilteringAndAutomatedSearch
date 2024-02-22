package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;

public class CountApplicantProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        MultipartFile file = exchange.getIn().getBody(MultipartFile.class);
        int applicantCount = 0;

        CSVParser csvParser = new CSVParser(new InputStreamReader(file.getInputStream()),
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        for (CSVRecord csvRecord : csvParser) {
            // Assuming 'applicant' is the field representing applicants
            if (csvRecord.isMapped("applicant")) {
                applicantCount++;
            }
        }
        exchange.getMessage().setBody(applicantCount);
    }
}
