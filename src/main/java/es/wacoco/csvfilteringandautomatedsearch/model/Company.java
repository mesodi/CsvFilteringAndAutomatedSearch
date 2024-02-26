package es.wacoco.csvfilteringandautomatedsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Company {
    String applicant;
    String websiteUrl;
    List<Patent> patents;
    List<InventorUrl> inventorUrls;

}
