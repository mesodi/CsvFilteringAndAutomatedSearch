package es.wacoco.csvfilteringandautomatedsearch.model;

import java.util.ArrayList;
import java.util.List;

public class ChosenCompanies {
    private List<String> companiesList;

 public ChosenCompanies(){
     this.companiesList= new ArrayList<>();
 }
 public void addCompany(String company){
this.companiesList.add(company);

 }
 public List<String> getCompaniesList() {
        return companiesList;
    }

}
