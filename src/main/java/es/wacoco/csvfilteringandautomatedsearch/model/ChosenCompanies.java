package es.wacoco.csvfilteringandautomatedsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ChosenCompanies {
    private List<Company> companyList;
}
