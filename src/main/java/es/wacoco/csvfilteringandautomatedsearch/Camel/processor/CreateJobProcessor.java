package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;

public class CreateJobProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        List<Company> companies = exchange.getIn().getBody(List.class);

    }

}
