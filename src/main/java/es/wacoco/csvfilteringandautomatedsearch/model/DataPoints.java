package es.wacoco.csvfilteringandautomatedsearch.model;

public class DataPoints {

    private String inventors;
    private String applicationDate;
    private String applicationNumber;
    private String applicant;
    private String title;
    private String abstractText;
    private String companies;


    public DataPoints(String  inventors, String applicationDate, String applicationNumber,
                      String applicant, String title, String abstractText,String
                       companies){

        this.inventors = inventors;
        this.applicationDate = applicationDate;
        this.applicationNumber = applicationNumber;
        this.applicant = applicant;
        this.title = title;
        this.abstractText = abstractText;
        this.companies= companies;

    }
    public String getInventors() {
        return inventors;
    }
    public void setInventors(String inventors) {
        this.inventors = inventors;
    }
    public String getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }
    public String getApplicationNumber() {
        return applicationNumber;
    }
    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }
    public String getApplicant() {
        return applicant;
    }
    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractText() {
        return abstractText;
    }
    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }
    public String getCompanies() {
        return companies;
    }

    public void setCompanies(String companies) {
        this.companies = companies;
    }
}
