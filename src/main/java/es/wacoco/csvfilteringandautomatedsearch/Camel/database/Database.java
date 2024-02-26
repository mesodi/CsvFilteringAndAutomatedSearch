package es.wacoco.csvfilteringandautomatedsearch.Camel.database;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.InventorUrl;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


public class Database {

    ArrayList<Job> jobDB = new ArrayList<>();

    public void createJob(Job job) {
        jobDB.add(job);
    }
}
