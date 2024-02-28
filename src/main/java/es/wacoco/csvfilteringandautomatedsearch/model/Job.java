package es.wacoco.csvfilteringandautomatedsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Job {
    private String jobID;
    private String dateCreated;
    private Status currentStatus;
    private List<Company> companies;

    public Job() {
    }

    public Job(String jobID, LocalDateTime dateTime, Status currentStatus, List<Company> companies) {
        this.jobID = jobID;
        this.dateCreated = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm"));
        this.currentStatus = currentStatus;
        this.companies = companies;
    }
    public enum Status {
        PROCESSING,
        MANUAL,
        DONE
    }
}
