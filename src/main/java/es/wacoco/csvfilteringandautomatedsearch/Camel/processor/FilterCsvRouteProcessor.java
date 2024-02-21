package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.web.multipart.MultipartFile;

public class FilterCsvRouteProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        MultipartFile file = exchange.getIn().getBody(MultipartFile.class);
    }
}
