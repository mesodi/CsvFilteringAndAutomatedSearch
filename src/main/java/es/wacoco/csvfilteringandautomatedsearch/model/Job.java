package es.wacoco.csvfilteringandautomatedsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Job {
    String jobID;
    String dateCreated;
    String status;
    List<Company> companies;


}
