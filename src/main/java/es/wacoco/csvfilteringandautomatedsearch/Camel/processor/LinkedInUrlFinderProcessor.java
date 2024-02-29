package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import es.wacoco.csvfilteringandautomatedsearch.database.Database;
import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.InventorUrl;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import es.wacoco.csvfilteringandautomatedsearch.model.Patent;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class LinkedInUrlFinderProcessor implements Processor {


    public void process(Exchange exchange) throws Exception {
        Job job = exchange.getIn().getBody(Job.class);
        for (Company company : job.getCompanies()) {
            for (Patent patent : company.getPatents()) {
                List<String> linkedInUrls = new ArrayList<>();
                String[] inventors = patent.getInventors().split(";;");
                for (String inventor : inventors) {
                    String trimmedInventor = inventor.trim();
                    String url = fetchFirstSearchResultUrl(trimmedInventor);
                    InventorUrl inventorUrl = new InventorUrl(trimmedInventor, url, job.getJobID());
                    Database.addInventorUrl(job.getJobID(), inventorUrl);
                    linkedInUrls.add(url);
                }

                patent.setLinkedInUrls(String.valueOf((linkedInUrls)));
                log.info("LinkedIn URLs: {}", linkedInUrls);
            }
        }
        exchange.getIn().setBody(job);
    }

    private String fetchFirstSearchResultUrl(String inventorName) {
        String query = "site:linkedin.com " + inventorName;
        String searchUrl = "https://www.google.com/search?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8);

        Document doc = null;
        try {
            doc = Jsoup.connect(searchUrl).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements links = doc.select("a[href]");

        for (Element link : links) {
            String url = link.absUrl("href");
            if (url.contains("linkedin.com") && !url.contains("/search?") && !url.contains("google.com")) {
                log.info("LinkedIn URL found for {}: {}", inventorName, url);
                return url;
            }
        }
        log.warn("No LinkedIn URL found for {}", inventorName);
        return "No LinkedIn URL found";
    }
}