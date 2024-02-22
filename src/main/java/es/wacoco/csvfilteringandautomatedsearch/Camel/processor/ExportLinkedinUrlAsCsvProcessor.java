package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import es.wacoco.csvfilteringandautomatedsearch.model.InventorUrl;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ExportLinkedinUrlAsCsvProcessor implements Processor {

    private final List<InventorUrl> inventorUrls = new ArrayList<>();
    @Override
    public void process(Exchange exchange) throws Exception {

        //This is just an example
        inventorUrls.add( new InventorUrl( "Example-1", "https://example.com/page1"));
        inventorUrls.add(new InventorUrl( "Example-2", "https://example.com/page2"));
        inventorUrls.add(new InventorUrl( "Example-3", "https://example.com/page3"));

        StringBuilder csvData = new StringBuilder();
        for (InventorUrl inventorUrl : inventorUrls) {

            String inventor = inventorUrl.getInventor();
            String linkedInUrl = inventorUrl.getLinkedInUrl();

            csvData.append(inventor).append(",").append(linkedInUrl).append("\n");
        }

        File directory = new File("data/outbox");
        directory.mkdirs();

        try (PrintWriter writer = new PrintWriter(new FileWriter(directory.getPath() + "/LinkedIUrls.csv"))) {
            writer.write(csvData.toString());
        }
    }
}
