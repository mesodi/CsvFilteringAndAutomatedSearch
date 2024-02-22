package es.wacoco.csvfilteringandautomatedsearch.model;

public class Patent {
    private String inventors;
    private String applicationDate;
    private String applicationNumber;
    private String title;
    private String abstractText;

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}