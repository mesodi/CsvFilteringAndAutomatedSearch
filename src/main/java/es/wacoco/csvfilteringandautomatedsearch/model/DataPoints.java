package es.wacoco.csvfilteringandautomatedsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DataPoints {

    private String inventors;
    private String applicationDate;
    private String applicationNumber;
    private String applicant;
    private String title;
    private String abstractText;
}
