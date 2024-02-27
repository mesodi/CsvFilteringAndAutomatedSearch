package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import es.wacoco.csvfilteringandautomatedsearch.database.Database;
import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.InventorUrl;
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
import java.util.List;


@Slf4j
public class LinkedInUrlFinderProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        @SuppressWarnings("unchecked")
        List<Company> companies = exchange.getIn().getBody(List.class);

        for (Company company : companies) {
            for (Patent patent : company.getPatents()) {

                String[] inventors = patent.getInventors().split(";;");
                for (String inv : inventors) {
                    String trimmedInventor = inv.trim();

                    String url = fetchFirstSearchResultUrl(trimmedInventor);
                    InventorUrl inventorUrl = new InventorUrl(trimmedInventor, url);
                    Database.addInventorUrl(inventorUrl);
                }
            }
        }
        exchange.getIn().setBody(companies);
    }

    private String fetchFirstSearchResultUrl(String inventorName) throws IOException {
        String query = "site:linkedin.com " + inventorName;
        String searchUrl = "https://www.google.com/search?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8);

        Document doc = Jsoup.connect(searchUrl).get();
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