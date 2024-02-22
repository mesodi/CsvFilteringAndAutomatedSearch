package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterCsvProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // Get the CSV file from the exchange
        MultipartFile file = exchange.getIn().getBody(MultipartFile.class);

        // Define a list to hold filtered results
        List<Map<String, String>> filteredResults = new ArrayList<>();

        // Read the CSV file and apply filtering logic
        CSVParser csvParser = new CSVParser(new InputStreamReader(file.getInputStream()), CSVFormat.DEFAULT);
        for (CSVRecord csvRecord : csvParser) {
            // Example filtering logic: filter records where the 'status' column value is 'active'
            String status = csvRecord.get("status");
            if ("active".equalsIgnoreCase(status)) {
                // Create a map to hold filtered record data
                Map<String, String> dataMap = new HashMap<>();
                for (String header : csvParser.getHeaderNames()) {
                    dataMap.put(header, csvRecord.get(header));
                }
                // Add the filtered record data to the results list
                filteredResults.add(dataMap);
            }
        }

        // Update the exchange body with the filtered results
        exchange.getMessage().setBody(filteredResults);
    }
}
