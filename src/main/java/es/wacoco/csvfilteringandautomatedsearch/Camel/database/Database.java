package es.wacoco.csvfilteringandautomatedsearch.Camel.database;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Database {

    ArrayList<Job> jobDB = new ArrayList<>();

    public void createJob(String jobID, String dateCreated, String status, List<Company> companies) {
        jobDB.add(new Job(jobID, dateCreated, status, companies));
    }
}
