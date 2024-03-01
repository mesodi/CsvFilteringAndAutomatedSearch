package es.wacoco.csvfilteringandautomatedsearch.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class InventorUrl {
    private String inventor;
    private String linkedInUrl;
    private String jobId;

    public InventorUrl(String inventor, String linkedInUrl, String jobId) {
        this.inventor = inventor;
        this.linkedInUrl = linkedInUrl;
        this.jobId = jobId;
    }

    public InventorUrl(String inventor, String linkedInUrl) {
        this.inventor = inventor;
        this.linkedInUrl = linkedInUrl;
    }
}
