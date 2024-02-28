package es.wacoco.csvfilteringandautomatedsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Company {
    private String applicant;
    private int appearances;
    private int inventorAppearances;
    private List<Patent> patents = new ArrayList<>();
    public Company(String applicant) {
        this.applicant = applicant;
        this.appearances = 0;
        this.inventorAppearances = 0;
    }
    public void addPatent(Patent patent) {
        appearances++;
        patents.add(patent);

        String[] inventors = patent.getInventors().split(";;");
        inventorAppearances += inventors.length;
    }
}
