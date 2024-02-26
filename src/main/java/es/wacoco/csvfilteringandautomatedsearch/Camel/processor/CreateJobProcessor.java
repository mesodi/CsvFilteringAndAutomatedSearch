package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import es.wacoco.csvfilteringandautomatedsearch.database.Database;
import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class CreateJobProcessor implements Processor {
    public Database database = new Database();
    DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime now = LocalDateTime.now();

    @Override
    public void process(Exchange exchange) throws Exception {
        List<Company> companies = exchange.getIn().getBody(List.class);
        Job job = new Job(generateUniqueID(), time.format(now), "Processing", companies);
        database.createJob(job);
        exchange.getIn().setBody(job);
    }

    public String generateUniqueID() {
        String staticPart = "QRY";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Random random = new Random();
        int randomPart = 100000 + random.nextInt(900000);
        String generatedID = staticPart + "-" + formatter.format(now) + "-" + randomPart;

        // Check if the generated ID already exists in the database
        while (isIDExistsInDatabase(generatedID)) {
            randomPart = 100000 + random.nextInt(900000);
            generatedID = staticPart + "-" + formatter.format(now) + "-" + randomPart;
        }

        return generatedID;
    }

    private boolean isIDExistsInDatabase(String generatedID) {
        for (Job job : database.getJobDB()) {
            if (job.getJobID().equals(generatedID)) {
                return true; // ID already exists
            }
        }
        return false; // ID is unique
    }
}