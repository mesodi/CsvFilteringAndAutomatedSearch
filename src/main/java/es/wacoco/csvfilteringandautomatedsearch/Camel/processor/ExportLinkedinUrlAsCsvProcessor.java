package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import es.wacoco.csvfilteringandautomatedsearch.database.Database;
import es.wacoco.csvfilteringandautomatedsearch.model.InventorUrl;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;

@Slf4j
public class ExportLinkedinUrlAsCsvProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        List<InventorUrl> inventorUrls = Database.getAllInventorUrlsWithJobId();

        if (inventorUrls.isEmpty()) {
            log.warn("No inventor URLs found.");
        } else {
            StringBuilder csvData = new StringBuilder("JobID,Inventor,LinkedInUrl\n");
            for (InventorUrl inventorUrl : inventorUrls) {
                csvData.append(inventorUrl.getJobId()).append(",")
                        .append(inventorUrl.getInventor()).append(",")
                        .append(inventorUrl.getLinkedInUrl()).append("\n");
            }
            exchange.getIn().setBody(csvData.toString());
            log.info("CSV data generated successfully.");
        }
    }
}
