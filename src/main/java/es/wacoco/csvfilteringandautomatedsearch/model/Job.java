package es.wacoco.csvfilteringandautomatedsearch.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    private String jobID;
    private LocalDateTime dateCreated;
    private JobStatus currentStatus;
    private List<Company> companies = new ArrayList<>();

    @JsonGetter("dateCreated")
    public String getDateCreatedAsString(){
        return dateCreated.format(DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm"));
    }

}
