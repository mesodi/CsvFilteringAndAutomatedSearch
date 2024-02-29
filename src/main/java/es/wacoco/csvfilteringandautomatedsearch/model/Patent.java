package es.wacoco.csvfilteringandautomatedsearch.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Patent {
    private String inventors;
    private String applicationDate;
    private String applicationNumber;
    private String title;
    private String abstractText;
    private String linkedInUrls;
    public Patent() {

    }
}