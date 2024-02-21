package es.wacoco.csvfilteringandautomatedsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Applicant {
    private String name;
    private List<String> websiteUrls = new ArrayList<>();
    private List<String> emailAddress = new ArrayList<>();

    public void addEmailAddress(String emailAddress) {
        this.emailAddress.add(emailAddress);
    }
    public void addWebsiteUrl(String websiteUrl) {
        this.websiteUrls.add(websiteUrl);
    }
}
